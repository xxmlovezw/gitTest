package com.example.ComprehensiveThree.Controller;


import com.example.ComprehensiveThree.Domain.User;
import com.example.ComprehensiveThree.Domain.UserInfo;
import com.example.ComprehensiveThree.POJO.UserDetailInfo;
import com.example.ComprehensiveThree.Service.UserInfoService;
import com.example.ComprehensiveThree.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(String userId)throws Exception{
        final UserInfo byId = userInfoService.findById(userId);
        return objectMapper.writeValueAsString(byId);
    }


    @RequestMapping(method = RequestMethod.GET,path = "/{userId}")
    @ResponseBody
    public String getUserInfosByName(@PathVariable String userId)throws Exception{
        final UserInfo byId = userInfoService.findById(userId);
        final User userByUserId = userService.findUserByUserId(userId);
        UserDetailInfo userDetailInfo = new UserDetailInfo();
        userDetailInfo.setUserId(byId.getUserId());
        userDetailInfo.setUserName(userByUserId.getUserName());
        userDetailInfo.setDepartments(byId.getDepartments());
        userDetailInfo.setMajor(byId.getMajor());
        userDetailInfo.setPhone(byId.getPhone());
        userDetailInfo.setEmail(byId.getEmail());
        userDetailInfo.setMax(byId.getMax());
        userDetailInfo.setTime(byId.getTime());
        userDetailInfo.setLendNum(byId.getLendNum());
        return objectMapper.writeValueAsString(userDetailInfo);
    }


    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String putUserInfo(UserInfo userInfo){
        final UserInfo byId = userInfoService.findById(userInfo.getUserId());
        if (userInfo.getDepartments() != null){
            byId.setPhone(userInfo.getDepartments());
        }
        if (userInfo.getMajor() != null){
            byId.setMajor(userInfo.getMajor());
        }
        if (userInfo.getPhone() != null){
            byId.setPhone(userInfo.getPhone());
        }
        if (userInfo.getEmail() != null){
            byId.setEmail(userInfo.getEmail());
        }
        userInfoService.updateUserInfo(byId);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String addUser(UserInfo userInfo, String userName){
        System.out.println(userInfo.getUserId());
        User user = new User();
        user.setUserId(userInfo.getUserId());
        user.setUserName(userName);
        user.setPassword(userInfo.getUserId());
        userInfoService.addUserInfo(userInfo);
        userService.addUser(user);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(String userId){
        userService.deleteUserByUserId(userId);
        userInfoService.deleteUserInfoByUserId(userId);
        return "ok";
    }
}
