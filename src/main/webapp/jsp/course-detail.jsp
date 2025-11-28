<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Chi tiết khóa học</title>
    <link rel="stylesheet" type="text/css" href="/bt3/css/style.css">
</head>
<body>
<h2>Chi tiết khóa học</h2>
<p>ID: ${course.id}</p>
<p>Tiêu đề: ${course.title}</p>
<p>Mô tả: ${course.description}</p>
<h3>Danh sách học viên</h3>
<ul>
<c:forEach var="s" items="${course.students}">
<li>${s.name} (${s.email})</li>
</c:forEach>
</ul>
</body>
</html>
