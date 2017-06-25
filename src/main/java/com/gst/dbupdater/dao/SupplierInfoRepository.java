package com.gst.dbupdater.dao;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gst.dbupdater.model.SupplierInfoDataModel;

public interface SupplierInfoRepository extends CrudRepository<SupplierInfoDataModel, Long> {
	
	SupplierInfoDataModel findByPanNo(String panNo);
	
	@Transactional
	@Modifying
    @Query(value="UPDATE supplier_info c SET gstin_no = :gstinNo , date = :date WHERE pan_no = :panNo")
    int updateSupplierInfo(@Param("gstinNo") String gstinNo, @Param("date") Date date, @Param("panNo") String panNo);
}
