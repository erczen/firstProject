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
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>조회수</td>
	</tr>
<c:if test="${documentPage.cautionPage.hasNoArticles()}">
	<tr>
		<td colspan="4">게시글이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="caution" items="${documentPage.cautionPage.content}">
	<tr>
		<td>${caution.number}</td>
		<td>
		<a href="read.do?no=${caution.number}&pageNo=${documentPage.cautionPage.currentPage}">
		<c:out value="${caution.title}"/>
		</a>
		</td><!--여기서 이름을 어떻게 받아야 할까???? 현재 공지사항, 승인대기, 승인을 표로 나타내기 위해 각각 페이지 클래스 만들고
					여기서 페이지번호를 받아서 테이블에서 가지고 오는데... 문제는 document 모델 클래스에는 이름이 존재하지 않는다.
					또한 부서 직책 이름을 띄워야 할것인데..  -->
		<td>${article.writer.name}</td>
		<td>${article.readCount}</td>
	</tr>
</c:forEach>
<c:if test="${articlePage.hasArticles()}">
	<tr>
		<td colspan="4">
			<c:if test="${articlePage.startPage > 5}">
			<a href="list.do?pageNo=${articlePage.startPage - 5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" 
					   begin="${articlePage.startPage}" 
					   end="${articlePage.endPage}">
			<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${articlePage.endPage < articlePage.totalPages}">
			<a href="list.do?pageNo=${articlePage.startPage + 5}">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</table>
</body>
</html>