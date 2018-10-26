/**
 * 
 */
package at.fh.swenga.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.model.PictureFrameManager;
import at.fh.swenga.model.PictureFrameModel;

/**
 * @author Gregor Fernbach
 *
 */

@Controller
public class PictureFrameManagerController {

	@Autowired
	private PictureFrameManager pictureFrameManager;

	
	@RequestMapping(value = { "/", "listPictureFrames" })
	public String showAllPictureFrames(Model model) {
		model.addAttribute("pictureFrames", pictureFrameManager.getAllPictureFrames());
		return "listPictureFrames";
	}

	@RequestMapping("/fillPictureFrameList")
	public String fillPictureFrameList(Model model) {
 
		pictureFrameManager.addPictureFrame(new PictureFrameModel(1, "SteelFramer3000", 200, 300, "LetterExtended", "Steel", "Gray", true));
		pictureFrameManager.addPictureFrame(new PictureFrameModel(2, "WoodenFrameXL", 500, 600, "XL", "Wood", "Brown", false));
		pictureFrameManager.addPictureFrame(new PictureFrameModel(3, "FrameworkPremium", 350, 290, "US", "Plastic", "Green", true));
		pictureFrameManager.addPictureFrame(new PictureFrameModel(4, "Framer500XL", 500, 600, "XL", "Plastics", "Brown", false));
 
		model.addAttribute("pictureFrames", pictureFrameManager.getAllPictureFrames());
		return "listPictureFrames";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
 
		return "error";
 
	}
	
	// Spring 4: @RequestMapping(value = "/deletePictureFrame", method = RequestMethod.GET)
		@GetMapping("/deletePictureFrame")
		public String delete(Model model, @RequestParam int productNumber) {
			boolean isRemoved = pictureFrameManager.remove(productNumber);
	 
			if (isRemoved) {
				model.addAttribute("warningMessage", "Picture Frame " + productNumber + " deleted");
			} else {
				model.addAttribute("errorMessage", "There is no PictureFrame " + productNumber);
			}
	 
			// Multiple ways to "forward"
			// return "forward:/listEmployees";
			return showAllPictureFrames(model);
		}
	
		// Spring 4: @RequestMapping(value = "/searchPictureFrames", method = RequestMethod.POST)
		@PostMapping("/searchPictureFrames")
		public String search(Model model, @RequestParam String searchString) {
			model.addAttribute("pictureFrames", pictureFrameManager.getFilteredPictureFrames(searchString));
			return "listPictureFrames";
		}
		
		// Spring 4: @RequestMapping(value = "/addPictureFrame", method = RequestMethod.GET)
		@GetMapping("/addPictureFrame")
		public String showAddEmployeeForm(Model model) {
			return "editPictureFrame";
		}
		
		// Spring 4: @RequestMapping(value = "/addPictureFrame", method = RequestMethod.POST)
		@PostMapping("/addPictureFrame")
		public String addPictureFrame(@Valid PictureFrameModel newPictureFrameModel, BindingResult bindingResult,
				Model model) {
	 
			// Any errors? -> Create a String out of all errors and return to the page
			if (bindingResult.hasErrors()) {
				String errorMessage = "";
				for (FieldError fieldError : bindingResult.getFieldErrors()) {
					errorMessage += fieldError.getField() + " is invalid<br>";
				}
				model.addAttribute("errorMessage", errorMessage);
	 
				// Multiple ways to "forward"
				return "forward:/listPictureFrames";
			}
	 
			// Look for pictureFrameModel in the List. One available -> Error 
			PictureFrameModel pictureFrameModel = pictureFrameManager.getPictureFrameByProductNumber(newPictureFrameModel.getProductNumber());
	 
			if (pictureFrameModel != null) {
				model.addAttribute("errorMessage", "Picture Frame already exists!<br>");
			} else {
				pictureFrameManager.addPictureFrame(newPictureFrameModel);
				model.addAttribute("message", "New Picture Frame " + newPictureFrameModel.getProductNumber() + " added.");
			}
	 
			return "forward:/listPictureFrames";
		}
		
		// Spring 4: @RequestMapping(value = "/editPictureFrame", method = RequestMethod.GET)
		@GetMapping("/editPictureFrame")
		public String showChangeEmployeeForm(Model model, @RequestParam int productNumber) {
	 
			PictureFrameModel pictureFrame = pictureFrameManager.getPictureFrameByProductNumber(productNumber);
	 
			if (pictureFrame != null) {
				model.addAttribute("pictureFrame", pictureFrame);
				return "editPictureFrame";
			} else {
				model.addAttribute("errorMessage", "Couldn't find Picture Frame " + productNumber);
				return "forward:/listPictureFrames";
			}
		}
		
		// Spring 4: @RequestMapping(value = "/editPictureFrame", method = RequestMethod.POST)
		@PostMapping("/editPictureFrame")
		public String editPictureFrame(@Valid PictureFrameModel changedPictureFrameModel, BindingResult bindingResult,
				Model model) {
	 
			// Any errors? -> Create a String out of all errors and return to the page
			if (bindingResult.hasErrors()) {
				String errorMessage = "";
				for (FieldError fieldError : bindingResult.getFieldErrors()) {
					errorMessage += fieldError.getField() + " is invalid<br>";
				}
				model.addAttribute("errorMessage", errorMessage);
				return "forward:/listPictureFrames";
			}
	 
			// Get the pictureFrame we want to change
			PictureFrameModel pictureFrame = pictureFrameManager.getPictureFrameByProductNumber(changedPictureFrameModel.getProductNumber());
	 
			if (pictureFrame == null) {
				model.addAttribute("errorMessage", "Picture Frame does not exist!<br>");
			} else {
				// Change the attributes
				pictureFrame.setProductName(changedPictureFrameModel.getProductName());
				pictureFrame.setHeight(changedPictureFrameModel.getHeight());
				pictureFrame.setWidth(changedPictureFrameModel.getWidth());
				pictureFrame.setFormat(changedPictureFrameModel.getFormat());
				pictureFrame.setMaterial(changedPictureFrameModel.getMaterial());
				pictureFrame.setColour(changedPictureFrameModel.getColour());
				pictureFrame.setOnlyOnePicture(changedPictureFrameModel.getOnlyOnePicture());
	 
				// Save a message for the web page
				model.addAttribute("message", "Changed Picture Frame " + changedPictureFrameModel.getProductNumber());
			}
	 
			return "forward:/listPictureFrames";
		}
		
}
