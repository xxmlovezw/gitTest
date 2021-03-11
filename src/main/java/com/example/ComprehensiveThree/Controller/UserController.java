package com.example.ComprehensiveThree.Controller;


import com.example.ComprehensiveThree.Domain.User;
import com.example.ComprehensiveThree.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/user")
@Api(tags = "用户信息管理")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="用户登录")
    public String login(String userId, String password)throws Exception{
        final User exists = userService.exists(userId, password);
        return objectMapper.writeValueAsString(exists);
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "用户修改密码")
    public String putPassword(String userId,String oldPassword, String newPassword){
        User exists = userService.exists(userId, oldPassword);
        if (exists != null){
            exists.setPassword(newPassword);
            User user = userService.updatePassword(exists);
            return "ok";
        }
        return "error";
    }

}
