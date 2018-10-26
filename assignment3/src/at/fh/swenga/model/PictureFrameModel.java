/**
 * 
 */
package at.fh.swenga.model;

/**
 * @author Gregor Fernbach
 *
 */
public class PictureFrameModel {
	
	//Wrapper-Types needed for purpose of remove-method in the class PictureFrameManager
	private int productNumber;
	private String productName;
	private Integer height;
	private Integer width;
	private String format;
	private String material;
	private String colour;
	private Boolean onlyOnePicture;
	
	
	// Constructors
	
	public PictureFrameModel() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param productNumber
	 * @param productName
	 * @param height
	 * @param width
	 * @param format
	 * @param material
	 * @param colour
	 * @param onlyOnePicture
	 */
	public PictureFrameModel(int productNumber, String productName, Integer height, Integer width, String format,
			String material, String colour, Boolean onlyOnePicture) {
		super();
		this.productNumber = productNumber;
		this.productName = productName;
		this.height = height;
		this.width = width;
		this.format = format;
		this.material = material;
		this.colour = colour;
		this.onlyOnePicture = onlyOnePicture;
	}

	
	// Getters & Setters
	
	/**
	 * @return the productNumber
	 */
	public int getProductNumber() {
		return productNumber;
	}

	/**
	 * @param productNumber the productNumber to set
	 */
	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * @return the onlyOnePicture
	 */
	public Boolean getOnlyOnePicture() {
		return onlyOnePicture;
	}

	/**
	 * @param onlyOnePicture the onlyOnePicture to set
	 */
	public void setOnlyOnePicture(Boolean onlyOnePicture) {
		this.onlyOnePicture = onlyOnePicture;
	}

	
	//equals method and hashCode
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productNumber;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PictureFrameModel other = (PictureFrameModel) obj;
		if (productNumber != other.productNumber)
			return false;
		return true;
	}
}
