package cn.xnmll.demo_2020.controller;

import cn.xnmll.demo_2020.dto.AccesstokenDTO;
import cn.xnmll.demo_2020.dto.GithubUser;
import cn.xnmll.demo_2020.mapper.UserMapper;
import cn.xnmll.demo_2020.model.User;
import cn.xnmll.demo_2020.provider.GithubProvider;
import cn.xnmll.demo_2020.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")    String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response){
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();

        accesstokenDTO.setClient_id(clientId);
        accesstokenDTO.setClient_secret(clientSecret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(clientURI);
        accesstokenDTO.setState(state);
        String accesstoken = githubProvider.getAccesstoken(accesstokenDTO);
        GithubUser githubUser=githubProvider.getUser(accesstoken);


        if(githubUser != null && githubUser.getId()!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            //user.setGmtCreate(System.currentTimeMillis());
            //user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            //userMapper.insert(user);

            userService.createOrUpdate(user);

            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
