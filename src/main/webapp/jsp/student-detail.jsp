<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Chi tiết học viên</title>
    <link rel="stylesheet" type="text/css" href="/bt3/css/style.css">
</head>
<body>
<h2>Chi tiết học viên</h2>
<p>ID: ${student.id}</p>
<p>Tên: ${student.name}</p>
<p>Email: ${student.email}</p>
<h3>Khóa học đã đăng ký</h3>
<ul>
<c:forEach var="c" items="${student.courses}">
<li>${c.title}</li>
</c:forEach>
</ul>
<a href="enroll?studentId=${student.id}">Đăng ký thêm khóa học</a>
</body>
</html>
