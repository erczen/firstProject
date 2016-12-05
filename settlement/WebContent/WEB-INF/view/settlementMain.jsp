<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>바이저 결제 페이지</title>
</head>
<body>
<p>부서 : ${authUser.departmentName} 직책 : ${authUser.positionName} 이름 : ${authUser.name}</p>
<hr/>
<table border="1">
	<tr>
		<td colspan="4">[공지사항]</td>
	</tr>
	<tr>
		<td>작성일자</td>
		<td>제목</td>
		<td>작성자</td>
	</tr>
<c:if test="${documentPage.cautionPage.hasNoDocuments()}">
	<tr>
		<td colspan="4">게시글이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="caution" items="${documentPage.cautionPage.content}">
	<tr>
		<td>${caution.regDate}</td>
		<td>
		<a href="read.do?no=${caution.documentNo}&pageNo=${documentPage.cautionPage.currentPage}">
		<c:out value="${caution.title}"/>
		</a>
		</td>
		<td>${caution.departmentName} ${caution.positionName} ${caution.name}</td>
	</tr>  
</c:forEach>
<c:if test="${documentPage.cautionPage.hasDocuments()}">
	<tr>
		<td colspan="4">
			<c:if test="${documentPage.cautionPage.startPage > 5}">
			<a href="settlementMain.do?pageNo=${documentPage.cautionPage.startPage - 5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" 
					   begin="${documentPage.cautionPage.startPage}" 
					   end="${documentPage.cautionPage.endPage}">
			<a href="settlementMain.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${documentPage.cautionPage.endPage < documentPage.cautionPage.totalPages}">
			<a href="settlementMain.do?pageNo=${documentPage.cautionPage.startPage + 5}">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</table>
</body>
</html>