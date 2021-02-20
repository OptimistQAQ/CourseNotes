package com.optimist.dao;

import com.optimist.pojo.Department;
import com.optimist.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//员工Dao层
@Repository
public class EmployeeDao {

    //模拟数据库中的数据

    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();

        employees.put(1001, new Employee(1001, "秦", "a656673477@qq.com", 1, new Department(101, "教学部")));
        employees.put(1002, new Employee(1002, "aa", "b656673477@qq.com", 0, new Department(101,"市场部")));
        employees.put(1003, new Employee(1003, "nn", "c656673477@qq.com", 1, new Department(101,"教研部")));
        employees.put(1004, new Employee(1004, "vv", "d656673477@qq.com", 0, new Department(101,"外交部")));
        employees.put(1005, new Employee(1005, "dd", "e656673477@qq.com", 1, new Department(101,"学生部")));
    }

    private static Integer initId = 1006;
    //主键自增
    //增加一个员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(), employee);
    }

    //查询全部员工
    public Collection<Employee> getAll() {
        return employees.values();
    }

    //通过Id查询员工
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    //删除员工
    public void delete(Integer id) {
        employees.remove(id);
    }

}
