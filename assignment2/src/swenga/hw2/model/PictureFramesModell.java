package swenga.hw2.model;

import swenga.hw2.model.*;
import swenga.hw2.servlet.*;

public class PictureFramesModell implements Comparable<PictureFramesModell> {

	private int productnumber;
	private String productname;
	private Integer heightWrapper;
	private Integer widthWrapper;
	private String format;
	private String material;
	private String colour;
	private Boolean onlyOnePictureWrapper;

	
	
	public int getProductnumber() {
		return productnumber;
	}

	public void setProductnumber(int productnumber) {
		this.productnumber = productnumber;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Integer getHeightWrapper() {
		return heightWrapper;
	}

	public void setHeightWrapper(Integer heightWrapper) {
		this.heightWrapper = heightWrapper;
	}

	public Integer getWidthWrapper() {
		return widthWrapper;
	}

	public void setWidthWrapper(Integer widthWrapper) {
		this.widthWrapper = widthWrapper;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Boolean getOnlyOnePictureWrapper() {
		return onlyOnePictureWrapper;
	}

	public void setOnlyOnePictureWrapper(Boolean onlyOnePictureWrapper) {
		this.onlyOnePictureWrapper = onlyOnePictureWrapper;
	}

	public PictureFramesModell(int productnumber, String productname, Integer heightWrapper, Integer widthWrapper,
			String format, String material, String colour, Boolean onlyOnePictureWrapper) {
		super();
		this.productnumber = productnumber;
		this.productname = productname;
		this.heightWrapper = heightWrapper;
		this.widthWrapper = widthWrapper;
		this.format = format;
		this.material = material;
		this.colour = colour;
		this.onlyOnePictureWrapper = onlyOnePictureWrapper;
	}

	@Override
	public int compareTo(PictureFramesModell o) {
		return productnumber - o.getProductnumber();
	}
	
}
