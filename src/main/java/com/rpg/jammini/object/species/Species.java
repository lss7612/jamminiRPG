package com.rpg.jammini.object.species;

import java.util.List;
import java.util.Random;

import com.rpg.jammini.object.skill.Skill;
import com.rpg.jammini.object.weapon.Weapon;

import lombok.Data;

@Data
public abstract class Species {
	
	public static final int levelMax = 10;
	
	protected float maxHp = 100;
	protected float maxMp = 100;
	protected float hp = 100;
	protected float mp = 100;
	protected float attNm = 10;
	protected float dffNm = 1;
	protected float dexNm = 1;
	protected float avoidRateNm = 0.1f;
	protected float att = 10;
	protected float dff = 1;
	protected float dex = 1;
	protected float avoidRate = 0.1f;
	protected int level = 1;
	protected boolean died = false;
	protected SpeciesCode speciesCode;
	protected Weapon weapon = Weapon.NULL;
	protected Skill active = Skill.NULL;
	protected boolean attAvailable = true;
	
	
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
	public void levelUp(List<String> logList) {
		
		if(this.level < levelMax) {
			this.maxHp += 5;
			this.maxMp += 5;
			this.hp = maxHp;
			this.mp = maxMp;
			this.attNm += 0.5;
			this.dffNm += 0.5;
			this.dexNm += 0.5;
			this.avoidRateNm *= 1.05;
			
			this.level ++;
			this.reInit();
			logList.add("레벨업!!");
		} else {
			logList.add("더이상 레벨이 오르지 않습니다");
		}
		
	}
	
	public void reInit() {
		this.att = attNm;
		this.dff = dffNm;
		this.dex = dexNm;
		this.avoidRate = avoidRateNm;
		this.att = attNm * this.weapon.getAtt();
		this.dex = dexNm * this.weapon.getDex();
		this.active.useSkill(this);
	}
	
	/**
	 * 공격함
	 */
	public void attack(Species monster, List<String> logList) {
		if(!attAvailable) {
			logList.add("아직 공격할 수 없습니다");
			return;
		}
		
		if(!monster.died) {
			this.attAvailable = false;
			logList.add(this.speciesCode + "이(가) 공격합니다");
			monster.getHit(this.att, this, logList);
			if(monster.died) this.levelUp(logList);
			
			Thread attAvailable = new attAvailableThread(this, 3 / this.dex);
			attAvailable.start();
		} else {
			logList.add("몬스터가 이미 죽어있습니다.");
		}
	}

	private class attAvailableThread extends Thread{
		
		Species species;
		float time;
		
		attAvailableThread(Species species, float time) {
			this.species = species;
			this.time = time;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep((long) (this.time * 1000));
				species.setAttAvailable(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 공격 당함 
	 */
	public void getHit(float power, Species attacker, List<String> logList) {
		if(new Random().nextInt(100) > (this.avoidRate * 100)) {
			logList.add(speciesCode + "이(가) 공격당함!!");
			float damage = power-dff;
			hp -= damage > 0 ? damage : 0;
			if(this.isDied()) {
				logList.add(this.speciesCode + "사망");
				this.died = true;
			}
				
		} else {
			logList.add(this.speciesCode + "은(는) 회피했습니다!!");
		}
	}
	
	
	/**
	 * 무기 장착
	 */
	public void setWeapon(Weapon weapon, List<String> logList) {
		if(!weapon.isMatch(this)) {
			logList.add(weapon + "은 " + this.speciesCode + "용 무기가 아닙니다.");
		} else {
			this.weapon = weapon;
			this.reInit();
			logList.add(weapon + " 장착 완료");
		}
	}
	
	/**
	 * 무기 해제
	 */
	public boolean dropWeapon() {
		this.weapon = Weapon.NULL;
		this.reInit();
		return true;
	}
	
	/**
	 * 스킬사용
	 */
	public void useSkill(Skill skill, List<String> logList) {
		if(!skill.isMatchSpecies(this.speciesCode)) {
			logList.add(this.speciesCode + "는 " + skill + "을(를)사용할 수 없습니다.");
		} else if(this.isAnggo(skill)) {
			logList.add("MP가 부족합니다.");
		} else if(skill.getLevel() > this.level) {
			logList.add(skill + "은 레벨" + skill.getLevel() + " 부터 사용 가능합니다.");
		} else {
			this.active = skill;
			this.mp -= skill.getNeedMp();
			this.reInit();
			logList.add(speciesCode + "가 " + skill + "을(를) 사용");
		}
	}
	
	/**
	 * 스킬 종료
	 */
	public void endSkill(List<String> logList) {
		logList.add(this.active +"스킬이 해제됩니다.");
		this.active = Skill.NULL;
		this.reInit();
	}
	
	
}
