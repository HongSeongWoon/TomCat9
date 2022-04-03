<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="resources/common.css" />
<title>EmployREG</title>
<script src="resources/resource.js"></script>
<script>
function getEmpCode(action){
	const form = makeForm("", action, "post");
	document.body.appendChild(form);
	form.submit();
}
	
function regEmploy() {
	const form = document.getElementsByName("regEmp")[0];
	
	/*2. 사용자 데이터의 수집*/
	const userData = [document.getElementsByName("seCode")[0], document.getElementsByName("emCode")[0], document.getElementsByName("emPassword")[0]];
	const message = ["매장코드 입력", "직원이름 입력", "패스워드 입력"];
	
	/*3. 유효성 검사*/	
	for(let index=0; index<userData.length; index++){
		if(!isEmpty(userData[index])){
			alert(message[index]);
			return;
		}
		
		
		
	}
	
	form.submit();	
}

function isEmpty(obj){
	let check=true;
	if(obj.value==""){
		check=false;
	}
	return check;
}
	
</script>
</head>
<style>
.background {
	height: 100vh;
	background-image: url('image001.png');
	background-repeat: no-repeat;
	background-size: cover;
}

.table {
	position: relative;
	top: 58%;
	left: 50%;
	border: 2px solid #353535;
	transform: translate(-50%, -50%);
	width: 70%;
	height: 680px;
}


.titlerow {
	font-size: 20pt;
	text-align: left;
	background-color: #D9D9D9;
	color: #FFFFFF;
	padding: 5px 40px 5px 40px;
}

.crow {border:1px solid #000000;
	background-color: #FFFFFF;
	color: #000000;
	height: 40px;
	width: 250px;
	
}

#stable { border:1px solid #000000;
	position:absolute; top:50%; left:50%; 
         transform: translate(-50%, -50%);
}

#cbtn {
	background-color: #3A4CA8;line-height:70px;	color: #FFFFFF;
	height: 70px;	width: 130px;	border: 0px solid #0BC904;	font-size: 24px;
	text-align: center; position:absolute; top:65%; left:48%; 
         transform: translate(-15%, -30%);
         box-shadow : 5px 5px 5px black; transition-duration:0.3s;
}
#cbtn:active {
	background-color: #3A4CA8;line-height:70px;	color: #FFFFFF;
	height: 70px;	width: 130px;	border: 0px solid #0BC904;	font-size: 24px;
	text-align: center; position:absolute; top:65%; left:48%; transform: translate(-15%, -30%);
	box-shadow : none; margin-left:5px; margin-top:5px;
}

#regbtn {
	background-color: #3A4CA8;line-height:70px;	color: #FFFFFF;
	height: 70px;	width: 130px;	border: 0px solid #0BC904;	font-size: 24px;
	text-align: center; position:absolute; top:65%; left:57%; 
         transform: translate(-15%, -30%);
         box-shadow : 5px 5px 5px black; transition-duration:0.3s;
}
#regbtn:active {
	background-color: #3A4CA8;line-height:70px;	color: #FFFFFF;
	height: 70px;	width: 130px;	border: 0px solid #0BC904;	font-size: 24px;
	text-align: center; position:absolute; top:65%; left:57%; transform: translate(-15%, -30%);
	box-shadow : none; margin-left:5px; margin-top:5px;
}
#square {
	line-height:70px;	color: #000000;
	height: 70px;	width:250px;		font-size: 34pt;
	text-align: center; position:absolute; top:35%; left:45%; 
         transform: translate(-15%, -30%);
}
</style>
<body class="background" ><form name ="regEmp" action="RegEmp" method="post">
		<div >
			<div id="square">관리자등록</div>
					<table id="stable">
						<tr>
							<td class="titlerow">매장코드</td>
							<td ><input class="crow" type="text" name="seCode" placeholder="STORE CODE"/></td>



						</tr>
						<tr>
							<td class="titlerow">관리자코드</td>
							<td ><input class="crow" type="text" name="emCode" value="${EmpCode }"/></td>



						</tr>
						<tr>
							<td class="titlerow">관리자이름</td>
							<td ><input class="crow" type="text" name="emName" placeholder="EMPLOY NAME"/></td>



						</tr>
						<tr>
							<td class="titlerow">관리자비밀번호</td>
							<td ><input class="crow"  type="password" name="emPassword" placeholder="EMPLOY PASSWORD"/></td>



						</tr>


					</table>
					<div id="cbtn" onClick="getEmpCode('GetEmpCode')">EmpCODE</div>
					<div id="regbtn" onClick="regEmploy()">등록</div>
				</div>

	
</form>
</body>