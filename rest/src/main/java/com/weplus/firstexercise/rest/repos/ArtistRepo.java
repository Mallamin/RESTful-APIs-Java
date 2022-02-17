package com.weplus.firstexercise.rest.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weplus.firstexercise.rest.models.Artist;

public interface ArtistRepo extends JpaRepository<Artist, Long> {

}
