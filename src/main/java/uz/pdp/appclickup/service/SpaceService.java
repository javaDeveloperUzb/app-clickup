package uz.pdp.appclickup.service;


import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.SpaceDTO;

public interface SpaceService {

    ApiResponse addSpace(SpaceDTO spaceDTO, User user);

    ApiResponse deleteSpace(Long spaceId, User user);

    ApiResponse getViewsBySpaceId(Long sId, User user);


}
