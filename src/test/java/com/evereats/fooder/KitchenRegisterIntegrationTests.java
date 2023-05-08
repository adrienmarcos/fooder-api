package com.evereats.fooder;

import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.service.KitchenService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@DisplayName("Tests for kitchen register service")
class FooderApiApplicationTests {

	@Autowired
	private KitchenService kitchenService;

	@Test
	@DisplayName("save kitchen")
	void save_PersistKitchen_whenSuccessful() {
		var kitchenToBeSaved = new Kitchen();
		kitchenToBeSaved.setName("chinese");
		var kitchenSaved = kitchenService.save(kitchenToBeSaved);

		Assertions.assertThat(kitchenSaved).isNotNull();
		Assertions.assertThat(kitchenSaved.getId()).isNotNull();
		Assertions.assertThat(kitchenSaved.getName()).isEqualTo(kitchenToBeSaved.getName());
	}

	@Test()
	void save_FailPersistKitchen_whenSucessful() {
		assertThrows(ConstraintViolationException.class, () -> {
			var kitchenToBeSaved = new Kitchen();
			kitchenToBeSaved.setName(null);
			var kitchenSaved = kitchenService.save(kitchenToBeSaved);
		});
	}

}
