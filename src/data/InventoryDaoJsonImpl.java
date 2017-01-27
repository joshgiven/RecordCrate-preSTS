package data;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;

public class InventoryDaoJsonImpl implements InventoryDAO {

	private Set<Album> albums;
	private Set<Artist> artists;
	private String jsonFile;
	
	
	public InventoryDaoJsonImpl() {
		albums  = new HashSet<>();
		artists = new HashSet<>();
	}
	
	public InventoryDaoJsonImpl(String fileName) {
		this();
		jsonFile = fileName;
		loadFromFile();
	}
	
	
	@JsonCreator
	public InventoryDaoJsonImpl(
			@JsonProperty("albums") Set<Album> albums, 
			@JsonProperty("artists") Set<Artist> artists, 
			@JsonProperty("jsonFile") String jsonFile) {
		this();
		this.albums = albums;
		this.artists = artists;
		this.jsonFile = jsonFile;
	}

	static class JsonStruct {
		private Set<Album> albums;
		private Set<Artist> artists;
		
		public Set<Album> getAlbums() {
			return albums;
		}
		public void setAlbums(Set<Album> albums) {
			this.albums = albums;
		}
		public Set<Artist> getArtists() {
			return artists;
		}
		public void setArtists(Set<Artist> artists) {
			this.artists = artists;
		}
	}
	
	private void loadFromFile() {
		boolean fakeOut = true;
		if(fakeOut) {
			fakeData();
			return;
		}
		
		System.out.println("reading from JSON file ("+ jsonFile +")");
		try {
			File inputFile = new File(jsonFile);
			ObjectMapper mapper = new ObjectMapper();
			JsonStruct tmp = 
					mapper.readValue(inputFile, JsonStruct.class);
			
			this.artists = tmp.artists;
			this.albums = tmp.albums;
		}
		catch (JsonParseException e) {
			e.printStackTrace();
		}
		catch (JsonMappingException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void flushToFile() {
		System.out.println("writing JSON file ("+ jsonFile +")");
		
		File outputFile = new File(jsonFile);
		ObjectMapper mapper = new ObjectMapper();

		try {
			JsonStruct tmp = new JsonStruct();
			tmp.artists = this.artists;
			tmp.albums = this.albums;
			mapper.writeValue(outputFile, tmp);
		}
		catch (JsonParseException e) {
			e.printStackTrace();
		}
		catch (JsonMappingException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateAlbum(Album album) {
		Album oldAlbum = getAlbumById(album.getId());

		if(oldAlbum != null) {
			albums.remove(oldAlbum);
		}
		
		albums.add(album);
		
		flushToFile();
	}

	@Override
	public List<Album> getAlbums(Predicate<Album> filter, Comparator<Album> sortMethod) {
		List<Album> albumsList = new ArrayList<>(albums);
		albumsList.removeIf(filter.negate());
		albumsList.sort(sortMethod);
		
		return albumsList;
	}

	@Override
	public void updateArtist(Artist artist) {
		Artist oldArtist = getArtistById(artist.getId());

		if(oldArtist != null) {
			artists.remove(oldArtist);
		}
		
		artists.add(artist);
		
		flushToFile();
	}
	
	@Override
	public void destroyArtist(Artist artist) {
		if(artist == null)
			return;
		
		for(Album album : getAlbumsByArtist(artist)) {
			destroyAlbum(album);
		}
		
		artists.remove(artist);
		flushToFile();
	}

	private void destroyAlbum(Album album) {
		if(album == null)
			return;
		
		albums.remove(album);
		flushToFile();
	}

	public Artist getArtistById(int id) {
		Artist artist = null;
		
		for(Artist a : artists) {
			if(a.getId() == id) {
				artist = a;
				break;
			}
		}
		
		return artist;
	}

	@Override
	public List<Artist> getArtists(Predicate<Artist> filter, Comparator<Artist> sortMethod) {
		List<Artist> artistsList = new ArrayList<>(artists);
		artistsList.removeIf(filter.negate());
		artistsList.sort(sortMethod);
		
		return artistsList;
	}

	@Override
	public Album getAlbumById(int id) {
		Album album = null;
		
		for(Album a : albums) {
			if(a.getId() == id) {
				album = a;
				break;
			}
		}
		
		return album;
	}

	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
		
		loadFromFile();
	}

	
	private void fakeData() {
		InventoryDAO dao = this;
		
		Artist fz = new Artist();
		fz.setName("Frank Zappa");
		fz.setSortName("Zappa, Frank");
		fz.setBioText("Born in Baltimore, named his kid Dweezil");
		fz.setLink("https://en.wikipedia.org/wiki/Frank_Zappa");
		fz.setImage("https://lastfm-img2.akamaized.net/i/u/770x0/fe453388772647028a36417858025a4d.jpg");
		fz.setThumbnail("https://lastfm-img2.akamaized.net/i/u/avatar170s/fe453388772647028a36417858025a4d.jpg");
		dao.updateArtist(fz);
		
		Album apos = new Album();
		apos.setArtist(fz);
		apos.setTitle("Apostrophe");
		apos.setYear(1974);
		apos.setGenre("Progressive Rock");
		apos.setCoverImage("https://e.snmc.io/lk/f/l/4e93881413045d3f8a7dd9c226b019e8/1575872.jpg");
		apos.setCoverThumbnail("https://e.snmc.io/lk/f/l/4e93881413045d3f8a7dd9c226b019e8/1575872.jpg");
		apos.setRating(80);
		apos.setCompilation(false);
		dao.updateAlbum(apos);

		Album zoot = new Album();
		zoot.setArtist(fz);
		zoot.setTitle("Zoot Allures");
		zoot.setYear(1976);
		zoot.setGenre("Progressive Rock");
		zoot.setCoverImage("https://e.snmc.io/lk/f/l/c33f0e42c069d36808cd61e25d342056/1576054.jpg");
		zoot.setCoverThumbnail("https://e.snmc.io/lk/f/l/c33f0e42c069d36808cd61e25d342056/1576054.jpg");
		zoot.setRating(70);
		zoot.setCompilation(false);
		dao.updateAlbum(zoot);
	
		Artist eno = new Artist();
		eno.setName("Brian Eno");
		eno.setSortName("Eno, Brian");
		eno.setBioText("Commonly appears in crossword puzzles");
		eno.setLink("https://en.wikipedia.org/wiki/Brian_Eno");
		eno.setImage("https://lastfm-img2.akamaized.net/i/u/770x0/85310ea903ba49b49f56c56fdf39e6b7.jpg");
		eno.setThumbnail("https://lastfm-img2.akamaized.net/i/u/avatar170s/85310ea903ba49b49f56c56fdf39e6b7.jpg");
		dao.updateArtist(eno);
		
		Album album = new Album();
		album.setArtist(eno);
		album.setTitle("Ambient 1: Music for Airports");
		album.setYear(1978);
		album.setGenre("Ambient");
		album.setCoverImage("http://e.snmc.io/lk/f/l/86ed50fba34a0f210fa3ecef4b85ce33/1576321.jpg");
		album.setCoverThumbnail("http://e.snmc.io/lk/f/l/86ed50fba34a0f210fa3ecef4b85ce33/1576321.jpg");
		album.setRating(80);
		album.setCompilation(false);
		dao.updateAlbum(album);
		
		Artist artist;
		artist = new Artist();
		artist.setName("Mogwai");
		artist.setSortName("Mogwai");
		artist.setBioText("Scottish band named after the creatures from \"Gremlins\"");
		artist.setLink("https://en.wikipedia.org/wiki/Mogwai");
		artist.setImage("https://lastfm-img2.akamaized.net/i/u/770x0/109a2687ef8b4183a7cecf09e6672e2f.jpg");
		artist.setThumbnail("https://lastfm-img2.akamaized.net/i/u/avatar170s/109a2687ef8b4183a7cecf09e6672e2f.jpg");
		dao.updateArtist(artist);
		
//		Album album = new Album();
		album = new Album();
		album.setArtist(artist);
		album.setTitle("Happy Songs for Happy People");
		album.setYear(2003);
		album.setGenre("Post-Rock");
		album.setCoverImage("http://e.snmc.io/lk/f/l/978e9850e081426740bb4aa5bc366b94/1278733.jpg");
		album.setCoverThumbnail("http://e.snmc.io/lk/f/l/978e9850e081426740bb4aa5bc366b94/1278733.jpg");
		album.setRating(80);
		album.setCompilation(false);
		dao.updateAlbum(album);
		
//		Artist artist = new Artist();
//		artist.setName("");
//		artist.setSortName("");
//		artist.setBioText("");
//		artist.setLink("");
//		artist.setImage("");
//		artist.setThumbnail("");
//		dao.updateArtist(artist);

//		Album album = new Album();
//		album.setArtist(artist);
//		album.setTitle("");
//		album.setYear(1976);
//		album.setGenre("");
//		album.setCoverImage("");
//		album.setCoverThumbnail("");
//		album.setRating(70);
//		album.setCompilation(false);
//		dao.updateAlbum(album);

	}
	
}
