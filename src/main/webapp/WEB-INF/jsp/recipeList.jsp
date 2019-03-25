<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>

<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Recipe Results</title>


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/recipeList.css" />


</head>
<body>
	<c:url value="css/CWKLogo5.png" var="logourl" />
	<c:url value="css/AboutUs5.png" var="aboutus" />

	<c:url value="/" var="mainPageLink" />
	<a href = "${mainPageLink}"><img src="${logourl}" id="logo" /></a>
	<c:url value="/aboutUs" var="aboutusLink" />
	<a href="${aboutusLink}"><img src="${aboutus}" id="aboutus" /></a>


	<div id="bodyish">


		<header>
			<c:url value="css/RecipesTitle.png" var="recipesTitle" />

			<h1>
				<img src="${recipesTitle}" id="recipesTitle" />
			</h1>
		</header>



		<section id="main-content">
			<div class="container" id="row3">

				<div class="row justify-content-center" id="row2">
					<div class="card-deck mb-5">
						<c:forEach var="recipe" items="${recipes}">
							<div class="col-auto col-auto pb-2" id="row1">
								<c:url var="recipePage"
									value="/recipeDetail?recipeId=${recipe.recipeId}" />
								<div class="card card-outline-info h-100"
									style="width: 14.5rem;">
									<c:url var="recipeImage" value="${recipe.recipeImage}" />
									<div class="card-title">
										<a href="${recipePage}"> <img src="${recipeImage}"
											class="card-img-top" alt="recipeimage">
										</a>
									</div>

									<div class="card-body d-flex flex-column justify-content-end">
										<a href="${recipePage}"
											class="d-flex text-center justify-content-center mb-3">${recipe.description}</a>
										<div class="card-text d-flex justify-content-between">

											<fmt:setLocale value="en_US" />
											<fmt:formatNumber var="recPrice" value="${recipe.price}" type="CURRENCY" pattern="$0.00" />
											<p>${recPrice}</p>
											<p>${recipe.cookTime}
												m <i class="far fa-clock"></i>
											</p>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</section>




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
	</div>
</body>
</html>