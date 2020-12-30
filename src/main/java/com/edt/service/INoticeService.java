package com.edt.service;

import com.edt.domain.Notice;
import com.edt.qo.NoticeQueryObject;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface INoticeService {
    void save(Notice notice);
    void delete(Long id);
    void update(Notice notice);
    Notice get(Long id);
    List<Notice> listAll();
    // 分页查询的方法
    PageInfo<Notice> query(NoticeQueryObject qo);

    void updateIsRead(Long id, Long empId);

    void insertRelation(Long id, List<Long> ids);


    Long selectReadNumber(Long id);
}
