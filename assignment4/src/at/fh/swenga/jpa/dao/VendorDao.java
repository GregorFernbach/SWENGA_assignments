package at.fh.swenga.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.Vendor;

@Repository
@Transactional
public class VendorDao {

	@PersistenceContext
	protected EntityManager entityManager;

	public List<Vendor> getVendors() {

		TypedQuery<Vendor> typedQuery = entityManager.createQuery("select p from Vendor p", Vendor.class);
		List<Vendor> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}

	public Vendor getVendor(String vendorName) {
		try {
			TypedQuery<Vendor> typedQuery = entityManager
					.createQuery("select p from Vendor p where p.vendorName = :vendorName", Vendor.class);
			typedQuery.setParameter("vendorName", vendorName);
			Vendor vendor = typedQuery.getSingleResult();
			return vendor;
		} catch (NoResultException e) {
			return null;
		}

	}
}