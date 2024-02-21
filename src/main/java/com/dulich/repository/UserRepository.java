package com.dulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dulich.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByUsername(String username);
	boolean existsByUsername(String username);
}
