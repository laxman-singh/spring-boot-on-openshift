package com.gst.dbupdater.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * 
 * @since 21 June,2017
 * @author Laxman Singh (laxman.1390@gmail.com)
 * 
 * Bean class for user form
 *
 */
public class UserForm {
	
	@NotNull
	@Pattern(regexp="[A-Z]{5}[0-9]{4}[A-Z]{1}",message="Invalid PAN Number")
	private String panNo;
	
	private String gstinNo;
	private String date;
	
	//private List<SupplierInfoDataModel> supplierInfoList;
	
	private SupplierInfoDataModel supplierInfo;

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo.toUpperCase(); // change pan number to upper case bcz css property 'text-transform' not sending it in uppercase
	}

	public SupplierInfoDataModel getSupplierInfo() {
		return supplierInfo;
	}

	public void setSupplierInfo(SupplierInfoDataModel supplierInfo) {
		this.supplierInfo = supplierInfo;
	}

	public String getGstinNo() {
		return gstinNo;
	}

	public void setGstinNo(String gstinNo) {
		this.gstinNo = gstinNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/*public List<SupplierInfoDataModel> getSupplierInfoList() {
		return supplierInfoList;
	}

	public void setSupplierInfoList(List<SupplierInfoDataModel> supplierInfoList) {
		this.supplierInfoList = supplierInfoList;
	}*/
}
