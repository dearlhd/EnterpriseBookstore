<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/Pages/js/jquery-2.2.3.min.js"> </script>
</head>
<body>

<button onclick = "addBook()">click me</button>

</body>

<script type="text/javascript">
	function addBook() {
		var addInfo = {
				"book.title" : "bookAdded",
				"actions" : "addBook"
		}
		$.post("cartActions", addInfo,
  				function(data){
  					var msg = eval("("+data+")");
  					console.log(msg);
				}, 'json');
	}
</script>

</html>