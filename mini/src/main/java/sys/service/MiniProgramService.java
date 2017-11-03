package sys.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sys.entity.WechatUser;
import sys.security.JWTTokenUtil;
import sys.util.AES;
import sys.web.controller.MiniController;

import java.io.IOException;
import java.util.Date;

@Service
public class MiniProgramService {

    private final Logger logger = LoggerFactory.getLogger(MiniController.class);

    @Value("${my.app-id}")
    private String appId;
    @Value("${my.app-secret}")
    private String appSecret;


    private WecharService wecharService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    public String login(String code, String encryptedData, String iv) throws Exception {

        String jsonStr = getSessionInfo(code);
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        if (!jsonObject.isEmpty()&&jsonObject.has("openid")) {

            String sessionKey = jsonObject.getString("session_key");
            String openId = jsonObject.getString("openid");

            String userInfo = getUserInfo(encryptedData, iv, sessionKey);
            saveUserInfo(openId, userInfo);

            //生成JsonWebToken返回给client，后续API的调用携带这个令牌进行身份验证
            return jwtTokenUtil.generateToken(openId);

        } else {
            System.err.println("微信服务器返回的数据：" + jsonStr);
            throw new Exception("获取openid失败 ！");
        }
    }

    /**
     * 保存用户信息
     */
    private WechatUser saveUserInfo(String openId, String userInfo) {
        //保存获取到的微信用户信息
        WechatUser wechatUser = wecharService.findOne(openId);
        if (wechatUser == null) {
            JSONObject jsonObject = JSONObject.fromObject(userInfo);
            wechatUser = (WechatUser) JSONObject.toBean(jsonObject, WechatUser.class);
            wechatUser = wecharService.saveWecharUser(wechatUser);
        } else {
            wechatUser.setUpdateTime(new Date());
        }
        return wechatUser;
    }


    private int getExpiresIn(JsonNode node) {
        return node.get("expires_in").asInt();
    }

    /**
     * 获取用户信息
     *
     * @param encryptedData 完整用户信息的加密数据
     * @param iv            加密算法的初始向量
     * @param sessionKey    会话密钥
     * @return 用户信息的json数据
     * @throws Exception
     */
    private String getUserInfo(String encryptedData, String iv, String sessionKey) throws Exception {
        AES aes = new AES();
        byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
        if (null != resultByte && resultByte.length > 0) {
            return new String(resultByte, "UTF-8");
        } else {
            logger.warn("decrypt failed!");
            throw new Exception("数据解密后,结果为空 检查数据正确性");
        }
    }

    /**
     * 根据code 访问微信服务器 获取到openId和sessionKey
     *
     * @param code 用户登录凭证
     * @return json
     */
    private String getSessionInfo(String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/jscode2session");
        sb.append("?appid=").append(appId);
        sb.append("&secret=").append(appSecret);
        sb.append("&js_code=").append(code);
        sb.append("&grant_type=authorization_code");

        logger.debug("url : " + sb.toString());

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate.getForEntity(sb.toString(), String.class).getBody();
    }

    @Autowired
    public void setWecharService(WecharService wecharService) {
        this.wecharService = wecharService;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

}
