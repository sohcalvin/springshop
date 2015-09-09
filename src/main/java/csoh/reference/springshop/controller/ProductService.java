package csoh.reference.springshop.controller;

import java.util.List;

import org.springframework.stereotype.Service;

import csoh.reference.springshop.model.Product;


@Service
public interface ProductService {
	
	List<Product> listProduct();
	
	void saveProduct(Product product);

}
