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
			<div class="monster_area area top"></div>
			<div class="charactor_area area bottom">
				<div class="charactor">
					<div id="img" class="oak_img"></div>
					<div class="skill_hp_mp">
						<div class="hpmp">
							<div id="hpMax">
								<div id="hp"></div>
							</div>
							<div id="mpMax">
								<div id="mp"></div>
							</div>
						</div>
						<div class="skill_area">
							<div class="skill" id="skill1"></div>
							<div class="skill" id="skill2"></div>
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
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>