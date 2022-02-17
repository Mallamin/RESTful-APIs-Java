package com.weplus.firstexercise.rest.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Painting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_painting")
	private long idPainting;
	@Column
	private String name;
	@Column
	private String style;
	@Column
	private LocalDate yearPainted;

	public Painting() {
		super();

	}

	public String getName() {
		return name;
	}

	public long getIdPainting() {
		return idPainting;
	}

	public void setIdPainting(long idPainting) {
		this.idPainting = idPainting;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public LocalDate getYearPainted() {
		return yearPainted;
	}

	public void setYearPainted(LocalDate yearPainted) {
		this.yearPainted = yearPainted;
	}

}
