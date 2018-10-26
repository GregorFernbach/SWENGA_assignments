package at.fh.swenga.jpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


// 1:1 relationship 
@Embeddable
public class Configuration {
	@Column(name = "configuration_material", nullable = false, length = 80)
	private String material;
	@Column(name = "configuration_colour", nullable = false, length = 80)
	private String colour;
	@Column(name = "configuration_format", nullable = false, length = 80)
	private String format;

	/**
	 * 
	 */
	public Configuration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param material
	 * @param colour
	 * @param format
	 */
	public Configuration(String material, String colour, String format) {
		super();
		this.material = material;
		this.colour = colour;
		this.format = format;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
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
	 * @param colour
	 *            the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

}