package com.retailapp.retail.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.retailapp.retail.model.ApprovalQueue;
import com.retailapp.retail.model.Product;
import com.retailapp.retail.service.ProductService;

@RestController
@RequestMapping("/api")
public class RetailController {

	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	@GetMapping("/products")
	public List<Product> getProductList() {

		return productService.getProduct();
	}

	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable long productId, @RequestBody Product product) {

		Product ProductObj = productService.updateProduct(product, productId);
		return ResponseEntity.ok(ProductObj);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {

		productService.deleteProduct(productId);
		return ResponseEntity.ok("deleted successfully!.");
	}

	@GetMapping("/products/search")
	@ResponseBody
	public ResponseEntity<List<Product>> searchProducts(@RequestParam("productName") String productName,
			@RequestParam("minPrice") Long minPrice, @RequestParam("maxPrice") Long maxPrice,
			@RequestParam("minPostedDate") Date minPostedDate, @RequestParam("maxPostedDate") Date maxPostedDate) {

		return ResponseEntity
				.ok(productService.searchProducts(productName, minPrice, maxPrice, minPostedDate, maxPostedDate));
	}
	
	@GetMapping("/products/approval-queue")
	public List<ApprovalQueue> getApprovalQueueList() {
		return productService.getApprovalQueList();
	}
	
	
	@PutMapping("/products/approval-queue/{approvalId}/approve")
	public ResponseEntity<Product> approveProduct(@PathVariable long approvalId) {

		Product ProductObj = productService.approveProduct(approvalId);
		return ResponseEntity.ok(ProductObj);
	}
	
	@PutMapping("/products/approval-queue/{approvalId}/reject")
	public ResponseEntity<Product> approveRejectProduct(@PathVariable long approvalId) {

		Product ProductObj = productService.approveProduct(approvalId);
		return ResponseEntity.ok(ProductObj);
	}
	
}
