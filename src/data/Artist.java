package data;

import com.fasterxml.jackson.annotation.*;

public class Artist {
	private static int nextArtistID = 1;
	
	private int artistID;
	private String name;
	private String sortName;
	private String bioText;
	private String link;
	private String image;
	private String thumbnail;
	
	public Artist() { 
		artistID = nextArtistID++;
	}
	
	public Artist(String name, String sortName) {
		this();
		this.name = name;
		this.sortName = sortName;
	}
	
	@JsonCreator
	public Artist(
			@JsonProperty("artistID")  int artistID, 
			@JsonProperty("name")      String name, 
			@JsonProperty("sortName")  String sortName, 
			@JsonProperty("image")     String image, 
			@JsonProperty("thumbnail") String thumbnail,
			@JsonProperty("bioText")   String bioText,
			@JsonProperty("link")      String link ) {
		this();
		this.artistID  = artistID;
		this.name      = name;
		this.sortName  = sortName;
		this.image     = image;
		this.thumbnail = thumbnail;
		this.bioText   = bioText;
		this.link      = link;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getId() {
		return artistID;
	}

	public void setId(int artistID) {
		this.artistID = artistID;
	}

	public String getBioText() {
		return bioText;
	}

	public void setBioText(String bioText) {
		this.bioText = bioText;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
}
