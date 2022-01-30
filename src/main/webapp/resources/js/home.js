document.addEventListener("DOMContentLoaded",function(){
	
	document.querySelectorAll('.selectSpecies').forEach(item => item.addEventListener('click',()=>{
		gameInit(item.getAttribute('value'));
	}))
	
})


function gameInit(code){
	console.log('gameInit - code',code)
	speciesCode=code;
	makeNewCharactor(speciesCode);
	makeNewCharactor("MONSTER");
	getSpeciesWeapon(speciesCode);
	getSpeciesSkill(speciesCode);
	document.getElementById('img').setAttribute('class',speciesCode);
	gameInitializer.style.display = 'none';
}
