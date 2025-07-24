package com.data.session13.service;


import com.data.session13.model.dto.request.UserLogin;
import com.data.session13.model.dto.request.UserRegister;
import com.data.session13.model.dto.response.JWTResponse;
import com.data.session13.model.entity.User;

public interface UserService {
    User registerUser(UserRegister userRegister);

    JWTResponse login(UserLogin userLogin);
}
