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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weplus.firstexercise.rest.dto.PaintingDto;
import com.weplus.firstexercise.rest.models.Artist;
import com.weplus.firstexercise.rest.models.Museum;
import com.weplus.firstexercise.rest.models.Painting;
import com.weplus.firstexercise.rest.repos.MuseumRepo;
import com.weplus.firstexercise.rest.repos.PaintingRepo;

@RestController
//@RequestMapping("/museum")
public class MuseumController {
	@Autowired
	private MuseumRepo museumRepo;

	@Autowired
	private PaintingRepo paintingRepo;

//Inserting a museum into the Database			
	@PostMapping("/museo")
	public String saveMuseum(@RequestBody Museum museum) {
		museumRepo.save(museum);
		return "Saved...";
	}

// Retrieving all museums from the database
	@GetMapping("/museo")
	public List<Museum> getMuseums() {
		return museumRepo.findAll();
	}

//Replacing/modifying a museum with a given id
	@PutMapping("/museo/{id}")
	public String updateMuseum(@PathVariable long id, @RequestBody Museum museum) {
		Museum updatedMuseum = museumRepo.findById(id).get();
		updatedMuseum.setName(museum.getName());
		updatedMuseum.setCity(museum.getCity());
		museumRepo.save(updatedMuseum);
		return "Updated...";
	}

//- Deleting a specific museum from the database
	@DeleteMapping("/museo/{id}")
	public String deleteMuseum(@PathVariable long id) {
		Optional<Museum> deleteMuseum = museumRepo.findById(id);
		if (deleteMuseum.isEmpty()) {
			throw new RuntimeException("Museum not found");
		}

		museumRepo.delete(deleteMuseum.get());
		return "Deleted Museum with the id:  " + id;
	}

//Retrieving a specific museum
	@GetMapping("/museo/{id}")
	public Museum findMuseum(@PathVariable long id) {
		Optional<Museum> museum = museumRepo.findById(id);
		if (museum.isEmpty()) {
			throw new RuntimeException("Museum not found");
		}
		return museum.get();
	}

	// Association-painting & museum
	@PostMapping("museo/{idMuseo}/dipinto")
	public String savePaintMuseum(@PathVariable long idMuseo, @RequestBody PaintingDto paintingDto) {
		try {
			
		System.out.println(idMuseo);
		System.out.println();
		Optional<Museum> museumOpt = museumRepo.findById(idMuseo);
		Museum theMuseum = null;
		if (!museumOpt.isPresent()) {
			throw new RuntimeException("Can't find Museum with id :" + idMuseo);
		} else {

			theMuseum = museumOpt.get();
		}
		// System.out.println(painting.getIdPainting());
		Optional<Painting> paintingOpt = paintingRepo.findById(paintingDto.getIdDipinto());
		if (paintingOpt.isPresent()) {
			// System.out.println(paintingOpt.get().getName());
			Painting thepainting = null;
			thepainting = paintingOpt.get();
			// System.out.println(theMuseum.getPaintings().size());
			theMuseum.getPaintings().add(thepainting);
			// System.out.println(theMuseum.getPaintings().size());
			// System.out.println(theMuseum.getName());
			museumRepo.save(theMuseum);
		} else {
			System.out.println("Painting not found");

		}

		return "Saved...";
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
