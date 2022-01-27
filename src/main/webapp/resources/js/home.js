document.addEventListener("DOMContentLoaded",function(){
	
})


function gameInit(code){
	speciesCode=code;
	makeNewCharactor(speciesCode);
	makeNewCharactor("MONSTER");
	getSpeciesWeapon(speciesCode);
	getSpeciesSkill(speciesCode);
	document.getElementById('img').setAttribute('class',speciesCode);
}