package com.edt.service.impl;

import com.edt.domain.ConsumptionItem;
import com.edt.mapper.ConsumptionItemMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IConsumptionItemService;
import com.edt.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsumptionItemServiceImpl implements IConsumptionItemService {

    @Autowired
    private ConsumptionItemMapper consumptionItemMapper;


    @Override
    public void save(ConsumptionItem consumptionItem) {
//        设置创建时间
        consumptionItem.setCreateTime(new Date());
//        设置创建人
        consumptionItem.setCreateUser(UserContext.getCurrentUser());
        consumptionItemMapper.insert(consumptionItem);
    }

    @Override
    public void delete(Long id) {
        consumptionItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ConsumptionItem consumptionItem) {
        consumptionItemMapper.updateByPrimaryKey(consumptionItem);
    }

    @Override
    public ConsumptionItem get(Long id) {
        return consumptionItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ConsumptionItem> listAll() {
        return consumptionItemMapper.selectAll();
    }

    @Override
    public PageInfo<ConsumptionItem> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize()); //对下一句sql进行自动分页
        List<ConsumptionItem> consumptionItems = consumptionItemMapper.selectForList(qo); //里面不需要自己写limit
        return new PageInfo<ConsumptionItem>(consumptionItems);
    }

    @Override
    public List<ConsumptionItem> selectByCno(String cno) {
        return consumptionItemMapper.selectByCno(cno);
    }

    @Override
    public void batchDelete(Long[] ids) {
        consumptionItemMapper.batchDelete(ids);
    }
}
