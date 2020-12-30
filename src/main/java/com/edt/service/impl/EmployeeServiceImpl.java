package com.edt.service.impl;

import com.edt.domain.Department;
import com.edt.domain.Employee;
import com.edt.exception.LoginException;
import com.edt.mapper.DepartmentMapper;
import com.edt.mapper.EmployeeMapper;
import com.edt.qo.EmployeeQueryObject;
import com.edt.qo.QueryObject;
import com.edt.service.IEmployeeService;
import com.edt.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Transactional
    public void save(Employee employee, Long[] ids) {
        employeeMapper.insert(employee);
        if (ids != null && ids.length > 0) {
            employeeMapper.insertRelation(employee.getId(), ids);
        }
    }


    public void delete(Long id) {
        employeeMapper.deleteRelation(id);
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void update(Employee employee, Long[] ids) {
        employeeMapper.updateByPrimaryKey(employee);
//        删除关系
        employeeMapper.deleteRelation(employee.getId());
//        关联关系
        if (ids != null && ids.length > 0) {
            employeeMapper.insertRelation(employee.getId(), ids);
        }
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> listAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public PageInfo<Employee> query(QueryObject qo) {

        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());//开始分页(对下一个sql进行分页),传当前页和每页显示数量
        List<Employee> employees = employeeMapper.selectForList(qo);
        return new PageInfo<>(employees);
    }

    @Override
    public Employee login(String username, String password) {

//        获取session
        HttpSession session = UserContext.getSession();
//        如果存进session的count变量为null
        if (session.getAttribute("count") == null) {
//            初始化count
            session.setAttribute("count", 0);
        }


//        密码错误5次 则限制登陆一段时间
        if ((Integer) session.getAttribute("count") >= 5) {
//            如果当前时间大于session设置的时间则清楚session
            if (new Date().after((Date) session.getAttribute("time"))) {
//                    清除session
                session.invalidate();
            }
//              抛出登录次数过多异常
            throw new LoginException("登陆次数过多,暂被禁止登陆,请稍后再试");
        }

        //              创建日历对象,方便对时间做操作
        Calendar instance = Calendar.getInstance();
//        为日历对象设置当前时间
        instance.setTime(new Date());
//       给当前时间加时
        instance.add(Calendar.SECOND, 5);
//       在获取当前时间加时
        Date time = instance.getTime();
//        将当前时间设置到session中
        session.setAttribute("time", time);


        if (!StringUtils.hasText(username)) {
            session.setAttribute("count", (Integer) session.getAttribute("count") + 1);
            throw new LoginException("用户名不能为空");
        }
        if (!StringUtils.hasText(password)) {
            session.setAttribute("count", (Integer) session.getAttribute("count") + 1);
            throw new LoginException("密码不能为空");
        }
        Employee employee = employeeMapper.selectByUsernameAndPassword(username, password);

        if (employee == null) {
            session.setAttribute("count", (Integer) session.getAttribute("count") + 1);
            throw new LoginException("账号或密码错误");
        }
        return employee;
    }

    @Override
    public Employee selectByUsername(String username) {
        return employeeMapper.selectByUsername(username);
    }

    @Override
    public Workbook exportXls(EmployeeQueryObject qo) {
//        查询所有员工信息
        List<Employee> employees = employeeMapper.selectForList(qo);
        //        生成一个excel,并且返回给浏览器
        Workbook wb = new HSSFWorkbook();
//        创建表格
        Sheet sheet = wb.createSheet("员工通讯录");
//        创建标题行
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("用户名");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("邮箱");
        row.createCell(3).setCellValue("年龄");
        row.createCell(4).setCellValue("部门");
        for (int i = 0; i < employees.size(); i++) {
//            获取员工对象
            Employee employee = employees.get(i);
            //        创建行
            row = sheet.createRow(i + 1);
//             创建单元格(列号)
            row.createCell(0).setCellValue(employee.getUsername());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getEmail());
            if (employee.getAge() != null) {
                row.createCell(3).setCellValue(employee.getAge());
            }
            if (employee.getDept() == null) {
                row.createCell(4).setCellValue("无");
            } else {
                row.createCell(4).setCellValue(employee.getDept().getName());
            }

        }
        return wb;
    }

    @Override
    public void importXls(MultipartFile file) throws IOException {
//        把文件输入流传给poi读取
        Workbook wb = new HSSFWorkbook(file.getInputStream());
//        读取第一张表格
        Sheet sheet = wb.getSheetAt(0);
//        获取最后一行的索引
        int lastRowNum = sheet.getLastRowNum();
//        标题行不读,所以从1开始
        for (int i = 1; i <= lastRowNum; i++) {
            Employee employee = new Employee();
//            一行一行读
            Row row = sheet.getRow(i);
//            读单元格
            String username = row.getCell(0).getStringCellValue();
            Employee employee2 = employeeMapper.selectByUsername(username);
            if (employee2 != null) {
                throw new LoginException(username + "用户名已存在");
            }
            employee.setUsername(row.getCell(0).getStringCellValue());
            employee.setName(row.getCell(1).getStringCellValue());
            employee.setEmail(row.getCell(2).getStringCellValue());
            double age = row.getCell(3).getNumericCellValue();
            employee.setAge((int) age);
//            拿到部门名称,查询部门
            String deptName = row.getCell(4).getStringCellValue();
            Department department = departmentMapper.selectByName(deptName);
            if (department != null) {
                employee.setDept(department);
            }
            employeeMapper.insert(employee);
        }
    }

}
