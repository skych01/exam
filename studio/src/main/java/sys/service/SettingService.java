package sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sys.entity.DRole;
import sys.entity.DUser;
import sys.repository.DUserRepository;
import sys.security.passwordAuthentication.CustomUserDetailsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettingService {

    private DUserRepository userRepository;

    private CustomUserDetailsService userDetailsService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final String defaultPwd = "123456";

    @Autowired
    public void setUserRepository(DUserRepository dUserRepository) {
        this.userRepository = dUserRepository;
    }

    @Autowired
    public void setUserDetailsService(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    /**
     * 修改个人信息
     */
    public void updateInfo(int userId, String eMail, String name, String mobile) {
        DUser user = userRepository.findOne(userId);

        if (validEmpty(eMail)) user.setEmail(eMail);

        if (validEmpty(name)) user.setNickname(name);

        user.setMobile(mobile);

        userRepository.save(user);

    }

    /**
     * 修改密码
     */
    public boolean updatePassword(String old, String now) {
        DUser dUser = getUser();
        if (validPassword(old, dUser.getPassword())) {
            if (!StringUtils.isEmpty(now)) {
                dUser.setPassword(passwordEncode(now));
                userRepository.save(dUser);
                return true;
            }
        }
        return false;
    }

    /**
     *获取当前用户
     */
    private DUser getUser() {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    public List<DUser> listAllUser(){
       return userRepository.findAll().stream().filter(user -> user.getUserId()>1).collect(Collectors.toList());
    }

    public void addUser(String username,String nickname,String mobile) {
        int userId =1+ userRepository.findMaxId();
        int thisUserId = getUser().getUserId();
        DUser user = new DUser();
        user.setUserId(userId);
        user.setUsername(username);
        user.setNickname(nickname);
        user.setMobile(mobile);
        user.setCreateDate(new java.util.Date());
        user.setCreatorId(thisUserId);

        user.setEnabled(true);
        user.setExpired(false);
        user.setLocked(false);
        user.setLoginWayAccount(true);
        user.setLoginWayQrcode(true);

        userRepository.save(user);
    }

    /**
     * 加密
     */
    private String passwordEncode(String nowPassword) {
        return passwordEncoder.encode(nowPassword);
    }

    /**
     * 校验 密码 是否正确
     */
    private boolean validPassword(String checkout, String pwd) {
        return StringUtils.isEmpty(pwd) || passwordEncoder.matches(checkout, pwd);
    }

    private boolean validEmpty(String eMail) {
        return eMail != null && !eMail.isEmpty();
    }

    /**
     * 获取指定用户拥有的角色
     * @param userId
     * @return
     */

    public List<DRole> getUserRoles(int userId) {
        DUser user = userRepository.findOne(userId);
       return user.getRoles();
    }

    /**
     * 启用/禁用
     * @param userId
     */
    public void enableUser (int userId){
        DUser user = userRepository.findOne(userId);
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
    }

    /**
     * 重置密码
     * @param userId
     */
    public void resetPwd (int userId){
        DUser user = userRepository.findOne(userId);
        user.setPassword(passwordEncode(defaultPwd));
        userRepository.save(user);
    }

}
