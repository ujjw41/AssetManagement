package assetmanagement.asset.repository;

import assetmanagement.asset.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StaffRepo extends JpaRepository<Staff, Long> {

	List<Staff> findByEmail(String email);

	@Query(value = "SELECT * FROM staff WHERE staff.email= :email", nativeQuery = true)
	public List<Staff> myquery(String email);


	List<Staff> findByMobile(String email);

}
