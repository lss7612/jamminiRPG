package com.rpg.jammini.object.skill;

import java.util.Arrays;

import com.rpg.jammini.object.species.Species;
import com.rpg.jammini.object.species.SpeciesCode;


public enum Skill {
	Heal("Heal",10,SpeciesCode.HUMAN,SpeciesCode.OAK,SpeciesCode.ELF),
	Stream("Stream",10,SpeciesCode.HUMAN,SpeciesCode.OAK,SpeciesCode.ELF),
	Guard("Guard",10,SpeciesCode.HUMAN),
	Invisible("Invisible",30,SpeciesCode.HUMAN),
	Elusion("Elusion",10,SpeciesCode.ELF),
	Rapid("Rapid",30,SpeciesCode.ELF),
	Anger("Anger",10,SpeciesCode.OAK),
	Frenzy("Frenzy",30,SpeciesCode.OAK),
	NULL("NULL",0,SpeciesCode.OAK,SpeciesCode.ELF,SpeciesCode.HUMAN)
	;
	Skill(String name, float needMp, SpeciesCode ...speciesCodes){
		this.name = name;
		this.needMp = needMp;
		this.speciesCodes = speciesCodes;
	}
	private String name;
	private float needMp;
	private int duration = 60;
	private SpeciesCode[] speciesCodes;

	public boolean isMatchSpecies(SpeciesCode speciesCode) {
		SpeciesCode match = Arrays.stream(this.speciesCodes).filter(code -> code==speciesCode)
														    .findAny()
															.orElse(SpeciesCode.NULL);
		return match == speciesCode;
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
	
	public static Skill nameToCode(String name) {
		return Arrays.stream(Skill.values()).filter(code-> code.getName().equals(name))
												  .findAny()
												  .orElse(NULL);
	}
	
	/**
	 * 스킬구현
	 * @param species
	 * @return 
	 * @return
	 */
	public boolean useSkill(Species species) {
		species.setMp(species.getMp()-this.getNeedMp());
		
		switch (this) {
		case Heal:
			species.setHp(species.getHp() + 10);
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
			species.setAvoidRate(species.getAtt() * 1.5f);
			species.setDff(species.getDff() * 0.9f);
			break;	
		default:
			break;
		}
		System.out.println(this + " SKILL 사용");
		
		return true;
	}
	
	
}
