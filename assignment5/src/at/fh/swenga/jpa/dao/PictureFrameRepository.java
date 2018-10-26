package at.fh.swenga.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.PictureFrame;

@Repository
@Transactional
public interface PictureFrameRepository extends JpaRepository<PictureFrame, Integer> {

	
	@Transactional
	@Query("Select c from PictureFrame c where c.productName = :productName")
	public List<PictureFrame> findByProductName(@Param("productName") String productName);

	@Transactional
	@Query("Select c from PictureFrame c where c.format = :format or c.colour = :colour")
	public List<PictureFrame> findByFormatOrColour(@Param("format") String format, @Param("colour") String colour);
	
	@Transactional
	public List<PictureFrame> findByProductNameContaining(String productName);
	
	@Transactional
	public List<PictureFrame> findByHeightLessThan(int height);
	
	@Transactional
	public List<PictureFrame> findByNamedQuery(@Param("search") int searchInt);
	
	@Transactional
	int deleteByProductName(String productName);
	
	@Transactional
	public List<PictureFrame> findByWidthGreaterThanOrderByWidthDesc(int width);
	
	@Transactional
	public List<PictureFrame> findTop20ByFormat(String format);
	
	@Transactional
	int deleteByColourContaining(String colour);
	
	@Transactional
	int countByProducerName(String name);

}
