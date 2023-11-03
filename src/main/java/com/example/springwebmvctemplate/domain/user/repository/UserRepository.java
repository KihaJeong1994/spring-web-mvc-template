package com.example.springwebmvctemplate.domain.user.repository;

import com.example.springwebmvctemplate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
