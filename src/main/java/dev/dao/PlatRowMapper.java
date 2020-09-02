package dev.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.entite.Plat;


public class PlatRowMapper implements RowMapper<Plat>{

	@Override
	public Plat mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Plat plat = new Plat();
		plat.setId(resultSet.getInt("id"));
		plat.setNom(resultSet.getString("nom"));
		plat.setPrixEnCentimesEuros(resultSet.getInt("prixEnCentimesEuros"));
        return plat;
	}

}