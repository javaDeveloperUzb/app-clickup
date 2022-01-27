package uz.pdp.appclickup.service;


import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.CategoryDTO;
import uz.pdp.appclickup.payload.CategoryUserDTO;

import java.util.UUID;

public interface CategoryService {
    ApiResponse addOrEditCategory(CategoryDTO categoryDTO, User user);

    ApiResponse deleteCategory(Long cId, User user);

    ApiResponse addCategoryUser(Long wId, CategoryUserDTO categoryUserDTO, User user);

    ApiResponse deleteCategoryUser(Long categoryUserId, User user);

    ApiResponse getAllCategoriesByUserId(UUID uId, User user);
}
