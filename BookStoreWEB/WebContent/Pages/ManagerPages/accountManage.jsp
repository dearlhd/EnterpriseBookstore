<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entityBean.User"%>
<%
	User user = new User();
	user = (User) session.getAttribute("user");
	if (user == null) {
		response.sendRedirect(request.getContextPath()
				+ "/Pages/GeneralPages/Login.jsp");
	}
	//else if (user.getAdm() == 0) {
	//	response.sendRedirect(request.getContextPath()+"/Pages/UserPages/queryBook.jsp");
	//}
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Account Management</title>

<!-- BOOTSTRAP STYLES-->
<link href="<%=request.getContextPath()%>/Pages/css/bootstrap.css"
	rel="stylesheet" />
<!--CUSTOM BASIC STYLES-->
<link href="<%=request.getContextPath()%>/Pages/css/basic.css"
	rel="stylesheet" />
<!--CUSTOM MAIN STYLES-->
<link href="<%=request.getContextPath()%>/Pages/css/custom.css"
	rel="stylesheet" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Pages/css/sweet-alert.css">
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-cls-top " role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"></a>
			</div>

			<div class="header-right">
				<a
					href="<%=request.getContextPath()%>/Pages/GeneralPages/logout.jsp"
					class="btn btn-danger">Logout</a>
			</div>
		</nav>
		<!-- /. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">
					<li>
						<div class="user-img-div">
							<div class="inner-text">
								Administrator <br /> <small>Last Login : 2 Weeks Ago </small>
							</div>
						</div>
					</li>
					<li><a class="active-menu" href="<%=request.getContextPath()%>/Pages/ManagerPages/accountManage.jsp">Account management</a></li>
					<li><a href="<%=request.getContextPath()%>/Pages/ManagerPages/addBook.jsp">Add Book</a></li>
				</ul>

			</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h1 class="page-head-line">Query Account</h1>
						<h1 class="page-subhead-line">You can query accounts by different way.</h1>

					</div>
				</div>
				<!-- /. ROW  -->

				<div class="row">
					<div class="col-lg-8 col-lg-offset-3">
						<div class="input-group" style="width: 460px">
							<input type="text" class="form-control" id="searchWhat">
							<div class="input-group-btn">
								<select class="form-control" id="searchMethod" style="width: auto">
									<option value="all">query all accounts</option>
									<option value="username">query by username</option>
								</select>
								<button type="button" class="btn btn-default"
									onclick="getAccount()">Query</button>
							</div>
							<!-- /btn-group -->
						</div>
						<!-- /input-group -->
					</div>
					<!-- /.col-lg-6 -->
					<br>
					<br>
				</div>
				<!-- /. ROW  -->

				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<table class="table table-condensed" id="showAccountTable">

						</table>
					</div>
					<!-- /.col-lg-6 -->
					<br>
					<br>
				</div>
				<!-- /. ROW  -->

				<div class="container">
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">x</button>
									<h4 class="modal-title" id="myModalLabel">Edit info</h4>
								</div>
								<div class="modal-body" id="modalContent">
									<form role="form">
										<div class="form-group">
											<input type="hidden" class="form-control" id="username">
										</div>
										<div class="form-group">
											<label for="name">Password</label>
											<input type="text" class="form-control" id="password">
										</div>
										<div class="form-group">
											<label for="name">Age</label>
											<input type="text" class="form-control" id="age">
										</div>
										<div class="form-group">
											<label for="name">Email</label>
											<input type="text" class="form-control" id="email">
										</div>
									</form>
								</div>
								<div class="modal-footer" id="modal-footer">
									<button type="button" class="btn btn-info"
										onclick="editAccount()">Commit</button>
									<button type="button" class="btn btn-primary"
										data-dismiss="modal">close</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->
				</div>

			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->

	<div id="footer-sec">Copyright &copy; 2016.BookStore All rights reserved.</div>
	<!-- /. FOOTER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	<script
		src="<%=request.getContextPath()%>/Pages/js/jquery-2.2.3.min.js"></script>
	<!-- BOOTSTRAP SCRIPTS -->
	<script src="<%=request.getContextPath()%>/Pages/js/bootstrap.min.js"></script>
	<!-- METISMENU SCRIPTS -->
	<script
		src="<%=request.getContextPath()%>/Pages/js/jquery.metisMenu.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="<%=request.getContextPath()%>/Pages/js/custom.js"></script>

	<script src="<%=request.getContextPath()%>/Pages/js/sweet-alert.min.js"></script>
</body>
<script type="text/javascript">
	var urlTitle = "http://localhost:8888/BookStoreWEB/rest"
	
	function getAccount() {
		var arg = $("#searchWhat").val();
		var searchType = $("#searchMethod option:selected").val();
	
		var searchInfo;
		if (searchType == "all") {
			$.ajax({
	            url: urlTitle + "/account",
	            contentType: "application/json",
	            type: "GET",
	            success: function (data) {
	                var accounts = eval(data);
	                showAccountsInfo(accounts);
	            }
	        });
		}
		else if (searchType == "username") {
			$.ajax({
	            url: urlTitle + "/account/" + arg,
	            contentType: "application/json",
	            type: "GET",
	            success: function (data) {
	                var account = eval(data);
	                var accounts = new Array();
	                accounts[0] = account;
	                console.log(accounts);
	                showAccountsInfo(accounts);
	            }
	        });
		}
	}
	
	function showAccountsInfo(accounts) {
		$("#showAccountTable").hide();
		var content = "<thead>\
					      <tr>\
					        <th>Username</th>\
					        <th>Password</th>\
					        <th>Email</th>\
					        <th>Age</th>\
					        <th>Edit</th>\
					        <th>Delete</th>\
					     </tr>\
					  </thead>";
					  
		content += "<tbody>";
		for (var i = 0; i < accounts.length; i++) {
			content += "<tr id='accountList" + accounts[i].username + "'>";
			content += "<td>" + accounts[i].username + "</td>";
			content += "<td>" + accounts[i].password + "</td>";
			content += "<td>" + accounts[i].email + "</td>";
			content += "<td>" + accounts[i].age + "</td>";
			content += '<td> <button type="button" class="btn btn-info" onclick="editAccountClicked(\''+accounts[i].username+'\')">Edit</button> </td>';
			content += '<td> <button type="button" class="btn btn-info" onclick="deleteAccount(\''+accounts[i].username+'\')">Delete</button> </td>';
			content += "</tr>"
		}
		content += "</tbody>";
		$("#showAccountTable").html(content);
		$("#showAccountTable").show('slow');
	}
	
	function editAccountClicked(username) {
		$.ajax({
            url: urlTitle + "/account/" + username,
            contentType: "application/json",
            type: "GET",
            success: function (data) {
                var account = eval(data);
                console.log(account);
                document.getElementById("username").value = account.username;
                document.getElementById("password").value = account.password;
                document.getElementById("age").value = account.age;
                document.getElementById("email").value = account.email;
            }
        });
		
		$("#myModal").modal();
	}
	
	function editAccount () {
		var uname = document.getElementById("username").value;
		var psw = document.getElementById("password").value;
		var age = document.getElementById("age").value;
		var email = document.getElementById("email").value;
		
		var accountInfo = {
				"username" : uname,
				"password" : psw,
				"age" : age,
				"email" : email
				};
		
		$.ajax({
            url: urlTitle + "/account/" + uname,
            contentType: "application/json",
            data: JSON.stringify(accountInfo),
            type: "PUT",
            success: function (data) {
                var account = eval(data);
                sweetAlert("", "Edit account successfully!", "success");
                $("#myModal").modal('hide');
                getAccount();
            }
        });
	}
	
	function deleteAccount(username) {
		console.log("delete account");
		$.ajax({
            url: urlTitle + "/account/" + username,
            type: "DELETE",
            success: function (data) {
				sweetAlert("", "Delete account successfully!", "success");
				getAccount();
            }
        });
	}
</script>


</html>

