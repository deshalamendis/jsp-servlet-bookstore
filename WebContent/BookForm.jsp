<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Store Application</title>
</head>
<body>
	<center>
		<h1>Book Management</h1>
		<h2>
			<a href="/BookStore/new">Add New Book</a>
			&nbsp;&nbsp;&nbsp;
			<a href="/BookStore/list">List All Books</a> 
		</h2>
	</center>
	
	<div align="center">
		
		<c:if test="${book != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${book == null}">
			<form action="insert" method="post">
		</c:if>
			<table border="1" cellpadding="5">
			
			<c:if test="${book != null}">
				<input type="hidden" name="id" value="<c:out value='${book.id }'/>"/>
			</c:if>
				
				<tr>
					<th>Title: </th>
					<c:if test="${book != null}">
					<td><input type="text" name="title" size="45" value="<c:out value='${book.title }'/>"/></td>
					</c:if>
					<c:if test="${book == null}">
					<td><input type="text" name="title" size="45" /></td>
					</c:if>
					
				</tr>
				<tr>
					<th>Author: </th>
					<c:if test="${book != null}">
					<td><input type="text" name="author" size="45" value="<c:out value='${book.author }'/>"/></td>
					</c:if>
					<c:if test="${book == null}">
					<td><input type="text" name="author" size="45" /></td>
					</c:if>
				</tr>
				<tr>
					<th>Price: </th>
					<c:if test="${book != null}">
					<td><input type="text" name="price" size="45" value="<c:out value='${book.price }'/>"/></td>
					</c:if>
					<c:if test="${book == null}">
					<td><input type="text" name="price" size="10" /></td>
					</c:if>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<c:if test="${book != null}">
						<input type="submit" value="Update"/>
					</c:if>
					<c:if test="${book == null}">
						<input type="submit" value="Save"/>
					</c:if>
					</td>
				</tr>
				
			</table>
		</form>
		
	</div>
</body>
</html>