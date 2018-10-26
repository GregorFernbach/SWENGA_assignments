package at.fh.swenga.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "PictureFrame")

@NamedQuery(name = "PictureFrame.findByNamedQuery", query = "select e from PictureFrame e where e.height > :search")

public class PictureFrame implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String productName;

	@Column(nullable = false, length = 3)
	private String format;

	@Column(nullable = false, length = 20)
	private String colour;

	@Column(nullable = false)
	private int height;

	@Column(nullable = false)
	private int width;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Producer producer;

	public PictureFrame() {
	}

	public PictureFrame(String productName, String format, String colour, int height, int width) {
		super();
		this.productName = productName;
		this.height = height;
		this.width = width;
		this.format = format;
		this.colour = colour;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
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
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the producer
	 */
	public Producer getProducer() {
		return producer;
	}

	/**
	 * @param producer
	 *            the producer to set
	 */
	public void setProducer(Producer producer) {
		this.producer = producer;
	}

}
