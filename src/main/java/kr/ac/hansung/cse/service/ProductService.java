package kr.ac.hansung.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.ProductDao;
import kr.ac.hansung.cse.model.Product;

@Service
public class ProductService { // 비즈니스 로직은 서비스 레이어에서 구현!

	@Autowired
	private ProductDao productDao;

	public Product getProductById(int id) {
		return productDao.getProductById(id);
	}

	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
	}

	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

}
