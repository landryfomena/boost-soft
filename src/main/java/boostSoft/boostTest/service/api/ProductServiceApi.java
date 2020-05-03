package boostSoft.boostTest.service.api;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import boostSoft.boostTest.data.Product;

@Service
public interface ProductServiceApi {

	public abstract HttpEntity<? extends Object> createProduct(Product product);
	public abstract HttpEntity<? extends Object> updateProduct(Product product);
	public abstract HttpEntity<? extends Object> findByTitle(String title);
	public abstract HttpEntity<? extends Object> deleteProduct(String title);
}
