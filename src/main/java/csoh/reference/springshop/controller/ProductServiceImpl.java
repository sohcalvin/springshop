package csoh.reference.springshop.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import csoh.reference.springshop.model.Product;
@Service
public class ProductServiceImpl implements ProductService {

	@PersistenceContext(name = "java-hello-world")
	private EntityManager em;

	@Override
	public List<Product> listProduct() {	
		
		return null;
	}

	@Override
	public void saveProduct(Product product) {
		em.persist(product);
		
	}
	
	
	
}
