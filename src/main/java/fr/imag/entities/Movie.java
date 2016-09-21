package fr.imag.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movie {
	@Id
	private String idMovie;
	private String title;
	private String year;
	private String genre;
	private String poster;
	private String directors;
	private String actors;
	private String overview;
	private String runtime;
	private String trailer;
}
