package cn.xnmll.demo_2020.controller;

import cn.xnmll.demo_2020.dto.AccesstokenDTO;
import cn.xnmll.demo_2020.dto.GithubUser;
import cn.xnmll.demo_2020.provider.GithubProvider;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {


    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String clientURI;



    @Autowired
    private GithubProvider githubProvider;



    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")    String code,
                           @RequestParam(name = "state") String state){
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();

        accesstokenDTO.setClient_id(clientId);
        accesstokenDTO.setClient_secret(clientSecret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(clientURI);
        accesstokenDTO.setState(state);
        String accesstoken = githubProvider.getAccesstoken(accesstokenDTO);
        GithubUser user=githubProvider.getUser(accesstoken);
        System.out.println(user.getName());
        return "index";
    }




}
