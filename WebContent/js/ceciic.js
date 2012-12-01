//////
if (document.all || document.getElementById){ //if IE4 or NS6+
	document.write('<style type="text/css">\n')
	document.write('.dyncontent{display: none; width: 250px; height: 60px;}\n')
	document.write('</style>')
}

var curcontentindex=0
var messages=new Array()

function rotateBanner(){
	window.onload=function(){
		if (document.all || document.getElementById){
			getElementByClass("dyncontent")
			setInterval("rotatecontent()", 2000)
		}
	}
}

function getElementByClass(classname){
	var inc=0
	var alltags=document.all? document.all : document.getElementsByTagName("*")
	for (i=0; i<alltags.length; i++){
	if (alltags[i].className==classname)
	messages[inc++]=alltags[i]
}
}

function rotatecontent(){
	//get current message index (to show it):
	curcontentindex=(curcontentindex<messages.length-1)? curcontentindex+1 : 0
	//get previous message index (to hide it):
	prevcontentindex=(curcontentindex==0)? messages.length-1 : curcontentindex-1
	messages[prevcontentindex].style.display="none" //hide previous message
	messages[curcontentindex].style.display="block" //show current message
}


			