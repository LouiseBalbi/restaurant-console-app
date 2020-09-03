package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.config.JpaConfig;

@SpringJUnitConfig({JdbcTestConfig.class, JpaConfig.class, PlatDaoJpa.class})
@ActiveProfiles("jpa")
public class PlatDaoJpaIntegrationTest {
	
	@Autowired
	private IPlatDao dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void listerPlatsNonVide() {
		int tailleListe = dao.listerPlats().size();
		assertThat(tailleListe > 0);
	}
	
	@Test
	public void ajouterPlat() {
		dao.ajouterPlat("Tartiflette", 1500);
		
		Integer prix = jdbcTemplate.queryForObject("select prix from plat where nom='Tartiflette'", Integer.class);

        assertThat(prix).isEqualTo(1500);

	}

}
