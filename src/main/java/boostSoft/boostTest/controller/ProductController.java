package boostSoft.boostTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import boostSoft.boostTest.data.Product;
import boostSoft.boostTest.service.api.ProductServiceApi;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

	@Autowired ProductServiceApi productServiceApi;
	
	@PostMapping("/register")
	public HttpEntity<? extends Object> createProduct(@RequestBody Product product){
		return productServiceApi.createProduct(product);
	}
	
	@PutMapping("/update")
	public HttpEntity<? extends Object> updateProduct(@RequestBody Product product){
		return productServiceApi.updateProduct(product);
	}
	
	@GetMapping("/select")
	public HttpEntity<? extends Object> findByTitle(@RequestParam String title){
		return productServiceApi.findByTitle(title);
	}
	
	@DeleteMapping("/delete")
	public HttpEntity<? extends Object> deleteProduct(@RequestParam String title){
		return productServiceApi.deleteProduct(title);
	}
}
