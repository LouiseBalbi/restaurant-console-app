package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.IPlatDao;
import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;
import dev.exception.PlatException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PlatServiceVersion2.class, PlatDaoMemoire.class })
@ActiveProfiles({"service2", "memoire"})
public class PlatServiceVersion2IntegrationTest {

	@Autowired
	private PlatServiceVersion2 platServiceVersion2;

	@Test
	public void ajouterPlatValide() {

		platServiceVersion2.ajouterPlat("PlatTest", 1234);
		List<Plat> listePlat = platServiceVersion2.listerPlats();
		assertThat(listePlat.size()).isEqualTo(1);
		assertThat(listePlat).extracting(Plat::getNom).contains("PlatTest");
		assertThat(listePlat).extracting(Plat::getPrixEnCentimesEuros).contains(1234);

	}

	@Test
	public void ajouterPlatNomInvalide() throws PlatException {

		//assertThrows(PlatException.class, () -> platServiceVersion2.ajouterPlat("PlatTest", 2));
		
		assertThatThrownBy( () -> platServiceVersion2.ajouterPlat("PlatTest", 2))
		.isInstanceOf(PlatException.class)
		.hasMessage("le prix d'un plat doit être supérieur à 10 €");
	}

}
