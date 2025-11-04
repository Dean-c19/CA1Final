<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>REST Example</title>
</head>
<body>

<form action= "http://localhost:8080/CA1Final/restful-services/fitness/members" method="GET">
    <input type="submit" value="View All Members">
</form>

<form action= "http://localhost:8080/CA1Final/restful-services/fitness/json/members" method="GET">
    <input type="submit" value="View all JSON Members ">
</form>

<form action= "http://localhost:8080/CA1Final/restful-services/fitness/members" method="GET">
    <input type="submit" value="View all XML Members ">
</form>


<form action= "http://localhost:8080/CA1Final/restful-services/fitness/json/member/10" method="GET">
    <input type="submit" value="View specific member">
</form>


</body>
</html>