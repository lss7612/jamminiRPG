package com.rpg.jammini.object.species;

import com.rpg.jammini.object.skill.Skill;
import com.rpg.jammini.object.weapon.Weapon;

import lombok.Data;

@Data
public abstract class Species {
	
	protected float maxHp = 100;
	protected float maxMp = 100;
	protected float hp = 100;
	protected float mp = 100;
	protected float attNm = 10;
	protected float dffNm = 5;
	protected float dexNm = 5;
	protected float att = 10;
	protected float dff = 5;
	protected float dex = 5;
	protected float avoidRate = 0.1f;
	protected int level = 1;
	protected boolean died = false;
	protected SpeciesCode speciesCode;
	protected Weapon weapon = Weapon.NULL;
	protected Skill active = Skill.NULL;
	
	
	/**
	 * 캐릭터 사망 여부 
	 */
	public boolean isDied() {
		return this.hp <= 0f ? true : false;
	}

	/**
	 * 마나가 부족한지 여부 
	 */
	public boolean isAnggo(Skill skill) {
		return skill.getNeedMp() > this.mp;
	}
	
	/**
	 * 레벨업
	 */
	public void levelUp() {
		this.maxHp += 5;
		this.maxMp += 5;
		this.hp = maxHp;
		this.mp = maxMp;
		this.attNm += 1;
		this.dffNm += 1;
		this.dexNm += 1;
		this.avoidRate += 1.2;
		this.level ++;
		this.reInit();
	}
	
	public void reInit() {
		this.att = attNm;
		this.dff = dffNm;
		this.dex = dexNm;
		this.setWeapon(this.weapon);
		this.useSkill(this.active);
	}
	
	/**
	 * 공격함
	 */
	public void attack(Monster monster) {
		if(!monster.isDied()) {
			monster.getHit(this.att, this);
		} 
	}

	/**
	 * 공격 당함 
	 */
	public void getHit(float power) {
		float damage = power-dff;
		hp -= damage > 0 ? damage : 0;
	}
	
	/**
	 * 무기 장착
	 */
	public boolean setWeapon(Weapon weapon) {
		if(!weapon.isMatch(this)) {
			System.out.println(weapon + "은 " + this.speciesCode + "용 무기가 아닙니다.");
			return false;
		}
		System.out.println(weapon + " 세팅완료");
		this.weapon = weapon;
		this.att = this.attNm * weapon.getAtt();
		this.dex = this.dexNm * weapon.getDex();
		return true;
	}
	
	/**
	 * 무기 해제
	 */
	public boolean dropWeapon(Weapon weapon) {
		this.weapon = null;
		this.att = this.attNm;
		System.out.println("무기 뺌");
		return true;
	}
	
	/**
	 * 스킬사용
	 */
	public boolean useSkill(Skill skill) {
		if(!skill.isMatchSpecies(this.speciesCode)) {
			System.out.println(this.speciesCode + "는 " + skill + "을 사용할 수 없습니다.");
			return false;
		} else if(this.isAnggo(skill)) {
			System.out.println("MP가 부족합니다.");
			return false;
		}
		this.active = skill;
		return skill.useSkill(this);
	}
	
	/**
	 * 스킬 종료
	 */
	public void endSkill() {
		this.active = Skill.NULL;
		System.out.println("스킬 해제됨");
		this.reInit();
	}
	
	
}
