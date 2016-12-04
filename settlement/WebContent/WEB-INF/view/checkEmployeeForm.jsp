<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>직원 확인</title>
</head>
<body>
<form action="checkEmployee.do" method="post">
<p>
	부서  <input type="text" name="departmentName">
	<c:if test="${errors.departmentName}">부서를 입력하세요.</c:if>
</p>
<p>
	직급 <input type="text" name="positionName">
	<c:if test="${errors.positionName}">직급를 입력하세요.</c:if>
</p>
<p>
	이름 <input type="text" name="name">
	<c:if test="${errors.name}">이름을 입력하세요.</c:if>
</p>
<input type="submit" value="조회"><c:if test="${errors.beEmployee}">명단에 없습니다.</c:if>
</form>
</body>
</html>