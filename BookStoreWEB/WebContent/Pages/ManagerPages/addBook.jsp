<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "entityBean.User" %>
<%
	User user = new User();
	user = (User) session.getAttribute("user");
	if (user == null) {
		 response.sendRedirect(request.getContextPath()+"/Pages/GeneralPages/Login.jsp");
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
    <title>Add Book</title>

    <!-- BOOTSTRAP STYLES-->
    <link href="<%=request.getContextPath()%>/Pages/css/bootstrap.css" rel="stylesheet" />
       <!--CUSTOM BASIC STYLES-->
    <link href="<%=request.getContextPath()%>/Pages/css/basic.css" rel="stylesheet" />
    <!--CUSTOM MAIN STYLES-->
    <link href="<%=request.getContextPath()%>/Pages/css/custom.css" rel="stylesheet" />
        
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Pages/css/sweet-alert.css">
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
                <a href="<%=request.getContextPath()%>/Pages/GeneralPages/logout.jsp" class="btn btn-danger">Logout</a>
            </div>
        </nav>
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li>
                        <div class="user-img-div">
                            <div class="inner-text">
                                Administrator
                                <br />
                                <small>Last Login : 2 Weeks Ago </small>
                            </div>
                        </div>
                    </li>
                    <li>
                        <a class="active-menu" href="<%=request.getContextPath()%>/Pages/ManagerPages/addBook.jsp">Add Book</a>
                    </li>
                </ul>

            </div>

        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">Add Book</h1>
                        <h1 class="page-subhead-line">You can add book now</h1>

                    </div>
                </div>
		        <!-- /. ROW  -->
		        
		        <div class="row">
		            <div class="col-lg-8 col-lg-offset-2">
		            	<form role="form">
							<div class="form-group">
								<label for="name">Title</label>
								<input type="text" class="form-control" id="title">
							</div>
							<div class="form-group">
								<label for="name">Author</label>
								<input type="text" class="form-control" id="author">
							</div>
							<div class="form-group">
								<label for="name">Price</label>
								<input type="text" class="form-control" id="price">
							</div>
							<div class="form-group">
								<label for="name">Count</label>
								<input type="text" class="form-control" id="count">
							</div>
						</form>
						<button class="btn btn-default" onclick="addBook()">Submit</button>
		            </div><!-- /.col-lg-6 -->
		            <br><br>
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
    
    <script src="<%=request.getContextPath()%>/Pages/js/sweet-alert.min.js"></script>
</body>
<script type="text/javascript">
	function addBook() {
		alert("add book");
		var title = $("#title").val();
		var author = $("#author").val();
		var price = $("#price").val();
		var count = $("#count").val();
		
		if (title == null || author == null || price == null || count == null ){
			sweetAlert("", "Please fill the blank!", "error");
		}
		
		var bookInfo = {
				"book.title":title,
				"book.author":author,
				"book.price":price,
				"book.count":count,
				"actions": "addBook"
		}
		
		$.post("bookActions", bookInfo,
  				function(data){
  					var msg = eval("("+data+")");
  					console.log(msg);
  					sweetAlert("", "Added book successfully!", "success");
				}, 'json');
		
	}
	
</script>


</html>

