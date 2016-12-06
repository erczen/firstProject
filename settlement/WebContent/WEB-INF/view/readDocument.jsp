<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 읽기</title>
</head>
<body>
<table border="1" width="100%">
<tr>
	<td>작성일자</td>
	<td>${documentForm.regDate}</td>
</tr>
<tr>
	<td>부서</td>
	<td>${documentForm.departmentName}</td>
</tr>
<tr>
	<td>직책</td>
	<td>${documentForm.positionName}</td>
</tr>
<tr>
	<td>작성자</td>
	<td>${documentForm.name}</td>
</tr>
<tr>
	<td>책임자</td>
	<td>${documentForm.officerDepartment} ${documentForm.officerPosition} ${documentForm.officerName}</td>
</tr>
<tr>
	<td>승인여부</td>
	<td>${documentForm.officerCheck}</td>
</tr>
<tr>
	<td>제목</td>
	<td><c:out value='${documentForm.title}' /></td>
</tr>
<tr>
	<td>내용</td>
	<td><u:pre value='${documentForm.content}'/></td>
</tr>
<tr>
	<td colspan="2">
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}" />
		<a href="list.do?pageNo=${pageNo}">[목록]</a>
		<c:if test="${authUser.employeeNo == writerNo}">
		<a href="modify.do?no=${articleData.article.number}">[게시글수정]</a>
		<a href="delete.do?no=${articleData.article.number}">[게시글삭제]</a>
		</c:if>
	</td>
</tr>
</table>

</body>
</html>