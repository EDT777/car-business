package com.edt.service.impl;

import com.edt.domain.Business;
import com.edt.mapper.BusinessMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IBusinessService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements IBusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public void save(Business business) {
        businessMapper.insert(business);
    }

    @Override
    public void delete(Long id) {
        businessMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Business business) {
        businessMapper.updateByPrimaryKey(business);
    }

    @Override
    public Business get(Long id) {

        return businessMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Business> listAll() {
        return businessMapper.selectAll();
    }

    @Override
    public PageInfo<Business> query(QueryObject qo) {
//分页插件  不需要我们写count语句,它会自动生成
//分页插件  高查sql不需要我们自己写limt,它会在你原本的sql上自动拼接limit上去并执行
//分页插件  limit是插件自动计算参数并拼接上去,qo
//分页插件  提供PageInfo类,用于封装分页相关数据,不需要自己写PageResult类
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(),"open_date desc");//开始分页(对下一个sql进行分页),传当前页和每页显示数量
        List<Business> businesss = businessMapper.selectForList(qo);
        return new PageInfo<>(businesss);
    }

    @Override
    public Business selectMainStore() {
        return businessMapper.selectMainStore();
    }
}
