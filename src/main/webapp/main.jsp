<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<% System.out.println("게시판 메인화면 접속"); %>
<meta charset="UTF-8">
<title>main.jsp</title>
</head>
<body>
	<h1>게시판 목록</h1>
	<table border="1">
		<col width="70px"> 	<!-- 글 번호 -->
		<col width="200px"> <!-- 제목 -->
		<col width="100px">	<!-- 작성자 -->
		<col width="70px"> 	<!-- 조회수 -->
		<col width="100px"> <!-- 작성일자 -->
		<col width="50px"> 	<!-- 수정 -->
		<col width="50px"> 	<!-- 삭제 -->
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성일자</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="DTO" items="${list }">
			<tr>
				<td>${DTO.boardNo }</td>
				<td>${DTO.title }</td>
				<td>${DTO.writer }</td>
				<td>${DTO.viewCount }</td>
				<td>${DTO.postTime }</td>
				<td><a href="">수정</a></td>
				<td><a href="">삭제</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7">
				<input type="button" value="새 글 쓰기" onclick="">
			</td>
		</tr>
	</table>
	<% System.out.println("게시판 출력 완료"); %>
</body>
</html>