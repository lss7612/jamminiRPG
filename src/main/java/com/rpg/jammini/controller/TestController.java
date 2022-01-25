package com.rpg.jammini.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rpg.jammini.object.weapon.Weapon;
import com.rpg.jammini.service.TestService;


@Controller
public class TestController {

	@Autowired TestService testService;
	
	@GetMapping("test")
	public String test() {
		
//		List<Map<String, Object>> result = testService.dbTest();
		
		
		return "home";
	}
	
	public static void main(String[] args) {
		Weapon w = Weapon.IronBow;
	}
	
}
