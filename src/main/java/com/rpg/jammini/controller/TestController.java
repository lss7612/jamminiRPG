package com.rpg.jammini.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.rpg.jammini.object.skill.Skill;
import com.rpg.jammini.object.species.Elf;
import com.rpg.jammini.object.species.Human;
import com.rpg.jammini.object.species.Monster;
import com.rpg.jammini.object.species.SpeciesCode;
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
		
		String str = "{\"maxHp\":100,\"maxMp\":100,\"hp\":100,\"mp\":100,\"attNm\":10,\"dffNm\":5,\"dexNm\":5,\"att\":10,\"dff\":5,\"dex\":5,\"avoidRate\":0.1,\"level\":1,\"speciesCode\":\"HUMAN\",\"weapon\":\"NULL\",\"active\":\"NULL\",\"died\":false}";
		Human result = new Gson().fromJson(str, Human.class);
		System.out.println(result);

		
	}
	
}
