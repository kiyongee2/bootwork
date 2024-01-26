package com.khit.board.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.khit.board.entity.User;
import com.khit.board.exception.CustomException;
import com.khit.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Integer id) {
		//검색된 회원이 없는 경우는 람다식으로 예외 처리
		User findUser = 
				userRepository.findById(id)
				.orElseThrow(() -> {
					return new CustomException(id + "번 회원이 없습니다.");
				});
		
		/*User findUser = 
				userRepository.findById(id)
				.orElseThrow(new Supplier<CustomException>() {

					@Override
					public CustomException get() {
						return new CustomException(id + "번 회원이 없습니다.");
					}
				});*/
		
		return findUser;
	}
    
	//회원 수정
	public void update(User user) {
		userRepository.save(user);
	}

	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}
}
