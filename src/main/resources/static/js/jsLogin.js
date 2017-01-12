 $(document).ready(function () {
    $('.forgot-pass').click(function(event) {
      $(".pr-wrap").toggleClass("show-pass-reset");
    }); 
    
    $('.pass-reset-submit').click(function(event) {
      $(".pr-wrap").removeClass("show-pass-reset");
    }); 
});
 
 
 var attempt = 3; 
 
 function validate() {
 	var username = document.getElementById("username").value;
 	var password = document.getElementById("password").value;
 	if (username == "user" && password == "password") {
 		alert("Login SUCCESS");
 		window.location = "home.html";
 		return false;
 	} else {
 		attempt--;
 		alert("You have left " + attempt + " attempt;");
 		if (attempt == 0) {
 			document.getElementById("username").disabled = true;
 			document.getElementById("password").disabled = true;
 			document.getElementById("submit").disabled = true;
 			return false;
 		}
 	}
 }