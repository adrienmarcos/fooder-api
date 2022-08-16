package com.evereats.fooder.jpa;

import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class ConsultKitchenMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(ConsultKitchenMain.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchenRegister = applicationContext.getBean(KitchenRepository.class);
        List<Kitchen> kitchens = kitchenRegister.list();

        for(Kitchen kitchen : kitchens) {
            System.out.println(kitchen.getName());
        }
    }
}
