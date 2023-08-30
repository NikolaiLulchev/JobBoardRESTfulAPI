package org.softuni.JobBoardRESTfulAPI.service;

import org.modelmapper.ModelMapper;
import org.softuni.JobBoardRESTfulAPI.model.dto.UserLoginDTO;
import org.softuni.JobBoardRESTfulAPI.model.dto.UserRegisterDTO;
import org.softuni.JobBoardRESTfulAPI.model.dto.UserUpdateDTO;
import org.softuni.JobBoardRESTfulAPI.model.entity.TechStackEntity;
import org.softuni.JobBoardRESTfulAPI.model.entity.UserEntity;
import org.softuni.JobBoardRESTfulAPI.model.entity.UserRoleEntity;
import org.softuni.JobBoardRESTfulAPI.model.enums.GenderEnum;
import org.softuni.JobBoardRESTfulAPI.model.enums.LevelEnum;
import org.softuni.JobBoardRESTfulAPI.model.enums.TechStackEnum;
import org.softuni.JobBoardRESTfulAPI.model.enums.UserRoleEnum;
import org.softuni.JobBoardRESTfulAPI.repository.TechStackRepository;
import org.softuni.JobBoardRESTfulAPI.repository.UserRepository;
import org.softuni.JobBoardRESTfulAPI.repository.UserRoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final TechStackRepository techStackRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository,
                       TechStackRepository techStackRepository, PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.techStackRepository = techStackRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
    }


    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {

        UserRoleEntity userRole = userRoleRepository.findFirstByRole(UserRoleEnum.valueOf(userRegisterDTO.getRole()));
        UserEntity newUser = modelMapper.map(userRegisterDTO, UserEntity.class);
        newUser.setRole(Set.of(userRole));
        //TODO handle real birthDate
        newUser.setDateOfBirth(LocalDate.now().minusYears(19));
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(newUser);
        login(newUser);
    }


    private void login(UserEntity userEntity) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userEntity.getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    public void login(UserLoginDTO userModel) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userModel.getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
        System.out.println("user " + userDetails.getUsername() + " logged in");
    }

    public UserEntity getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + "was not found"));
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<UserEntity> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public void updateUser(UserEntity user, UserUpdateDTO userUpdateDTO) {

        Set<UserRoleEntity> userRoleEntitySet = getUserRoleEntitySet(user, userUpdateDTO);
        List<TechStackEntity> userTechStackList = getTechStackEntityList(userUpdateDTO.getTechStack());

        user.setFirstName(userUpdateDTO.getFirstName())
                .setLastName(userUpdateDTO.getLastName())
                .setEmail(userUpdateDTO.getEmail())
                .setAge(userUpdateDTO.getAge())
                .setGender(userUpdateDTO.getGender())
                .setRole(userRoleEntitySet)
                .setLevel(LevelEnum.valueOf(userUpdateDTO.getLevel()))
                .setTechStack(userTechStackList);
        userRepository.save(user);
    }

    private Set<UserRoleEntity> getUserRoleEntitySet(UserEntity user, UserUpdateDTO userUpdateDTO) {
        Set<UserRoleEntity> userRoleEntitySet = new HashSet<>();
        if (userUpdateDTO.getRole() == null) {
            userRoleEntitySet = user.getRole();
        } else {
            for (String role : userUpdateDTO.getRole()) {
                UserRoleEntity userRoleEntity = userRoleRepository.findFirstByRole(UserRoleEnum.valueOf(role));
                userRoleEntitySet.add(userRoleEntity.setRole(UserRoleEnum.valueOf(role)));
            }
        }
        return userRoleEntitySet;
    }

    public List<TechStackEntity> getTechStackEntityList(List<String> techStackList) {
        List<TechStackEntity> userTechStackList = new ArrayList<>();
        for (String stack : techStackList) {
            TechStackEntity techStackEntity = techStackRepository.findByTechStack(TechStackEnum.valueOf(stack));
            userTechStackList.add(techStackEntity.setTechStack(TechStackEnum.valueOf(stack)));
        }
        return userTechStackList;
    }

    public void initializeAdminUser() {
        Optional<UserEntity> optAdmin = userRepository.findByUsername("admin");
        if (optAdmin.isPresent()) {
            return;
        }
        UserEntity admin = new UserEntity();
//        UserRoleEntity adminRole = userRoleRepository.findFirstByRole(UserRoleEnum.valueOf("ADMIN"));
        List <UserRoleEntity> allRoles = userRoleRepository.findAll();
        admin.setUsername("admin")
                .setPassword(passwordEncoder.encode("1234"))
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setEmail("admin@jobboard.com")
                .setDateOfBirth(LocalDate.parse("1980-12-12"))
                .setGender(GenderEnum.MALE)
                .setRole(Set.copyOf(allRoles));
        userRepository.save(admin);
    }

    public boolean verifyPassword(UserEntity user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}