<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
                        <a class="active-menu" href="<%=request.getContextPath()%>/Pages/UserPages/queryBook.jsp">Query Books</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/Pages/UserPages/userOrders.jsp">My orders</a>
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
                        <h1 class="page-head-line">Query Books</h1>
                        <h1 class="page-subhead-line">You can query books by different way </h1>

                    </div>
                </div>
                <!-- /. ROW  -->
                
                <div class="row">
		            <div class="col-lg-8 col-lg-offset-3">
		                <div class="input-group" style="width: 460px">
		                    <input type="text" class="form-control" id="searchWhat">
		                    <div class="input-group-btn">
		                        <select class="form-control" id="searchMethod" style="width: auto">
		                            <option value="title">query by title</option>
		                            <option value="author">query by author</option>
		                        </select>
		                        <button type="button" class="btn btn-default" onclick="searchBooks()">Query</button>
		                    </div><!-- /btn-group -->
		                </div><!-- /input-group -->
		            </div><!-- /.col-lg-6 -->
		            <br><br>
		        </div>
		        <!-- /. ROW  -->
		        
		        <div class="row">
		            <div class="col-lg-8 col-lg-offset-2">
		                <table class="table table-condensed" id="showBooksTable">
		                
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
			                        
			                    </div>
				                    <div class="modal-footer" id="modal-footer">
				
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
    
</body>
<script type="text/javascript">
	function searchBooks() {
		var arg = $("#searchWhat").val();
		var searchType = $("#searchMethod option:selected").val();

		var searchInfo;
		if (searchType == "title") {
			searchInfo = {
					"book.title":arg,
					"actions": "queryByTitle"
			}
		}
		else if (searchType == "author") {
			searchInfo = {
					"book.title": arg,
					"actions": "queryByAuthor"
			}
		}
		
		$.post("bookActions", searchInfo,
  				function(data){
  					var msg = eval("("+data+")");
  					console.log(msg);
  					showBooksInfo(msg);
				}, 'json');
		
	}
	
	function showBooksInfo(books) {
		$("#showBooksTable").hide();
		var content = "<thead>\
					      <tr>\
					        <th>Title</th>\
					        <th>Author</th>\
					        <th>Price</th>\
					        <th>Count</th>\
					        <th>Add to cart</th>\
					     </tr>\
					  </thead>";
					  
		content += "<tbody>";
		for (var i = 0; i < books.length; i++) {
			content += "<tr>";
			content += "<td>" + books[i].title + "</td>";
			content += "<td>" + books[i].author + "</td>";
			content += "<td>" + books[i].price + "</td>";
			content += "<td>" + books[i].count + "</td>";
			content += '<td> <button type="button" class="btn btn-info" onclick="addToCart('+books[i].bookId+')">\
							Add to cart</button> </td>';
		}
		content += "</tbody>";
		$("#showBooksTable").html(content);
		$("#showBooksTable").show('slow');
	}
	
	function addToCart(bookId) {
		var cartInfo = {
				"book.bookId" : bookId,
				"actions" : "addBook"
		}
		$.post("cartActions", cartInfo,
  				function(data){
  					var msg = eval("("+data+")");
  					console.log(msg);
  					$("#myModal").modal();
				}, 'json');
	}
</script>

</html>

