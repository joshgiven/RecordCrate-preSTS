<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Artist View</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<h1><a href="/Inventory/">Record Bin</a></h1>
	
	<h2>Artist View</h2>
	
	<img src="${artist.image}" alt="<c:out value="${artist.name}" />" />
	
	<ul>
		<li><c:out value="${artist.name}" /></li>
		<li><c:out value="${artist.bioText}" /></li>
		<li><a href="<c:out value="${artist.link}" />"><c:out value="${artist.link}" /></a></li>
	</ul>
	
	<h2>Albums by Artist</h2>
	<table>
		<thead>
			<tr>
				<td>Year</td><td>Title</td>
			</tr>
		</thead>
		<c:forEach var="album" items="${albums}">
			<tr>
				<td><img src="<c:out value="${album.coverThumbnail}" />" /></td>
				<td><c:out value="${album.year}" /></td>
				<td><a href="album.do?id=<c:out value="${album.id}" />"><c:out value="${album.title}" /></a></td>
			</tr>
		</c:forEach>
	</table>
	
	<ul>
		<li><a href="updateArtist.do?id=<c:out value="${artist.id}" />">edit</a></li>
		<li><a href="">delete</a></li>
	</ul>
	
</body>
</html>