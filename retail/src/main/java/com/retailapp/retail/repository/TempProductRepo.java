package com.retailapp.retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retailapp.retail.model.TempAddProduct;

@Repository
public interface TempProductRepo extends JpaRepository<TempAddProduct, Long> {

	@Query(value = "select p from TempAddProduct p where approvaId = :approvalId  order by id desc")
	public TempAddProduct getTempProductList(@Param("approvalId") Long approvalId);

}
