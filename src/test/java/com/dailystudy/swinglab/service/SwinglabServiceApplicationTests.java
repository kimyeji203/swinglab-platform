package com.dailystudy.swinglab.service;

import com.dailystudy.swinglab.service.business.domain.entity.user.User;
import com.dailystudy.swinglab.service.business.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SwinglabServiceApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void insertUser()
	{
		User user = new User();
		user.setName("test");
		user.setPwd("qhdks@00");
		user.setPwdChk("qhdks@00");
		user.setDelYn(false);
		user.setLoginId("test");
		user.setSvcStDay(new Date());
		user.setSvcEdDay(new Date());
		userRepository.save(user);
	}

}
