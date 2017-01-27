package data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Album {
	private static int nextAlbumID = 1;
	
	private int albumID;
	private String title;
	private Artist artist;
	private int year;
	private String genre;
	private List<Track> tracks;
	private int rating;
	private boolean compilation;
	private String coverImage;
	private String coverThumbnail;
	
	public Album() {
		albumID = nextAlbumID++;
	}
	
	public Album(String title, Artist artist, int year, String genre, boolean compilation) {
		this();
		this.title = title;
		this.artist = artist;
		this.year = year;
		this.genre = genre;
		this.compilation = compilation;
	}
	
	@JsonCreator
	public Album(
			@JsonProperty("albumID") int albumID, 
			@JsonProperty("title") String title, 
			@JsonProperty("artist") Artist artist, 
			@JsonProperty("year") int year, 
			@JsonProperty("genre") String genre, 
			@JsonProperty("tracks") List<Track> tracks, 
			@JsonProperty("rating") int rating,
			@JsonProperty("compilation") boolean compilation, 
			@JsonProperty("coverImage") String coverImage, 
			@JsonProperty("coverThumbnail") String coverThumbnail) {
		this();
		this.albumID = albumID;
		this.title = title;
		this.artist = artist;
		this.year = year;
		this.genre = genre;
		this.tracks = tracks;
		this.rating = rating;
		this.compilation = compilation;
		this.coverImage = coverImage;
		this.coverThumbnail = coverThumbnail;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public List<Track> getTracks() {
		return tracks;
	}
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	public boolean isCompilation() {
		return compilation;
	}
	public void setCompilation(boolean compilation) {
		this.compilation = compilation;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public String getCoverThumbnail() {
		return coverThumbnail;
	}
	public void setCoverThumbnail(String coverThumbnail) {
		this.coverThumbnail = coverThumbnail;
	}

	public void setId(int id) {
		albumID = id;
	}
	
	public int getId() {
		return albumID;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		if(rating < 0)
			rating = 0;
		if(rating > 100)
			rating = 100;
		
		this.rating = rating;
	}
}
