<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Danh sách khóa học</title>
    <link rel="stylesheet" type="text/css" href="/bt3/css/style.css">
</head>
<body>
<h2>Danh sách khóa học</h2>

<table border="1">
<tr><th>ID</th><th>Tiêu đề</th><th>Mô tả</th><th>Chi tiết</th></tr>
<c:forEach var="c" items="${courses}">
<tr>
<td>${c.id}</td>
<td>${c.title}</td>
<td>${c.description}</td>
<td><a href="courses?id=${c.id}">Xem</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>
