package com.dulich.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dulich.dto.UsernameExistsException;
import com.dulich.entity.UserEntity;
import com.dulich.repository.UserRepository;
import com.dulich.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;



@Service
public class UserService implements IUserService{
	
	 private final int BCRYPT_WORKLOAD = 12;
	
	@Autowired
	private UserRepository userRepository;

	
    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(BCRYPT_WORKLOAD));
    }

	
	@Override
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
	
    public boolean isUsernameUnique(String username) {
        return !userRepository.existsByUsername(username);
    }
    
    
	
	
	@Override
	public UserEntity save(UserEntity userEntity) {
		if (isUsernameUnique(userEntity.getUsername())) {
			UserEntity newuser = new UserEntity();
			newuser.setPassword(hashPassword(userEntity.getPassword()));
			newuser.setId(userEntity.getId());
			newuser.setName(userEntity.getName());
			newuser.setUsername(userEntity.getUsername());
			newuser.setDob(userEntity.getDob());
			newuser.setGender(userEntity.getGender());
			UserEntity user = userRepository.save(newuser);
			return user;
		} else {
			throw new UsernameExistsException("Username is already taken");
		}
	}

	@Override
	public UserEntity findUserByUsername(String username) {
		UserEntity userEntity = userRepository.findOneByUsername(username);
		return userEntity;
	}

	

	
	

}
