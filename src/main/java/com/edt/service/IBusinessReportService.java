package com.edt.service;

import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;

public interface IBusinessReportService {
    PageInfo<HashMap> selectReport(QueryObject qo);
}
