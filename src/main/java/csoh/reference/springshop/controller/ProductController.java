package csoh.reference.springshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import csoh.reference.springshop.model.Product;

@Controller
@RequestMapping("/")
public class ProductController {
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String list(ModelMap model){
		List<Product> products = productService.listProduct();
		model.addAttribute("message","A test message" + products);
		return "product";
	}
	
	
	
}
