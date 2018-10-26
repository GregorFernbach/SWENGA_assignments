package at.fh.swenga.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Version;

// 1:n Relationship
@Entity
public class Producer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "companyName", nullable =false, length = 50)
	private String companyName;

	@OneToMany(mappedBy = "producer", fetch = FetchType.EAGER)
	@OrderBy("productName")
	private Set<PictureFrameModel> pictureFrames;

	@Version
	long version;

	public Producer() {
		// TODO Auto-generated constructor stub
	}

	public Producer(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Set<PictureFrameModel> getPictureFrames() {
		return pictureFrames;
	}

	public void setPictureFrames(Set<PictureFrameModel> pictureFrames) {
		this.pictureFrames = pictureFrames;
	}

	public void addPictureFrame(PictureFrameModel pictureFrame) {
		if (pictureFrames == null) {
			pictureFrames = new HashSet<PictureFrameModel>();
		}
		pictureFrames.add(pictureFrame);
	}

}