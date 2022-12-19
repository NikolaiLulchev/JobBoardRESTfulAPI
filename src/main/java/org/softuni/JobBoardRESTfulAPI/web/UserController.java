package org.softuni.JobBoardRESTfulAPI.web;

import org.modelmapper.ModelMapper;
import org.softuni.JobBoardRESTfulAPI.model.dto.UserLoginDTO;
import org.softuni.JobBoardRESTfulAPI.model.dto.UserRegisterDTO;
import org.softuni.JobBoardRESTfulAPI.model.entity.UserEntity;
import org.softuni.JobBoardRESTfulAPI.model.user.JobboardUserDetails;
import org.softuni.JobBoardRESTfulAPI.model.view.UserViewModel;
import org.softuni.JobBoardRESTfulAPI.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(
        origins = "http://localhost:4200",
        maxAge = 3600,
        allowCredentials = "true"
)
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserViewModel>> getAllUsers() {
        // Retrieve the list of users from your database or other data source
        List<UserEntity> users = userService.getAllUsers();

        // Convert the list of entity objects into a list of DTO objects
        List<UserViewModel> userDtos = users.stream()
                .map(user -> modelMapper.map(user, UserViewModel.class))
                .collect(Collectors.toList());

        // Return the list of DTOs as the response of your API
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("profile/{userId}")
    public ResponseEntity<UserViewModel> getUserById(@PathVariable Long userId) {
        UserEntity user = userService.getUserById(userId);
        UserViewModel userDTO = modelMapper.map(user, UserViewModel.class);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDTO userModel,
                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        this.userService.registerAndLogin(userModel);

        return ResponseEntity.ok(userModel);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDTO userModel) {

        this.userService.login(userModel);

        return ResponseEntity.ok(userModel);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Principal principal){
        UserEntity user = userService.getUser(principal.getName());
        UserViewModel userDTO = modelMapper.map(user, UserViewModel.class);
        return ResponseEntity.ok(userDTO);
    }
}
