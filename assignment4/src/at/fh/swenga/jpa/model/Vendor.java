package at.fh.swenga.jpa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "vendorName", nullable = false, length = 50)
	private String vendorName;

	@ManyToMany(mappedBy = "vendors", fetch = FetchType.EAGER)
	private List<PictureFrameModel> pictureFrames;

	public Vendor() {
	}

	public Vendor(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public List<PictureFrameModel> getPictureFrames() {
		return pictureFrames;
	}

	public void setPictureFrames(List<PictureFrameModel> pictureFrames) {
		this.pictureFrames = pictureFrames;
	}

}