package repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.config.JdbcTestConfig;
import dev.config.JpaConfig;
import dev.dao.PlatDaoJpa;
import dev.entite.Plat;
import dev.repository.PlatRepository;

@SpringJUnitConfig({JdbcTestConfig.class, JpaConfig.class})
@ActiveProfiles("jpa")
public class PlatRepositoryIntegrationTest {
	
	@Autowired
	private PlatRepository platRepository;

	
	@Test
	public void testFindAll() {
		List<Plat> plats = platRepository.findAll();
		assertThat(plats.size()).isEqualTo(6);
	}
	
	
	@Test
	public void testFindAllSortAsc() {
		List <Plat> plats = platRepository.findAll(Sort.by(Sort.Direction.ASC, "prixEnCentimesEuros"));
		assertThat(plats.get(0).getNom()).isEqualTo("Côte de boeuf");
	}
	
	@Test
	public void testFindAllSortDesc() {
		List <Plat> plats = platRepository.findAll(Sort.by(Sort.Direction.DESC, "prixEnCentimesEuros"));
		assertThat(plats.get(0).getNom()).isEqualTo("Gigot d'agneau");
	}
	
	@Test
	public void testFindAllPageable(){
		Pageable p = PageRequest.of(0, 2);
		Page<Plat> pagePlat = platRepository.findAll(p);
		assertThat(pagePlat.getContent().get(0).getNom()).isEqualTo("Blanquette de veau");
		assertThat(pagePlat.getContent().get(1).getNom()).isEqualTo("Couscous");
	}
	
	@Test
	public void testFindById() {
		assertThat(platRepository.findById(1).get().getNom()).isEqualTo("Magret de canard");
	}
	
	@Transactional
	@Test
	public void testGetOne() {
		assertThat(platRepository.getOne(1).getNom()).isEqualTo("Magret de canard");
	}
	
	
	@Test
	public void testCount() {
		assertThat(platRepository.count()).isEqualTo(6);
	}
	
	@Test
	public void testFindByPrix() {
		assertThat(platRepository.findByPrixEnCentimesEuros(1100)).extracting(Plat::getNom).contains("Côte de boeuf");
	}

}
