$(document).ready(function () {
	    // Use class when you want to do an operation on 
		// lots of things. 
		
	   // Decreases the length of long episode titles 
	   /*
	   if($(".epTitle").text().length > 25) {
		  var text = $(".epTitle").text();
		  $(".epTitle").text(text.substring(0, 25-3) + "...");
		  console.log(text);
	   }*/
	   
	   // You each to loop through individual elements in 
	   // a class. 
	   $(".epTitle").each(function() {
	     var text = $(this).text();
	     if (text.length > 60) {
		    $(this).text(text.substring(0, 60-3) + "...");
		 }
	   });
	   // TODO - Decreases the length of long categories 
	   // this will be awkward
		
		
	   // TODO - And more important  TOGGLE FUNCTIONALITY 
	   // go through each id for this class 
	   
	   // wait, wait wait, I need to have a bar that
	   // goes underneath this, dynamically generated based on 
	   // how many pages there are 
	   
	   // for now, lets just try to get the amount of pages
	   function makePageSelector() {
	      var pages = 0;
	      $(".category_table").each(function() { 
	           pages ++;
	      });
	      console.log(pages);
		  // insert some html into the body,
		  // on the actual page we'll be inserting
		  // to the indent of the content wrapper 
		  
		  // JQuery doesn't like it when you append 
		  // pieces of HTML  at a time. Have to send
		  // One String
		  var htmlString = ""; 
		  //htmlString += "<div id='page_line'>";
		  
		  for(var i = 1; i <= pages; i++) {
			htmlString += "<a   id='"  +i+  "' + class='page_link'>";
			htmlString += (i + "</a>");
		  }
		  
		  //htmlString += "</div>";
		  $("#page_line").append(htmlString);
	  }
	  
	  makePageSelector();
	  // make sure the first page shows first
	  //$("#1.page_link #1").show();
	  $(".category_table").each(function () {
		if ((this).id != '1') {
			$(this).hide();
		}
	  }); 
	  
	  // wait, we're supposed to be hiding 
	  // the tables, not the page links !
	  $(".page_link").click(function () {
		// get the id and show the category table
		// with that id 
		var selectorString;
		var visibleID = (this).id;
		selectorString = "#" + (visibleID) + ".category_table";
		console.log(selectorString);
		$(selectorString).show();
		$(".category_table").each(function () {
			// hide everything else. 
			if ((this).id != visibleID) {
				$(this).hide();
			}
		});
	  });
	  
	  // onclick, do the toggle thing 
	  // execute pageHandler() function 
	  function categoryPageHandler() {
		// take the id of whatever was clicked
		// run show on it
		// hide everything else 
	  }
});
