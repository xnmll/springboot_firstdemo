package cn.xnmll.demo_2020.controller;

import cn.xnmll.demo_2020.dto.AccesstokenDTO;
import cn.xnmll.demo_2020.dto.GithubUser;
import cn.xnmll.demo_2020.mapper.UserMapper;
import cn.xnmll.demo_2020.model.User;
import cn.xnmll.demo_2020.provider.GithubProvider;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {


    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String clientURI;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GithubProvider githubProvider;



    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")    String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();

        accesstokenDTO.setClient_id(clientId);
        accesstokenDTO.setClient_secret(clientSecret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(clientURI);
        accesstokenDTO.setState(state);
        String accesstoken = githubProvider.getAccesstoken(accesstokenDTO);
        GithubUser githubUser=githubProvider.getUser(accesstoken);
        System.out.println(githubUser.getName());


        if(githubUser != null){
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());
            userMapper.insert(user);
            request.getSession().setAttribute("user",githubUser);//创建账户
            return "redirect:/";
        }else {
            return "redirect:/";
        }

    }

    /*
    create table USER
(
	ID INT auto_increment
		primary key,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT
);


     */



}
