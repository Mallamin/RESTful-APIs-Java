package com.weplus.firstexercise.rest.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity

public class Museum {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_museum")
	private long idMuseum;

	@Column
	private String name;
	@Column
	private String city;

//OneToMany Association(Museum&Painting)
	@OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_museum")
	private List<Painting> paintings = new ArrayList<Painting>();

	public List<Painting> getPaintings() {
		return paintings;
	}

	public void setPaintings(List<Painting> paintings) {
		this.paintings = paintings;
	}

	public Museum() {
		super();

	}

	public String getName() {
		return name;
	}

	public long getIdMuseum() {
		return idMuseum;
	}

	public void setIdMuseum(long idMuseum) {
		this.idMuseum = idMuseum;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
