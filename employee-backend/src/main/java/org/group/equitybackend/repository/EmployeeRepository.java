package org.group.equitybackend.repository;

import org.group.equitybackend.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(value = """
            WITH RECURSIVE EmployeeHierarchy AS (
                SELECT 
                    e.employee_id,
                    e.employee_name,
                    e.employee_manager_id,
                    0 AS path_level,
                    cast(e.employee_name as TEXT) AS path_hierarchy,
                    cast(e.employee_name as TEXT) AS employee_format
                FROM tbl_employee e
                WHERE e.employee_manager_id = :managerId -- Filter by manager ID
                
                UNION ALL
                
                SELECT 
                    e.employee_id,
                    e.employee_name,
                    e.employee_manager_id,
                    eh.path_level + 1 AS path_level,
                    CONCAT(eh.path_hierarchy, '->', e.employee_name) AS path_hierarchy,
                    CONCAT(REPEAT('    ', eh.path_level + 1), '|__', e.employee_name) AS employee_format
                FROM tbl_employee e
                INNER JOIN EmployeeHierarchy eh
                ON e.employee_manager_id = eh.employee_id
            )
            SELECT * FROM EmployeeHierarchy
            ORDER BY employee_id ASC
            """, nativeQuery = true)
    List<Employee> findAllByManagerId(@Param("managerId") Long managerId);

}
