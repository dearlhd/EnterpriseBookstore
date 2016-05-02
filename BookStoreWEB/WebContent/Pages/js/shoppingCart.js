function showShoppingCart() {
		var content = "\
			<thead>\
		      <tr>\
		        <th>Title</th>\
		        <th>Author</th>\
		        <th>Price</th>\
		        <th>Count</th>\
		      </tr>\
		  	</thead>";
		content += "<tbody>";
		  
		$.post("cartActions", {"actions":"getCart"},
  				function(data){
  					var msg = eval("("+data+")");
  					console.log(msg);
  					
  					for (var i = 0; i < msg.length; i++) {
  						content += "<tr>";
  						content += "<td>" + msg[i].title + "</td>";
  						content += "<td>" + msg[i].author + "</td>";
  						content += "<td>" + msg[i].price + "</td>";
  						content += "<td>" + msg[i].countInCart + "</td>";
  						content += '<td> <button type="button" class="btn btn-danger" onclick="removeFromCart('+msg[i].bookId+')">\
								Remove</button> </td>';
  					}
  					content += "</tbody>";
  					$("#showCartTable").html(content);
  					$("#myModal").modal();
  					
				}, 'json');
	}
	
	function removeFromCart(bookId) {
		var cartInfo = {
				"book.bookId" : bookId,
				"actions" : "removeBook"
		}
		$.post("cartActions", cartInfo,
  				function(data){
  					var msg = eval("("+data+")");
  					console.log(msg);
  					sweetAlert("", "Removed!", "success");
  					showShoppingCart()
				}, 'json');
	}
	
	function clearCart() {
		$.post("cartActions", {"actions":"clearCart"},
  				function(data){
					sweetAlert("", "Cleared!", "success");
					showShoppingCart()
				}, 'json');
	}
	
	function commitToOrder() {
		$.post("cartActions", {"actions":"commitToOrder"},
  				function(data){
					sweetAlert("", "Order finished!", "success");
					showShoppingCart()
				}, 'json');
	}