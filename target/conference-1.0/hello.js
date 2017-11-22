/*
 * http://stackoverflow.com/questions/18260815/use-gapi-client-javascript-to-execute-my-custom-google-api
 * https://developers.google.com/appengine/docs/java/endpoints/consume_js
 * https://developers.google.com/api-client-library/javascript/reference/referencedocs#gapiclientload
 *
 */

/**
 * After the client library has loaded, this init() function is called.
 * The init() function loads the helloworldendpoints API.
 */
// package com.google.training.helloworld;

//<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>

function init() {
	
	// You need to pass the root path when you load your API
	// otherwise calls to execute the API run into a problem
	
	// rootpath will evaulate to either of these, depending on where the app is running:
	// //localhost:8080/_ah/api
	// //your-app-id/_ah/api

	var rootpath = "//" + window.location.host + "/_ah/api";
	
	// Load the helloworldendpoints API
	// If loading completes successfully, call loadCallback function
	gapi.client.load('helloworldendpoints', 'v1', loadCallback, rootpath);
}

/*
 * When helloworldendpoints API has loaded, this callback is called.
 * 
 * We need to wait until the helloworldendpoints API has loaded to
 * enable the actions for the buttons in index.html,
 * because the buttons call functions in the helloworldendpoints API
 */
function loadCallback () {	
	// Enable the button actions
	enableButtons ();
}

function enableButtons () {
	// Set the onclick action for the first button
	//btn = document.getElementById("input_greet_generically");
	//btn.onclick= function(){greetGenerically();};
	
	// Update the button label now that the button is active
	//btn.value="Submit";
	
	// Set the onclick action for the second button
	btn = document.getElementById("input_greet_name");
	btn.onclick= function(){greetByName();};
	
	// Update the button label now that the button is active
	btn.value="Submit";
}

/*
 * Execute a request to the sayHello() endpoints function
 */
function greetGenerically () {
	// Construct the request for the sayHello() function
	var request = gapi.client.helloworldendpoints.sayHello();
	
	// Execute the request.
	// On success, pass the response to sayHelloCallback()
	request.execute(sayHelloCallback);
}

/*
 * Execute a request to the sayHelloByName() endpoints function.
 * Illustrates calling an endpoints function that takes an argument.
 */
function greetByName () {

      var loc;
        var sal;
        var schoolTypes;
        var request;
        request = gapi.client.helloworldendpoints.sayHello();
        loc = document.getElementById("loc").value;
        sal = document.getElementById("sal").value;
        schoolTypes = document.getElementById("schoolTypes").value;

        if(loc == "" || sal == "" || schoolTypes == "")
        {
            alert("Enter an appropriate response in every field!");
        }
         else if(_.contains(['Northeastern', 'Southern', 'Midwestern', 'Western', 'California']) && _.contains(['Engineering', 'Liberal Arts', 'Party', 'Ivy League', 'State'])
                {
                            request = gapi.client.helloworldendpoints.sayHelloByName({'loc': loc, 'sal': sal, 'schoolTypes': schoolTypes});
                }

        else
        {

	  //  request = gapi.client.helloworldendpoints.sayHelloByName({'loc': loc, 'sal': sal, 'schoolTypes': schoolTypes});
	    alert("Please enter all valid options (case sensitive)");
	}


	request.execute(sayHelloCallback);
/*
 var loc;
        var sal;
        var schoolTypes;
        var request;
        loc = document.getElementById("loc").value;
        sal = document.getElementById("sal").value;
        schoolTypes = document.getElementById("schoolTypes").value;
        if(loc == "" || sal == "" || schoolTypes == "")
        {
        alert("Please enter all valid options");
       request = gapi.client.helloworldendpoints.sayHello();
        }
        /*
        loc = loc.toLowerCase():
        schoolTypes = schoolTypes.toLowerCase();
        else if(loc != "northeastern" && loc != "southern" && loc != "midwestern" && loc != "western" && loc != "california")
        {
                    alert("Please enter all valid options");
        }
        else if (schoolTypes != "engineering" && schoolTypes != "liberal arts" && schoolTypes != "party" && schoolTypes != "ivy league" && schoolTypes != "state")
        {

                  alert("Please enter all valid options");
        }

        else
        {
        loc = loc.substring(0, 1).toUpperCase() + loc.substring(1);
        schoolTypes = schoolTypes.substring(0, 1).toUpperCase() + schoolTypes.substring(1);
	    request = gapi.client.helloworldendpoints.sayHelloByName({'loc': loc, 'sal': sal, 'schoolTypes': schoolTypes});
	}


	request.execute(sayHelloCallback);
	*/
}





// Process the JSON response
// In this case, just show an alert dialog box
// displaying the value of the message field in the response
function sayHelloCallback (response) {

 //var new_tbody = document.createElement('wheel');
 // populate_with_new_rows(new_tbody);
 // old_tbody.parentNode.replaceChild(new_tbody, table);

   var table = document.getElementById("wheel");

   for(i = 0; i < response.message.length; i++)
   {
        var row = table.insertRow(0);
        var cell = row.insertCell(0);
        cell.innerHTML = response["message"][i];
   }

	//alert(response.message);
}
/*

//window.alert(response.message);
     var table = document.getElementById("wheel");
     document.getElementById("wheels").style.display = 'none';
      if(response.message.length == 0)
      {
      response.message = "No results";
      var row = table.insertRow(0);
      var cell = row.insertCell(0);
      cell.innerHTML = response.message;
      }
     for(i = 0; i < response.message.length; i++)
     {
          var row = table.insertRow(0);
          var cell = row.insertCell(0);
          cell.innerHTML = response["message"][i];
     }

  	//alert(response.message);
  }
  */
/*
function showOne(id) {
    $('.hide').not('#' + id).hide();
}

*/

