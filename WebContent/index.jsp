<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Record Bin</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<h1><a href="/Inventory/">Record Bin</a></h1>
	
	<h2>Album Gallery</h2>
	<ul>
	<c:forEach var="album" items="${albums}">
		<li>
			<a href="album.do?id=<c:out value="${album.id}" />">
				<img src="<c:out value="${album.coverThumbnail}" />" />
			</a>
			<a href="album.do?id=<c:out value="${album.id}" />">
				<c:out value="${album.title}" />
			</a>
			<a href="artist.do?id=<c:out value="${album.artist.id}" />">
				<c:out value="${album.artist.name}" />
			</a>
		</li>
	</c:forEach>
	</ul>
	
	<h2>Artists</h2>
	<ul>
	<c:forEach var="artist" items="${artists}">
		<li>
			<a href="artist.do?id=<c:out value="${artist.id}" />">
			<img src="<c:out value="${artist.thumbnail}" />" />
			<c:out value="${artist.name}" />
			</a>
		</li>
	</c:forEach>
	</ul>
	
	<h2>Add Items</h2>
	<ul>
		<li><a href="create.do#CreateAlbum">add album</a></li>
		<li><a href="create.do#CreateArtist">add artist</a></li>
	</ul>
	
</body>
</html>