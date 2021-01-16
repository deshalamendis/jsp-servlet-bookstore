<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Store Application</title>
</head>
<script type="text/javascript">
	function deleteConfirmation(id) {
		var doIt = confirm('Do you want to delete the book?');
		
		if(doIt == true){
			document.forms[0].action = "/BookStore/delete?id=" + id;
			document.forms[0].submit();
		}
		else{}
	}
</script>
<body>
	<center>
		<h1>Book Management</h1>
		<h2>
			<a href="/BookStore/new">Add New Book</a>
			&nbsp;&nbsp;&nbsp;
			<a href="/BookStore/list">List All Books</a> 
			&nbsp;&nbsp;&nbsp;
			<a href="/BookStore/report">Create Report</a> 
		</h2>
	</center>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<caption><h2>List of Books</h2></caption>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="book" items="${listBook }">
				<tr>
					<td><c:out value="${book.id}" /></td>
					<td><c:out value="${book.title}" /></td>
					<td><c:out value="${book.author}" /></td>
					<td><c:out value="${book.price}" /></td>
					<td>
						<a href="/BookStore/edit?id=<c:out value='${book.id }' />">Edit</a>
						&nbsp;&nbsp;&nbsp;
						<%-- <a href="/BookStore/delete?id=<c:out value='${book.id }' />">Delete</a> --%>
						
						<input type="submit" name="delete" value="Delete" onClick="deleteConfirmation(<c:out value='${book.id }' />)" />
						<!-- <a >Delete</a> -->
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>