<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Create</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<h1><a href="/Inventory/">Record Bin</a></h1>
	
	<a name="CreateArtist"></a><h2>Create Artist</h2>
	
	<form action="createArtist.do" method="POST">
<!-- 		
		name("Brian Eno");
		sortName("Eno, Brian");
		bioText("Commonly appears in crossword puzzles");
		link("https://en.wikipedia.org/wiki/Brian_Eno");
		image("https://lastfm-img2.akamaized.net/i/u/770x0/85310ea903ba49b49f56c56fdf39e6b7.jpg");
		thumbnail("https://lastfm-img2.akamaized.net/i/u/avatar170s/85310ea903ba49b49f56c56fdf39e6b7.jpg");
 -->	
 		<ul>
			<li><label>Artist Name<input type="text" name="name" <c:if test="${update == true}"> value="<c:out value="${theArtist.name}"/>"</c:if>></label></li>
			<li><label>Sort Name<input type="text" name="sortName" <c:if test="${update == true}"> value="<c:out value="${theArtist.sortName}"/>"</c:if>></label></li>
	 		<li><label>Short Bio<textarea name="bioText"> <c:if test="${update == true}"><c:out value="${theArtist.bioText}"/></c:if></textarea></label></li>
			<li><label>WWW Link<input type="text" name="link" <c:if test="${update == true}"> value="<c:out value="${theArtist.link}"/>"</c:if>></label></li>
			<li><label>Image Path<input type="text" name="image" <c:if test="${update == true}"> value="<c:out value="${theArtist.image}"/>"</c:if>></label></li>
			<li><label>Thumbnail Path<input type="text" name="thumbnail" <c:if test="${update == true}"> value="<c:out value="${theArtist.thumbnail}"/>"</c:if>></label></li>
		</ul>
		<c:choose>
			<c:when test="${update == true}">
 				<input type="hidden" name="id" value="<c:out value="${theArtist.id}"/>">
				<button>Update Artist</button>
			</c:when>
			<c:otherwise>
				<button>Add Artist</button>
			</c:otherwise>
		</c:choose>
	</form>
	
	<c:if test="${update == true}">
	<form action="destroyArtist.do" method="POST">
		<input type="hidden" name="id" value="<c:out value="${theArtist.id}"/>">
		<button class="deleteButton">Delete Artist</button>
		<label><input type="checkbox">safety switch</label>
	</form>
	</c:if>
	
	<a name="CreateAlbum"></a><h2>Create Album</h2>
	
	<form action="createAlbum.do" method="POST">
		<ul>
		<!-- 	
		private String title;
		private Artist artist;
		private int year;
		private String genre;
		private List<Track> tracks;
		private int rating;
		private boolean compilation;
		private String coverImage;
		private String coverThumbnail;
		 -->
			<li>
				<label>Artist
					<select name="artistId">
					<c:forEach var="artist" items="${artists}">
						<option value="<c:out value="${artist.id}"/>"><c:out value="${artist.name}"/></option>
					</c:forEach>
					</select>
				</label>
			</li>
			<li><label>Title<input type="text" name="title"></label></li>
			<li><label>Year<input type="number" name="year" min="1900" max="2100"></label></li>
	 		<li><label>Genre<input type="text" name="genre"></label></li>
			<li><label>Image Path<input type="text" name="coverImage"></label></li>
			<li><label>Thumbnail Path<input type="text" name="coverThumbnail"></label></li>
		</ul>
		<button>Add Album</button>
	</form>
	
	<c:if test="${update == true}">
	<form action="destroyAlbum.do" method="POST">
		<input type="hidden" name="id" value="<c:out value="${theAlbum.id}"/>">
		<button class="deleteButton">Delete Album</button>
		<label><input type="checkbox">safety switch</label>
	</form>
	</c:if>
	
</body>
</html>