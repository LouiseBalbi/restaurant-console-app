package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.entite.Plat;
import dev.service.PlatServiceVersion2;

@SpringJUnitConfig({JdbcTestConfig.class, PlatDaoJdbc.class})
@ActiveProfiles("jdbc")
public class PlatDaoJdbcIntegrationTest {
	
	@Autowired
	private PlatDaoJdbc platDaoJdbc;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void listerPlatsNonVide() {
		
		
		List<Plat> plats = platDaoJdbc.listerPlats();
		assertThat(plats).isNotEmpty();
		
	}
	
	   @Test
	    void ajouterPlat() {
	        platDaoJdbc.ajouterPlat("Tartiflette", 1500);

	        Integer prix = jdbcTemplate.queryForObject("select prix from plat where nom='Tartiflette'", Integer.class);

	        assertThat(prix).isEqualTo(1500);

	        //assertThat(platDaoJDBC.listerPlats()).extracting(Plat::getNom).contains("Tartiflette");
	    }

}
