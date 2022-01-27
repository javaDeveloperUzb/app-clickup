package uz.pdp.appclickup.service;


import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.StatusDTO;

public interface StatusService {
    ApiResponse addOrEditStatus(StatusDTO statusDTO, User user);
    ApiResponse deleteStatus(Long statusId, User user);

}
