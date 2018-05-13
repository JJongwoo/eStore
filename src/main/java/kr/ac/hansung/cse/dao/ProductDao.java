package kr.ac.hansung.cse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Product;

@Repository
@Transactional // 각각의 메소드가 트랜잭션 이다.
public class ProductDao { // dao->jdbcTemplate(DataSource)->jdbcdriver->db

	/*
	 * private JdbcTemplate jdbcTemplate;
	 * 
	 * @Autowired public void setDataSource(DataSource dataSource) {//
	 * daocontext.xml�� bean�� ���Ե�(BasicDataSource) jdbcTemplate = new
	 * JdbcTemplate(dataSource); }
	 */

	@Autowired
	private SessionFactory sessionFactory;

	public Product getProductById(int id) {

		/*
		  String sqlStatement = "select * from product where id=?";
		  
		  return jdbcTemplate.queryForObject(sqlStatement, new Object[] { id }, new
		  RowMapper<Product>() {
		  
		  @Override public Product mapRow(ResultSet rs, int arg1) throws SQLException {
		  Product product = new Product(); product.setId(rs.getInt("id"));
		  product.setName(rs.getString("name"));
		  product.setCategory(rs.getString("category"));
		  product.setPrice(rs.getInt("price"));
		  product.setManufacturer(rs.getString("manufacturer"));
		  product.setUnitInStock(rs.getInt("unitInStock"));
		  product.setDescription(rs.getString("description"));
		  
		  return product; }// query sql record->object });// query는 List를 반환
		 */

		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);

		return product;

	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {

		/*
		 * String sqlStatement = "select * from product";
		 * 
		 * return jdbcTemplate.query(sqlStatement, new RowMapper<Product>() {
		 * 
		 * @Override 
		 * public Product mapRow(ResultSet rs, int arg1) throws SQLException {
		 * Product product = new Product(); product.setId(rs.getInt("id"));
		 * product.setName(rs.getString("name"));
		 * product.setCategory(rs.getString("category"));
		 * product.setPrice(rs.getInt("price"));
		 * product.setManufacturer(rs.getString("manufacturer"));
		 * product.setUnitInStock(rs.getInt("unitInStock"));
		 * product.setDescription(rs.getString("description"));
		 * 
		 * return product; }// query sql record->object
		 * 
		 * });// query는 List를 반환
		 */

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product");
		List<Product> productList = query.list();

		return productList;

	}

	public void addProduct(Product product) {

		/*
		 * String name = product.getName(); String category = product.getCategory(); int
		 * price = product.getPrice(); String manufacturer = product.getManufacturer();
		 * int unitInStock = product.getUnitInStock(); String description =
		 * product.getDescription();
		 * 
		 * String sqlStatement =
		 * "insert into product (name, category, price, manufacturer, unitInStock, description)"
		 * + "values(?, ?, ?, ?, ?, ?)";
		 * 
		 * System.out.println(category);
		 * 
		 * return (jdbcTemplate.update(sqlStatement, new Object[] { name, category,
		 * price, manufacturer, unitInStock, description }) == 1);
		 */
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();

	}

	public void deleteProduct(Product product) {

		/*
		 * String sqlStatement = "delete from product where id=?";
		 * 
		 * return (jdbcTemplate.update(sqlStatement, new Object[] { id }) == 1);
		 */

		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();

	}

	public void updateProduct(Product product) {

		/*
		 * int id = product.getId(); String name = product.getName(); String category =
		 * product.getCategory(); int price = product.getPrice(); String manufacturer =
		 * product.getManufacturer(); int unitInStock = product.getUnitInStock(); String
		 * description = product.getDescription();
		 * 
		 * String sqlStatement =
		 * "update product set name=?, category=?, price=?, manufacturer=?, unitInStock=?, description=? where id=?"
		 * ;
		 * 
		 * System.out.println(category);
		 * 
		 * return (jdbcTemplate.update(sqlStatement, new Object[] { name, category,
		 * price, manufacturer, unitInStock, description, id }) == 1);
		 */

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	}

}
