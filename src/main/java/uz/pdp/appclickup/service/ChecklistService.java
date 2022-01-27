package uz.pdp.appclickup.service;


import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.CheckListDTO;
import uz.pdp.appclickup.payload.CheckListItemDTO;

public interface ChecklistService {
    ApiResponse addOrEditChecklist(CheckListDTO checkListDTO, Long wId, User user);
    ApiResponse deleteChecklist(Long chId, User user);
    ApiResponse addOrEditChecklistItem(CheckListItemDTO checkListItemDTO, Long wId, User user);
    ApiResponse deleteChecklistItem(Long chId, User user);


}
