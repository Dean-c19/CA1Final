<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>REST Example</title>
</head>
<body>
<form action= "http://localhost:8080/CA1Final/restful-services/sampleservice/hello" method="GET">


    <input type="submit" value="Hello World Test">
</form>

<form action= "http://localhost:8080/CA1Final/restful-services/sampleservice/echo/message" method="GET">

    <input type="submit" value="Print Message ">
</form>

<form action= "http://localhost:8080/CA1Final/restful-services/sampleservice/employees" method="GET">
    <input type="submit" value="View All Employees">
</form>

<form action= "http://localhost:8080/CA1Final/restful-services/sampleservice/json/employees" method="GET">
    <input type="submit" value="View JSON All Employee ">
</form>

<form action= "http://localhost:8080/CA1Final/restful-services/sampleservice/json/employee/1" method="GET">
    <input type="submit" value="View JSON Employee 1">
</form>

</body>
</html>