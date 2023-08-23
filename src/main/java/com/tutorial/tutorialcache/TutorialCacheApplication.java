package com.tutorial.tutorialcache;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableCaching
public class TutorialCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialCacheApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(ProductService productService) {
		return args -> {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Id: 1 " + productService.getById(1L));
			System.out.println("Id: 2 " + productService.getById(2L));
			System.out.println("Id: 1 " + productService.getById(1L));
			System.out.println("Id: 1 " + productService.getById(1L));
			System.out.println("Id: 1 " + productService.getById(1L));
			System.out.println("Id: 3 " + productService.getById(3L));
			System.out.println("Id: 4 " + productService.getById(4L));
		};
	}
}
record Product (Long id, String name, String description) implements Serializable {}
@Service
class ProductService {
	Map<Long, Product> products = new HashMap<>() {
		{
			put(1L, new Product(1L, "Notebook", "Macbook Prod"));
			put(2L, new Product(2L, "Notebook", "XPS"));
			put(3L, new Product(1L, "Notebook", "Aliam"));
			put(4L, new Product(1L, "Notebook", "Samsung"));
			put(5L, new Product(1L, "Notebook", "DELL"));
			put(6L, new Product(3L, "Notebook", "LENOVO"));
			put(7L, new Product(4L, "Notebook", "AMD 400"));

		}

	};
	@Cacheable(value = "products")
	Product getById (Long id) {
		System.out.println("Buscando products... ");
		simulationLatency();
		return products.get(id);
	}

	private void simulationLatency() {
		try {
			long time = 1000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
