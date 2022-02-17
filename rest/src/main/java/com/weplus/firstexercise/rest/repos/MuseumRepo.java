package com.weplus.firstexercise.rest.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weplus.firstexercise.rest.models.Museum;

public interface MuseumRepo extends JpaRepository<Museum, Long> {

}
