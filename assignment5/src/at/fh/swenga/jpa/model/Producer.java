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

@Entity
public class Producer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@OneToMany(mappedBy = "producer", fetch = FetchType.LAZY)
	@OrderBy("productName")
	private Set<PictureFrame> pictureFrames;

	@Version
	long version;

	public Producer() {
		// TODO Auto-generated constructor stub
	}

	public Producer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PictureFrame> getPictureFrames() {
		return pictureFrames;
	}

	public void setPictureFrames(Set<PictureFrame> pictureFrames) {
		this.pictureFrames = pictureFrames;
	}

	public void addPictureFrame(PictureFrame pictureFrame) {
		if (pictureFrames == null) {
			pictureFrames = new HashSet<PictureFrame>();
		}
		pictureFrames.add(pictureFrame);
	}

}
