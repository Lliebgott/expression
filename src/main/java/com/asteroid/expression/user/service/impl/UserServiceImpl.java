package com.asteroid.expression.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.common.base.BaseService;
import com.asteroid.expression.common.eenum.StatusEnum;
import com.asteroid.expression.login.dao.LoginDao;
import com.asteroid.expression.user.dao.UserDao;
import com.asteroid.expression.user.model.*;
import com.asteroid.expression.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author: YuSai
 * @date: 2020-09-23 15:26
 */
@Service("UserService")
public class UserServiceImpl extends BaseService implements UserService {

    @Resource
    private UserDao userDao;

    @Autowired
    private Environment environment;

    @Override
    public User findByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    public JSONObject addUser(User user) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        user.setAddress(user.getProvince() + user.getCity() + user.getDistrict() + user.getAddress());
        user.setStatus(StatusEnum.EFFECTIVE.getId());
        user.setCreate_date(new Date());
        int n = userDao.addUser(user);
        if (n > 0) {
            result.put("success", true);
        }
        return result;
    }

    @Override
    public JSONObject checkExist(Integer id, String username) {
        JSONObject result = new JSONObject();
        result.put("valid", true);
        User user = new User();
        if (null != id){
            user.setId(id);
        }
        user.setUsername(username);
        if (userDao.checkExist(user) > 0) {
            result.put("valid", false);
        }
        return result;
    }

    @Override
    public JSONObject publish(String content, MultipartFile files[]) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        Content model = new Content();
        // 设置user_id
        model.setUser_id(super.getLoginUser().getId());
        model.setContent(content);
        model.setCreate_date(new Date());
        model.setStatus(StatusEnum.EFFECTIVE.getId());
        int n = userDao.publish(model);
        for (MultipartFile file: files) {
            upload(model.getId(), file, file.getOriginalFilename());
        }
        if (n > 0) {
            result.put("success", true);
        }
        return result;
    }

    public void upload(Integer contentId, MultipartFile file, String fileName) {
        try {
            String dirPath = environment.getProperty("uploadPath");
            String imageName = UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
            // 生成新的文件名
            File dest = new File(dirPath + imageName);
            // 判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            // 保存文件
            file.transferTo(dest);
            ContentFile contentFile = new ContentFile();
            contentFile.setContent_id(contentId);
            contentFile.setFile_name(fileName);
            contentFile.setFile_path(dirPath + imageName);
            userDao.saveContextFile(contentFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject collect(Integer contentId, Integer collectId, Integer friendId, boolean state) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        CollectContent model = new CollectContent();
        model.setUser_id(super.getLoginUser().getId());
        model.setFriend_id(friendId);
        model.setContent_id(contentId);
        model.setCollect_id(collectId);
        model.setCreate_date(new Date());
        int n;
        if (state) {
            n = userDao.saveCollect(model);
        } else {
            n = userDao.cancelCollect(model);
        }
        if (n > 0) {
            result.put("success", true);
        }
        return result;
    }

    @Override
    public JSONObject forward(Integer contentId, String contentText) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        Content model = new Content();
        model.setUser_id(super.getLoginUser().getId());
        model.setContent(contentText);
        model.setP_id(contentId);
        model.setCreate_date(new Date());
        model.setStatus(StatusEnum.EFFECTIVE.getId());
        int n = userDao.publish(model);
        if (n > 0) {
            result.put("success", true);
        }
        return result;
    }

    @Override
    public JSONObject comment(Integer contentId, Integer friendId, String coment) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        Comment comment = new Comment();
        comment.setUser_id(super.getLoginUser().getId());
        comment.setComment_text(coment);
        comment.setFriend_id(friendId);
        comment.setContent_id(contentId);
        comment.setCreate_date(new Date());
        int n = userDao.saveComment(comment);
        if (n > 0) {
            result.put("success", true);
        }
        return result;
    }

    @Override
    public JSONObject thumb(Integer contentId, Integer friendId, boolean state) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        Thumb model = new Thumb();
        model.setUser_id(super.getLoginUser().getId());
        model.setFriend_id(friendId);
        model.setContent_id(contentId);
        model.setCreate_date(new Date());
        int n;
        if (state) {
            n = userDao.saveThumb(model);
        } else {
            n = userDao.cancelThumb(model);
        }
        if (n > 0) {
            result.put("success", true);
        }
        return result;
    }

}
