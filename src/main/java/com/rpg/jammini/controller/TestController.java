package com.rpg.jammini.controller;


import java.util.List;
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
		
		List<Weapon> list = Weapon.getSpeciesWeapon(SpeciesCode.HUMAN);
		for(Weapon w : list) {
			System.out.println(w);
		}
		
		List<Skill> s_list = Skill.getSpeciesSkill(SpeciesCode.HUMAN);
		for(Skill s : s_list) {
			System.out.println(s);
		}
		
	}
	
}
