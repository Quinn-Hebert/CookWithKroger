<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<meta name="viewport" content="width=device-width" />
    <title>Cook With Kroger</title>
    <link rel="stylesheet" href="css/mainPage.css" />
    <link href="https://fonts.googleapis.com/css?family=Pacifico"
	rel="stylesheet">
	<style>
	#welcome {
	font-family: 'Pacifico';
	font-size: 17px;
	
	}
	</style>

	

<body>

	<header>

		<c:url value="css/CWKLogo5.png" var="logourl" />
		<c:url value="css/AboutUs5.png" var="aboutus" />

		<img src="${logourl}" id="logo" />
		<p id="welcome"> Welcome Back Tracy McGrady!</p>
		
		<c:url value="/aboutUs" var="aboutusLink" />
		<a href = "${aboutusLink}"><img src="${aboutus}" id="aboutus" /></a>
	

	</header>




		<div id="getInfo">

			<c:url value="/recipeList" var="recipeList" />
			<form method="GET" action="${recipeList}">
				<br>


				<p>
					<label class="Text" for="budget">Budget:</label> <select id="budget"
						name="budget">
						<option value="5">Less than $5.00</option>
						<option value="10">Less than $10.00</option>
						<option value="15">Less than $15.00</option>
						<option value="20">Less than $20.00</option>
						<option value="25">Less than $25.00</option>
						<option value="50">Less than $50.00</option>
					</select>
				</p>




				<p>
					<label class="Text" for="category">Meal Category:</label> <select id="category"
						name="category">

						<%-- ADD for each loop to loop through each category in our database --%>

						<option value="*">I Like It All!</option>
						<option value="Vegetarian">Vegetarian</option>
						<option value="American">American</option>
						<option value="Italian">Italian</option>
						<option value="Mexican">Mexican</option>

					</select>
				</p>


				<p>
					<label class="Text" for="servings">Servings:</label> <select id="servings"
						name="servings">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>

					</select>
				</p>

				<p>
					<label class="Text" for="cooktime">Cook Time:</label> <select id="cooktime"
						name="cooktime">
						<option value="1000">I can cook all day, every day.</option>
						<option value="15">Less than 15 Minutes</option>
						<option value="30">Less than 30 Minutes</option>
						<option value="60">Less than 1 Hour</option>
						<option value="90">Less than 1 1/2 Hours</option>
				

					</select>
				</p>



				<br>
				<br> <input id="SubmitButton" type="submit"
					value="Find Recipes!">
			</form>

		</div>


	
</body>







</html>