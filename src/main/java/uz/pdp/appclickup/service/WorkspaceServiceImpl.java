package uz.pdp.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.appclickup.entity.*;
import uz.pdp.appclickup.entity.enums.AddType;
import uz.pdp.appclickup.entity.enums.WorkspacePermissionName;
import uz.pdp.appclickup.entity.enums.WorkspaceRoleName;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.MemberDto;
import uz.pdp.appclickup.payload.WorkspaceDto;
import uz.pdp.appclickup.payload.WorkspaceRoleDto;
import uz.pdp.appclickup.repository.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    WorkspaceUserRepository workspaceUserRepository;

    @Autowired
    WorkspaceRoleRepository workspaceRoleRepository;

    @Autowired
    WorkspacePermissionRepository workspacePermissionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse addWorkspace(WorkspaceDto workspaceDto, User user) {
//        kirgan userni bilish 1-usul
//        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //WORKSPACE OCHDIK
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDto.getName()))
            return new ApiResponse("Sizda bunday nomli ishxona mavjud", false);
        Workspace workspace = new Workspace(
                workspaceDto.getName(),
                workspaceDto.getColor(),
                user,
                workspaceDto.getAvatarId() == null ? null : attachmentRepository.findById(workspaceDto.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("attachment"))
        );
        workspaceRepository.save(workspace);

        //WORKSPACE ROLE OCHDIK
        WorkspaceRole ownerRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_OWNER.name(),
                null
        ));

        WorkspaceRole adminRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_ADMIN.name(), null));
        WorkspaceRole memberRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_MEMBER.name(), null));
        WorkspaceRole guestRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_GUEST.name(), null));


        //OWNERGA HUQUQLARNI BERYAPMIZ
        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissions = new ArrayList<>();
        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames) {
            WorkspacePermission workspacePermission = new WorkspacePermission(
                    ownerRole,
                    workspacePermissionName);
            workspacePermissions.add(workspacePermission);
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissions.add(new WorkspacePermission(
                        adminRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                workspacePermissions.add(new WorkspacePermission(
                        memberRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissions.add(new WorkspacePermission(
                        guestRole,
                        workspacePermissionName));
            }
        }
        workspacePermissionRepository.saveAll(workspacePermissions);

        //WORKSPACE USER OCHDIK
        workspaceUserRepository.save(new WorkspaceUser(
                workspace,
                user,
                ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        ));
        return new ApiResponse("Ishxona saqlandi", true);
    }

    @Override
    public ApiResponse editWorkspace(WorkspaceDto workspaceDto) {
        Workspace workspace = workspaceRepository.findById(workspaceDto.getId()).orElseThrow(() -> new ResourceNotFoundException("id"));
        workspace.setColor(workspaceDto.getColor());
        workspace.setName(workspaceDto.getName());
//        workspace.setAvatar(attachmentRepository.findById(workspaceDto.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("Avatar id")));
        workspaceRepository.save(workspace);
        return new ApiResponse("Workspace edited", true);
    }

    @Override
    public ApiResponse changeOwnerWorkspace(Long id, UUID ownerId, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, user.getId()).orElseThrow(() -> new ResourceNotFoundException("id"));
        String roleName = workspaceUser.getWorkspaceRole().getName();
        User newOwner = userRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("id"));
        workspaceUser.setUser(newOwner);
        workspaceUserRepository.save(workspaceUser);

        Workspace editingWorkspace = workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id"));
        editingWorkspace.setOwner(newOwner);
        workspaceRepository.save(editingWorkspace);
        return new ApiResponse("Workspace owner edited", true);
    }

    @Override
    public ApiResponse deleteWorkspace(Long id) {
        try {
            workspaceRepository.deleteById(id);
            return new ApiResponse("Ishxona o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Ishxona topilmadi", false);
        }
    }

    @Override
    public ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDto memberDto) {
        if (memberDto.getAddType().equals(AddType.ADD)) {
            WorkspaceUser workspaceUser = new WorkspaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id")),
                    userRepository.findById(memberDto.getId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    workspaceRoleRepository.findById(memberDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            );
            workspaceUserRepository.save(workspaceUser);
            //TODO EMAILGA INVITE XABAR YUBORISH
        } else if (memberDto.getAddType().equals(AddType.EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, memberDto.getId()).orElseGet(WorkspaceUser::new);
            workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(memberDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")));
            workspaceUserRepository.save(workspaceUser);
        } else if (memberDto.getAddType().equals(AddType.REMOVE)) {
            workspaceUserRepository.deleteByWorkspaceIdAndUserId(id, memberDto.getId());
        }
        return new ApiResponse("Muvaffaqiyatli", true);
    }

    @Override
    public ApiResponse joinToWorkspace(Long id, User user) {
        Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, user.getId());
        if (optionalWorkspaceUser.isPresent()) {
            WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
            workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
            workspaceUserRepository.save(workspaceUser);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Error", false);
    }

    @Override
    public List<MemberDto> getMemberAndGuest(Long id) {
        List<WorkspaceUser> workspaceUsers = workspaceUserRepository.findAllByWorkspaceId(id);
        //1-usul for orqali
//        List<MemberDto> members = new ArrayList<>();
//        for (WorkspaceUser workspaceUser : workspaceUsers) {
//            MemberDto memberDto = mapWorkspaceUserToMemberDto(workspaceUser);
//            members.add(memberDto);
//        }
//        return members;

        //2-usul stream orqali
        List<MemberDto> memberDtoList = workspaceUsers.stream().map(workspaceUser -> mapWorkspaceUserToMemberDto(workspaceUser)).collect(Collectors.toList());
        return memberDtoList;
    }

    @Override
    public List<WorkspaceDto> getMyWorkspaces(User user) {
        List<WorkspaceUser> workspaceUsers = workspaceUserRepository.findAllByUserId(user.getId());
        List<WorkspaceDto> collect = workspaceUsers.stream().map(workspaceUser -> mapWorkspaceUserToWorkspaceDto(workspaceUser.getWorkspace())).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ApiResponse addOrRemovePermissionToRole(WorkspaceRoleDto workspaceRoleDto) {
        WorkspaceRole workspaceRole = workspaceRoleRepository.findById(workspaceRoleDto.getId()).orElseThrow(() -> new ResourceNotFoundException("workspaceRole"));
        Optional<WorkspacePermission> optionalWorkspacePermission = workspacePermissionRepository.findByWorkspaceRoleIdAndPermission(workspaceRole.getId(), workspaceRoleDto.getPermissionName());
        if (workspaceRoleDto.getAddType().equals(AddType.ADD)) {
            if (optionalWorkspacePermission.isPresent())
                return new ApiResponse("Allaqachon qo'shilgan", false);
            WorkspacePermission workspacePermission = new WorkspacePermission(workspaceRole, workspaceRoleDto.getPermissionName());
            workspacePermissionRepository.save(workspacePermission);
            return new ApiResponse("Muvaffaqiyatli qo'shildi", true);
        } else if (workspaceRoleDto.getAddType().equals(AddType.REMOVE)) {
            if (optionalWorkspacePermission.isPresent()) {
                workspacePermissionRepository.delete(optionalWorkspacePermission.get());
                return new ApiResponse("Muvaffaqiyatli o'chirildi", true);
            }
            return new ApiResponse("Bunday obyekt yo'q", false);
        }
        return new ApiResponse("Bunday buyruq yo'q", false);
    }

    @Override
    public ApiResponse addRole(Long workspaceId, WorkspaceRoleDto workspaceRoleDto, User user) {
        if (workspaceRoleRepository.existsByWorkspaceIdAndName(workspaceId, workspaceRoleDto.getName()))
            return new ApiResponse("error", false);
        WorkspaceRole workspaceRole = new WorkspaceRole(
                workspaceRepository.findById(workspaceId).orElseThrow(() -> new ResourceNotFoundException("id")),
                workspaceRoleDto.getName(),
                workspaceRoleDto.getExtendsRole()
        );
        workspaceRoleRepository.save(workspaceRole);
        //1-usul
//        List<WorkspacePermission> workspacePermissions = workspacePermissionRepository.findAllByWorkspaceRole_NameAndWorkspaceRole_WorkspaceId(workspaceRoleDto.getExtendsRole().name(), workspaceId);

        //2-usul
        List<WorkspacePermission> workspacePermissions = workspacePermissionRepository.findAll();
        List<WorkspacePermission> newWorkspacePermissions = new ArrayList<>();
        for (WorkspacePermission workspacePermission : workspacePermissions) {
            WorkspacePermission newWorkspacePermission = new WorkspacePermission(
                    workspaceRole,
                    workspacePermission.getPermission());
            newWorkspacePermissions.add(newWorkspacePermission);
        }
        workspacePermissionRepository.saveAll(newWorkspacePermissions);
        return new ApiResponse("accepted", true);
    }

    //MY METHOD
    public WorkspaceDto mapWorkspaceUserToWorkspaceDto(Workspace workspace) {
        WorkspaceDto workspaceDto = new WorkspaceDto();
        workspaceDto.setId(workspace.getId());
        workspaceDto.setName(workspace.getName());
        workspaceDto.setColor(workspace.getColor());
        workspaceDto.setInitialLetter(workspace.getInitialLetter());
        workspaceDto.setAvatarId(workspace.getAvatar() == null ? null : workspace.getAvatar().getId());
        return workspaceDto;
    }

    public MemberDto mapWorkspaceUserToMemberDto(WorkspaceUser workspaceUser) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(workspaceUser.getUser().getId());
        memberDto.setFullName(workspaceUser.getUser().getFullName());
        memberDto.setEmail(workspaceUser.getUser().getEmail());
        memberDto.setRoleName(workspaceUser.getWorkspaceRole().getName());
        memberDto.setLastActive(workspaceUser.getUser().getLastActiveTime());
        return memberDto;
    }


}

