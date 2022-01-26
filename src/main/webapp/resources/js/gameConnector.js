let charactor = null;
let monster = null;

function makeNewCharactor(name){
	$.ajax({
		method : 'GET',
		url : contextPath + '/getCharactor',
		dataType : 'json',
		data : {name : name},
		success : function(res){
			console.log(res);
			
			if(name!="MONSTER") {
				setCharactor(res.result);
			} else {
				monster = res.result;
			}
			
		},
		error : function(){
			alert("makeNewCharactor 실패");
		}
	})
}

function setWeapon(weapon) {
	let obj = {weapon : weapon, charactorJson : JSON.stringify(charactor), name : charactor.speciesCode};
	console.log('obj',obj);
	$.ajax({
		method : 'POST',
		url : contextPath + '/setWeapon',
		dataType : 'json',
		data : obj,
		success : function(res){
			console.log(res);
			setCharactor(res.charactor);
		},
		error : function(){
			alert("setWeapon 실패");
		}
	})
}

function attack() {
	$.ajax({
		method : 'POST',
		url : contextPath + '/attack',
		dataType : 'json',
		data : {
			name : charactor.speciesCode,
			charactorJson : JSON.stringify(charactor),
			monsterJson : JSON.stringify(monster),
		},
		success : function(res){
			console.log(res);
			setCharactor(res.charactor);
			monster = res.monster;
		},
		error : function(){
			alert("attack 실패");
		}
	})
}

function useSkill(skill){
	$.ajax({
		method : 'POST',
		url : contextPath + '/useSkill',
		dataType : 'json',
		data : {
			name : charactor.speciesCode,
			charactorJson : JSON.stringify(charactor),
			skill : skill
		},
		success : function(res){
			console.log(res);
			setCharactor(res.charactor);
		},
		error : function(){
			alert("useSkill 실패");
		}
	})
}

function endSkill(){
	$.ajax({
		method : 'POST',
		url : contextPath + '/endSkill',
		dataType : 'json',
		data : {
			name : charactor.speciesCode,
			charactorJson : JSON.stringify(charactor),
		},
		success : function(res){
			console.log(res);
			setCharactor(res.charactor);
		},
		error : function(){
			alert("endSkill 실패");
		}
	})
}

function setCharactor(cha) {
	
	charactor = cha;
	document.getElementById('att').innerHTML = cha.att;
	document.getElementById('dff').innerHTML = cha.dff;
	document.getElementById('dex').innerHTML = cha.dex;
	document.getElementById('weapon').innerHTML = cha.weapon;
	document.getElementById('avoidRate').innerHTML = cha.avoidRate;
	document.getElementById('skill').innerHTML = cha.active;
	
}
