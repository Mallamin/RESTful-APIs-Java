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

import com.weplus.firstexercise.rest.models.Painting;
import com.weplus.firstexercise.rest.repos.PaintingRepo;

@RestController
//@RequestMapping("/painting")
public class PaintingController {
	@Autowired
	private PaintingRepo paintingRepo;

	// Inserting a painting into the Database
	@PostMapping("/dipinto")
	public String savePainting(@RequestBody Painting painting) {
		paintingRepo.save(painting);
		return "Saved...";

	}

	// Retrieving all paintings from the database
	@GetMapping("/dipinto")
	public List<Painting> getPainting() {
		return paintingRepo.findAll();
	}

	// Replacing/modifying a painting with a given id
	@PutMapping("/dipinto/{id}")
	public String updatePainting(@PathVariable long id, @RequestBody Painting painting) {
		Painting updatedPainting = paintingRepo.findById(id).get();
		updatedPainting.setName(painting.getName());
		updatedPainting.setStyle(painting.getStyle());
		updatedPainting.setYearPainted(painting.getYearPainted());
		paintingRepo.save(updatedPainting);
		return "Updated...";
	}

	// - Deleting a specific painting from the database
	@DeleteMapping("/dipinto/{id}")
	public String deletePainting(@PathVariable long id) {

		Painting deletePainting = paintingRepo.findById(id).get();
		paintingRepo.delete(deletePainting);
		return "Deleted Painting with the id: " + id;
	}

	// Retrieving a specific painting
	@GetMapping("/dipinto/{id}")
	public Painting findPainting(@PathVariable long id) {
		Optional<Painting> painting = paintingRepo.findById(id);
		if (painting.isEmpty()) {
			throw new RuntimeException("Painting not found");
		}
		return painting.get();
	}

}
