package com.rpg.jammini.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.rpg.jammini.object.skill.Skill;
import com.rpg.jammini.object.species.Elf;
import com.rpg.jammini.object.species.Human;
import com.rpg.jammini.object.species.Monster;
import com.rpg.jammini.object.species.Oak;
import com.rpg.jammini.object.species.Species;
import com.rpg.jammini.object.species.SpeciesCode;
import com.rpg.jammini.object.weapon.Weapon;

@Controller
public class GameController {

	private Species makeCharactorNow(String name, String charactorJson) {
		
		switch (SpeciesCode.nameToCode(name)) {
		case HUMAN:
			return new Gson().fromJson(charactorJson, Human.class);
		case ELF:
			return new Gson().fromJson(charactorJson, Elf.class);
		case OAK:
			return new Gson().fromJson(charactorJson, Oak.class);
		case MONSTER:
			return new Gson().fromJson(charactorJson, Monster.class);
		default:
			return null;
		}
	}
	
	
	@GetMapping("/getCharactor")
	@ResponseBody
	public Map<String, Object> makeNewCharactor(String name){
		System.out.println("새로운캐릭터생성");
		Map<String, Object> result = new HashMap<>();
		switch (SpeciesCode.nameToCode(name)) {
		case HUMAN:
			result.put("result", new Human());
			break;
		case OAK:
			result.put("result", new Oak());
			break;
		case ELF:
			result.put("result", new Elf());
			break;
		case MONSTER:
			result.put("result", new Monster());
			break;
		default:
			break;
		}
		return result;
	}
	
	@PostMapping("/setWeapon")
	@ResponseBody
	public Map<String, Object> setWeapon(
			String name,
			String weapon, 
			String charactorJson
			) {
		System.out.println("무기장착");
		Map<String, Object> result = new HashMap<>();
		
		List<String> logList = new ArrayList<>();
		
		Species charactor = makeCharactorNow(name, charactorJson);
		charactor.setWeapon(Weapon.nameToCode(weapon), logList);
		
		result.put("charactor", charactor);
		result.put("logList", logList);
		
		return result;
	}
	
	
	@PostMapping("/attack")
	@ResponseBody
	public Map<String, Object> attack(
			String charactorJson,
			String monsterJson,
			String name
			) {
		
		System.out.println("공격");
		Map<String, Object> result = new HashMap<>();
		List<String> logList = new ArrayList<>();
		
		Species charactor = makeCharactorNow(name, charactorJson);
		Species monster = makeCharactorNow("MONSTER",monsterJson);
		
		charactor.attack(monster, logList);
		
		result.put("charactor", charactor);
		result.put("monster", monster);
		result.put("logList", logList);
		
		return result;
		
	}
	
	@PostMapping("/useSkill")
	@ResponseBody
	public Map<String, Object> useSkill(String skill, String name, String charactorJson){
		
		System.out.println("스킬 사용");
		Map<String,Object> result = new HashMap<>();
		List<String> logList = new ArrayList<>();
		
		Species charactor = makeCharactorNow(name, charactorJson);
		Skill skillCode = Skill.nameToCode(skill);
		charactor.useSkill(skillCode, logList);
		
		result.put("charactor", charactor);
		result.put("logList", logList);
		
		return result;
		
	}
	
	@PostMapping("/endSkill")
	@ResponseBody
	public Map<String, Object> endSkill(String name, String charactorJson) {
		
		Map<String,Object> result = new HashMap<>();
		List<String> logList = new ArrayList<>();
		
		Species charactor = makeCharactorNow(name, charactorJson);
		charactor.endSkill(logList);
		
		result.put("charactor", charactor);
		result.put("logList", logList);
		
		return result;
		
	}
	
	@GetMapping("/getWeapons")
	@ResponseBody
	public Map<String, Object> getSpeciesWeapon(String name){
		
		Map<String, Object> result = new HashMap<>();
		SpeciesCode code = SpeciesCode.nameToCode(name);
		
		List<Weapon> weapons = Weapon.getSpeciesWeapon(code);
		result.put("weapons", weapons);
		
		return result;
		
	}
	
	@GetMapping("/getSkills")
	@ResponseBody
	public Map<String, Object> getSpeciesSkill(String name, int level) {
		
		Map<String, Object> result = new HashMap<>();
		SpeciesCode code = SpeciesCode.nameToCode(name);
		
		List<Skill> skills = Skill.getSpeciesSkill(code);
		List<Integer> durations= new ArrayList<>();
		for(Skill skill : skills) {
			durations.add(skill.getDuration());
		}
		List<Integer> levels = new ArrayList<>();
		for(Skill skill : skills) {
			levels.add(skill.getLevel());
		}
		
		
		result.put("skills", skills);
		result.put("durations", durations);
		result.put("levels", levels);
		
		return result;
		
	}
	
	
	
	
}
