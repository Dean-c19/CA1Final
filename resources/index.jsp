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
    <input type="submit" value="View All Emissions (JSON)">
</form>

<form action="http://localhost:8080/CA1Final/restful-services/emissions/1" method="GET">
    <input type="submit" value="View Emission with ID 1">
</form>

<form action="http://localhost:8080/CA1Final/restful-services/emissions/json/approve/1?userId=1" method="POST">
    <input type="submit" value="Approve Emission ID 1 (User ID 1)">
</form>

<form action="http://localhost:8080/CA1Final/rest/emissions/json/1" method="DELETE">
    <input type="submit" value="Delete Emission ID 1">
</form>

<hr>

<h2>Users</h2>

<form action="http://localhost:8080/CA1Final/rest/users/all" method="GET">
    <input type="submit" value="View All Users (JSON)">
</form>

<form action="http://localhost:8080/CA1Final/rest/users/json/1" method="DELETE">
    <input type="submit" value="Delete User ID 1">
</form>

</body>
</html>