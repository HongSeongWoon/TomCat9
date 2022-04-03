<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberMod</title>
<script src="resources/resource.js"></script>
<script>

function accessOut2(pSeCode, pEmCode){
	
	const form =  makeForm("","EmAccessOut","post");
	const seCode = makeInputElement("hidden","seCode",pSeCode,"");

	const emCode = makeInputElement("hidden","emCode",pEmCode,"");
	form.appendChild(seCode);
	form.appendChild(emCode);
	
	document.body.appendChild(form);
	form.submit();	
	}

function moveService(action){
	const form = makeForm("", action, "post");
	document.body.appendChild(form);
	form.submit();
}

function searchMember(){
	const cuNum=document.getElementById("searchWindow").value;
	const clientData ="cuNum="+document.getElementById("searchWindow").value;
	/*입력제어*/
	if(cuNum.length!=8){		
		alert("회원코드(8자리)를 다시입력해주세요!");
	}
	getAjaxJson("SearchMember", clientData, "addGoods");	
}

function getAjaxJson(action, data, fn) {
	
	const ajax = new XMLHttpRequest();
	
	ajax.onreadystatechange = function() {
	
    				if (ajax.readyState == 4 && ajax.status == 200) {
						window[fn](ajax.responseText);
    					}
    			};
		
    			ajax.open("post", action, true);
    			ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    			ajax.send(data);
}

function addGoods(goodsInfo) {
	
	let jsonData = JSON.parse(goodsInfo);
			
	const list = document.getElementById("mbName");
	list.innerText = jsonData[0].cuName;
}

function modCU() {
	const form = document.getElementsByName("modMember")[0];
	
	/*2. 사용자 데이터의 수집*/
	const userData = [document.getElementsByName("cuName")[0],document.getElementsByName("cuPassword")[0]];
	const message = ["회원이름입력","비밀번호 입력"];
	
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
	position: absolute;
	top: 58%;
	left: 50%;
	border: 2px solid #353535;
	transform: translate(-50%, -50%);
	width: 70%;
	height: 680px;
}

#firstrow {
	height: 250px;
	width: 25%;
	font-size: 32px;
	border: 1px solid #353535;
	text-align: center;
	background-color: #3A4CA8;
	color: #FFFFFF
}

#secondrow {
	height: 50px;
	line-height: 50px;
	width: 25%;
	font-size: 32px;
	text-align: center;
	background-color: #D9D9D9;
	color: #FFFFFF
}

#thirdrow {
	height: 50px;
	line-height: 50px;
	width: 25%;
	font-size: 32px;
	text-align: center;
	background-color: #767171;
	color: #FFFFFF
}

#forthrow {
	width: 25%;
	font-size: 32px;
	text-align: center;
	background-color: #D9D9D9;
	color: #FFFFFF
}

#trapezoid {
	margin-left: 20px;
	float: left;
	border-bottom: 100px solid #BF9000;
	border-left: 30px solid transparent;
	box-shadow: 5px #D9D9D9;
	border-right: 30px solid transparent;
	height: 0;
	width: 100px;
	font-size: 18.5pt;
	text-align: center;
	color: #000000;
	line-height: 100px;
}

#trapezoid:after {
	margin-left: 20px;
	float: left;
	border-bottom: 102px solid #000000;
	border-left: 32px solid transparent;
	border-right: 32px solid transparent;
	height: 0;
	width: 100px;
	font-size: 18.5pt;
	text-align: center;
	color: #000000;
	line-height: 100px;
}

.backtrapezoid {
	margin-left: -20px;
	float: left;
	border-bottom: 100px solid #BF9000;
	border-left: 30px solid transparent;
	border-right: 30px solid transparent;
	height: 0;
	width: 100px;
	font-size: 18.5pt;
	text-align: center;
	color: #000000;
	line-height: 100px;
}

#backtrapezoid1 {
	margin-left: -20px;
	float: left;
	border-bottom: 100px solid #FFC000;
	border-left: 30px solid transparent;
	border-right: 30px solid transparent;
	height: 0;
	width: 100px;
	font-size: 18.5pt;
	text-align: center;
	color: #000000;
	line-height: 100px;
}

#square {
	clear: both;
	margin-left: 20px;
	background-color: #FFC000;
	width: 95%;
	height: 500px;
}

.titlerow1 {
	font-size: 20pt;
	text-align: left;
	background-color: #D9D9D9;
	color: #FFFFFF;
	padding: 10px 80px 10px 40px;
}
.titlerow2 {
	font-size: 20pt;
	text-align: left;
	background-color: #D9D9D9;
	color: #FFFFFF;
	padding: 10px 20px 10px 40px;
}

.crow1 {
	background-color: #FFFFFF;
	color: #000000;
	height: 80px;
	width: 64%;
	font-size:20pt;
}
.crow2 {
	background-color: #FFFFFF;
	color: #000000;
	height: 80px;
	width: 400px;
	
}

#stable {
	position: relative;
	top: 10%;
	left: 5%;
}

#stable2 {
	position: relative;
	top: 20%;
	left: 5%;
}

#searchBtn {
	background-color: #3A4CA8;line-height:100px;	color: #FFFFFF;
	height: 100px;	width: 130px;	border: 0px solid #0BC904;	font-size: 24px;
	text-align: center; position: absolute;	top: 33%;	left: 82%;
	box-shadow : 5px 5px 5px black; transition-duration:0.3s;
	
}
#searchBtn:active {
	background-color: #3A4CA8;line-height:100px;	color: #FFFFFF;
	height: 100px;	width: 130px;	border: 0px solid #0BC904;	font-size: 24px;
	text-align: center; position: absolute;	top: 33%;	left: 82%;
	box-shadow : none; margin-left:5px; margin-top:5px;
}
#modBtn {
	background-color: #3A4CA8;line-height:100px;	color: #FFFFFF;
	height: 100px;	width: 130px;	border: 0px solid #0BC904;	font-size: 24px;
	text-align: center; position: absolute;	top: 67%;	left: 82%;
	box-shadow : 5px 5px 5px black; transition-duration:0.3s;
	
}
#modBtn:active {
	background-color: #3A4CA8;line-height:100px;	color: #FFFFFF;
	height: 100px;	width: 130px;	border: 0px solid #0BC904;	font-size: 24px;
	text-align: center; position: absolute;	top: 67%;	left: 82%;
	box-shadow : none; margin-left:5px; margin-top:5px;
}
#searchWindow {
	background-color: #FFFFFF;
	color: #000000;
	height: 80px;
	width: 390px;
	font-size:20pt;
}
#inputName {
	background-color: #FFFFFF;
	color: #000000;
	height: 80px;
	width: 390px;
	font-size:20pt;
}
#inputPassword{
	background-color: #FFFFFF;
	color: #000000;
	height: 80px;
	width: 390px;
	font-size:20pt;
}
#top	{ width : 100%; height:60px;line-height:60px; padding:0px 5px 0px 0px;
 		  color:#FFFFFF; font-size: 16pt;
 		  text-align:right; }
.outbtn	{width:100px; height:40px; 
 		 background-color:#3A4CA8; border: 2px solid #FFFFFF;
 		 color:#FFFFFF; font-size:13pt; font-weight : bolder;cursor:pointer;}

</style>
<body class="background"><form name ="modMember" action="ModMember" method="post">
	<div id="top">
	${accessInfo[0].seName}(${accessInfo[0].seCode})
	${accessInfo[0].emName}(${accessInfo[0].emCode})
 	최근 접속 일시 : ${accessInfo[0].emDate}
 	<input class="outbtn" type="button" value="로그아웃" onClick="accessOut2('${accessInfo[0].seCode}', '${accessInfo[0].emCode}')" />
 </div>
	<table class="table">
		<tr>
			<td id="firstrow"><img src="person1.png"></td>
			<td rowspan="4">
				<div id="trapezoid" style="position: relative; z-index: 4;" onClick="moveService('MoveInfo')">회원정보</div>
				<div class="backtrapezoid" style="position: relative; z-index: 3;" onClick="moveService('MoveRM')">회원등록</div>
				<div id="backtrapezoid1" style="position: relative; z-index: 2;">회원수정</div>
				<div class="backtrapezoid" style="position: relative; z-index: 1;" onClick="moveService('MoveDel')">회원삭제</div>
				<div id="square">
					<table id="stable">
						<tr>
							<td class="titlerow1">회원번호</td>
							<td>
							<input type="text" id="searchWindow" name="cuNum" placeholder="회원번호"/>
							</td>



						</tr>
						<tr>
							<td class="titlerow1">회원이름</td>
							<td class="crow1" id="mbName"></td>



						</tr>
						

					</table>
					<div id="searchBtn" type="button" onClick="searchMember()">검 색</div>
					
					<table id="stable2">
						<tr>
							<td class="titlerow2">회원이름</td>
							<td >
							<input type="text" id="inputName" name="cuName" placeholder="새로운회원이름"/>
							</td>



						</tr>
						<tr>
							<td class="titlerow2">회원 비밀번호</td>
							<td>
							<input type="password" id="inputPassword" name="cuPassword" placeholder="새로운비밀번호"/>							
							</td>



						</tr>
						

					</table>
					<div id="modBtn" onClick="modCU()">수 정</div>
				</div>

			</td>
		</tr>
		<tr>
			<td id="secondrow" onClick="moveService('MoveEmpList')">매장관리</td>
		</tr>
		<tr>
			<td id="thirdrow">회원관리</td>
		</tr>
		<tr>
			<td id="forthrow"></td>
		</tr>
			</table>
			</form>
</body>