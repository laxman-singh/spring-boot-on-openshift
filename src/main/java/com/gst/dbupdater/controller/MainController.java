package com.gst.dbupdater.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gst.dbupdater.dao.SupplierInfoRepository;
import com.gst.dbupdater.model.SupplierInfoDataModel;
import com.gst.dbupdater.model.UserForm;

/**
 * 
 * @since 21 June,2017
 * @author Laxman Singh (laxman.1390@gmail.com)
 * 
 * Main controller responsible for showing index page and handling form submission 
 *
 */
@Controller
public class MainController {
	
	@Autowired
	private SupplierInfoRepository supplierInfoRepo;

	@GetMapping("/")
	public String showIndexPage(Model model) {
		
		model.addAttribute("userForm", new UserForm());
		return "index";
	}
	
	//@PostMapping("/")
	@RequestMapping(value="/",params="search",method=RequestMethod.POST)
	public String search(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, ModelMap model) {
		
		//System.err.println(userForm.getPanNo());
		
		if (!bindingResult.hasErrors()) {
			// pan num is in correct format. lets search it in our DB
			
			/*System.err.println(supplierInfoRepo.findByPanNo(userForm.getPanNo()));
			System.err.println(supplierInfoRepo.findByPanNo(userForm.getPanNo()).get(0).getSupplierName());*/
			
			// we are good here. :) lets do the rest of work
			
			/** old code */
			/*List<SupplierInfoDataModel> supplierInfoList = supplierInfoRepo.findByPanNo(userForm.getPanNo());
			userForm.setSupplierInfoList(supplierInfoList);*/
			
			SupplierInfoDataModel supplierInfo = supplierInfoRepo.findByPanNo(userForm.getPanNo());
			userForm.setSupplierInfo(supplierInfo);
			
			convertTimeStampToBootstrapFormat(supplierInfo.getDate(), model);
			
			/*// also set gstin no and date
			userForm.setGstinNo(supplierInfo.getGstinNo());
			if(supplierInfo.getDate() != null) {
				userForm.setDate(supplierInfo.getDate().toString());
			}*/
        }
		
		return "index";
	}

	@RequestMapping(value="/",params="update",method=RequestMethod.POST)
	public String updateSupplierDetails(@Valid @ModelAttribute UserForm userForm, ModelMap model) {
		
		//System.err.println(userForm.getGstinNo() + "  " + userForm.getDate() + "  " + userForm.getPanNo());
		// validate form data
		int errCount = 0;
		if(userForm.getGstinNo() == null || !userForm.getGstinNo().matches("[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{1}[Z]{1}[0-9]{1}")) {
			model.addAttribute("gstErr", "Invalid GST No.");
			errCount++;
		}
		
		if(userForm.getDate() == null || userForm.getDate().equals("")) {
			model.addAttribute("dateErr", "Please select Date and Time.");
			errCount++;
		}
		
		if(errCount > 0) {
			SupplierInfoDataModel supplierInfo = supplierInfoRepo.findByPanNo(userForm.getPanNo());
			userForm.setSupplierInfo(supplierInfo);
			convertTimeStampToBootstrapFormat(supplierInfo.getDate(), model);
		} else  {
			// update db table
			Date date = new Date(userForm.getDate());
			if(supplierInfoRepo.updateSupplierInfo(userForm.getGstinNo(), new Timestamp(date.getTime()), userForm.getPanNo()) == 1) {
				model.addAttribute("success", "Supplier Info with PAN No: "+userForm.getPanNo()+" Updated Successfully.");
			} else {
				model.addAttribute("failed", "Supplier Info with PAN No: "+userForm.getPanNo()+" Failed to update.");
				SupplierInfoDataModel supplierInfo = supplierInfoRepo.findByPanNo(userForm.getPanNo());
				userForm.setSupplierInfo(supplierInfo);
				convertTimeStampToBootstrapFormat(supplierInfo.getDate(), model);
			}
		}
		
		return "index";
	}

	private void convertTimeStampToBootstrapFormat(Timestamp date, ModelMap model) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String formatedDate  = dateFormat.format(date.getTime());
		
		model.addAttribute("formatedDate", formatedDate);
	}
	
	/*public static void main(String[] args) {
		System.err.println("12ABCDE1234A1Z9".matches("[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{1}[Z]{1}[0-9]{1}"));
		System.err.println("12ABCDE1234A1A9".matches("[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{1}[Z]{1}[0-9]{1}"));
		System.err.println("12ABCDE1234A1Z9".matches("[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{1}[Z]{1}[0-9]{1}"));
		System.err.println("12ABCDE1234A13Z9".matches("[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{1}[Z]{1}[0-9]{1}"));
	}*/
}
