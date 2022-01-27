package uz.pdp.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.appclickup.entity.*;
import uz.pdp.appclickup.entity.enums.WorkspaceRoleName;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.StatusDTO;
import uz.pdp.appclickup.repository.*;

import java.util.Optional;

import static uz.pdp.appclickup.utils.CommonUtils.isExistsAuthority;


@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;
    @Autowired
    SpaceRepository spaceRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ApiResponse addOrEditStatus(StatusDTO statusDTO, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(statusDTO.getWorkspaceId(), user.getId()).get();
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }
        Status status = null;
        Space space = spaceRepository.findById(statusDTO.getSpaceId()).orElseThrow(() -> new ResourceNotFoundException("space id"));
        Project project = projectRepository.findById(statusDTO.getProjectId()).orElseThrow(() -> new ResourceNotFoundException("project id"));
        Category category = categoryRepository.findById(statusDTO.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("category id"));

        if (statusDTO.getId() == null) {
            status = new Status(
                    statusDTO.getName(),
                    space,
                    project,
                    category,
                    statusDTO.getColor(),
                    statusDTO.getStatusType());
        } else {
            Optional<Status> optionalStatus = statusRepository.findById(statusDTO.getId());
            if (optionalStatus.isPresent()) {
                status = optionalStatus.get();
                status.setName(statusDTO.getName());
                status.setSpace(space);
                status.setProject(project);
                status.setCategory(category);
                status.setColor(statusDTO.getColor());
                status.setType(statusDTO.getStatusType());
            } else {
                return new ApiResponse("Status id not found", false);
            }
        }
        statusRepository.save(status);
        return new ApiResponse("Status saved", true);
    }

    @Override
    public ApiResponse deleteStatus(Long statusId, User user) {
        try {
            statusRepository.deleteById(statusId);
            return new ApiResponse("Status deleted", true);
        } catch (Exception exception) {
            return new ApiResponse("Error", false);
        }
    }
}
