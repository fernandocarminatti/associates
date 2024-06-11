package com.associates.associates.repository;

import com.associates.associates.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    boolean existsByUserCpf(String userCpf);
}
