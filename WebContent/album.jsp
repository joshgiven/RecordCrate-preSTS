<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Album View</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<h1><a href="/Inventory/">Record Bin</a></h1>
	
	<h2>Album View</h2>
	
	<img src="${album.coverImage}" alt="<c:out value="${album.title}" />" />
	
	<ul>
		<li><c:out value="${album.title}" /></li>
		<li><a href="artist.do?id=<c:out value="${album.artist.id}" />"><c:out value="${album.artist.name}" /></a></li>
		<li><c:out value="${album.year}" /></li>
		<li><c:out value="${album.genre}" /></li>
	</ul>
	
	<table>
		<thead>
			<tr>
				<td>Track</td><td>Title</td><td>Time</td>
			</tr>
		</thead>
		<c:forEach var="track" items="${album.tracks}">
			<tr>
				<td><c:out value="${track.index}" /></td>
				<td><c:out value="${track.title}" /></td>
				<td><c:out value="${track.time}" /></td>
			</tr>
		</c:forEach>
		<tfoot>
			<tr>
				<td colspan="2">Total Time</td><td>--:--</td>
			</tr>
		</tfoot>
	</table>
	
	<a href="">edit</a>
	<a href="">delete</a>
</body>
</html>