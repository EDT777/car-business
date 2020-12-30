package com.edt.service.impl;

import com.edt.domain.Employee;
import com.edt.domain.Notice;
import com.edt.mapper.NoticeMapper;
import com.edt.qo.NoticeQueryObject;
import com.edt.qo.QueryObject;
import com.edt.service.INoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public void save(Notice notice) {
        noticeMapper.insert(notice);
    }

    @Override
    public void delete(Long id) {
        noticeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Notice notice) {
        noticeMapper.updateByPrimaryKey(notice);
    }

    @Override
    public Notice get(Long id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Notice> listAll() {
        return noticeMapper.selectAll();
    }

    @Override
    public PageInfo<Notice> query(NoticeQueryObject qo) {
//        将当前用户设进公告类内
        Employee currentUser = qo.getCurrentUser();
        updateAllCurrentUser(currentUser.getId());

        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize()); //对下一句sql进行自动分页
        List<Notice> notices = noticeMapper.selectForList(qo); //里面不需要自己写limit

        return new PageInfo<Notice>(notices);
    }

    @Override
    public void updateIsRead(Long id, Long empId) {
        noticeMapper.updateIsRead(id,empId);
    }

    @Override
    public void insertRelation(Long id, List<Long> ids) {
        noticeMapper.insertRelation(id,ids);
    }

    @Override
    public Long selectReadNumber(Long id) {
        return noticeMapper.selectReadNumber(id);
    }


    public void updateAllCurrentUser(Long id) {
        noticeMapper.updateAllCurrentUser(id);
    }


}
