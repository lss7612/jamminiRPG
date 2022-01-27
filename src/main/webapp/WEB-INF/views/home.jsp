<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>잼민이알피지</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
	let contextPath = `${pageContext.request.contextPath}`;
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/home.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/gameConnector.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
</head>
<body>

	<div class="wrapper">
		<div class="left">
			<div class="monster_area area top">
				<div class="monster" onclick="attack()">
					<div id="monster_img">
					</div>
					<div class="hpmp">
						<div class="hp_area">
							hp
							<progress id="monster_hp" class="hp" low="20" hight="80"></progress>
						</div>
					</div>
					attack cooltime<br>
					<progress id="attack_cooltime"></progress>
				</div>
				<button id="createMon" onclick="makeNewCharactor('MONSTER')" style="display:none;">새 몬스터 생성</button>
			</div>
			<div class="charactor_area area bottom">
				<div class="charactor">
					<div id="img"></div>
					<div class="skill_hp_mp">
						<div class="hpmp">
							<div class="hp_area">
								hp
								<progress id="hp" class="hp" low="20" high="80">hp</progress>
							</div>
							<div class="mp_area">
								mp
								<progress id="mp" class="mp" low="20" high="80">mp</progress>
							</div>
						</div>
						<div class="skill_area">
						</div>
						<div class="weapon_area">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="right">
			<div class="log_area area top">
			</div>
			<div class="stat_area area bottom">
				<div class="stat">
					레벨 : <span id="level"></span>
				</div>
				<div class="stat">
					공격력 : <span id="att"></span>
				</div>
				<div class="stat">
					민첩성 : <span id="dex"></span>
				</div>
				<div class="stat">
					방어력 : <span id="dff"></span>
				</div>
				<div class="stat">
					회피율 : <span id="avoidRate"></span>
				</div>
				<div class="stat">
					무기 : <span id="weapon"></span>	
				</div>
				<div class="stat">
					스킬 : <span id="skill"></span>
					<progress id="skillCnt" style="display:none;"></progress>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>