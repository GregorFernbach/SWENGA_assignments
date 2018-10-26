package at.fh.swenga.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.Producer;

@Repository
@Transactional
public class ProducerDao {

	@PersistenceContext
	protected EntityManager entityManager;

	public List<Producer> getProducers() {

		TypedQuery<Producer> typedQuery = entityManager.createQuery("select d from Producer d", Producer.class);
		List<Producer> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}

	public Producer getProducer(String companyName) {
		try {

			TypedQuery<Producer> typedQuery = entityManager
					.createQuery("select d from Producer d where d.companyName = :companyName", Producer.class);
			typedQuery.setParameter("companyName", companyName);
			Producer producer = typedQuery.getSingleResult();
			return producer;
		} catch (NoResultException e) {
			return null;
		}
	}

}