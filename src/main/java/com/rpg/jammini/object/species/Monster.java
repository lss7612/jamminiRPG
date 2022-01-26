package com.rpg.jammini.object.species;

import java.util.Random;

public class Monster extends Species{
	
	protected float counterRate = 30;
	
	public Monster() {
		this.speciesCode = SpeciesCode.MONSTER;
	}
	
	public void getHit(float power, Species attaker) {
		float damage = power-dff;
		hp -= damage;
		if(!this.isDied()) {
			counterAttack(attaker);
		} else {
			this.died = true;
		}
	}
	
	public void counterAttack(Species attacker) {
		if(new Random().nextInt(100) < counterRate) {
			System.out.println(this.speciesCode + "가 반격합니다!");
			attacker.getHit((float) (this.att * 0.7));
		}
	}
	
}
