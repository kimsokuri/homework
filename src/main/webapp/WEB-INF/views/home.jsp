<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수강-홈</title>


</head>
<body>
	<h1>수강 확인 사이트</h1>
	<table class="tt">
		<tr>
			<td><a href="${pageContext.request.contextPath}/semester">[1]
					학년/학기 별 이수 총 학점</a></td>
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath}/division">[2]
					이수 구분 별 학점 내역</a></td>
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath}/registerCourse">[3]
					수강 신청하기</a></td>
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath}/lookup">[4]
					수강 신청 조회</a></td>
		</tr>

	</table>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<a href="javascript:document.getElementById('logout').submit()">Logout</a>
	</c:if>

	<form id="logout" action="<c:url value='/logout'/>" method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token }" />
	</form>


</body>
</html>
