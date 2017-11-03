package sys.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.entity.Answer;
import sys.entity.JSONResult;
import sys.entity.Questions;
import sys.service.ManageService;

@Controller
@RequestMapping("exam")
public class ManageController {


    private ManageService manageService;
    private Logger logger = LoggerFactory.getLogger(ManageController.class);

    @Autowired
    public ManageController(ManageService manageService) {
        this.manageService = manageService;
    }

    @RequestMapping("getQuestion")
    public @ResponseBody
    JSONResult getQuestion(@RequestParam(required = false) String name,int pageNo,int pageSize){
        return JSONResult.success(manageService.findQuestionAndAnswer(pageNo,pageSize,name));
    }

    @RequestMapping("addQuestion")
    public @ResponseBody
    JSONResult addQuestion(@RequestParam String name){
        JSONResult result = JSONResult.success(null);
        try {
            manageService.addQuestion(name);
        }
        catch (Exception e){
            result = JSONResult.fail("服务器出现错误，请稍后再试");
            logger.error(this.getClass().getName()+"addAnswer 方法出错");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("editQuestion")
    public @ResponseBody
    JSONResult editQuestion(int questionId,String questionName){
        JSONResult result = JSONResult.success(null);
        try {
            Questions questions = manageService.findOneQuestion(questionId);
            questions.setQuestionName(questionName);
            manageService.updateQuestion(questions);
        }
        catch (Exception e){
            result = JSONResult.fail("服务器出现错误，请稍后再试");
            logger.error(this.getClass().getName()+"editQuestion 方法出错");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("delQuestion")
    public @ResponseBody
    JSONResult delQuestion(int questionId){
        JSONResult result = JSONResult.success(null);
        try {
            manageService.delQuestion(questionId);
        }
        catch (Exception e){
            result = JSONResult.fail("正在使用中无法删除！");
            logger.error(this.getClass().getName()+"delQuestion 方法出错");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("answer")
    public String answer(Model model,int questionId){
        Questions questions = manageService.findOneQuestion(questionId);
        model.addAttribute("question", questions);
        return "answer";
    }

    @RequestMapping("getAnswer")
    public @ResponseBody
    JSONResult getAnswer(int  answerId){
        return JSONResult.success(manageService.findAnswerByQuestionId(answerId));
    }

    @RequestMapping("addAnswer")
    public @ResponseBody
    JSONResult addAnswer( Answer answer){
        JSONResult result = JSONResult.success(null);
        try {
            manageService.addAnswer(answer);
        }
        catch (Exception e){
            result = JSONResult.fail("服务器出现错误，请稍后再试");
            logger.error(this.getClass().getName()+"addAnswer 方法出错");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("editAnswer")
    public @ResponseBody
    JSONResult editAnswer( Answer answer){
        System.out.println(answer);
        JSONResult result = JSONResult.success(null);
        try {
            Answer answer1 = manageService.findOneAnswer(answer.getAnswerId());
            answer.setCreateTime(answer1.getCreateTime());
            manageService.UpdateAnswer(answer);
        }
        catch (Exception e){
            result = JSONResult.fail("服务器出现错误，请稍后再试");
            logger.error(this.getClass().getName()+"editQuestion 方法出错");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("delAnswer")
    public @ResponseBody
    JSONResult delAnswer(int answerId){
        JSONResult result = JSONResult.success(null);
        try {
            manageService.delAnswer(answerId);
        }
        catch (Exception e){
            result = JSONResult.fail("正在使用中无法删除！");
            logger.error(this.getClass().getName()+"delAnswer 方法出错");
            e.printStackTrace();
        }
        return result;
    }
}
