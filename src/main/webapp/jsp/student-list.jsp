<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Danh sách học viên</title>
    <link rel="stylesheet" type="text/css" href="/bt3/css/style.css">
</head>
<body>
<h2>Danh sách học viên</h2>

<table border="1">
<tr><th>ID</th><th>Tên</th><th>Email</th><th>Chi tiết</th></tr>
<c:forEach var="s" items="${students}">
<tr>
<td>${s.id}</td>
<td>${s.name}</td>
<td>${s.email}</td>
<td><a href="students?id=${s.id}">Xem</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>
