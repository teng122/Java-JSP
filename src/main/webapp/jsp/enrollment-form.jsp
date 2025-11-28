<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Đăng ký khóa học</title>
    <link rel="stylesheet" type="text/css" href="/bt3/css/style.css">
</head>
<body>
<h2>Đăng ký khóa học</h2>
<c:if test="${not empty error}"><p style="color:red">${error}</p></c:if>
<c:if test="${not empty message}"><p style="color:blue">${message}</p></c:if>
<form method="post">
    <label>MSSV:</label>
    <input type="text" name="studentId" required />
    <br/>
    <label>Tên môn học:</label>
    <input type="text" name="courseTitle" required />
    <br/>
    <button type="submit">Đăng ký</button>
</form>
</body>
</html>
