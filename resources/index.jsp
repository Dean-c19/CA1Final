<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>GHG Emissions REST</title>
</head>
<body>

<h2>Emissions</h2>

<form action="http://localhost:8080/CA1Final/restful-services/emissions/all" method="GET">
    <input type="submit" value="View All Emissions">
</form>



<form action="http://localhost:8080/CA1Final/restful-services/emissions/json/approve/2?userId=1" method="POST">
    <input type="submit" value="Approve Emission by Id from User Id">
</form>

<form action="http://localhost:8080/CA1Final/restful-services/emissions/byCategory" method="GET">
    <input type="text" name="category" value=" " />
    <input type="submit" value="View Emission by Category">
</form>



<hr>

<h2>Users</h2>

<form action="http://localhost:8080/CA1Final/restful-services/emissions/allUsers" method="GET">
    <input type="submit" value="View All Users (JSON)">
</form>

</body>
</html>