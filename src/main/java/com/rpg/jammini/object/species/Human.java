package com.rpg.jammini.object.species;

import java.util.List;

import com.rpg.jammini.object.skill.Skill;

public class Human extends Species{

	public Human() {
		this.speciesCode = SpeciesCode.HUMAN;
	}
	
	@Override
	public void getHit(float power, Species attacker, List<String> logList) {
		if(this.active == Skill.Invisible) {
			logList.add(this.active +"스킬을 사용중이므로 맞지 않습니다.");
		} else {
			super.getHit(power,attacker, logList);
		}
	}
	
	@Override
	public void levelUp(List<String> logList) {
			
		super.levelUp(logList);
		if(this.level < levelMax) {
			this.dffNm += 1;
			this.reInit();
		} 
		
	}
	
}
