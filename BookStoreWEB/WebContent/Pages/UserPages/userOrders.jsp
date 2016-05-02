<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "entityBean.User" %>
<%
	User user = new User();
	user = (User) session.getAttribute("user");
	String username = user.getUsername();
	if (user == null) {
		 response.sendRedirect(request.getContextPath()+"/Pages/GeneralPages/Login.jsp");
	}
	else if (user.getAdm() == 1) {
		response.sendRedirect(request.getContextPath()+"/Pages/ManagerPages/addBook.jsp");
	}
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User</title>

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
        	    <a class="btn btn-success" onclick="showShoppingCart()">Shopping Cart</a>
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
                        <a class="active-menu" href="<%=request.getContextPath()%>/Pages/UserPages/userOrders.jsp">My orders</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/Pages/UserPages/chatRoom.jsp">Chat room</a>
                    </li>
                </ul>

            </div>

        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-head-line">Check Orders</h1>
                        <h1 class="page-subhead-line">You can query your orders by different way.</h1>
                        <h1 class="page-subhead-line" style="color:red">Tips:I'll change the appearance of this page later,
                        				so when you search order by time, you should input legal form such as 2016 or 2106-5 or 2016-5-1</h1>
                    </div>
                </div>
                <!-- /. ROW  -->
                
                <div class="row">
		            <div class="col-lg-8 col-lg-offset-3">
		                <div class="input-group" style="width: 460px">
		                    <input type="text" class="form-control" id="searchWhat">
		                    <div class="input-group-btn">
		                        <select class="form-control" id="searchMethod" style="width: auto">
		                            <option value="all">check all</option>
		                            <option value="year">check by year</option>
		                            <option value="month">check by mouth</option>
		                            <option value="day">check by day</option>
		                        </select>
		                        <button type="button" class="btn btn-default" onclick="searchOrders()">Query</button>
		                    </div><!-- /btn-group -->
		                </div><!-- /input-group -->
		            </div><!-- /.col-lg-6 -->
		            <br><br>
		        </div>
		        <!-- /. ROW  -->
		        
		        <div class="row">
		            <div class="col-lg-8 col-lg-offset-2">
		                <table class="table table-condensed" id="showOrdersTable">
		                
						</table>
		            </div><!-- /.col-lg-6 -->
		            <br><br>
		        </div>
		        <!-- /. ROW  -->
		        
		        <div class="container">
		            <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			             aria-labelledby="myModalLabel" aria-hidden="true">
			            <div class="modal-dialog">
			                <div class="modal-content">
			                    <div class="modal-header">
			                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
			                        <h4 class="modal-title" id="myModalLabel">Shopping Cart</h4>
			                    </div>
			                    <div class="modal-body" id="modalContent">
			                        <table class="table table-condensed" id="showCartTable">
		                
									</table>
			                    </div>
			                    <div class="modal-footer" id="modal-footer">
			                    	<button type="button" class="btn btn-info" onclick="commitToOrder()">Commit To Order</button>
			                    	<button type="button" class="btn btn-danger" onclick="clearCart()">Clear Cart</button>
									<button type="button" class="btn btn-primary" data-dismiss="modal">close</button>
			                    </div>
				        	</div><!-- /.modal-content -->
				    	</div><!-- /.modal-dialog -->
					</div><!-- /.modal -->';
		        </div>

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
	
	<script src="<%=request.getContextPath()%>/Pages/js/shoppingCart.js"></script>
</body>
<script type="text/javascript">
function searchOrders() {
	var arg = $("#searchWhat").val();
	var searchType = $("#searchMethod option:selected").val();

	var searchInfo;
	if (searchType == "all") {
		searchInfo = {
				"actions": "queryAllOrder"
		}
	}
	else if (searchType == "year") {
		searchInfo = {
				"queryCondition": arg,
				"actions": "queryByYear"
		}
	}
	else if (searchType == "month") {
		searchInfo = {
				"queryCondition": arg,
				"actions": "queryByMonth"
		}
	}
	else if (searchType == "day") {
		searchInfo = {
				"queryCondition": arg,
				"actions": "queryByDay"
		}
	}
	
	$.post("orderActions", searchInfo,
				function(data){
					var msg = eval("("+data+")");
					if (msg[0] == null) {
						sweetAlert("", "No such order!", "warning");
						return;
					}
					console.log(msg);
					showOrderInfo(msg);
			}, 'json');
}

function showOrderInfo (orders) {
	$("#showOrdersTable").hide();
	var content = "<thead>\
				      <tr>\
				        <th>Book</th>\
				        <th>Price</th>\
				        <th>Count</th>\
				        <th>Time</th>\
				     </tr>\
				  </thead>";
				  
	content += "<tbody>";
	for (var i = 0; i < orders.length; i++) {
		content += "<tr>";
		content += "<td>" + orders[i].bookTitle + "</td>";
		content += "<td>" + orders[i].price + "</td>";
		content += "<td>" + orders[i].count + "</td>";
		content += "<td>" + orders[i].orderTime + "</td>";
		content += "</tr>"
	}
	content += "</tbody>";
	$("#showOrdersTable").html(content);
	$("#showOrdersTable").show('slow');
}
</script>

</html>
