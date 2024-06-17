package com.associates.associates.service;

import com.associates.associates.exceptions.CpfException;
import com.associates.associates.exceptions.IdNotFoundException;
import com.associates.associates.exceptions.PropertyException;
import com.associates.associates.model.User;
import com.associates.associates.repository.UserRepository;
import com.associates.associates.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User createUser(User user) {

        if( !Utils.checkExistenceOfProperty(user.getProperties() )){
            throw new PropertyException("Bad request, check your data.");
        }

        if(userRepository.existsByUserCpf(user.getUserCpf())) {
            throw new CpfException("CPF Already Exists");
        }
        return userRepository.save(user);
    }

    @Transactional
    public User getUserById(Integer id) {
        return userRepository.findById(Long.valueOf(id)).orElseThrow(() -> new IdNotFoundException("ID " + id + " not found"));
    }

    @Transactional
    public void deleteUserById(Integer id) {
        if( !userRepository.existsById(Long.valueOf(id)) ) {
            throw new IdNotFoundException("ID " + id + " not found");
        }
        userRepository.deleteById(Long.valueOf(id));
    }

    @Transactional
    public Optional<User> patchUser(Integer id, User user) {

        Optional<User> currUser = Optional.ofNullable(this.getUserById(id));
        if(!currUser.isPresent()) {
            throw new IdNotFoundException("ID " + id + " not found");
        }

        if(userRepository.existsByUserCpf(user.getUserCpf())) {
            throw new CpfException("CPF Already Exists");
        }

        if( !Utils.checkExistenceOfProperty(user.getProperties() )) {
            throw new PropertyException("Properties to update not found, check your data.");
        }

        try {
            Utils.copyNonNull(user, currUser.get());
            userRepository.save(currUser.get());
            return currUser;

        } catch (Exception e) {
            throw new RuntimeException("Error trying to update user: " + e.getMessage());
        }
    }
}
