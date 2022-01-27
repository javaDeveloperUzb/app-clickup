package uz.pdp.appclickup.service;


import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.ProjectDTO;
import uz.pdp.appclickup.payload.ProjectUserDTO;

public interface ProjectService {

    ApiResponse addProject(ProjectDTO projectDTO, User user);

    ApiResponse editProject(Long projectId, ProjectDTO projectDTO, User user);

    ApiResponse deleteProject(Long projectId, User user);

    ApiResponse addProjectUser(ProjectUserDTO projectUserDTO, User user);

    ApiResponse editProjectUser(Long projectUserId, ProjectUserDTO projectUserDTO, User user);

    ApiResponse deleteProjectUser(Long projectUserId, User user);


}
