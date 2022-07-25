import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface EmployeeItemsRepository {

	EmployeeItems save(EmployeeItems ei);
	@Query("select ei from EmployeeItems ei where employee.id=?1")
	List<String> getItemsByEmployeeId(Long id);
	@Query("select ei from EmployeeItems ei where item.id=?1")
	List<String> getEmployeesByItemId(Long id);

}
