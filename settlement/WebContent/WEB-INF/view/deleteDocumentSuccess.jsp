<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>문서 삭제</title>
</head>
<body>

문서를 삭제했습니다.
<br>
${ctxPath = pageContext.request.contextPath ; ''}
<a href="${ctxPath}/settlementMain.do">[문서목록보기]</a>
</body>
</html>