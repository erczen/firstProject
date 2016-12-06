<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>문서 등록</title>
</head>
<body>

문서를 등록했습니다.
<br>
${ctxPath = pageContext.request.contextPath ; ''}
<a href="${ctxPath}/settlementMain.do">[문서목록보기]</a>
<a href="${ctxPath}/readDocument.do?documentNo=${documentNo}">[문서내용보기]</a>
</body>
</html>