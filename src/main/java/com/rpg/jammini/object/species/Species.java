package com.rpg.jammini.object.species;

import com.rpg.jammini.object.skill.Skill;
import com.rpg.jammini.object.weapon.Weapon;

import lombok.Data;

@Data
public abstract class Species {
	
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
	protected SpeciesCode speciesCode;
	protected Weapon weapon;
	
	protected boolean isDied() {
		return this.hp <= 0f ? true : false;
	}
	
	protected boolean isAnggo(Skill skill) {
		return skill.getNeedMp() > this.mp;
	}
	
	protected void attack(Monster monster) {
		monster.getHit(this.att, this);
	}
	
	protected void getHit(float power, Species attaker) {
		float damage = power-dff;
		hp -= damage > 0 ? damage : 0;
	}
	
	protected boolean setWeapon(Weapon weapon) {
		if(this.speciesCode != weapon.getSpeciesCode()) {
			System.out.println("weapon은 " + weapon.getSpeciesCode() + "용 무기입니다.");
			return false;
		}
		System.out.println(weapon + " 세팅완료");
		this.weapon = weapon;
		this.att = this.attNm * weapon.getAtt();
		return true;
	}
	
	protected boolean dropWeapon(Weapon weapon) {
		this.weapon = null;
		this.att = this.attNm;
		System.out.println("무기 뺌");
		return true;
	}
	
	
}
