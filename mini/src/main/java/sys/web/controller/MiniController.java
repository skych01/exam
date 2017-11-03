package sys.web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSON;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import sys.entity.JSONResult;
import sys.properties.MiniProgramProperties;
import sys.service.MiniProgramService;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;

@Controller
public class MiniController {

    private final Logger logger = LoggerFactory.getLogger(MiniController.class);

    @Autowired
    private MiniProgramService miniProgramService;

    @RequestMapping("/login")
    @ResponseBody
    public JSONResult login(@RequestParam String code,
                            @RequestParam String encryptedData,
                            @RequestParam String iv) {
        JSONResult result = JSONResult.fail(null);
        try {
            result.setContent(miniProgramService.login(code, encryptedData, iv));
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
