package swenga.hw2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import swenga.hw2.model.*;

public class PictureFramesManager {

	private List<PictureFramesModell> pictureFramesEntries = new ArrayList<PictureFramesModell>();

	public void clear() {
		pictureFramesEntries.clear();
	}

	public void add(PictureFramesModell model) {
		pictureFramesEntries.add(model); // call is delegated
	}

	public boolean isEmpty() {
		return pictureFramesEntries.isEmpty();
	}

	public List<PictureFramesModell> getPictureFrameEntries() {
		return pictureFramesEntries;
	}

	public int getSize() {
		return pictureFramesEntries.size();
	}

	public PictureFramesManager() {
		addDummyPictureFrames();
	}

	public void addDummyPictureFrames() {
		add(new PictureFramesModell(001, "SteelFramer3000", 200, 300, "LetterExtended", "Steel", "Gray", true));
		add(new PictureFramesModell(002, "WoodenFrameXL", 500, 600, "XL", "Wood", "Brown", false));
		add(new PictureFramesModell(003, "FrameworkPremium", 350, 290, "US", "Plastic", "Green", true));
		add(new PictureFramesModell(004, "Framer500XL", 500, 600, "XL", "Plastics", "Brown", false));
	}

	public PictureFramesModell getPictureFramesByProductNumber(int productnumber) {
		for (PictureFramesModell pictureFramesModell : pictureFramesEntries) {
			if (pictureFramesModell.getProductnumber() == productnumber) {
				return pictureFramesModell;
			}
		}
		return null;
	}

	public boolean contains(PictureFramesModell pictureFramesModell) {
		return pictureFramesEntries.contains(pictureFramesModell);
	}

	public List<PictureFramesModell> getFilteredPictureFrames(String searchString) {

		// List for results
		List<PictureFramesModell> filteredList = new ArrayList<PictureFramesModell>();

		// check every employee
		for (PictureFramesModell pictureFramesModell : pictureFramesEntries) {

			if ((pictureFramesModell.getProductname() != null
					&& pictureFramesModell.getProductname().contains(searchString))) {
				filteredList.add(pictureFramesModell);
			}
		}
		return filteredList;
	}

	// public void remove(int productnumber) {
	/**
	 * don't know why but it does not work !!! Some crazy shit is going on ...
	 * //return pictureFramesEntries.remove(new PictureFramesModell(productnumber,
	 * null,null,null,null,null,null,null))
	 **/

	/**
	 * another try:for (PictureFramesModell pfm : pictureFramesEntries) { //does not
	 * work -> always gives me an java.util.ConcurrentModificationException, to
	 * complex to solve so that it runs concurrently if (new
	 * PictureFramesModell(productnumber,
	 * null,null,null,null,null,null,null).compareTo(pfm) == 0)
	 * pictureFramesEntries.remove(pfm);
	 **/

	/** // third try removeByProductName
	public void removeByProductName(String name) {
		// hoedlale16: We assume that name is unique. So we delete all illnesses with
		// given name!
		for (PictureFramesModell m : pictureFramesEntries) {
			if (m.getProductname().equalsIgnoreCase(name))
				pictureFramesEntries.remove(m);
		}
	}**/
	
	//fourth try using Iterator 
	public void removeByProductNumber(int pn) {
		for (Iterator<PictureFramesModell> pf = pictureFramesEntries.iterator() ; pf.hasNext() ;) {
			PictureFramesModell pfm = pf.next();
			if (pfm.getProductnumber()==pn) pf.remove();
		}
	}
}
