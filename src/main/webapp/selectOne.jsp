<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% System.out.println("게시글 클릭"); %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 보기</h1>
	<table border="1">
		<col width="70px">
		<col width="100px">
		<col width="200px">
		<col width="100px">
		<col width="50px">
		<tr>
			<th>작성자</th>
			<td>${dto.writer }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60">${dto.content }</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="수정" onclick="">
				<input type="button" value="삭제" onclick="">
				<input type="button" value="글 목록" onclick="location.href='controller.do?command=main'">
			</td>
		</tr>
	</table>
	<% System.out.println("게시글(selectOne) 출력 완료"); %>
</body>
</html>