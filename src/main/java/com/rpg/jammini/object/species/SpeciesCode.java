package com.rpg.jammini.object.species;

import java.util.Arrays;

public enum SpeciesCode {
	NULL("NULL"),
	HUMAN("휴먼"),
	ELF("엘프"),
	OAK("오크"),
	;
	
	SpeciesCode(String name) {
		this.name = name;
	}
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	public static SpeciesCode nameToCode(String name) {
		return Arrays.stream(SpeciesCode.values())
					 .filter(e -> e.getName().equals(name))
					 .findAny()
					 .orElse(NULL);
	}
}
