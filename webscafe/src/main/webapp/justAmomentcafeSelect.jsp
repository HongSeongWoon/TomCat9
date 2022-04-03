<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CAFESELECT</title>
<script >
function sendSeCode(){
	 /*1. HTML 개체와의 연결
	 const form = document.getElementsByName("login")[0];*/
	 /*2. 상점코드 수집*/
	 let seCode = document.getElementsByName("seCode")[0];
	 
	 /*2. 사용자 데이터의 수집
	let seCode = document.getElementsByName("seCode")[0];
	let emCode = document.getElementsByName("emCode")[0];
	let emPassword = document.getElementsByName("emPassword")[0];*/
	/*2. 사용자 데이터의 수집
	const userData = [document.getElementsByName("seCode")[0], document.getElementsByName("emCode")[0], document.getElementsByName("emPassword")[0]];*/
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
.first     {width:99.8%;height: 40px;line-height:40px;border:1px solid #000000;
         text-align:center; background :#D9D9D9;background : #D9D9D9; text-align:center;font-size:20px;color:#FFFFFF;}
.second     {width:99.8%;height: 400px;line-height:80px;border:1px solid #000000;}
#wrap      {position:absolute; top:50%; left:50%; 
         transform: translate(-50%, -50%); width:70%;height: 450px;}
.input      {height: 36px;width:300px;font-size:18px;border:1px solid #3A4CA8}
.selectbtn {background-color: #3A4CA8; color:#FFFFFF; height: 50px;
           line-height:50px;width:100px;clear: both;float: right;
         border:px solid #0BC904; font-size:35px;text-align:center;
            margin-top:20px;  margin-right:20px;
          box-shadow : 5px 5px 5px black; transition-duration:0.3s; 
         }
.selectbtn:active {background-color: #3A4CA8; color:#FFFFFF; height: 50px;
           line-height:50px;width:100px;clear: both;float: right;
         border:px solid #0BC904; font-size:35px;text-align:center;
            margin-top:20px;  margin-right:20px;
           box-shadow : none; margin-left:5px; margin-top:5px;
         }
#selectbtn2 {background-color: #3A4CA8; color:#FFFFFF; height: 40px;
           line-height:40px;width:120px;clear: both;float: right;
         border:0px solid #0BC904; font-size:24px;text-align:center;
         padding-top:10px;}
.text        {text-align:center;width:110%;font-size:35px;color: #000000;
         height:95px; ;}
.td1 {}

</style>
<body class ="background">
<div id="wrap">
   <div class="first">
   <span>예약가능한 스터디 카페 목록</span>
   </div>
   <div class="second">
   
   <table>
   <tr>
   <td class="text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;히야누곤듀 스터디 카페 학익점</td>
   <td class="selectbtn">선택</td>
   </tr>
   <tr>
   <td class="text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;히야누곤듀 스터디 카페 구월점</td> 
      <td class="selectbtn" >선택</td>
   </tr>
   <tr>
   <td class="text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;히야누곤듀 스터디 카페 주안점</td>

   <td class="selectbtn">선택</td>
   </tr>
   <tr>
   <td class="text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               히야누곤듀 스터디 카페 송도점</td>
   <td class="selectbtn">선택</td>
   </tr>
   </table>
   
   </div>
</div>
</body>
</html>