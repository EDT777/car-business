package com.edt.service.impl;

import com.edt.domain.Role;
import com.edt.mapper.RoleMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    public void save(Role role, Long[] ids) {
        roleMapper.insert(role);
        if (ids!=null&&ids.length>0){
            roleMapper.insertRelation(role.getId(),ids);
        }
    }

    @Transactional
    public void delete(Long id) {
        roleMapper.deleteRelation(id);
        roleMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void update(Role role, Long[] ids) {
        roleMapper.updateByPrimaryKey(role);
//        删除关系
        roleMapper.deleteRelation(role.getId());
//        关联关系
        if (ids!=null&&ids.length>0){
            roleMapper.insertRelation(role.getId(),ids);
        }
    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.selectAll();
    }

    @Override
    public PageInfo<Role> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());//开始分页(对下一个sql进行分页),传当前页和每页显示数量
        List<Role> Roles = roleMapper.selectForList(qo);
        return new PageInfo<>(Roles);
    }

    @Override
    public void updateRole(Long id, long roleId) {
        roleMapper.updateRole(id,roleId);
    }
}
