package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entite.Ingredient;
import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {

	List<Plat> findByPrixEnCentimesEuros(Integer prixEnCentimesEuros);
	
	@Query("select avg(p.prixEnCentimesEuros) from Plat p where p.prixEnCentimesEuros > ?1")
	Double AvgPrix(Integer prixEnCentimesEuros);
	
	
	@Modifying
	@Query("UPDATE Plat p SET p.nom = :nouveauNom WHERE p.nom = :ancienNom")
	void updateNomPlat(@Param("ancienNom") String ancienNom, @Param("nouveauNom") String nouveauNom);

	@Query("select p from Plat p join fetch p.ingredients where p.nom = ?1")
	Optional<Plat> findByNomWithIngredients(String nom);

}
