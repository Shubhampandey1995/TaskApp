package com.retailapp.retail.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
     private Long id;
     
     @Column(name="name")
     private String productName ;
     
     @Column(name="price")
     private Long price;
     
     
     @Column(name="status")
     private String approvalStatus;
     
     @Column(name="delete")
     private boolean delete;
             
     @Column(name="post_date")
     private Date postDate;
     
     @Column(name="approve_id")
     private Long approveId;
     
     @OneToMany
     @JoinColumn(columnDefinition="approve_id",referencedColumnName="id")
     private ApprovalQueue approvalQueue; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Long getApproveId() {
		return approveId;
	}

	public void setApproveId(Long approveId) {
		this.approveId = approveId;
	}

	public ApprovalQueue getApprovalQueue() {
		return approvalQueue;
	}

	public void setApprovalQueue(ApprovalQueue approvalQueue) {
		this.approvalQueue = approvalQueue;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
     
     
     
}
