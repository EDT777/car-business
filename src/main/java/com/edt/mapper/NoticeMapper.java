package com.edt.mapper;

import com.edt.domain.Notice;
import com.edt.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    Notice selectByPrimaryKey(Long id);

    List<Notice> selectAll();

    int updateByPrimaryKey(Notice record);

    List<Notice> selectForList(QueryObject qo);


    void updateAllCurrentUser(Long id);

    void updateIsRead(@Param("id") Long id, @Param("empId") Long empId);

    void insertRelation(@Param("id") Long id, @Param("ids") List<Long> ids);


    Long selectReadNumber(Long id);
}