package com.rpg.jammini.object.species;

import java.util.List;

public class Elf extends Species{

	public Elf() {
		this.speciesCode = SpeciesCode.ELF;
	}
	
	@Override
	public void levelUp(List<String> logList) {
			
		super.levelUp(logList);
		if(this.level < levelMax) {
			this.dexNm += 1;
			this.reInit();
		} 
		
	}
}
