package com.data.session13.service.impl;


import com.data.session13.model.dto.request.UserLogin;
import com.data.session13.model.dto.request.UserRegister;
import com.data.session13.model.dto.response.JWTResponse;
import com.data.session13.model.entity.Role;
import com.data.session13.model.entity.User;
import com.data.session13.repository.RoleRepository;
import com.data.session13.repository.UserRepository;
import com.data.session13.security.jwt.JWTProvider;
import com.data.session13.security.principal.CustomUserDetails;
import com.data.session13.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User registerUser(UserRegister userRegister) {
        User user = User.builder()
                .username(userRegister.getUsername())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .fullName(userRegister.getFullName())
                .address(userRegister.getAddress())
                .email(userRegister.getEmail())
                .phone(userRegister.getPhone())
                .enabled(true)
                .roles(mapRoleStringToRole(userRegister.getRoles()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public JWTResponse login(UserLogin userLogin) {
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(),userLogin.getPassword()));
        }catch(AuthenticationException e){
            log.error("Sai username hoac password!");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userDetails.getUsername());

        return JWTResponse.builder()
                .username(userDetails.getUsername())
                .fullName(userDetails.getFullName())
                .enabled(userDetails.isEnabled())
                .email(userDetails.getEmail())
                .phone(userDetails.getPhone())
                .authorities(userDetails.getAuthorities())
                .token(token)
                .build();
    }

    private List<Role> mapRoleStringToRole(List<String> roles) {
        List<Role> roleList = new ArrayList<>();

        if(roles!=null && !roles.isEmpty()){
            roles.forEach(role->{
                switch (role){
                    case "ROLE_ADMIN":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()-> new NoSuchElementException("Khong ton tai role_admin")));
                        break;
                    case "ROLE_USER":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
                        break;
                    case "ROLE_MODERATOR":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()-> new NoSuchElementException("Khong ton tai role_moderator")));
                        break;
                    default:
                        roleList.add(roleRepository.findByRoleName("ROLE_USER").orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
                }
            });
        }else{
            roleList.add(roleRepository.findByRoleName("ROLE_USER").orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
        }
        return roleList;
    }
}