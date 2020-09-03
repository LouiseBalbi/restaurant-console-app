package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {

	List<Plat> findByPrixEnCentimesEuros(Integer prixEnCentimesEuros);
	
}
