package at.fh.swenga.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "PictureFrame")
public class PictureFrameModel implements java.io.Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private int productNumber;

	@Column(nullable = false, length = 50)
	private String productName;

	@Column(nullable = false)
	private float height;

	@Column(nullable = false)
	private float width;

	@Column(nullable = false, updatable = true)
	private boolean onlyOnePicture;

	@Embedded
	private Configuration configuration;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Producer producer;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Vendor> vendors;

	@Version
	long version;

	/**
	 * 
	 */
	public PictureFrameModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param productName
	 * @param height
	 * @param width
	 * @param onlyOnePicture
	 */
	public PictureFrameModel(int productNumber, String productName, Float height, Float width, Boolean onlyOnePicture) {
		this.productNumber = productNumber;
		this.productName = productName;
		this.height = height;
		this.width = width;
		this.onlyOnePicture = onlyOnePicture;
	}

	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the height
	 */
	public Float getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Float height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public Float getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Float width) {
		this.width = width;
	}

	/**
	 * @return the onlyOnePicture
	 */
	public Boolean getOnlyOnePicture() {
		return onlyOnePicture;
	}

	/**
	 * @param onlyOnePicture
	 *            the onlyOnePicture to set
	 */
	public void setOnlyOnePicture(Boolean onlyOnePicture) {
		this.onlyOnePicture = onlyOnePicture;
	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration
	 *            the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
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

	/**
	 * @return the vendors
	 */
	public List<Vendor> getVendors() {
		return vendors;
	}

	/**
	 * @param vendors
	 *            the vendors to set
	 */
	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}

	public void addVendor(Vendor vendor) {
		if (vendors == null) {
			vendors = new ArrayList<Vendor>();
		}
		vendors.add(vendor);
	}

}