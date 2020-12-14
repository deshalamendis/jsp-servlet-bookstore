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
			<a href="/new">Add New Book</a>
			&nbsp;&nbsp;&nbsp;
			<a href="/list">List All Books</a> 
		</h2>
	</center>
	
	<div align="center">
		
		
		<form action="insert" method="post">
			<table border="1" cellpadding="5">
				
				<tr>
					<th>Title: </th>
					<td><input type="text" name="title" size="45" /></td>
				</tr>
				<tr>
					<th>Author: </th>
					<td><input type="text" name="author" size="45" /></td>
				</tr>
				<tr>
					<th>Price: </th>
					<td><input type="text" name="price" size="10" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Save"/>
					</td>
				</tr>
				
			</table>
		</form>
		
	</div>
</body>
</html>