<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% System.out.println("게시글 수정"); %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 수정</h1>
	<form action="controller.do" method="post">
		<input type="hidden" name="command" value="boardUpdate">
		<input type="hidden" name="boardNo" value="${dto.boardNo }">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td>${dto.writer }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="boardTitle" value="${dto.title }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="boardContent">${dto.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="수정하기">
					<input type="button" value="취소" onclick="location.href='controller.do?command=one&boardNo=${dto.boardNo }'">
				</td>
			</tr>
		</table>
	</form>

	<% System.out.println("게시글 수정 완료"); %>
</body>
</html>