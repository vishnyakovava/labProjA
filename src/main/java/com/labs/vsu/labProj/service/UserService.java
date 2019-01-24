package com.labs.vsu.labProj.service;

import com.labs.vsu.labProj.entity.User;
import com.labs.vsu.labProj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public void saveAll(List<User> users){
        userRepository.saveAll(users);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByUserId(long id){
        return userRepository.findByUserId(id);
    }

    public List<User> findByUserFirstName(String userFirstName){
        return userRepository.findByUserFirstName(userFirstName);
    }

    public List<User> findByUserLastName(String userLastName){
        return userRepository.findByUserLastName(userLastName);
    }

    public User findByUserEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail);
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }

    public void deleteByUserEmail(String userEmail){
        userRepository.deleteByUserEmail(userEmail);
    }

}
