package com.rpg.jammini.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpg.jammini.mapper.TestMapper;
import com.rpg.jammini.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Autowired TestMapper testMapper;
	
	@Override
	public List<Map<String, Object>> dbTest() {
		return testMapper.dbTest();
	}
	
}
