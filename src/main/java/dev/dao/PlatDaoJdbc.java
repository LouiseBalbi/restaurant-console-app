package dev.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.entite.Plat;

@Repository
@Profile("jdbc")
public class PlatDaoJdbc implements IPlatDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public PlatDaoJdbc(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Plat> listerPlats() {
		return this.jdbcTemplate.query("select * from plat", new PlatRowMapper());
	}

	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		this.jdbcTemplate.update("insert into plat(nom, prixEnCentimesEuros) values(?, ?)", nomPlat, prixPlat);
	}

}
