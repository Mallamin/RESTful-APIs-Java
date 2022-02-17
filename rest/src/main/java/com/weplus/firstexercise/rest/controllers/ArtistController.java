package com.weplus.firstexercise.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weplus.firstexercise.rest.dto.PaintingDto;
import com.weplus.firstexercise.rest.models.Artist;
import com.weplus.firstexercise.rest.models.Painting;
import com.weplus.firstexercise.rest.repos.ArtistRepo;
import com.weplus.firstexercise.rest.repos.PaintingRepo;

@RestController
public class ArtistController {
	@Autowired
	private ArtistRepo artistRepo;

	@Autowired
	private PaintingRepo paintingRepo;

//Inserting an artist into the Database	 			
	@PostMapping("/artista")
	public String saveArtist(@RequestBody Artist artist) {
		artistRepo.save(artist);
		return "Saved...";
	}

//Retrieving all artists from the database
	@GetMapping("/artista")
	public List<Artist> getArtist() {
		return artistRepo.findAll();

	}

//Replacing/modifying an artist with a given id
	@PutMapping("artista/{id}")
	public String updateArtist(@PathVariable long id, @RequestBody Artist artist) {
		Artist updatedArtist = artistRepo.findById(id).get();
		updatedArtist.setFirstName(artist.getFirstName());
		updatedArtist.setLastName(artist.getLastName());
		updatedArtist.setDateOfBirth(artist.getDateOfBirth());
		updatedArtist.setCityOfBirth(artist.getCityOfBirth());
		updatedArtist.setDateOfDeath(artist.getDateOfDeath());
		artistRepo.save(updatedArtist);
		return "Updated";
	}

//- Deleting a specific artist from the database
	@DeleteMapping("/artista/{id}")
	public String deleteArtist(@PathVariable long id) {
		Optional <Artist> deleteArtist = artistRepo.findById(id);
		
		if (deleteArtist.isEmpty()) {
			throw new RuntimeException("Artist not found");
		}
		artistRepo.delete(deleteArtist.get());
		return "Deleted artist with the id:" + id;
	}

//Retrieving a specific Artist
	@GetMapping("/artista/{id}")
	public Artist findArtist(@PathVariable long id) {
		Optional<Artist> artist = artistRepo.findById(id);
		if (artist.isEmpty()) {
			throw new RuntimeException("Artist not found");
		}
		return artist.get();
	}

//Association Painting & Artist

	@PostMapping("/artista/{idArtista}/dipinto")
	public String savePaintArtist(@PathVariable long idArtista, @RequestBody PaintingDto paintingDto) {
		
		try {
			System.out.println(idArtista);
			System.out.println();
		Optional<Artist> artistOpt = artistRepo.findById(idArtista);
		Artist theArtist = null;
		if (!artistOpt.isPresent()) {
			throw new RuntimeException("Can't find Artist with id :" + idArtista);
		} else {

			theArtist = artistOpt.get();
		}
		// System.out.println(painting.getIdPainting());
		Optional<Painting> paintingOpt = paintingRepo.findById(paintingDto.getIdDipinto());
		if (paintingOpt.isPresent()) {
			// System.out.println(paintingOpt.get().getName());
			Painting thepainting = null;
			thepainting = paintingOpt.get();
			// System.out.println(theArtist.getPaintings().size());
			theArtist.getPaintings().add(thepainting);
			// System.out.println(theArtist.getPaintings().size());
			// System.out.println(theArtist.getFirstName());
			artistRepo.save(theArtist);
		} else {
			System.out.println("Painting not found");

		}

		return "Saved...";
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
