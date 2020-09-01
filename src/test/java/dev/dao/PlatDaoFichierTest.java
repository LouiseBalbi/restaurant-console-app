package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;


@SpringJUnitConfig(PlatDaoFichier.class)
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoFichierTest {
	
	
	@Autowired
	private PlatDaoFichier platDaoFichier;
	
	@Test
	public void ajouterPlatValide() {

		platDaoFichier.ajouterPlat("PlatTest", 1234);
		List<Plat> listePlat = platDaoFichier.listerPlats();
		assertThat(listePlat).extracting(Plat::getNom).contains("PlatTest");
		assertThat(listePlat).extracting(Plat::getPrixEnCentimesEuros).contains(1234);
		assertThat(listePlat.size()).isEqualTo(1);

	}
	
	@Test
	public void ajouterPlatBis() {

		platDaoFichier.ajouterPlat("PlatTest", 1234);
		List<Plat> listePlat = platDaoFichier.listerPlats();
		assertThat(listePlat.size()).isEqualTo(1);

	}
	


}
