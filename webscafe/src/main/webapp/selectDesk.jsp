<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>SelectDesk</title>
<script src="resources/resource.js"></script>

</head>
<style>
.background{
    height: 100vh;
    background-image:url('image002.png');
    background-repeat : no-repeat;
    background-size : cover;
}
#wrap {
	position:absolute; top:50%; left:50%; 
			transform: translate(-50%, -50%);
}

/*background-color: #70AD47; background-color: #ED7D31;*/
.desk {position:absolute; top:50%; left:50%; 
			transform: translate(-50%, -50%);
	float:left ;height: 150px;width:150px;line-height:150px;
	border:1px solid #000000;font-size:20pt;
	text-align:center;
}
.btn{position:absolute; top:50%; left:50%; 
			transform: translate(-50%, -50%);
	float:left ;height: 150px;width:150px;line-height:150px;
	border:1px solid #000000;font-size:20pt;
	text-align:center;}

#button1 {position:absolute; top:50%; left:34.25%; 
			transform: translate(-50%, -50%);
	float:left ;height: 150px;width:150px;line-height:150px;
	border:1px solid #000000;font-size:20pt;
	text-align:center;}
#button2 {position:absolute; top:50%; left:42.15%; 
			transform: translate(-50%, -50%);
	float:left ;height: 150px;width:150px;line-height:150px;
	border:1px solid #000000;font-size:20pt;
	text-align:center;}
#button3 {position:absolute; top:50%; left:50%; 
			transform: translate(-50%, -50%);
	float:left ;height: 150px;width:150px;line-height:150px;
	border:1px solid #000000;font-size:20pt;
	text-align:center;}
#button4 {position:absolute; top:50%; left:57.9%; 
			transform: translate(-50%, -50%);
	float:left ;height: 150px;width:150px;line-height:150px;
	border:1px solid #000000;font-size:20pt;
	text-align:center;}
#button5 {position:absolute; top:50%; left:65.8%; 
			transform: translate(-50%, -50%);
	float:left ;height: 150px;width:150px;line-height:150px;
	border:1px solid #000000;font-size:20pt;
	text-align:center;}
 #top	{ width : 100%; height:60px;line-height:60px; padding:0px 5px 0px 0px;
 		  color:#FFFFFF; font-size: 16pt;
 		  text-align:right; }
.outbtn	{width:100px; height:40px; 
 		 background-color:#3A4CA8; border: 2px solid #FFFFFF;
 		 color:#FFFFFF; font-size:13pt; font-weight : bolder;cursor:pointer;}


</style>
<body class="background"><script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<div id="top">
	${accessInfo[0].seName}(${accessInfo[0].seCode})
	${accessInfo[0].cuName}(${accessInfo[0].cuNum})
 	최근 접속 일시 : ${accessInfo[0].date}
 	<input class="outbtn" type="button" value="로그아웃" onClick="accessOut1('${accessInfo[0].seCode}', '${accessInfo[0].cuNum}')" />
 </div>

<input type="button" id="button1" value = "D01" style= "background-color:#70AD47" onclick="setColor('button1','D01','${accessInfo[0].seCode}', '${accessInfo[0].cuNum}','button2','button3','button4','button5')")";/>
<input type="button" id="button2" value = "D02" style= "background-color:#70AD47" onclick="setColor('button2','D02','${accessInfo[0].seCode}', '${accessInfo[0].cuNum}','button1','button3','button4','button5')")";/>
<input type="button" id="button3" value = "D03" style= "background-color:#70AD47" onclick="setColor('button3','D03','${accessInfo[0].seCode}', '${accessInfo[0].cuNum}','button1','button2','button4','button5')")";/>
<input type="button" id="button4" value = "D04" style= "background-color:#70AD47" onclick="setColor('button4','D04','${accessInfo[0].seCode}', '${accessInfo[0].cuNum}','button1','button2','button3','button5')")";/>
<input type="button" id="button5" value = "D05" style= "background-color:#70AD47" onclick="setColor('button5','D05','${accessInfo[0].seCode}', '${accessInfo[0].cuNum}','button1','button2','button3','button4')")";/>


<script>

function accessOut1(pSeCode, pCuNum){
	
	const form =  makeForm("","AccessOut","post");
	const seCode = makeInputElement("hidden","seCode",pSeCode,"");

	const cuNum = makeInputElement("hidden","cuNum",pCuNum,"");
	form.appendChild(seCode);
	form.appendChild(cuNum);
	
	document.body.appendChild(form);
	form.submit();	
	}

var count = 1;

function setColor(btn,dkCode,seCode,cuNum,btnId1,btnId2,btnId3,btnId4) {
    var property = document.getElementById(btn);
    btn1Disabled(btnId1);
    btn1Disabled(btnId2);
    btn1Disabled(btnId3);
    btn1Disabled(btnId4);
    if (count == 2) {
        property.style.backgroundColor = "#70AD47"
        count ++; 
        
        let clientData="";
        clientData = "dkCode="+dkCode+ "&seCode="+seCode+"&cuNum="+cuNum;
        getAjaxJson("CancelDesk", clientData, "message");
        
    }else if(count == 1){
    	 property.style.backgroundColor = "#ED7D31"
    	        count ++;

    	 
    	        let clientData="";
    	        clientData = "dkCode="+dkCode+ "&seCode="+seCode+"&cuNum="+cuNum;
    	        getAjaxJson("ReserveDesk", clientData, "message");
    }
    else {
    	property.style.backgroundColor = "#70AD47"
        
    		Swal.fire({
    			  icon: '1dls',
    			  title: '다시로그인 해주세요.',
    			  text: '재로그인후 예약가능합니다.',
    			  footer: '관리자에게 문의해주세요.'
    			})
        
       
    }
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

function message(goodsInfo) {
	
	let jsonData = JSON.parse(goodsInfo);
	let msg = jsonData.split('&');
	var msg2 = msg[0]+"\n"+msg[1]+"\n"+msg[2]+"\n"+msg[3];
	var msg3 = msg[4];

	//alert(msg2);	
	/**/
	if (count == 2) {
	
		Swal.fire({
    		title: msg3,
    		text: msg2,
    		imageUrl: 'StrawberryPrincessSuccess.png',
    		imageWidth: 600,
    		imageHeight: 250,
    		imageAlt: 'Custom image',
    	});
        
    }else {
    	Swal.fire({
			title: msg3,
			text: msg2,
			imageUrl: 'StrawberryPrincessCancel.png',
			imageWidth: 600,
			imageHeight: 250,
			imageAlt: 'Custom image',
		});
    	
    }
	


}

function btn1Disabled(btn)  {
	  const target = document.getElementById(btn);
	  target.disabled = true;
	}


</script>
</body>
</html>