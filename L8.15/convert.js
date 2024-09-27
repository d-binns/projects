window.addEventListener("DOMContentLoaded", domLoaded);



function domLoaded() {
   // TODO: Complete the function
   
	let convert = document.getElementById("convertButton");
	convert.addEventListener("click", clickHandler);
	
	let f = document.getElementById("fInput");//.value;
	f.addEventListener("input", fChangeHandler);
	
	let c = document.getElementById("cInput");//.value;
	c.addEventListener("input", cChangeHandler);
}

function fChangeHandler(event){
	document.getElementById("cInput").value = "";
	
}

function cChangeHandler(event){
	document.getElementById("fInput").value = "";
	
}

	
	
function clickHandler(event){
	//window.alert("This Worked LETS GOOO!!!!!");
	let f = document.getElementById("fInput").value;
	let c = document.getElementById("cInput").value;
		
	
	
	
	let convert = document.getElementById("convertButton");
	if ((f.length >0) && (c.length ==0)) { 
		convert.addEventListener("click", convertFtoC);	
	}
	if ((f.length == 0) && (c.length >0)){
		convert.addEventListener("click", convertCtoF);
	}

		

}
	

function convertCtoF(degreesCelsius) {
   // TODO: Complete the function


	
	if (isNaN(degreesCelsius)){
		document.getElementById("errorMessage").innerHTML = degreesCelsius + " is not a number";
		event.preventDefault();
		document.getElementById("fInput").value = "";
	}
	
	else{
		let c = degreesCelsius;
		let f = (c * (9/5) + 32);

	
		if (f < 32){
			document.getElementById("weatherImage").src = "cold.png";
		
		}
		else if ((f >= 32) && (f <= 50)){
			document.getElementById("weatherImage").src = "cool.png";
		}
		
		else {
			document.getElementById("weatherImage").src = "warm.png";
		}
		
		document.getElementById("errorMessage").innerHTML = "";
		document.getElementById("fInput").value = f;
		
		(c);
	}

}

function convertFtoC(degreesFahrenheit) {
   // TODO: Complete the function


	
	if (isNaN(degreesFahrenheit)){
		document.getElementById("errorMessage").innerHTML = degreesFahrenheit + " is not a number";
		event.preventDefault();
		document.getElementById("cInput").value = "";
	}
	else {
		let f = degreesFahrenheit;
		let c = ((f - 32) * (5/9));


   
		if (f < 32){
			document.getElementById("weatherImage").src = "cold.png";
		
		}
		else if ((f >= 32) && (f <= 50)){
			document.getElementById("weatherImage").src = "cool.png";
		}
		else {
			document.getElementById("weatherImage").src = "warm.png";
		}
		
		document.getElementById("errorMessage").innerHTML = "";
		document.getElementById("cInput").value = c;

		console.log();
	}
	
	
}
