package com.edt.web.controller;


import com.edt.domain.Exam;
import com.edt.qo.ExamNumbers;
import com.edt.mapper.QuestionItemMapper;
import com.edt.service.IExamService;
import com.edt.qo.JsonResult;
import com.edt.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private IExamService examService;

    @RequestMapping("/list")
    public String list(Model model, Long id) {//接收试卷的id值
        if (id==null){
            id=14L;
        }
        Exam exam = examService.get(id);
        model.addAttribute("exam", exam);
        int singles = exam.getSingles().size()*exam.getSingles().get(0).getScore();
        int oneSingle= exam.getSingles().get(0).getScore();
        model.addAttribute("oneSingle", oneSingle);
        model.addAttribute("singles", singles);

        int multiples = exam.getMultiples().size()*exam.getMultiples().get(0).getScore();
        int oneMultiple= exam.getMultiples().get(0).getScore();
        model.addAttribute("oneMultiple", oneMultiple);
        model.addAttribute("multiples", multiples);

        int judges = exam.getJudges().size()*exam.getJudges().get(0).getScore();
        int oneJudge= exam.getJudges().get(0).getScore();
        model.addAttribute("oneJudge", oneJudge);
        model.addAttribute("judges", judges);
        return "exam/list";
    }

    @RequestMapping("/input")
    @RequiredPermission(name = "试卷发布", expression = "exam:input")
    public String list() {
        return "exam/input";
    }


    @RequestMapping("/delete")
    @RequiredPermission(name = "试卷删除", expression = "exam:delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            examService.delete(id);
        }
        return new JsonResult();
    }


    @RequestMapping("/save")
    @RequiredPermission(name = "试卷新增", expression = "exam:saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(ExamNumbers examNumbers) {
        JsonResult jsonResult = new JsonResult();
        //新增
        Long examId = examService.save(examNumbers);
        jsonResult.setId(examId);
        return jsonResult;
    }

}
