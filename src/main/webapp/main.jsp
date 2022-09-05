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
				<td><div align="center">${DTO.boardNo }</div></td>
				<td><a href="controller.do?command=one&boardNo=${DTO.boardNo }">${DTO.title }</a></td>
				<td><div align="center">${DTO.writer }</div></td>
				<td><div align="center">${DTO.viewCount }</div></td>
				<td>${DTO.postTime }</td>
				<td><div align="center"><a href="controller.do?command=update&boardNo=${DTO.boardNo }">수정</a></div></td>
				<td><div align="center"><a href="controller.do?command=delete&boardNo=${DTO.boardNo }">삭제</a></div></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7">
				<div align="right">
					<input type="button" value="새 글 쓰기" onclick="location.href='controller.do?command=insert'">
				</div>
			</td>
		</tr>
	</table>
	<% System.out.println("게시판 출력 완료"); %>
</body>
</html>