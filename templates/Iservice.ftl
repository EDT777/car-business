package com.edt.service;

import com.edt.domain.${capitalize};
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface I${capitalize}Service {
    void save(${capitalize} ${uncapitalize});
    void delete(Long id);
    void update(${capitalize} ${uncapitalize});
    ${capitalize} get(Long id);
    List<${capitalize}> listAll();
    // 分页查询的方法
    PageInfo<${capitalize}> query(QueryObject qo);
}
