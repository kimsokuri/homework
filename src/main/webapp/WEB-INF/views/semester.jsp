<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수강-학기별학점</title>

</head>
<body>
	<h1>학년/학기 별 취득 학점</h1>
	<table class="tt">
		<tr>
			<th>년도</th>
			<th>학기</th>
			<th>학점</th>
			<th>상세보기</th>
		</tr>
		<c:forEach var="course" items="${semeCourses}">
			<tr>
				<td><c:out value="${course.year}"></c:out></td>
				<td><c:out value="${course.semester}"></c:out></td>
				<td><c:out value="${course.credit}"></c:out></td>
				<td><a
					href="${pageContext.request.contextPath}/detailSemester?year=${course.year}&semester=${course.semester}">링크</a></td>
			</tr>

		</c:forEach>
	</table>
	<a href="${pageContext.request.contextPath}">돌아가기</a>
</body>
</html>