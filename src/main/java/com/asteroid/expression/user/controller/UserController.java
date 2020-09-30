package com.asteroid.expression.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.user.model.User;
import com.asteroid.expression.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: YuSai
 * @date: 2020-09-23 14:59
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * check用户名是否重复
     * @param username 用户名称
     * @return check结果
     */
    @RequestMapping(value = {"/checkExist"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject checkExist(String username) {
        return userService.checkExist(null, username);
    }

    /**
     * 注册
     * @param user 用户信息
     * @return 注册结果
     */
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addUser(User user) {
        return userService.addUser(user);
    }

    /**
     * 发布
     * @param content 发布内容
     * @param files 发布内容
     * @return 发布结果
     */
    @RequestMapping(value = {"/publish"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addUser(@RequestParam("content")final String content, @RequestParam("files[]") MultipartFile files[]) {
        return userService.publish(content, files);
    }

    /**
     * 赞或取消
     * @param contentId 发布内容id
     * @param friendId 用户id
     * @param state 赞或取消
     * @return 操作结果
     */
    @RequestMapping(value = {"/thumbs"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject thumbs(@RequestParam("contentId") Integer contentId,
                             @RequestParam("friendId") Integer friendId,
                             @RequestParam("state") boolean state) {
        return userService.thumb(contentId, friendId, state);
    }

    /**
     * 收藏或取消
     * @param contentId 发布内容id
     * @param friendId 用户id
     * @param state 收藏或取消
     * @return 操作结果
     */
    @RequestMapping(value = {"/collect"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject collect(@RequestParam("contentId") Integer contentId,
                             @RequestParam("collectId") Integer collectId,
                             @RequestParam("friendId") Integer friendId,
                             @RequestParam("state") boolean state) {
        return userService.collect(contentId, collectId,  friendId, state);
    }

    @RequestMapping("/forwardPage")
    public ModelAndView forwardPage(@RequestParam("id") Integer id) {
        ModelAndView view = new ModelAndView("main/forward");
        view.addObject("contentId", id);
        return view;
    }

    @RequestMapping("/forward")
    @ResponseBody
    public JSONObject forward(@RequestParam("contentId") Integer contentId, @RequestParam("contentText") String contentText) {
       return userService.forward(contentId, contentText);
    }

    @RequestMapping("/comment")
    @ResponseBody
    public JSONObject commnet(@RequestParam("contentId") Integer contentId,
                              @RequestParam("friendId") Integer friendId,
                              @RequestParam("comment") String comment) {
       return userService.comment(contentId, friendId, comment);
    }

}
