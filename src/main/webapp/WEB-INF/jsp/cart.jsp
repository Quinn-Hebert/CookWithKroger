<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Shopping Cart</title>


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/cart.css" />
<link href="https://fonts.googleapis.com/css?family=Pacifico"
	rel="stylesheet">


<body>
	<c:url value="css/CWKLogo5.png" var="logourl" />
	<c:url value="css/AboutUs5.png" var="aboutus" />

	<c:url value="/" var="mainPageLink" />
	<a href="${mainPageLink}"><img src="${logourl}" id="logo"></a>
	<c:url value="/aboutUs" var="aboutusLink" />
	<a href="${aboutusLink}"><img src="${aboutus}" id="aboutus"></a>

	<div id="bodyish">
		<h1 class="pt-3">Your Cart</h1>
		<div class="container mt-3">
			<c:set var="totalPrice" value="0" />
			<c:forEach var="ingredient" items="${ingredients}">
				<fmt:setLocale value="en_US" />
				<fmt:formatNumber var="ingPrice" value="${(ingredient.price  * ingQty.get(ingredient.productUPC))}" type="CURRENCY" pattern="$0.00" />
				<div class="d-flex justify-content-between">
					<h5>${ingQty.get(ingredient.productUPC)} ${ingredient.name}</h5> 
					<h5>${ingPrice}</h5>
				</div>
				
				<c:set var="totalPrice" value="${totalPrice + (ingredient.price * ingQty.get(ingredient.productUPC))}" />
			</c:forEach>
			<fmt:formatNumber var="totalPrice" value="${totalPrice}" type="CURRENCY" pattern="$0.00" />
			<h5 class="text-center mt-5">Total: ${totalPrice}</h5>

			<c:url value="/thankyouPage" var="thankYouLink" />
			<div class="text-center">
				<a href="${thankYouLink}">
					<button type="button" class="btn btn-outline-success btn-sm mb-5">Check
						Out</button>
				</a>
			</div>

		</div>
	</div>



	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>