package at.fh.swenga.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.PictureFrameRepository;
import at.fh.swenga.jpa.dao.ProducerRepository;
import at.fh.swenga.jpa.model.PictureFrame;
import at.fh.swenga.jpa.model.Producer;

@Controller
public class PictureFrameController {

	@Autowired
	PictureFrameRepository pictureFrameRepository;

	@Autowired
	ProducerRepository producerRepository;

	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {
		List<PictureFrame> pictureFrames = pictureFrameRepository.findAll();
		model.addAttribute("pictureFrames", pictureFrames);
		model.addAttribute("count", pictureFrames.size());
		return "index";
	}

	@RequestMapping(value = { "/getPage" })
	public String getPage(Pageable page, Model model) {
		Page<PictureFrame> pictureFramesPage = pictureFrameRepository.findAll(page);
		model.addAttribute("pictureFramesPage", pictureFramesPage);
		model.addAttribute("pictureFrames", pictureFramesPage.getContent());
		model.addAttribute("count", pictureFramesPage.getTotalElements());
		return "index";
	}

	@RequestMapping(value = { "/find" })
	public String find(Model model, @RequestParam String searchString, @RequestParam int searchInt,
			@RequestParam String searchType) {
		List<PictureFrame> pictureFrames = null;
		int count = 0;

		switch (searchType) {
		case "query1":
			pictureFrames = pictureFrameRepository.findAll();
			break;
		case "query2":
			pictureFrames = pictureFrameRepository.findByProductName(searchString);
			break;
		case "query3":
			pictureFrames = pictureFrameRepository.findByFormatOrColour(searchString, searchString);
			break;
		case "query4":
			pictureFrames = pictureFrameRepository.findByProductNameContaining(searchString);
			break;
		case "query5":
			pictureFrames = pictureFrameRepository.findByHeightLessThan(searchInt);
			break;
		case "query6":
			pictureFrames = pictureFrameRepository.findByNamedQuery(searchInt);
			break;
		case "query7":
			count = pictureFrameRepository.deleteByProductName(searchString);
			break;
		case "query8":
			pictureFrames = pictureFrameRepository.findByWidthGreaterThanOrderByWidthDesc(searchInt);
			break;
		case "query9":
			pictureFrames = pictureFrameRepository.findTop20ByFormat(searchString);
			break;
		case "query10":
			count = pictureFrameRepository.deleteByColourContaining(searchString);
			break;
		case "query11":
			count = pictureFrameRepository.countByProducerName(searchString);
			break;

		default:
			pictureFrames = pictureFrameRepository.findAll();
		}

		model.addAttribute("pictureFrames", pictureFrames);

		if (pictureFrames != null) {
			model.addAttribute("count", pictureFrames.size());
		} else {
			model.addAttribute("count", count);
		}
		return "index";
	}

	@RequestMapping(value = { "/findById" })
	public String findById(@RequestParam("id") PictureFrame e, Model model) {
		if (e != null) {
			List<PictureFrame> pictureFrames = new ArrayList<PictureFrame>();
			pictureFrames.add(e);
			model.addAttribute("pictureFrames", pictureFrames);
		}
		return "index";
	}

	@RequestMapping("/fillList")
	@Transactional
	public String fillData(Model model) {

		DataFactory df = new DataFactory();

		String[] pnames = { "Framer", "Picturer", "SteelFramer", "WoodenFramer", "PlasticFramer", "SteelPicturer",
				"WoodenPicturer", "PlasticPicturer" };
		String[] formats = { "XS", "S", "M", "L", "XL", "XXL", "XXL" };
		String[] colour = { "blue", "red", "green", "black", "orange", "yellow", "white", "gray" };

		Producer producer = null;

		for (int i = 0; i < 100; i++) {
			if (i % 10 == 0) {
				String name = df.getBusinessName();
				producer = producerRepository.findFirstByName(name);
				if (producer == null) {
					producer = new Producer(name);
				}
			}

			PictureFrame pictureFrame = new PictureFrame(df.getItem(pnames), df.getItem(formats), df.getItem(colour),
					df.getNumberUpTo(500), df.getNumberUpTo(500));
			pictureFrame.setProducer(producer);
			pictureFrameRepository.save(pictureFrame);
		}

		return "forward:list";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		pictureFrameRepository.deleteById(id);

		return "forward:list";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}
}
