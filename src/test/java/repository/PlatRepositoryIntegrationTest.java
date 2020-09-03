package repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.assertj.core.data.Index;
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
import dev.entite.Ingredient;
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
		Pageable p = PageRequest.of(0, 2, Sort.Direction.ASC, "nom");
		Page<Plat> pagePlat = platRepository.findAll(p);
		assertThat(pagePlat.getSize()).isEqualTo(2);
		assertThat(pagePlat.getContent().get(0).getId()).isEqualTo(4);
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
	
	@Test
	public void testAvgPrix() {
		Double moyenne = platRepository.AvgPrix(0);
		Double sommeAvecFindAll = 0.0;
		
		List<Plat> listePlat = platRepository.findAll();
		for (Plat plat : listePlat) {
			sommeAvecFindAll += plat.getPrixEnCentimesEuros();
		}
		
		double moyenneJava8 = listePlat.stream().mapToInt(Plat::getPrixEnCentimesEuros).average().orElse(0);
		
		assertThat(moyenne).isEqualTo(sommeAvecFindAll/listePlat.size());
		assertThat(moyenne).isEqualTo(moyenneJava8);
	}
	


	
//	@Test
//	public void testFindByNomWithIngredients() {
//		Plat plat = platRepository.findByNomWithIngredients("Moules-frites").orElseThrow(() ->new RuntimeErrorException("'Moule-frites' existe"));
//		assertThat(plat.getIngredients()).extracting(Ingredient::getNom).contains("sel");
//	}
	
	@Test
	public void testSave() {
		long countBefore = platRepository.count();

		Plat plat = new Plat();
		plat.setNom("Pizza");
		plat.setPrixEnCentimesEuros(1400);

		platRepository.save(plat);

		long countAfter = platRepository.count();
		assertThat(countAfter).isEqualTo(countBefore + 1);

		assertThat(platRepository.findAll()).extracting(Plat::getNom).contains(plat.getNom());

	}
	
	@Test
	public void testChangerNom() {
		platRepository.updateNomPlat("Couscous", "Couscous-Merguez");

		List<Plat> listPlat = platRepository.findAll();

		assertThat(listPlat).extracting(Plat::getNom).contains("Couscous-Merguez", Index.atIndex(2));


		
	}

}
