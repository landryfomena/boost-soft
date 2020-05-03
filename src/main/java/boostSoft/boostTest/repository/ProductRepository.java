package boostSoft.boostTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import boostSoft.boostTest.data.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByTitle(String title);

}
