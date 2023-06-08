package com.dailystudy.swinglab.service;

import com.dailystudy.swinglab.service.business.common.domain.entity.user.User;
import com.dailystudy.swinglab.service.business.common.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class SwinglabServiceApplicationTests {

	@Autowired
	private UserRepository userRepository;

//	@Test
	public void insertUser()
	{
		User user = new User();
		user.setName("test");
		user.setPwd("qhdks@00");
		user.setPwdChk("qhdks@00");
		user.setDelYn(false);
		user.setLoginId("test");
		user.setSvcStDay(LocalDate.now());
		user.setSvcEdDay(LocalDate.now());
		userRepository.save(user);
	}

}
