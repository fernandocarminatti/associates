package com.associates.associates.service;

import com.associates.associates.exceptions.CpfException;
import com.associates.associates.exceptions.IdNotFoundException;
import com.associates.associates.model.UserModel;
import com.associates.associates.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public UserModel createUser(UserModel user) {
        System.out.println("inside service --> " + user);
        if(userRepository.existsByUserCpf(user.getUserCpf())) {
            throw new CpfException("CPF Already Exists");
        }
        return userRepository.save(user);
    }

    @Transactional
    public UserModel getUserById(Integer id) {
        return userRepository.findById(Long.valueOf(id)).orElseThrow(() -> new IdNotFoundException("ID " + id + " not found"));
    }

    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.deleteById(Long.valueOf(id));
    }
}
