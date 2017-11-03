package sys.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.config.resource.MySettings;
import sys.entity.JSONResult;

import java.util.Date;

/**
 * Created by Administrator on 2017/10/11.
 */
@Controller
public class HelloController {

    @Autowired
    private MySettings mySettings;

    @RequestMapping("/index")
    public String index(){return "index";}

    @RequestMapping("/login")
    public String login(Model model,Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/index";
        } else {
            model.addAttribute("my", mySettings);
            return "login";
        }
    }

    @RequestMapping("/getDate")
    public @ResponseBody JSONResult getDate(){
        return JSONResult.success(new Date());
    }
}
