package uz.pdp.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.SpaceDTO;
import uz.pdp.appclickup.security.CurrentUser;
import uz.pdp.appclickup.service.SpaceService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/space")
public class SpaceController {

    @Autowired
    SpaceService spaceService;


    @PostMapping
    public HttpEntity<?> addWorkspace(@Valid @RequestBody SpaceDTO spaceDTO, @CurrentUser User user) {
        ApiResponse apiResponse = spaceService.addSpace(spaceDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{spaceId}")
    public HttpEntity<?> deleteWorkspace(@PathVariable Long spaceId, @CurrentUser User user) {
        ApiResponse apiResponse = spaceService.deleteSpace(spaceId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/viewsBySpaceId")
    public HttpEntity<?> getViewsBySpaceId(@RequestParam Long sId, @CurrentUser User user) {
        ApiResponse apiResponse = spaceService.getViewsBySpaceId(sId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
