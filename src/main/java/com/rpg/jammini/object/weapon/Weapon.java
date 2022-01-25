package com.rpg.jammini.object.weapon;

import com.rpg.jammini.object.species.SpeciesCode;

public enum Weapon {
	ShortSword(1.05f,1.1f,"휴먼"),
	LongSword(1.05f,1.1f,"휴먼"),
	ShortAxe(1.1f,0.95f,"오크"),
	IronHammer(1.2f,0.95f,"오크"),
	ShortBow(1f, 1.05f,"엘프"),
	IronBow(1f, 1.1f,"엘프"),
	;
	
	Weapon(float att,float dex, String name) {
		this.att = att;
		this.dex = dex;
		this.speciesCode = SpeciesCode.nameToCode(name);
	}
	
	private float att;
	private float dex;
	private SpeciesCode speciesCode;
	
	
	public float getAtt() {
		return this.att;
	}
	public float getDex() {
		return this.dex;
	}
	public SpeciesCode getSpeciesCode() {
		return this.speciesCode;
	}
}
