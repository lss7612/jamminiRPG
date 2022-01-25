package com.rpg.jammini.object.species;

import com.rpg.jammini.object.skill.Skill;

import lombok.Data;

@Data
public class Species {
	
	protected float maxHp; 
	protected float maxMp;
	protected float hp;
	protected float mp;
	protected float attNm;
	protected float dffNm;
	protected float dexNm;
	protected float att;
	protected float dff;
	protected float dex;
	protected float avoidRate;
	protected int level;
	protected SpeciesCode code;
	
	protected boolean isDied() {
		return this.hp <= 0f ? true : false;
	}
	
	protected boolean isAnggo(Skill skill) {
		return skill.getNeedMp() > this.mp;
	}
	
	protected void attack(Monster monster) {
		monster.getHit(this.att, this);
	}
	
	public void getHit(float power, Species attaker) {
		float damage = power-dff;
		hp -= damage > 0 ? damage : 0;
	}
	
	
	
}
