package data;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface InventoryDAO {
	final Comparator<Album> ALBUM_SORT_DEFAULT = 
		new Comparator<Album>() {
			public int compare(Album a, Album b) {
				// sortName, date, title
				String[] aFields = { 
						a.getArtist().getSortName(),
						"" + a.getYear(),
						a.getTitle()
						};
			
				String[] bFields = { 
						b.getArtist().getSortName(),
						"" + b.getYear(),
						b.getTitle()
				};
				
				int retVal = 0;
				for(int i = 0; i < aFields.length; i++) {
					retVal = aFields[i].compareTo(bFields[i]);
					if(retVal != 0)
						break;
				}
				
				return 0;
			}};
			
	final Comparator<Album> ALBUM_SORT_BY_YEAR = 
			(a,b) -> Integer.compare(a.getYear(), b.getYear());
	
	void updateAlbum(Album album);
	
	Album getAlbumById(int id);
	
	List<Album> getAlbums(Predicate<Album> filter, Comparator<Album> sortMethod);
	
	default List<Album> getAllAlbums() {
		return getAlbums(a -> true, ALBUM_SORT_DEFAULT);
	}
	
	default List<Album> getAlbumsByArtist(Artist artist) {
		return getAlbums( a -> a.getArtist().getName().equals(artist.getName()), 
		                  ALBUM_SORT_BY_YEAR );
	}

	void updateArtist(Artist artist);
	
	void destroyArtist(Artist theArtist);

	Artist getArtistById(int id);
	
	List<Artist> getArtists(Predicate<Artist> filter, Comparator<Artist> sortMethod);
	
	final Comparator<Artist> ARTIST_SORT_DEFAULT = (a,b) -> a.getSortName().compareTo(b.getSortName());
	default List<Artist> getAllArtists() {
		return getArtists( a -> true, ARTIST_SORT_DEFAULT );
	}
}
