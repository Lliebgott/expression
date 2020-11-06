package com.asteroid.expression.sys.controller;

import com.asteroid.expression.common.base.constant.SystemStaticConst;
import com.asteroid.expression.common.base.controller.GenericController;
import com.asteroid.expression.common.base.service.GenericService;
import com.asteroid.expression.common.config.security.CustomPasswordEncoder;
import com.asteroid.expression.common.config.security.CustomUserService;
import com.asteroid.expression.common.util.DateUtil;
import com.asteroid.expression.sys.entity.QueryUser;
import com.asteroid.expression.sys.entity.User;
import com.asteroid.expression.sys.entity.UserRole;
import com.asteroid.expression.sys.service.UserRoleService;
import com.asteroid.expression.sys.service.UserService;
import com.asteroid.expression.sys.sysenum.UserRoleEnum;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.Date;

/**
 * @author: YuSai
 * @date: 2020-10-21 16:40
 */
@Controller
@RequestMapping("/user")
public class UserController extends GenericController<User,QueryUser> {

    @Autowired
    private UserService userService;

    @Override
    protected GenericService<User, QueryUser> getService() {
        return userService;
    }

    /**
     * check用户名是否重复
     * @param username 用户名称
     * @return check结果
     */
    @RequestMapping(value = "/checkExist", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject checkExist(String username) {
        return userService.checkExist(null, username);
    }

    /**
     * 功能描述: 注册新增用户
     * @param user 用户信息
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject save(User user) throws Exception{
        return userService.saveUser(user);
    }

    /**
     * 发布
     * @param content 发布内容
     * @param files 发布内容
     * @return 发布结果
     */
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addUser(@RequestParam("content")final String content, @RequestParam("files[]") MultipartFile files[]) {
        return userService.publish(content, files);
    }

    /**
     * 收藏或取消
     * @param contentId 发布内容id
     * @param friendId 用户id
     * @param state 收藏或取消
     * @return 操作结果
     */
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject collect(@RequestParam("contentId") Integer contentId,
                              @RequestParam("collectId") Integer collectId,
                              @RequestParam("friendId") Integer friendId,
                              @RequestParam("state") boolean state) {
        return userService.collect(contentId, collectId,  friendId, state);
    }

    /**
     * 转发页面
     * @param id 发布内容id
     * @return 转发页面
     */
    @RequestMapping(value = "/forwardPage")
    public ModelAndView forwardPage(@RequestParam("id") Integer id) {
        ModelAndView view = new ModelAndView("forward");
        view.addObject("contentId", id);
        return view;
    }

    /**
     * 转发

     * @param contentId 发布内容id
     * @param contentText 转发内容
     * @return 操作结果
     */
    @RequestMapping(value = "/forward", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject forward(@RequestParam("contentId") Integer contentId, @RequestParam("contentText") String contentText) {
        return userService.forward(contentId, contentText);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject commnet(@RequestParam("contentId") Integer contentId,
                              @RequestParam("friendId") Integer friendId,
                              @RequestParam("comment") String comment) {
        return userService.comment(contentId, friendId, comment);
    }

    /**
     * 赞或取消
     * @param contentId 发布内容id
     * @param friendId 用户id
     * @param state 赞或取消
     * @return 操作结果
     */
    @RequestMapping(value = "/thumbs", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject thumbs(@RequestParam("contentId") Integer contentId,
                             @RequestParam("friendId") Integer friendId,
                             @RequestParam("state") boolean state) {
        return userService.thumb(contentId, friendId, state);
    }

    /**
     * 添加好友页面
     * @return 添加好友页面
     */
    @RequestMapping(value = "/addFriendPage")
    public ModelAndView addFriendPage() {
        ModelAndView view = new ModelAndView("addFriend");
        view.addObject("userId", CustomUserService.user.getId());
        return view;
    }

    /**
     * 查询用户
     * @param name 用户姓名/用户名
     * @return 用户
     */
    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray searchUser(@RequestParam("name") String name) {
        return userService.searchUser(name);
    }

}

