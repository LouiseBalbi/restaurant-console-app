package dev.entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	
	@ManyToMany(mappedBy="ingredients", cascade=CascadeType.ALL)
	private List<Plat> plats = new ArrayList<Plat>();
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the plats
	 */
	public List<Plat> getPlats() {
		return plats;
	}
	/**
	 * @param plats the plats to set
	 */
	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}
	
	

}
