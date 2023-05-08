package com.evereats.fooder;

import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.service.KitchenService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KitchenRegisterIntegrationTests {

	@Autowired
	private KitchenService kitchenService;

	@Test
	@DisplayName("Should register a Kitchen if valid data is provided")
	public void save_PersistKitchen_whenSuccessful() {
		var kitchenToBeSaved = new Kitchen();
		kitchenToBeSaved.setName("Chinese");
		var savedKitchen = kitchenService.save(kitchenToBeSaved);

		Assertions.assertThat(savedKitchen).isNotNull();
		Assertions.assertThat(savedKitchen.getId()).isNotNull();
		Assertions.assertThat(savedKitchen.getName()).isEqualTo(kitchenToBeSaved.getName());
	}
}
