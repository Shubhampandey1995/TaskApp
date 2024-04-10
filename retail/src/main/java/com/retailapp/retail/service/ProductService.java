package com.retailapp.retail.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailapp.retail.model.ApprovalQueue;
import com.retailapp.retail.model.Product;
import com.retailapp.retail.model.TempAddProduct;
import com.retailapp.retail.repository.ApprovalRepo;
import com.retailapp.retail.repository.ProductRepo;
import com.retailapp.retail.repository.TempProductRepo;
import com.retailapplication.exception.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private ApprovalRepo approvalRepo;

	@Autowired
	private TempProductRepo tempProductRepo;

	// CREATE
	public Product createProduct(Product product) {

		Product pro = null;

		if (product.getPrice() < 10000 && product.getPrice() > 5000) {

			product.setApprovalStatus("pending");
			product.setPostDate(new Date());

			pro = productRepo.save(product);

			ApprovalQueue aq = new ApprovalQueue();
			aq.setProductId(pro.getId());
			aq.setDelete(false);
			aq = approvalRepo.save(aq);

			pro.setApproveId(aq.getId());

		} else {
			product.setApprovalStatus("approved");
			product.setPostDate(new Date());
			product.setApproveId(null);
		}

		return productRepo.save(pro);
	}

	// Update
	public Product updateProduct(Product product, Long productId) {

		Product pr = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + productId));

		if ((product.getPrice() - pr.getPrice()) >= (pr.getPrice() / 2)) {

			ApprovalQueue appQue = new ApprovalQueue();

			appQue.setProductId(productId);
			appQue.setCreateDate(new Date());
			appQue.setApprovaluserId(20l);
			appQue.setDelete(false);

			ApprovalQueue appId = approvalRepo.save(appQue);

			TempAddProduct entity = new TempAddProduct();
			entity.setApprovaId(appId.getId());
			entity.setName(product.getProductName());
			entity.setPrice(product.getPrice());

			tempProductRepo.save(entity);

		} else {
			pr.setApproveId(null);
			pr.setProductName(product.getProductName());
			pr.setPrice(product.getPrice());
			pr.setApprovalStatus("approved");
		}

		return productRepo.save(pr);
	}

	public List<Product> getProduct() {
		return productRepo.getProductList();
	}

	public List<ApprovalQueue> getApprovalQueList() {
		return approvalRepo.getApprovalList();
	}

	// DELETE
	public void deleteProduct(Long productId) {

		ApprovalQueue aq = new ApprovalQueue();
		aq.setProductId(productId);
		aq.setDelete(false);
		productRepo.updateProductStatus(productId);
	}

	public List<Product> searchProducts(String productName, Long minPrice, Long maxPrice, Date minPostedDate,
			Date maxPostedDate) {
		return productRepo.searchProducts(productName, minPrice, maxPrice, minPostedDate, maxPostedDate);
	}

	public Product approveProduct(Long approveId) {

		ApprovalQueue approval = approvalRepo.findById(approveId)
				.orElseThrow(() -> new ResourceNotFoundException("Approval not exist with id: " + approveId));

		Product product = productRepo.findById(approval.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("product id not exist : " + approval.getProductId()));

		TempAddProduct tmpProd = tempProductRepo.getTempProductList(approveId);
		
		product.setProductName(tmpProd.getName());
		product.setPrice(tmpProd.getPrice());
		product.setApprovalStatus("approved");
		product.setApproveId(null);

		productRepo.save(product);

		approvalRepo.deleteById(approval.getId());
		tempProductRepo.deleteById(tmpProd.getId());

		return product;
	}

	public void approvalRejectProduct(Long approveId) {

		ApprovalQueue approval = approvalRepo.findById(approveId)
				.orElseThrow(() -> new ResourceNotFoundException("Approval not exist with id: " + approveId));

		Product product = productRepo.findById(approval.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("product id not exist : " + approval.getProductId()));

		product.setApprovalStatus("Approved");
		product.setApproveId(null);

		productRepo.save(product);

		approvalRepo.deleteById(approval.getId());
		tempProductRepo.deleteById(approveId);
	}

}
