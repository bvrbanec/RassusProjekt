<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Registracija psa</h1>
	<form action="DogRegistrationServlet" method="POST">
	<br>
	Ime psa: <br>
	<input type="text" name="dogName"><br>
	<br>
	Pasmina: <br>
	<input type="text" name="dogBreed"><br>
	<br>
	Ime i prezime vlasnika: <br>
	<input type="text" name="dogOwner"><br>
	<br>
	Starost u godinama: <br>
	<input type="text" name="dogAge"><br>
	<br>
	Spol:<br>
	<input type="radio" name="dogGender" value="male"> Male<br>
	<input type="radio" name="dogGender" value="female"> Female<br>
	<br>
	Zdravstveno stanje:<br>
	<input type="radio" name="dogHealthCondition" value="healthy">Zdrav/a<br>
	<input type="radio" name="dogHealthCondition" value="fewProblems">Male zdravstvene poteskoce koje nebi smjele utjecati na setnju<br>
	<input type="radio" name="dogHealthCondition" value="needSpecialCare">Zahtjeva posebnu brigu i paznju za vrijeme setnje zbog zdravstvenih poteskoca.<br>
	<br>
	Boja dlake: <br>
	<input type="text" name="dogHairColour"><br>
	<br>
	Boja ociju: <br>
	<input type="text" name="dogEyeColour"><br>
	<br>
	Tezina psa (u kg): <br>
	<input type="text" name="dogWeight"><br>
	<br>
	Visina psa (u cm): <br>
	<input type="text" name="dogHeight"><br>
	<br>
	Duzina dlake: <br>
	<input type="radio" name="dogHairLength" value="shortHair"> Kratka dlaka<br>
	<input type="radio" name="dogHairLength" value="longHair"> Duga dlaka<br>
	<input type="radio" name="dogHairLength" value="noHair"> Bez dlake<br>
	<br>
	<input type="submit" value="Registriraj">
	</form>
</body>
</html>