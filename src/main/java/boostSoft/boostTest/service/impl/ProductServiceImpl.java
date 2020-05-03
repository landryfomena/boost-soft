package boostSoft.boostTest.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import boostSoft.boostTest.data.Product;
import boostSoft.boostTest.data.ProductStatus;
import boostSoft.boostTest.repository.ProductRepository;
import boostSoft.boostTest.repository.UserRepository;
import boostSoft.boostTest.service.api.ProductServiceApi;

@Service
public class ProductServiceImpl implements ProductServiceApi {
	
	@Autowired ProductRepository productRepository;
	@Autowired UserRepository userRepository;

	@Override
	public HttpEntity<? extends Object> createProduct(Product product) {
		try {
			Product currentProduct = productRepository.save(product);
			Date date = new Date();
			product.setDateCreation(date);
			product.setStatus(ProductStatus.DISPONIBLE.getStatut());
			productRepository.saveAndFlush(currentProduct);
			return new ResponseEntity<Product>(currentProduct, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> updateProduct(Product product) {
		try {
			Date date = new Date();
			Product currentProduct = productRepository.findByTitle(product.getTitle());
			if (product.getTitle()!= null || product.getDateCreation()!= null || product.getPrice()!= 0 || product.getQuantity()!= 0 || product.getStatus()!= null) {
				currentProduct.setTitle(product.getTitle());
				currentProduct.setDateCreation(date);
				currentProduct.setPrice(product.getPrice());
				currentProduct.setQuantity(product.getQuantity());
				currentProduct.setStatus(product.getStatus());
				productRepository.saveAndFlush(currentProduct);
				return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
			}else {
				return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> findByTitle(String title) {
		try {
			Product currentProduct = productRepository.findByTitle(title);
			return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public HttpEntity<? extends Object> deleteProduct(String title) {
		try {
			Product currentProduct = productRepository.findByTitle(title);
			currentProduct.setStatus(ProductStatus.DELETED.getStatut());
			productRepository.saveAndFlush(currentProduct);
			return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
