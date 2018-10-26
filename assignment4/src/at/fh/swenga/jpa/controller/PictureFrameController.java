package at.fh.swenga.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.PictureFrameDao;
import at.fh.swenga.jpa.dao.ProducerDao;
import at.fh.swenga.jpa.dao.VendorDao;
import at.fh.swenga.jpa.model.Configuration;
import at.fh.swenga.jpa.model.PictureFrameModel;
import at.fh.swenga.jpa.model.Producer;
import at.fh.swenga.jpa.model.Vendor;

@Controller
public class PictureFrameController {

	@Autowired
	PictureFrameDao pictureFrameDao;
	@Autowired
	ProducerDao producerDao;
	@Autowired
	VendorDao vendorDao;

	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {

		List<PictureFrameModel> pictureFrames = pictureFrameDao.getPictureFrames();
		List<Producer> producers = producerDao.getProducers();
		List<Vendor> vendors = vendorDao.getVendors();

		model.addAttribute("pictureFrames", pictureFrames);
		model.addAttribute("producers", producers);
		model.addAttribute("vendors", vendors);

		return "index";
	}

	@RequestMapping("/fillPictureFrameList")
	@Transactional
	public String fillData(Model model) {

		// Configuration
		Configuration config1 = new Configuration("Steel", "Gray", "XXL");
		Configuration config2 = new Configuration("Wood", "Brown", "S");
		Configuration config3 = new Configuration("Plastic", "Red", "M");

		// Producer
		Producer zotter = producerDao.getProducer("ZotterGmbH");
		if (zotter == null)
			zotter = new Producer("ZotterGmbH");

		Producer heider = producerDao.getProducer("HeiderAG");
		if (heider == null)
			heider = new Producer("HeiderAG");

		Producer doberer = producerDao.getProducer("DobererLTD");
		if (doberer == null)
			doberer = new Producer("DobererLTD");

		// Vendor
		Vendor saturn = vendorDao.getVendor("Saturn");
		if (saturn == null)
			saturn = new Vendor("Saturn");

		Vendor merkur = vendorDao.getVendor("Merkur");
		if (merkur == null)
			merkur = new Vendor("Merkur");

		Vendor leiner = vendorDao.getVendor("Leiner");
		if (leiner == null)
			leiner = new Vendor("Leiner");

		Vendor kastner = vendorDao.getVendor("Kastner");
		if (kastner == null)
			kastner = new Vendor("Kastner");

		PictureFrameModel p1 = new PictureFrameModel(103, "WoodenFramer", 50.5F, 60.8F, true);
		p1.setConfiguration(config1);
		p1.setProducer(zotter);
		p1.addVendor(saturn);
		p1.addVendor(kastner);
		p1.addVendor(leiner);
		pictureFrameDao.persist(p1);

		
		PictureFrameModel p2 = new PictureFrameModel(27, "SteelFramer", 13.5F, 14.2F, false);
		p2.setConfiguration(config2);
		p2.setProducer(heider);
		p2.addVendor(saturn);
		p2.addVendor(kastner);
		pictureFrameDao.persist(p2);

		PictureFrameModel p3 = new PictureFrameModel(312, "PlasticFramer", 20.3F, 24.2F, false);
		p3.setConfiguration(config3);
		p3.setProducer(doberer);
		p3.addVendor(saturn);
		p3.addVendor(merkur);
		p3.addVendor(kastner);
		pictureFrameDao.persist(p3);

		return "forward:list";
	}

	@RequestMapping("/searchPictureFrames")
	public String search(Model model, @RequestParam String searchString) {
		model.addAttribute("pictureFrames", pictureFrameDao.searchPictureFrames(searchString));
		model.addAttribute("producers", producerDao.getProducers());
		model.addAttribute("vendors", vendorDao.getVendors());
		return "index";
	}

	@RequestMapping("/deletePictureFrame")
	public String deleteData(Model model, @RequestParam int id) {
		pictureFrameDao.delete(id);

		return "forward:list";
	}

	// @ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}
}