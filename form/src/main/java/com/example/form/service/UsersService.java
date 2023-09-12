package com.example.form.service;

import com.example.form.model.UsersModel;
import com.example.form.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersModel registerUser(String login, String password, String email) {

        if(userRepository.findByLogin(login).isPresent()){
            System.out.println("Duplicate Login");
            return null;
        }
        UsersModel usersModel = new UsersModel();
        usersModel.setLogin(login);
        usersModel.setPassword(password);
        usersModel.setEmail(email);
        return userRepository.save(usersModel);
    }


    public  UsersModel authenticate(String login, String password){
        return  userRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
