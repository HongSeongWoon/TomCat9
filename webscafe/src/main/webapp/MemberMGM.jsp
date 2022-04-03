<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Start</title>
<link rel="stylesheet" type="text/css" href="resources/common.css" />
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
	
	function getList(action) {
		const form = makeForm("", action, "post");
		document.body.appendChild(form);
		form.submit();
	}
	
	function moveService(action){
		const form = makeForm("", action, "post");
		document.body.appendChild(form);
		form.submit();
	}
</script>
</head>
<style>
.background{
    height: 100vh;
    background-image:url('image001.png');
    background-repeat : no-repeat;
    background-size : cover;

}
.table   {position:absolute; top:58%; left:20%; border: 2px solid #353535;
			transform: translate(-50%, -50%); width:10%;height:680px;}
#firstrow	  {height:250px;width:25%;font-size:32px;border: 1px solid #353535;
				text-align:center;background-color: #3A4CA8;color:#FFFFFF}
#secondrow	  {height:50px;line-height:50px;width:25%;font-size:32px;
				text-align:center;background-color: #D9D9D9;color:#FFFFFF}
#thirdrow	  {height:50px;line-height:50px;width:25%;font-size:32px;
				text-align:center;background-color: #767171;color:#FFFFFF}
#forthrow	  {width:25%;font-size:32px;
				text-align:center;background-color: #D9D9D9;color:#FFFFFF}
#trapezoid    
     {position:absolute; top:17%; left:27.8%;
      margin-left:20px;float:left;
      border-bottom: 100px solid #FFC000;
      border-left: 30px solid transparent;box-shadow: 5px #D9D9D9;
      border-right: 30px solid transparent;
      height: 0;
      width: 100px;font-size:18.5pt;
      text-align:center;color:#000000;line-height:100px;
    }

    
.backtrapezoid {position:absolute; top:17%; left:27.5%;
	  margin-left:-20px;float:left;
      border-bottom: 100px solid #BF9000;
      border-left: 30px solid transparent;
      border-right: 30px solid transparent;
      height: 0;
      width: 100px;font-size:18.5pt;
      text-align:center;color:#000000;line-height:100px;
    }
#square   {position:absolute; top:35%; left:28%;
			margin-left:20px;background-color: #FFC000;width:60%;height:500px;}
.titlerow	  {font-size:20pt;text-align:center;background-color: #D9D9D9;color:#FFFFFF;
			   padding:10px 40px 10px 40px;
				}
.crow		  {background-color: #FFFFFF;color:#000000;height:70px;}
#stable {position:relative; top:15%; left:5%; }
#top	{ width : 100%; height:60px;line-height:60px; padding:0px 5px 0px 0px;
 		  color:#FFFFFF; font-size: 16pt;
 		  text-align:right; }
.outbtn	{width:100px; height:40px; 
 		 background-color:#3A4CA8; border: 2px solid #FFFFFF;
 		 color:#FFFFFF; font-size:13pt; font-weight : bolder;cursor:pointer;}
</style>
<body class="background">
<div id="top">
	${accessInfo[0].seName}(${accessInfo[0].seCode})
	${accessInfo[0].emName}(${accessInfo[0].emCode})
 	최근 접속 일시 : ${accessInfo[0].emDate}
 	<input class="outbtn" type="button" value="로그아웃" onClick="accessOut2('${accessInfo[0].seCode}', '${accessInfo[0].emCode}')" />
 </div>
<table class ="table">
	<tr>
	<td id = "firstrow">
	<img src= "person1.png">
	</td>
	
		
	
	</tr>
	<tr>
	<td id = "secondrow" onClick="moveService('MoveEmpList')">매장관리</td>
	</tr>
	<tr>
	<td id = "thirdrow">회원관리</td>
	</tr>
	<tr>
	<td id = "forthrow"></td>
	</tr>
</table>
<div id ="trapezoid" style="position: relative; z-index: 4;" onClick = "getList('MlAccessInfo')">회원정보</div>
		<div class ="backtrapezoid" style="position: relative; z-index: 3;" onClick="moveService('MoveRM')">회원등록</div>
		<div class ="backtrapezoid" style="position: relative; z-index: 2;" onClick="moveService('MoveMod')">회원수정</div>
		<div class ="backtrapezoid" style="position: relative; z-index: 1;" onClick="moveService('MoveDel')">회원삭제</div>
		<div style=overflow:auto; id = "square" > ${memberlist}</div>
</body>
</html>