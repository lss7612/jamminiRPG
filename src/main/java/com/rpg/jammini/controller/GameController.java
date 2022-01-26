package com.rpg.jammini.controller;

import java.util.HashMap;
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
	
//	private Monster makeMonsterNow(String monsterJson) {
//		return new Gson().fromJson(monsterJson, Monster.class);
//	}
	
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
			result.put("result", new Elf());
			break;
		case ELF:
			result.put("result", new Oak());
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
		
		Species charactor = makeCharactorNow(name, charactorJson);
		charactor.setWeapon(Weapon.nameToCode(weapon));
		
		result.put("charactor", charactor);
		
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
		
		Species charactor = makeCharactorNow(name, charactorJson);
		Monster monster = (Monster) makeCharactorNow("MONSTER",monsterJson);
		
		charactor.attack(monster);
		
		result.put("charactor", charactor);
		result.put("monster", monster);
		
		return result;
		
	}
	
	@PostMapping("/useSkill")
	@ResponseBody
	public Map<String, Object> useSkill(String skill, String name, String charactorJson){
		
		System.out.println("스킬 사용");
		Map<String,Object> result = new HashMap<>();
		
		Species charactor = makeCharactorNow(name, charactorJson);
		Skill skillCode = Skill.nameToCode(skill);
		charactor.useSkill(skillCode);
		
		result.put("charactor", charactor);
		
		return result;
		
	}
	
	@PostMapping("/endSkill")
	@ResponseBody
	public Map<String, Object> endSkill(String name, String charactorJson) {
		
		System.out.println("스킬 사용");
		Map<String,Object> result = new HashMap<>();
		
		Species charactor = makeCharactorNow(name, charactorJson);
		charactor.endSkill();
		
		result.put("charactor", charactor);
		
		return result;
		
	}
	
	
	
	
}
