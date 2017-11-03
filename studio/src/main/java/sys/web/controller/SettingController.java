package sys.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sys.entity.JSONResult;
import sys.security.passwordAuthentication.CustomUserDetailsService;
import sys.security.passwordAuthentication.SecurityUser;
import sys.service.SettingService;

@Controller
@RequestMapping("setting")
public class SettingController {

    private  SettingService settingService;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public void setCustomUserDetailsService(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Autowired
    public void setSettingService(SettingService settingService) {
        this.settingService = settingService;
    }

    @RequestMapping("info")
    public String info(Authentication authentication, Model model) {
        SecurityUser user = (SecurityUser)customUserDetailsService.loadUserByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "setting/info";
    }

    @RequestMapping("updateInfo")
    public @ResponseBody
    JSONResult updateInfo(int userId, @RequestParam(required = false)String email,
                          @RequestParam(required = false)String nickname,
                          @RequestParam(required = false)String mobile ) {
        JSONResult result = JSONResult.fail("服务器内部错误");
        try {
            settingService.updateInfo(userId, email, nickname, mobile);
            result = JSONResult.success(null);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("pwd")
    public String pwd(Authentication authentication, Model model) {
        return "setting/pwd";
    }

    @RequestMapping("updatePassword")
    public @ResponseBody
    JSONResult updatePassword(String old,String now) {
        JSONResult result = JSONResult.fail("服务器内部错误");
        try {
            if (settingService.updatePassword(old,now)){
                result = JSONResult.success(null);
            }else {
                result.setMessage("密码有误");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("account")
    public String account(Authentication authentication, Model model) {
        model.addAttribute("users", settingService.listAllUser());
        return "setting/account";
    }



}
