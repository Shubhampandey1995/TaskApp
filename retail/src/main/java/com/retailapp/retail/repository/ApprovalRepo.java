package com.retailapp.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.retailapp.retail.model.ApprovalQueue;

@Repository
public interface ApprovalRepo extends JpaRepository<ApprovalQueue, Long>{

    @Query(value = "select a from ApprovalQueue a order by createDate desc")
    public List<ApprovalQueue> getApprovalList();
	
}
