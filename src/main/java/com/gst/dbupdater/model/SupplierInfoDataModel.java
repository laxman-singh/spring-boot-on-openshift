package com.gst.dbupdater.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author Laxman Singh (laxman.1390@gmail.com)
 * 
 * Entity class for supplier data model
 *
 */
@Entity(name="supplier_info")
public class SupplierInfoDataModel {
	
	@Id 
	@Column(name="id") // primary key of the table. if primary key name is different then this need to be changed
	private Integer id;
	
	@Column(name="supplier_no") // column name of the table
	private String supplierNo;
	
	@Column(name="supplier_name")
	private String supplierName;
	
	@Column(name="vendor_site_code")
	private String vendorSiteCode;
	
	@Column(name="site_rao")
	private String siteRao;
	
	@Column(name="pan_no")
	private String panNo;
	
	@Column(name="address")
	private String address;
	
	@Column(name="bank")
	private String bank;
	
	@Column(name="branch")
	private String branch;
	
	@Column(name="ifsc")
	private String ifsc;

	@Column(name="bank_ac_no")
	private String bankACNo;
	
	@Column(name="gstin_no")
	private String gstinNo;
	
	@Column(name="date")
	private Timestamp date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getVendorSiteCode() {
		return vendorSiteCode;
	}

	public void setVendorSiteCode(String vendorSiteCode) {
		this.vendorSiteCode = vendorSiteCode;
	}

	public String getSiteRao() {
		return siteRao;
	}

	public void setSiteRao(String siteRao) {
		this.siteRao = siteRao;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBankACNo() {
		return bankACNo;
	}

	public void setBankACNo(String bankACNo) {
		this.bankACNo = bankACNo;
	}

	public String getGstinNo() {
		return gstinNo;
	}

	public void setGstinNo(String gstinNo) {
		this.gstinNo = gstinNo;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
