package SWENGA.firstHW;

//this class holds the model
public class PictureFrames {
	
	private String productname;
	private int height;
	private int width;
	private String format;
	private String material;
	private String colour;
	private boolean	onlyOnePicture;
	
	
	//constructor
	public PictureFrames(String productname, int height, int width, String format, String material, String colour,
			boolean onlyOnePicture) {
		super();
		this.productname = productname;
		this.height = height;
		this.width = width;
		this.format = format;
		this.material = material;
		this.colour = colour;
		this.onlyOnePicture = onlyOnePicture;
	}
	
	//Getter & Setter Methods
	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
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
	public boolean isOnlyOnePicture() {
		return onlyOnePicture;
	}
	public void setOnlyOnePicture(boolean onlyOnePicture) {
		this.onlyOnePicture = onlyOnePicture;
	}
	
	//toString Method
	@Override
	public String toString() {
		return "PictureFrames [height=" + height + ", width=" + width + ", format=" + format + ", material=" + material
				+ ", colour=" + colour + ", onlyOnePicture=" + onlyOnePicture + "]";
	}
	//equals Method
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + height;
		result = prime * result + ((material == null) ? 0 : material.hashCode());
		result = prime * result + (onlyOnePicture ? 1231 : 1237);
		result = prime * result + width;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PictureFrames other = (PictureFrames) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (height != other.height)
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (onlyOnePicture != other.onlyOnePicture)
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	public PictureFrames() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
