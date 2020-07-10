package org.zerock.shoppingcart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

import org.zerock.shoppingcart.domain.Product;
import org.zerock.shoppingcart.repository.ProductRepository;

import java.util.List;

@Service
@GraphQLApi
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GraphQLQuery(name = "products")
	public List<Product> getProducts() {
		return productRepository.findAll();
	}
	@GraphQLQuery(name = "product")
	public Product findProduct(String code) {
		return productRepository.findProduct(code);
	}
	/*
	@GraphQLMutation(name = "saveProduct")
	public Product savePost(Product post) {
		return productRepository.save(post);
	}*/
	
	@GraphQLMutation(name = "deleteProduct")
	public void deleteProduct(String code) {
		productRepository.delete(code);
	}
}
