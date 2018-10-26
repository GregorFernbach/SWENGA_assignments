/**
 * 
 */
package at.fh.swenga.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

/**
 * @author Gregor Fernbach
 *
 */

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class PictureFrameManager {

	private List<PictureFrameModel> pictureFrames = new ArrayList<PictureFrameModel>();

	/**
	 * Add PictureFrame to List
	 * 
	 * @param pictureFrame
	 */
	public void addPictureFrame(PictureFrameModel pictureFrame) {
		pictureFrames.add(pictureFrame);
	}

	/**
	 * Verify if list contains PictureFrame with same productNumber
	 * 
	 * @param pictureFrame
	 * @return
	 */
	public boolean contains(PictureFrameModel pictureFrame) {
		return pictureFrames.contains(pictureFrame);
	}

	/**
	 * convenient method: true if list is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return pictureFrames.isEmpty();
	}

	/**
	 * try to find PictureFrameModel with given productNumber return model,
	 * otherwise null
	 * 
	 * @param ssn
	 * @return
	 */
	public PictureFrameModel getPictureFrameByProductNumber(int productNumber) {

		for (PictureFrameModel pictureFrameModel : pictureFrames) {
			if (pictureFrameModel.getProductNumber() == productNumber) {
				return pictureFrameModel;
			}
		}
		return null;
	}

	/**
	 * return list with all pictureFrames
	 * 
	 * @return
	 */
	public List<PictureFrameModel> getAllPictureFrames() {
		return pictureFrames;
	}

	/**
	 * return a new list with all pictureFrames where productName, format, material or colour contains search string
	 * @param searchString
	 * @return
	 */
	public List<PictureFrameModel> getFilteredPictureFrames(String searchString) {
 
		if (searchString == null || searchString.equals("")) {
			return pictureFrames;
		}
 
		// List for results
		List<PictureFrameModel> filteredList = new ArrayList<PictureFrameModel>();
 
		// check every employee
		for (PictureFrameModel pictureFrameModel : pictureFrames) {
 
			if ((pictureFrameModel.getProductName() != null && pictureFrameModel.getProductName().contains(searchString))
				|| (pictureFrameModel.getFormat() != null && pictureFrameModel.getFormat().contains(searchString))
				|| (pictureFrameModel.getMaterial() != null && pictureFrameModel.getMaterial().contains(searchString))
				|| (pictureFrameModel.getColour() != null && pictureFrameModel.getColour().contains(searchString)))
			{
				filteredList.add(pictureFrameModel);
			}
		}
		return filteredList;
	}

	/**
	 * remove pictureFrames with same productNumber
	 * 
	 * @param productNumber
	 * @return
	 */
	public boolean remove(int productNumber) {
		return pictureFrames.remove(new PictureFrameModel(productNumber, null, null, null, null, null, null, null));
	}

}
