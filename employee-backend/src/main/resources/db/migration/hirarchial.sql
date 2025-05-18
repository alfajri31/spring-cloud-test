select * from myemployees m ;

select * from myemployees m left join myemployees_domicili md on m.employee_id = md.employee_id ;


WITH RECURSIVE EmployeeHierarchy AS (
	SELECT
        employee_id,
        employee_name,
        employee_manager_id,
        0 AS path_level,
        employee_name::VARCHAR AS path_hierarchy,
        employee_name::VARCHAR AS employee_format
    FROM myemployees
    WHERE employee_manager_id IS NULL

   	UNION ALL

    SELECT
        e.employee_id,
        e.employee_name,
        e.employee_manager_id,
        eh.path_level + 1 AS path_level,
        CONCAT(eh.path_hierarchy, '->', e.employee_name) AS path_hierarchy,
        CONCAT(REPEAT('    ', eh.path_level + 1), '|__', e.employee_name) AS employee_format
    FROM myemployees e
    INNER JOIN EmployeeHierarchy eh
    ON e.employee_manager_id = eh.employee_id
) SELECT * FROM EmployeeHierarchy order by employee_id asc;