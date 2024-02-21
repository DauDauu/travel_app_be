package com.dulich.service;

import com.dulich.entity.UserEntity;

public interface IUserService {
	UserEntity save(UserEntity userEntity);
	UserEntity findUserByUsername(String username);
	boolean checkPassword(String plainPassword, String hashedPassword);
}
