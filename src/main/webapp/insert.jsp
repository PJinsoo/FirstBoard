<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<% System.out.println("새 글 쓰기 버튼 클릭"); %>
<title>Insert title here</title>
</head>
<body>
	<h1>새 글 쓰기</h1>
	<form action="controller.do" method="post">
		<input type="hidden" name="command" value="boardInsert">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="boardWriter"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="boardTitle"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="boardContent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="작성하기">
					<input type="button" value="취소" onclick="loacation.href=controller.do?command=main">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>