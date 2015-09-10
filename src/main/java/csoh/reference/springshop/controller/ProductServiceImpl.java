package csoh.reference.springshop.controller;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csoh.reference.springshop.model.Product;
@Service
public class ProductServiceImpl implements ProductService {

	@PersistenceContext(name = "java-hello-world")
	private EntityManager em;
	
	@Override
	public List<Product> listProduct() {
		System.out.println("EM is " + em);
		Product p = em.find(Product.class, 0L);
		System.out.println(p.getName());
		return null;
	}

	@Override
	@Transactional
	public void saveProduct(Product product) {
		em.persist(product);
	}
	
	
	
}
