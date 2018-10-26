package SWENGA.firstHW;

import java.util.ArrayList;
import java.util.List;


public class PictureFramesManager {
	private List<PictureFrames> pictureFramesEntry = new ArrayList<PictureFrames>();
	 
	 public void clear() {
		 pictureFramesEntry.clear();
	 }
	 
	 public void add(PictureFrames model) {
		    pictureFramesEntry.add(model); //call is delegated
		 }
	 
	 public boolean isEmpty() {
	    return pictureFramesEntry.isEmpty();
	 }
	 
	 public List<PictureFrames> getPictureFrameEntry() {
	    return pictureFramesEntry;
	 }
	 
	 public List<PictureFrames> getPictureFramesEntry() {
		return pictureFramesEntry;
	}

	public void setPictureFramesEntry(List<PictureFrames> pictureFramesEntry) {
		this.pictureFramesEntry = pictureFramesEntry;
	}

	public int getSize() {
	    return pictureFramesEntry.size();
	 }
}
