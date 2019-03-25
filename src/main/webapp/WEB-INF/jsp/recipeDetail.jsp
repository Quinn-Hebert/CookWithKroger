<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${recipe.description}</title>
<link rel="stylesheet" href="css/recipeDetail.css" />

<link href="https://fonts.googleapis.com/css?family=Pacifico"
	rel="stylesheet">

<style>
h1 {
	font-family: 'Pacifico';
	font-size: 40px;
}

#ingP {
	font-family: 'Pacifico';
	font-size: 20px;
}

#vi {
	font-family: 'Pacifico';
	font-size: 14px;
}
#Ingredients {
	padding-bottom: 3rem!important;
}
</style>
</head>

<body>
	<header>
		<c:url value="css/CWKLogo5.png" var="logourl" />
		<c:url value="css/AboutUs5.png" var="aboutus" />

		<c:url value="/" var="mainPageLink" />
		<a href = "${mainPageLink}"><img src="${logourl}" id="logo" /></a>
		<c:url value="/aboutUs" var="aboutusLink" />
		<a href="${aboutusLink}"><img src="${aboutus}" id="aboutus" /></a>

	</header>

	<div id="bodyInside">
		<h1>${recipe.description}</h1>

		<c:forEach begin="0" end="${recipe.instructions.size() -1 }"
			var="count">
			<p>${ recipe.instructions[ count ] }</p>
		</c:forEach>


		<c:if test="${not empty recipe.recipeVideo}">
			<p id="videop">

				<iframe id="video" width="336" height="189"
					src="${recipe.recipeVideo}" frameborder="0"
					allow="autoplay; encrypted-media" allowfullscreen></iframe>
			</p>
		</c:if>

		<div id="Ingredients">

			<p id="ingP">Ingredients</p>


			<c:url value="/cart" var="cart" />
			<form method="GET" action="${cart}">

				<c:forEach var="ingredient" items="${recipe.ingredients}">
					<input type="checkbox" name="ingredient" value="${ingredient.key.productUPC}" ${ingredient.key.checked} style="opacity:0; position:absolute; left:-1px;">
					<c:forEach var="qty" begin="1" end="${ingredient.value}">
	        			<fmt:setLocale value = "en_US"/>
						<fmt:formatNumber var="ingPrice" value="${ingredient.key.price}" type="CURRENCY" pattern="$0.00" />
						<input type="checkbox" ${ingredient.key.checked}> ${ingredient.key.name} ${ingPrice}<br>
					</c:forEach>
				</c:forEach>
				<input type="submit" value="Add To Cart">
			</form>
		</div>

	</div>

</body>

</html>