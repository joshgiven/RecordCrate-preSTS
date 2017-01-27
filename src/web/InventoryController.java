package web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import data.*;

@Controller
public class InventoryController {

	@Autowired
	private WebApplicationContext wac;
	
	private InventoryDAO dao;
	
	@PostConstruct
	void tmpInitDAO() {
		String file = wac.getServletContext().getRealPath("WEB-INF/data.txt");
		dao = new InventoryDaoJsonImpl(file);
	}
	
	@RequestMapping(path="index.do", method=RequestMethod.GET)
	public ModelAndView displayIndex() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("albums", dao.getAllAlbums());
		mv.addObject("artists", dao.getAllArtists());
		
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(path="create.do", method=RequestMethod.GET)
	public ModelAndView createForm() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("artists", dao.getAllArtists());
		mv.setViewName("createForm");

		return mv;
	}
	
	@ModelAttribute("newArtist")
	Artist newArtist() {
		return new Artist();
	}
	
	@RequestMapping(path="createArtist.do", method=RequestMethod.POST)
	public ModelAndView createArtist(
			@ModelAttribute("newArtist") Artist newArtist ) {
		
		dao.updateArtist(newArtist);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("artist", newArtist);
		mv.addObject("albums", dao.getAlbumsByArtist(newArtist));
		
		mv.setViewName("artist");
		return mv;
	}

	@RequestMapping(path="artist.do", method=RequestMethod.GET)
	public ModelAndView displayArtist(
			@RequestParam Integer id ) {
		ModelAndView mv = new ModelAndView();
		
		Artist artist = dao.getArtistById(id);
		mv.addObject("artist", artist);
		mv.addObject("albums", dao.getAlbumsByArtist(artist));
		
		mv.setViewName("artist");
		return mv;
	}

	@RequestMapping(path="updateArtist.do", method=RequestMethod.GET)
	public ModelAndView updateArtist( @RequestParam Integer id ) {
		
		Artist theArtist = dao.getArtistById(id);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("update", true);
		mv.addObject("theArtist", theArtist);
		
		mv.setViewName("createForm");
		return mv;

	}
	
	@RequestMapping(path="destroyArtist.do", method=RequestMethod.POST)
	public ModelAndView destroyArtist( @RequestParam Integer id ) {
		Artist theArtist = dao.getArtistById(id);
		dao.destroyArtist(theArtist);
		
		return displayIndex();
	}
	
	@ModelAttribute("newAlbum") 
	Album newAlbum() {
		return new Album();
	}

	@RequestMapping(path="createAlbum.do", method=RequestMethod.POST)
	public ModelAndView createAlbum(
			@RequestParam("artistId") int artistId,
			@ModelAttribute("newAlbum") Album newAlbum ) {
		
		Artist artist = dao.getArtistById(artistId);
		newAlbum.setArtist(artist);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("album", newAlbum);

		mv.setViewName("album");
		return mv;
	}

	@RequestMapping(path="album.do", method=RequestMethod.GET)
	public ModelAndView displayAlbum( @RequestParam Integer id ) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("album", dao.getAlbumById(id));
		mv.setViewName("album");
		return mv;
	}
	
	public ModelAndView updateAlbum() {
		return null;
	}
	
	public ModelAndView deleteAlbum( @RequestParam Integer id ) {
		return null;
	}

}
