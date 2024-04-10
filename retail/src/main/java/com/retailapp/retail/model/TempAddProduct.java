package com.retailapp.retail.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "temp_product")
public class TempAddProduct {

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
     private Long id;
     
     @Column(name="name")
     private String name ;
     
     @Column(name="price")
     private Long price;
          
     @Column(name="status")
     private String status;
      
     @Column(name="approval_id")
     private Long approvaId;
     
     @OneToMany
     @JoinColumn(columnDefinition="approval_id",referencedColumnName="id")
     private ApprovalQueue approvalQueue; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getApprovaId() {
		return approvaId;
	}

	public void setApprovaId(Long approvaId) {
		this.approvaId = approvaId;
	}

	public ApprovalQueue getApprovalQueue() {
		return approvalQueue;
	}

	public void setApprovalQueue(ApprovalQueue approvalQueue) {
		this.approvalQueue = approvalQueue;
	}

	     
     
}
