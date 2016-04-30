<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" />
    <title>Login</title>
    <meta name="author" content="dearlhd" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Pages/css/login.css" />
    <script src="<%=request.getContextPath()%>/Pages/js/jquery-2.2.3.min.js"> </script>
    <script src="<%=request.getContextPath()%>/Pages/js/sweet-alert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Pages/css/sweet-alert.css">

</head>
<body>
<div class="container">
    <header>
        <span><br/></span>

    </header>
    <section>
        <div id="container_demo" >
            <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
            <a class="hiddenanchor" id="toregister"></a>
            <a class="hiddenanchor" id="tologin"></a>
            <div id="wrapper">
                <div id="login" class="animate form">
                    <form  action="loginAction" autocomplete="on">
                        <h1>Log in</h1>
                        <input type="hidden" name="actions" value="login">
                        <p>
                            <label for="username" class="uname" data-icon="u" > Your email or username </label>
                            <input id="username" name="user.username" required="required" type="text" placeholder="myusername or mymail@mail.com"/>
                        </p>
                        <p>
                            <label for="password" class="youpasswd" data-icon="p"> Your password </label>
                            <input id="password" name="user.password" required="required" type="password" placeholder="eg. X8df!90EO" />
                        </p>
                        <p class="login button">
                            <input type="button" onclick="login()" value="Login" />
                        </p>
                        <p class="change_link">
                            Not a member yet ?
                            <a href="#toregister" class="to_register">Join us</a>
                        </p>
                    </form>
                </div>

                <div id="register" class="animate form">
                    <form  action="registerAction" autocomplete="on">
                        <h1> Sign up </h1>
                        <p>
                            <label for="usernamesignup" class="uname" data-icon="u">Your username</label>
                            <input id="usernamesignup" name="usernamesignup" required="required" type="text" placeholder="mysuperusername690" />
                        </p>
                        <p>
                            <label for="passwordsignup" class="youpasswd" data-icon="p">Your password </label>
                            <input id="passwordsignup" name="passwordsignup" required="required" type="password" placeholder="eg. X8df!90EO"/>
                        </p>
                        <p>
                            <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Please confirm your password </label>
                            <input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required" type="password" placeholder="eg. X8df!90EO"/>
                        </p>
                        <p>
                            <label for="agesignup" class="youage" data-icon="n">Your age </label>
                            <input id="agesignup" name="agesignup" required="required" type="text" placeholder="eg. 20"/>
                        </p>
                        <p>
                            <label for="emailsignup" class="youemail" data-icon="e">Your email </label>
                            <input id="emailsignup" name="emailsignup" required="required" type="text" placeholder="eg. abc@123.com"/>
                        </p>
                        <p class="signin button">
                            <input type="button" onclick="register()" value="Sign up"/>
                        </p>
                        <p class="change_link">
                            Already a member ?
                            <a href="#tologin" class="to_register"> Go and log in </a>
                        </p>
                    </form>
                </div>

            </div>
        </div>
    </section>
</div>
</body>
<script type="text/javascript">
	function login () {
		var uname = document.getElementById('username').value;
        var psw = document.getElementById('password').value;
        
        var userInfo = {
    			"user.username" : uname,
    			"user.password" : psw,
        		"actions" : "login"
				};
        
        $.post("accountActions", userInfo,
	  				function(data){
	  					var msg = eval("("+data+")");
	  					if (msg.msg == "success") {
	  						window.location.href="<%=request.getContextPath()%>/Pages/UserPages/success.jsp";
	  					}
	  					else {
	  						sweetAlert("", "Wrong username or password!", "error");
	  					}
				}, 'json');
	}
	
	function register () {
		var uname = document.getElementById('usernamesignup').value;
        var psw = document.getElementById('passwordsignup').value;
        var pswcp = document.getElementById('passwordsignup_confirm').value;
        var age = document.getElementById('agesignup').value;
        var email = document.getElementById('emailsignup').value;

        if (uname == "" || psw == "" || pswcp == "" || age == "" || email == "") {
            sweetAlert("", "You must input all the info!", "error");
            return false;
        }
        
        if (psw != pswcp) {
            sweetAlert("", "Differences exist between two input in password!", "error");
            return false;
        }
        
        var userInfo = {
    			"user.username" : uname,
    			"user.password" : psw,
    			"user.age" : age,
    			"user.email" : email,
        		"actions" : "register"
				};
        
        $.post("accountActions", userInfo,
  				function(data){
  					var msg = eval("("+data+")");
  					if (msg.msg == "success") {
  						window.location.href="<%=request.getContextPath()%>/Pages/UserPages/queryBook.jsp";
  					}
  					else {
  						sweetAlert("", "This username is exsited!", "error");
  					}
			}, 'json');

        
	}
</script>
</html>