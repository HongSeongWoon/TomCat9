<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EmployList</title>

<link rel="stylesheet" type="text/css" href="resources/common.css" />
<script src="resources/resource.js"></script>
<script>
	
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
.table    {position:absolute; top:58%; left:20%; border: 2px solid #353535;
			transform: translate(-50%, -50%); width:10%;height:680px;}
#firstrow	  {height:250px;width:25%;font-size:32px;border: 1px solid #353535;
				text-align:center;background-color: #3A4CA8;color:#FFFFFF}
#secondrow	  {height:50px;line-height:50px;width:25%;font-size:32px;
				text-align:center;background-color: #767171;color:#FFFFFF}
#thirdrow	  {height:50px;line-height:50px;width:25%;font-size:32px;
				text-align:center;background-color: #D9D9D9;color:#FFFFFF}
#forthrow	  {width:25%;font-size:32px;
				text-align:center;background-color: #D9D9D9;color:#FFFFFF}
#trapezoid { position:absolute; top:24%; left:28%;
	  margin-left:20px;
      border-bottom: 100px solid #FFC000;
      border-left: 30px solid transparent;
      border-right: 30px solid transparent;
      height: 0;
      width: 100px;font-size:14pt;
      text-align:center;color:#FFFFFF;line-height:100px;
    }
#square  {position:absolute; top:35%; left:28%;
			margin-left:20px;background-color: #FFC000;width:60%;height:500px;}
.titlerow	  {font-size:20pt;text-align:center;background-color: #D9D9D9;color:#FFFFFF;
			   padding:10px 20px 10px 20px;
				}
.crow		  {background-color: #FFFFFF;color:#000000;height:70px;}
#stable {position:relative; top:15%; left:3%; }
.selectbtn {background-color: #3A4CA8; color:#FFFFFF; height: 40px;
	        line-height:40px;
			border:0px solid #0BC904; font-size:24px;text-align:center;
			}

</style>
<body class="background" >
<table class ="table">
	<tr>
	<td id = "firstrow">
	<img src= "person1.png">
	</td>
	</td>
	</tr>
	<tr>
	<td id = "secondrow">????????????</td>
	</tr>
	<tr>
	<td id = "thirdrow" onClick="moveService('MoveInfo')">????????????</td>
	</tr>
	<tr>
	<td id = "forthrow"></td>
	</tr>
</table>
<div id ="trapezoid" onClick = "getList('EmpList')">???????????????</div>
		<div style=overflow:auto; id = "square" > ${list}</div>
		
</body>
</html>