package com.edt.service.impl;

import com.edt.domain.Department;
import com.edt.mapper.DepartmentMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void save(Department department) {
        departmentMapper.insert(department);
    }

    @Override
    public void delete(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Department department) {
        departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public Department get(Long id) {

        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> listAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public PageInfo<Department> query(QueryObject qo) {
//分页插件  不需要我们写count语句,它会自动生成
//分页插件  高查sql不需要我们自己写limt,它会在你原本的sql上自动拼接limit上去并执行
//分页插件  limit是插件自动计算参数并拼接上去,qo
//分页插件  提供PageInfo类,用于封装分页相关数据,不需要自己写PageResult类
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());//开始分页(对下一个sql进行分页),传当前页和每页显示数量
        List<Department> departments = departmentMapper.selectForList(qo);
        return new PageInfo<>(departments);
    }
}
