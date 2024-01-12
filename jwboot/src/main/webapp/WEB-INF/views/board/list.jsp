<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록...</title>
<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div class="wrap">
		<h2>글 목록</h2>
		<table class="tbl_write">
		    <thead>
		    	<tr>
		    		<th>번호</th>
		    		<th>제목</th>
		    		<th>작성자</th>
		    		<th>작성일</th>
		    	</tr>
		    </thead>
			<tbody>
				<c:forEach items="${boardList}" var="board">
					<tr>
						<td>${board.id}</td>
						<td><a href="/board?id=${board.id}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td><fmt:formatDate value="${board.createdDate}" 
								pattern="yyyy-MM-dd HH:mm:ss" /> </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>