package kr.ac.hansung.cse.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping
	public String adminPage() {
		return "admin";
	}
	
	@RequestMapping("/productInventory")
	public String getProducts(Model model) { //컨트롤러에서 가져온 정보(products)를  model에 넣는다. 이는 뷰에서 보여진다.
		
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		
		return "productInventory";
		
	}
	
	@RequestMapping(value="/productInventory/addProduct", method=RequestMethod.GET)
	public String addProduct(Model model) {
		
		Product product = new Product();
		product.setCategory("컴퓨터");
		
		model.addAttribute("product", product);
		
		return "addProduct";
	}
	
	@RequestMapping(value="/productInventory/addProduct", method=RequestMethod.POST)
	public String addProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {//product객체 안에 사용자가 입력한 정보가 담긴다(Data Binding)
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
		
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "addProduct";
		}
		
		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
		
		if(productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		product.setImageFilename(productImage.getOriginalFilename());
		
		productService.addProduct(product);
			
		return "redirect:/admin/productInventory";//이 함수에서는 모델에 값을 넣지 않아서 바로띄워도 값이 나오지 않는다 그러므로 리다이렉트로 리퀘스트를 넘긴다.그러면 위의 getProducts함수로 이동
	}
	
	@RequestMapping(value="/productInventory/deleteProduct/{id}", method=RequestMethod.GET)
	public String deleteProduct(@PathVariable int id, HttpServletRequest request) {//request로 id가 넘어오면  인자로 들어오게됨
		
		Product product = productService.getProductById(id);
		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + product.getImageFilename());
		
		if(Files.exists(savePath) ) {
			try {
				Files.delete(savePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		productService.deleteProduct(product);
		
		return "redirect:/admin/productInventory";
	}
	
	@RequestMapping(value="/productInventory/updateProduct/{id}", method=RequestMethod.GET)
	public String updateProduct(@PathVariable int id, Model model) {//request로 id가 넘어오면  인자로 들어오게됨
		
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		
		return "updateProduct";
	}
	
	@RequestMapping(value="/productInventory/updateProduct", method=RequestMethod.POST)
	public String updateProductPost(@Valid Product product, BindingResult result, HttpServletRequest request) {//product객체 안에 사용자가 입력한 정보가 담긴다(Data Binding)
		
		//System.out.println(product);
		
		if(result.hasErrors()) {
			System.out.println("Form data has some erros");
		
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "addProduct";
		}
		
		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
	
		if(productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		product.setImageFilename(productImage.getOriginalFilename());
		
		productService.updateProduct(product);
		
		return "redirect:/admin/productInventory";//이 함수에서는 모델에 값을 넣지 않음 그러므로 바로띄워도 값이 나오지 않는다 그러므로 리다이렉트로 리퀘스트를 넘긴다.그러면 위의 getProducts함수로 이동
	}
}
