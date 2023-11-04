document.addEventListener("DOMContentLoaded", function(){
    document.getElementById("show-password").addEventListener("click", function() {
      var passwordField = document.getElementById("password");
      if (passwordField.type === "password") {
        passwordField.type = "text";
        this.innerHTML="visibility_off";
      } else {
        passwordField.type = "password";
        this.innerHTML="visibility";
      }
    });
});