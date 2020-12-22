package com.edt.mapper;


import com.edt.qo.QueryObject;

import java.util.HashMap;
import java.util.List;

public interface BusinessReportMapper {
    List<HashMap> selectReport(QueryObject qo);
}