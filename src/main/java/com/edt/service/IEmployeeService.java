package com.edt.service;


import com.edt.domain.Employee;
import com.edt.qo.EmployeeQueryObject;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IEmployeeService {
    void save(Employee employee, Long[] ids);
    void delete(Long id);
    void update(Employee employee, Long[] ids);
    Employee get(Long id);
    List<Employee> listAll();
    //分页查询方法
    PageInfo<Employee> query(QueryObject queryobject);

    Employee login(String username, String password);

    Employee selectByUsername(String username);

    Workbook exportXls(EmployeeQueryObject qo);

    void importXls(MultipartFile file) throws IOException;
}