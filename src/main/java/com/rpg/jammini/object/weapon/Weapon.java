package com.rpg.jammini.object.weapon;

import java.util.Arrays;

import com.rpg.jammini.object.species.Species;
import com.rpg.jammini.object.species.SpeciesCode;

public enum Weapon {
	ShortSword("ShortSword",1.05f,1f, SpeciesCode.HUMAN),
	LongSword("LongSword",1.1f,1f, SpeciesCode.HUMAN),
	ShortAxe("ShortAxe",1.1f,0.95f, SpeciesCode.OAK),
	IronHammer("IronHammer",1.2f,0.95f, SpeciesCode.OAK),
	ShortBow("ShortBow",1f, 1.05f, SpeciesCode.ELF),
	IronBow("IronBow",1f, 1.1f, SpeciesCode.ELF),
	NULL("무기없음",1f,1f,SpeciesCode.ELF,SpeciesCode.OAK,SpeciesCode.HUMAN)
	;
	
	Weapon(String name, float att,float dex, SpeciesCode ...speciesCodes) {
		this.name = name;
		this.att = att;
		this.dex = dex;
		this.speciesCodes = speciesCodes;
	}
	
	private String name;
	private float att;
	private float dex;
	private SpeciesCode[] speciesCodes;
	
	public String getName() {
		return this.name;
	}
	public float getAtt() {
		return this.att;
	}
	public float getDex() {
		return this.dex;
	}
	public SpeciesCode[] getSpeciesCode() {
		return this.speciesCodes;
	}
	
	public boolean isMatch(Species species) {
		SpeciesCode match =	Arrays.stream(this.speciesCodes).filter(code -> code == species.getSpeciesCode())
														   .findAny()
														   .orElse(SpeciesCode.NULL);
		System.out.println(match);
		System.out.println(species.getSpeciesCode());
		return species.getSpeciesCode() == match;
	}
	
	public static Weapon nameToCode(String name) {
		return Arrays.stream(Weapon.values())
					 .filter(e -> e.getName().equals(name))
					 .findAny()
					 .orElse(NULL);
	}
}
