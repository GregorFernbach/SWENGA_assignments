package at.fh.swenga.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.PictureFrameModel;

@Repository
@Transactional
public class PictureFrameDao {

	@PersistenceContext
	protected EntityManager entityManager;

	public List<PictureFrameModel> getPictureFrames() {
		TypedQuery<PictureFrameModel> typedQuery = entityManager.createQuery("select e from PictureFrameModel e",
				PictureFrameModel.class);
		List<PictureFrameModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}

	public List<PictureFrameModel> searchPictureFrames(String searchString) {
		TypedQuery<PictureFrameModel> typedQuery = entityManager.createQuery(
				"select e from PictureFrameModel e where e.productName like :search or e.productName like :search",
				PictureFrameModel.class);
		typedQuery.setParameter("search", "%" + searchString + "%");
		List<PictureFrameModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}

	public PictureFrameModel getPictureFrame(int i) throws DataAccessException {
		return entityManager.find(PictureFrameModel.class, i);
	}

	public void persist(PictureFrameModel pictureFrame) {
		entityManager.persist(pictureFrame);
	}

	public PictureFrameModel merge(PictureFrameModel pictureFrame) {
		return entityManager.merge(pictureFrame);
	}

	public void delete(PictureFrameModel pictureFrame) {
		entityManager.remove(pictureFrame);
	}

	public int deleteAllPictureFrames() {
		int count = entityManager.createQuery("DELETE FROM PictureFrameModel").executeUpdate();
		return count;
	}

	public void delete(int pkID) {
		PictureFrameModel pictureFrame = getPictureFrame(pkID);
		if (pictureFrame != null)
			delete(pictureFrame);
	}
}