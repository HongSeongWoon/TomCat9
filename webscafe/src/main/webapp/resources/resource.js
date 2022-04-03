/**
 * Scafe Common Moudule
 */
function isEmpty(obj){
	let check=true;
	if(obj.value==""){
		check=false;
	}
	return check;
}

function accessOut(seCode, cuNum){
alert(seCode);
	location.href = "AccessOut?seCode=" + seCode + "&cuNum=" + cuNum;
	
}

function makeForm(fname, faction, fmethod){
	const form = document.createElement("form");
	form.setAttribute("name", fname);
	form.setAttribute("action", faction);
	form.setAttribute("method", fmethod);
	return form;
}

function makeInputElement(type, name, value, placeholder){
	const input = document.createElement("input");
	input.setAttribute("type", type);
	input.setAttribute("name", name);
	if(value != ""){input.setAttribute("value", value);}
	if(placeholder != ""){input.setAttribute("placeholder", placeholder);}
	
	return input;
}