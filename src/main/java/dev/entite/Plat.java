package dev.entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Plat {

    private String nom;
    @Column(name="prix")
    private Integer prixEnCentimesEuros;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @ManyToMany
    @JoinTable(name="plat_ingredient",
    			joinColumns=@JoinColumn(name="plat_id", referencedColumnName="id"),
    			inverseJoinColumns=@JoinColumn(name="ingredient_id", referencedColumnName="id"))
    private List<Ingredient> ingredients = new ArrayList<>();
    



	public Plat() {
    }

    public Plat(String nom, Integer prixEnCentimesEuros) {
        this.nom = nom;
        this.prixEnCentimesEuros = prixEnCentimesEuros;
    }
    
    /**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPrixEnCentimesEuros() {
        return prixEnCentimesEuros;
    }

    public void setPrixEnCentimesEuros(Integer prixEnCentimesEuros) {
        this.prixEnCentimesEuros = prixEnCentimesEuros;
    }

    /**
	 * @return the ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plat plat = (Plat) o;
        return nom.equals(plat.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
