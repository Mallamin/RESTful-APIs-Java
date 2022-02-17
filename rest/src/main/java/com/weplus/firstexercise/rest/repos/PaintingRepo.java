package com.weplus.firstexercise.rest.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weplus.firstexercise.rest.models.Painting;
//import com.weplus.firstexercise.rest.models.PaintingModel;

public interface PaintingRepo extends JpaRepository<Painting, Long> {

}
