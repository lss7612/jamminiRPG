let charactor = null;
let monster = null;
let speciesCode = null;
let skillInterval = null;
let skillTimeout = null;
let skillCnt = 0;
let attackCnt = 0;
let attackInterval;

function makeNewCharactor(name){
	$.ajax({
		method : 'GET',
		async : false,
		url : contextPath + '/getCharactor',
		dataType : 'json',
		data : {name : name},
		success : function(res){
			console.debug(res);
			logInterceptor(res);
			if(name!="MONSTER") {
				setCharactor(res.result);
			} else {
				setMonster(res.result);
			}
			
		},
		error : function(){
			alert("makeNewCharactor 실패");
		}
	})
}

function setWeapon(weapon) {
	let obj = {weapon : weapon, charactorJson : JSON.stringify(charactor), name : charactor.speciesCode};
	console.debug('obj',obj);
	$.ajax({
		method : 'POST',
		async : false,
		url : contextPath + '/setWeapon',
		dataType : 'json',
		data : obj,
		success : function(res){
			console.debug(res);
			logInterceptor(res);
			setCharactor(res.charactor);
//			setMonster(res.monster);
		},
		error : function(){
			alert("setWeapon 실패");
		}
	})
}

function attack() {
	if(attackCnt > 0) {
		logInterceptor({
			logList : ['아직 공격하지 못합니다.']
		});
		return;
	}
	$.ajax({
		method : 'POST',
		async : false,
		url : contextPath + '/attack',
		dataType : 'json',
		data : {
			name : charactor.speciesCode,
			charactorJson : JSON.stringify(charactor),
			monsterJson : JSON.stringify(monster),
		},
		success : function(res){
			console.debug(res);
			logInterceptor(res);
			setCharactor(res.charactor);
			console.log('dexNm',res.dexNm,'dex',dex);
			setMonster(res.monster);
			let att_cool = document.getElementById('attack_cooltime');
			attackCnt = att_cool.max = 3 / res.charactor.dex;
			
			attackInterval = setInterval(() => {
				attackCnt -= 0.1;
				att_cool.value = attackCnt;
				if(attackCnt <= 0) clearInterval(attackInterval);
			}, 100);
			
		},
		error : function(){
			alert("attack 실패");
		}
	})
}

function useSkill(skill, duration, level){
	console.log(level);
	if(charactor.level < level){
		logInterceptor({logList : [`해당 스킬을 ${level}부터 사용 가능합니다.`]});
		return;
	}
	$.ajax({
		method : 'POST',
		async : false,
		url : contextPath + '/useSkill',
		dataType : 'json',
		data : {
			name : charactor.speciesCode,
			charactorJson : JSON.stringify(charactor),
			skill : skill
		},
		success : function(res){
			console.debug(res);
			logInterceptor(res);
			setCharactor(res.charactor);
			clearTimeout(skillTimeout);
			skillTimeout = setTimeout(() => {
				endSkill();
			}, duration * 1000);
			skillCnt = duration;
			
			let skillCntDom = document.getElementById('skillCnt');
			skillCntDom.max = duration;
			clearInterval(skillInterval);
			skillInterval = setInterval(() => {
				skillCnt -= 0.1;
				setSkillCnt(skillCntDom);
			}, 100);
		},
		error : function(){
			alert("useSkill 실패");
		}
	})
}

function setSkillCnt(dom) {
	if(skillCnt <= 0) {
		clearInterval(skillInterval);
		dom.style.display="none";
	} else {
		dom.style.display="block";
	}
	dom.value=skillCnt;
}

function endSkill(){
	$.ajax({
		method : 'POST',
		async : false,
		url : contextPath + '/endSkill',
		dataType : 'json',
		data : {
			name : charactor.speciesCode,
			charactorJson : JSON.stringify(charactor),
		},
		success : function(res){
			console.debug(res);
			logInterceptor(res);
			setCharactor(res.charactor);
		},
		error : function(){
			alert("endSkill 실패");
		}
	})
}

function getSpeciesWeapon(){
	$.ajax({
		method : 'GET',
		async : false,
		url : contextPath + '/getWeapons',
		dataType : 'json',
		data : {
			name : speciesCode,
		},
		success : function(res){
			console.debug(res);
			logInterceptor(res);
			let dom = document.getElementsByClassName('weapon_area')[0]
			dom.innerHTML='weapon';
			res.weapons.forEach(item => {
				dom.innerHTML += `<span class="clickable skill" onclick="setWeapon('${item}')">${item}</span>`
			})
		},
		error : function(){
			alert("endSkill 실패");
		}
	})
}

function getSpeciesSkill(){
	$.ajax({
		method : 'GET',
		async : false,
		url : contextPath + '/getSkills',
		dataType : 'json',
		data : {
			name : speciesCode,
			level : charactor.level
		},
		success : function(res){
			console.debug(res);
			logInterceptor(res);
			let dom = document.getElementsByClassName('skill_area')[0];
			dom.innerHTML='skill';
			res.skills.forEach((item, idx) => {
				dom.innerHTML += `<span class="clickable skill" onclick="useSkill('${item}',${res.durations[idx]},${res.levels[idx]})">${item}</span>`
			})
			
		},
		error : function(){
			alert("getSpeciesSkill 실패");
		}
	})
}

function setMonster(mon) {
	monster = mon;
	document.getElementById('monster_hp').max = mon.maxHp;
	document.getElementById('monster_hp').value = mon.hp;
	document.getElementById('monster_img').setAttribute('class','MONSTER');
	document.getElementById('createMon').style.display="none";
	if(mon.died) {
		document.getElementById('createMon').style.display="block";
		document.getElementById('monster_img').setAttribute('class','DIED_MONSTER');
	}
}


function setCharactor(cha) {
	
	charactor = cha;
	document.getElementById('hp').max=cha.maxHp;
	document.getElementById('hp').value=cha.hp;
	document.getElementById('mp').max=cha.maxMp;
	document.getElementById('mp').value=cha.mp;
	document.getElementById('level').innerHTML = cha.level.toString().slice(0,5);
	document.getElementById('att').innerHTML = cha.att.toString().slice(0,5);
	document.getElementById('dff').innerHTML = cha.dff.toString().slice(0,5);
	document.getElementById('dex').innerHTML = cha.dex.toString().slice(0,5);
	document.getElementById('avoidRate').innerHTML = cha.avoidRate.toString().slice(0,5);
	document.getElementById('weapon').innerHTML = cha.weapon == 'NULL' ? '없음' : cha.weapon;
	document.getElementById('skill').innerHTML = cha.active == 'NULL' ? '없음' : cha.active;
	
}


function logInterceptor(obj){
	if(obj.logList == undefined) return;
	
	let logs = obj.logList;
	console.debug('logs',logs);
	let dom = document.getElementsByClassName('log_area')[0];
	console.debug(dom);
	logs.forEach(log => {
		dom.innerHTML += `<div class="log">${log}<div>`;
	})
	dom.innerHTML += '<hr>'
	dom.scrollTop = dom.scrollHeight;
}













