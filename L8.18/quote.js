window.addEventListener("DOMContentLoaded", function () {
   document.querySelector("#fetchQuotesBtn").addEventListener("click", function () {

      // Get values from drop-downs
      const topicDropdown = document.querySelector("#topicSelection");
      const selectedTopic = topicDropdown.options[topicDropdown.selectedIndex].value;
      const countDropdown = document.querySelector("#countSelection");
      const selectedCount = countDropdown.options[countDropdown.selectedIndex].value;
   
      // Get and display quotes
      fetchQuotes(selectedTopic, selectedCount);	   
   });
});

function fetchQuotes(topic, count) {
   // TODO: Modify to use XMLHttpRequest
   
   let xhr = new XMLHttpRequest();
   xhr.addEventListener("load", responseReceivedHandler);
   xhr.responseType = "json";
   xhr.open("GET", "https://wp.zybooks.com/quotes.php?topic=" + topic +"&" +"count=" + count); // love&count=3");
   
		
    //let title = document.getElementById("quotes");
	//let queryString = "quote" + encodeURIComponent(title.value);
	//xhr.open("GET", "lookup.php?" + queryString);
	xhr. send();
	
	

	

   let html = "<ol>";
   for (let c = 1; c <= count; c++) {
      html += `<li>Quote ${c} - Anonymous</li>`;
   }
   html += "</ol>";

   document.querySelector("#quotes").innerHTML = html;
  //window.alert(html);
}

// TODO: Add responseReceivedHandler() 
function responseReceivedHandler(){
	let quoteList = document.querySelector("#quotes").innerHTML
	const topicDropdown = document.querySelector("#topicSelection");
    const selectedTopic = topicDropdown.options[topicDropdown.selectedIndex].value;
	if (this.status === 200){
		let html = "<ol>";
		let quoteResponse = this.response;
		document.querySelector("#quotes").innerHTML = "<li>"+ this.response.quote[1] + "</li>";
		//window.alert(quote);
	}
	else {
		document.querySelector("#quotes").innerHTML = "Topic '" + topicDropdown.options[topicDropdown.selectedIndex].value   + "' not found";
	}
}