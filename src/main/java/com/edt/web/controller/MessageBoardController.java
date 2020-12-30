package com.edt.web.controller;


import com.edt.domain.MessageBoard;
import com.edt.domain.MessageReply;
import com.edt.service.IBusinessService;
import com.edt.service.IMessageBoardService;
import com.edt.qo.QueryObject;
import com.edt.qo.JsonResult;
import com.edt.service.IMessageReplyService;
import com.edt.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MessageBoardController {

    @Autowired
    private IMessageBoardService messageBoardService;
    @Autowired
    private IBusinessService businessService;
    @Autowired
    private IMessageReplyService messageReplyService;

    @RequestMapping("/pageBoard")
    public String pageBoard(Model model, @ModelAttribute("qo") QueryObject qo) {
        PageInfo<MessageBoard> pageInfo = messageBoardService.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("business", businessService.selectMainStore());
        model.addAttribute("businesses", businessService.listAll());
        return "boardPage";
    }

    @RequestMapping("/listBoard")
    public String listBoard(Long id, Model model) {
        MessageBoard messageBoard = messageBoardService.get(id);
        List<MessageReply> messageReply = messageReplyService.selectByBoardId(id);
        model.addAttribute("messageReply", messageReply);
        model.addAttribute("messageBoard", messageBoard);
        model.addAttribute("business", businessService.selectMainStore());
        model.addAttribute("businesses", businessService.listAll());
        return "boardList";
    }

    @RequestMapping("messageBoard/reply")
    @RequiredPermission(name = "回复留言", expression = "messageBoard:reply")
    @ResponseBody
    public JsonResult reply(Long boardId,MessageReply messageReply) {
        messageReplyService.save(boardId,messageReply);
        return new JsonResult();
    }

    @RequestMapping("/delete")
    @RequiredPermission(name = "留言板删除", expression = "messageBoard:delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            messageBoardService.delete(id);
        }
        return new JsonResult();
    }


    @RequestMapping("/messageBoard/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(MessageBoard messageBoard) {

        messageBoardService.save(messageBoard);

        return new JsonResult();
    }
}
