package com.edt.service.impl;

import com.edt.mapper.BusinessReportMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IBusinessReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class BusinessReportServiceImpl implements IBusinessReportService {
   @Autowired
   private BusinessReportMapper businessReportMapper;
    @Override
    public PageInfo<HashMap> selectReport(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<HashMap> list  = businessReportMapper.selectReport(qo);
        return new PageInfo<>(list);
    }
}
