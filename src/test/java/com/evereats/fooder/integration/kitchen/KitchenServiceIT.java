package com.evereats.fooder.integration.kitchen;

import com.evereats.fooder.domain.exception.EntityInUseException;
import com.evereats.fooder.domain.exception.KitchenNotFoundException;
import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.service.KitchenService;
import com.evereats.fooder.util.DatabaseCleaner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class KitchenServiceIT {

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

	@Test
	@DisplayName("Should throw an Error if invalid data is provided")
	public void save_FailPersistKitchen_whenSuccessful() {
		assertThrows(ConstraintViolationException.class, () -> {
			var kitchenToBeSaved = new Kitchen();
			kitchenToBeSaved.setName(null);
			var savedKitchen = kitchenService.save(kitchenToBeSaved);
		});
	}

	@Test
	@DisplayName("Should throw an Error if Kitchen is in use")
	public void delete_FailDeleteKitchenInUse_whenKitchenIsInUse() {
		assertThrows(EntityInUseException.class, () -> {
			var kitchenToBeDeleted = kitchenService.find(1L);
			if (kitchenToBeDeleted != null) kitchenService.delete(kitchenToBeDeleted.getId());
		});
	}

	@Test
	@DisplayName("Should throw an Error if Kitchen is not found")
	public void delete_FailDeleteKitchen_whenKitchenIsNotFound() {
		assertThrows(KitchenNotFoundException.class, () -> {
			var kitchenToBeDeleted = kitchenService.find(-1L);
			if (kitchenToBeDeleted != null) kitchenService.delete(kitchenToBeDeleted.getId());
		});
	}
}
