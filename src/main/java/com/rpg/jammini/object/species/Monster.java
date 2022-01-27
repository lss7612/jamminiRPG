package com.rpg.jammini.object.species;

import java.util.List;
import java.util.Random;

public class Monster extends Species{
	
	protected float counterRate = 30;
	
	public Monster() {
		this.speciesCode = SpeciesCode.MONSTER;
		this.maxHp /= 2;
		this.hp /= 2;
	}
	
	public void getHit(float power, Species attacker, List<String> logList) {
		
		super.getHit(power, attacker, logList);
		if(!this.died) {
			counterAttack(attacker, logList);
		} 
		
	}
	
	public void counterAttack(Species attacker, List<String> logList) {
		if(new Random().nextInt(100) < counterRate) {
			logList.add("몬스터가 반격합니다");
			attacker.getHit((float) (this.att * 0.7), attacker, logList);
		} else {
			logList.add("몬스터가 반격하지 못했습니다.");
		}
	}
	
}
