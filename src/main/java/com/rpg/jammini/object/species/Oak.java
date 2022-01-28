package com.rpg.jammini.object.species;

import java.util.List;

public class Oak extends Species{

	public Oak() {
		this.speciesCode = SpeciesCode.OAK;
	}
	
	@Override
	public void levelUp(List<String> logList) {
			
		super.levelUp(logList);
		if(this.level < levelMax) {
			this.attNm += 0.5;
			this.reInit();
		} 
		
	}
	
}
