package com.retailapp.retail.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retailapp.retail.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
	   
    @Query(value = "select p from Product p order by id desc")
    public List<Product>getProductList();
    
    @Query(value = "select p from Product p where ( productName LIKE CONCAT('%',:productName, '%') ) or ( price BETWEEN minPrice AND maxPrice ) or (postDate BETWEEN :minPostedDate AND maxPostedDate )")
    public List<Product> searchProducts(@Param("productName ") String productName , @Param("minPrice") Long minPrice, @Param("maxPrice") Long maxPrice, @Param("minPostedDate") Date minPostedDate, @Param("maxPostedDate") Date maxPostedDate);

    @Transactional
    @Modifying
    @Query(value = "update Product set delete = 'true' , approvalStatus = 'deleted'  where id=:productId")
    public void updateProductStatus(@Param("productId") long productId);


}
