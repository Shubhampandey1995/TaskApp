package com.retailapp.retail.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "approval_queue")
public class ApprovalQueue {

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
     private Long id;
     
  
     @Column(name="product_id")
     private Long productId;
     
     
     @Column(name="approve_user_id")
     private Long approvaluserId;
         
     @Column(name="create_date")
     private Date createDate;
     
     @Column(name="delete")
     private boolean delete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getApprovaluserId() {
		return approvaluserId;
	}

	public void setApprovaluserId(Long approvaluserId) {
		this.approvaluserId = approvaluserId;
	}
     
     
 

}
