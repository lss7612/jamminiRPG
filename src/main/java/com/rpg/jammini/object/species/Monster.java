package com.rpg.jammini.object.species;

import java.util.Random;

public class Monster extends Species{
	
	protected float counterRate = 0.3f;
	
	@Override
	public void getHit(float power, Species attaker) {
		float damage = power-dff;
		hp -= damage > 0 ? damage : 0;
		if(!this.isDied()) {
			counterAttack(attaker);
		}
	}
	
	public void counterAttack(Species attacker) {
		if(new Random().nextInt(10) < 3) {
			attacker.getHit((float) (this.att * 0.7), this);
		}
	}
	
}
