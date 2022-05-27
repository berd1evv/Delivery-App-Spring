package com.example.Final.service;

import com.example.Final.model.UserModel;
import com.example.Final.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserModel registerUser(String login, String password, String email){
        if (login == null || password == null) {
            return  null;
        } else {
            if(usersRepository.findFirstByLogin(login).isPresent()) {
                System.out.println("Duplicate login");
                return null;
            }
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setEmail(email);
            return usersRepository.save(userModel);
        }
    }

    public UserModel authenticate(String login, String password) {
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
