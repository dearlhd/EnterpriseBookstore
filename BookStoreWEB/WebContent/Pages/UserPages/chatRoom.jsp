<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "entityBean.User" %>
<%
	User user = new User();
	user = (User) session.getAttribute("user");
	if (user == null) {
		 response.sendRedirect(request.getContextPath()+"/GeneralUI/Login.jsp");
	}
	String username = user.getUsername();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Char Room</title>

    <!-- BOOTSTRAP STYLES-->
    <link href="<%=request.getContextPath()%>/Pages/css/bootstrap.css" rel="stylesheet" />
       <!--CUSTOM BASIC STYLES-->
    <link href="<%=request.getContextPath()%>/Pages/css/basic.css" rel="stylesheet" />
    <!--CUSTOM MAIN STYLES-->
    <link href="<%=request.getContextPath()%>/Pages/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"></a>
            </div>

            <div class="header-right">
        	    <a href="login.html" class="btn btn-success">Shopping Cart</a>
                <a href="login.html" class="btn btn-danger">Logout</a>
            </div>
        </nav>
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li>
                        <div class="user-img-div">
                            <div class="inner-text">
                                Username
                                <br />
                                <small>Last Login : 2 Weeks Ago </small>
                            </div>
                        </div>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/Pages/UserPages/queryBook.jsp">Query Books</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/Pages/UserPages/userOrders.jsp">My orders</a>
                    </li>
                    <li>
                        <a class="active-menu" href="<%=request.getContextPath()%>/Pages/UserPages/chatRoom.jsp">Chat room</a>
                    </li>
                </ul>

            </div>

        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">Chat with book friends!</h1>
                    </div>
                </div>
                <!-- /. ROW  -->
                
                <div class="row">
                	<div class="col-md-12">
                		<div class="form-inline">
				    	<textarea id="chatInput" class="form-control" cols="70" rows="1" onkeyup="checkJoin(event);"></textarea>
				    	<button id="join" class="btn btn-primary" onclick="sendMessage()">Send!</button>
				    	</div>
				    </div>
				</div>
				<br/>
				<div class="row">
					<div class="col-md-7">
				    	<textarea id="textarea" class="form-control" cols="70" rows="20" readonly="true"></textarea>
				    	<textarea id="userlist" style="display:none" cols="20" rows="20" readonly="true"></textarea>
				    </div>
				    
		        </div>
		        <!-- /. ROW  -->
            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->

    <div id="footer-sec">
        Copyright &copy; 2016.BookStore All rights reserved.
    </div>
    <!-- /. FOOTER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="<%=request.getContextPath()%>/Pages/js/jquery-2.2.3.min.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="<%=request.getContextPath()%>/Pages/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="<%=request.getContextPath()%>/Pages/js/jquery.metisMenu.js"></script>
       <!-- CUSTOM SCRIPTS -->
    <script src="<%=request.getContextPath()%>/Pages/js/custom.js"></script>
    
</body>
<script type="text/javascript">
	var wsocket;
	function connect() {
		wsocket = new WebSocket("ws://59.78.22.100:8888/BookStoreWEB/chatRoom");
        wsocket.onmessage = onMessage;
	}
	
	function checkJoin(evt) {
		var inputElement = document.getElementById("chatInput");
		var inputVal = inputElement.value;
		if (evt.keyCode === 13 && inputVal.length == 1) {
			document.getElementById("chatInput").value = null;
		}
		
        if (evt.keyCode === 13 && inputVal.length > 1) {
            sendMessage();
        }
    }

	function sendMessage() {
		var inputElement = document.getElementById("chatInput");
		var inputVal = inputElement.value;
		if (inputVal.length == 0) {
			return;
		}
		
		if (inputVal[inputVal.length-1] == '\n' || inputVal[inputVal.length-1] == '\r')
			inputVal = inputVal.substring(0, inputVal.length-1);
		wsocket.send("<%=username%>: "+inputVal);
		document.getElementById("chatInput").value = null;
	}
	
	function onMessage (evt){
		$("#textarea").append(evt.data+"\n");
	}
	window.addEventListener("load", connect, false);
</script>

</html>
