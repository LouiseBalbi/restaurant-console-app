package dev.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

public class PlatDaoMemoireTest {

	private PlatDaoMemoire platDaoMemoire;

	@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}

	@Test
	void listerPlatsVideALInitialisation() {

		List<Plat> resultat = platDaoMemoire.listerPlats();

		// test
		Assertions.assertThat(resultat).isEmpty();
	}

	@Test
	  void ajouterPlatCasPassants() {
		
		List<Plat> resultatAvant = platDaoMemoire.listerPlats();
		assertThat(resultatAvant.size()).isEqualTo(0);
		 
		  Plat platTest = new Plat("PlatTest", 1100);
		  platDaoMemoire.ajouterPlat("PlatTest", 1100);
		  
		  List<Plat> resultatApres = platDaoMemoire.listerPlats();
		  assertThat(resultatApres.size()).isEqualTo(1);
		  assertThat(resultatApres).extracting(Plat::getNom).containsAnyOf("PlatTest");
		  assertThat(resultatApres).extracting(Plat::getPrixEnCentimesEuros).containsAnyOf(1100);
		  
		  
//		  Assertions.assertThat(platTest.getNom()).isEqualTo("PlatTest");
//		  Assertions.assertThat(platTest.getPrixEnCentimesEuros()).isEqualTo(1100);

		  
	  }

}
