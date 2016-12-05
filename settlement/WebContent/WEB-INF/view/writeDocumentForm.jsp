<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>문서 작성</title>
</head>
<body>
<form action="writeDocument.do" method="post">
<p>
	문서종류 : <input type="radio" name="doctypeName" value="보고서">보고서
	<input type="radio" name="doctypeName" value="회보">회보
	<input type="radio" name="doctypeName" value="명령서">명령서
	<input type="radio" name="doctypeName" value="지시서">지시서
	<input type="radio" name="doctypeName" value="품의서">품의서 
</p>
<p>
	부서 : ${authUser.departmentName}
</p>
<p>
	직책 : ${authUser.positionName}
</p>
<p>
	이름 : ${authUser.name}
</p>
<p>
	상급자 부서 : <input type="text" name="officerDepartment" value="${param.officerDepartment}">
</p>
<p>
	상급자 직책 : <input type="text" name="officerPosition" value="${param.officerPosition}">
</p>
<p>
	상급자 이름 : <input type="text" name="officerName" value="${param.officerName}">
</p>
<p>
	제목 : <input type="text" name="title" value="${param.title}">
	<c:if test="${errors.title}">제목을 입력하세요.</c:if>
</p>
<p>
	내용 : <br/>
	<textarea name="content" rows="5" cols="30">${param.content}</textarea>
</p>
<input type="submit" value="새 문서 등록">
</form>
</body>
</html>