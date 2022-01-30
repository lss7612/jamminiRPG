package com.rpg.jammini.object.species;

import java.util.List;

import com.rpg.jammini.object.skill.Skill;

public class Human extends Species{

	public Human() {
		this.speciesCode = SpeciesCode.HUMAN;
	}
	
	@Override
	public void levelUp(List<String> logList) {
			
		super.levelUp(logList);
		if(this.level < levelMax) {
			this.dffNm += 0.5;
			this.reInit();
		} 
		
	}
	
}
