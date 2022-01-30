package com.rpg.jammini.object.skill;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.rpg.jammini.object.species.Species;
import com.rpg.jammini.object.species.SpeciesCode;


public enum Skill {
	Heal		("Heal"		,10, 0, 1, SpeciesCode.HUMAN,SpeciesCode.OAK,SpeciesCode.ELF),
	Stream		("Stream"	,10, 10, 1,SpeciesCode.HUMAN,SpeciesCode.OAK,SpeciesCode.ELF),
	Guard		("Guard"	,10, 10, 3, SpeciesCode.HUMAN),
	Elusion		("Elusion"	,10, 10, 3, SpeciesCode.ELF),
	Anger		("Anger"	,10, 10, 3, SpeciesCode.OAK),
	
	
	Rapid		("Rapid"	,30, 60, Species.levelMax, SpeciesCode.ELF),
	Frenzy		("Frenzy"	,30, 60, Species.levelMax, SpeciesCode.OAK),
	Invisible	("Invisible",10, 60, Species.levelMax, SpeciesCode.HUMAN),
	
	
	NULL("NULL",0, 0, 0, SpeciesCode.OAK,SpeciesCode.ELF,SpeciesCode.HUMAN)
	;
	Skill(String name, float needMp, int duration, int level, SpeciesCode ...speciesCodes){
		this.name = name;
		this.needMp = needMp;
		this.speciesCodes = speciesCodes;
		this.duration = duration;
		this.level = level;
	}
	private String name;
	private float needMp;
	private int duration;
	private SpeciesCode[] speciesCodes;
	private int level;

	public boolean isMatchSpecies(SpeciesCode speciesCode) {
		SpeciesCode match = Arrays.stream(this.speciesCodes).filter(code -> code==speciesCode)
														    .findAny()
															.orElse(SpeciesCode.NULL);
		return match == speciesCode;
	}
	public int getLevel() {
		return this.level;
	}
	
	public float getNeedMp() {
		return this.needMp;
	}
	public SpeciesCode[] getSpeciesCode() {
		return this.speciesCodes;
	}
	public String getName() {
		return this.name;
	}
	public int getDuration() {
		return this.duration;
	}
	
	public static Skill nameToCode(String name) {
		return Arrays.stream(Skill.values()).filter(code-> code.getName().equals(name))
												  .findAny()
												  .orElse(NULL);
	}
	
	public static List<Skill> getSpeciesSkill(SpeciesCode species) {
		return Arrays.stream(Skill.values())
					 .filter(skill-> {
						 return Arrays.stream(skill.speciesCodes).anyMatch(code->code==species);
					 })
					 .filter(skill->skill!=NULL)
					 .collect(Collectors.toList());
	}
	
	/**
	 * 스킬구현
	 * @param species
	 * @return 
	 * @return
	 */
	public boolean useSkill(Species species) {
		
		switch (this) {
		case Heal:
			if(species.getHp() + 10 > species.getMaxHp()) {
				species.setHp(species.getMaxHp());
			} else {
				species.setHp(species.getHp() + 10);
			}
			break;
		case Stream:
			species.setAtt(species.getAtt() * 1.2f);
			break;	
		case Guard:
			species.setDff(species.getDff() * 1.3f);
			break;	
		case Elusion:
			species.setAvoidRate(species.getAvoidRate() * 1.3f);
			break;	
		case Anger:
			species.setAtt(species.getAtt() * 1.5f);
			species.setDff(species.getDff() * 0.9f);
			break;	
		case Rapid:
			species.setDex(species.getDex() * 5);
			break;
		case Frenzy:
			species.setAtt(species.getAtt() * 5);
			break;
		case Invisible:
			species.setAvoidRate(1);
			break;
		default:
			break;
		}
		return true;
	}
	
	
}
