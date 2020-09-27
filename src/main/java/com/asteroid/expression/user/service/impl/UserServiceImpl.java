package com.asteroid.expression.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.common.eenum.StatusEnum;
import com.asteroid.expression.user.dao.UserDao;
import com.asteroid.expression.user.model.Content;
import com.asteroid.expression.user.model.User;
import com.asteroid.expression.user.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    private String dirPath = "F:\\workspace\\upload";

    @Override
    public JSONObject addUser(User user) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        user.setAddress(user.getProvince() + user.getCity() + user.getDistrict() + user.getAddress());
        user.setStatus(StatusEnum.EFFECTIVE.getId());
        user.setLast_login_time(new Date());
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
        Content c = new Content();
        // 设置user_id
        c.setUser_id(9);
        c.setContent(content);
        c.setP_date(new Date());
        c.setStatus(StatusEnum.EFFECTIVE.getId());
        int n = userDao.publish(c);
        for (MultipartFile file: files) {
            upload(file, dirPath, file.getOriginalFilename());
        }
        if (n > 0) {
            result.put("success", true);
        }
        return result;
    }

    public String upload(MultipartFile file, String path, String fileName) {
        // 生成新的文件名
        String realPath = path + "/" + UUID.randomUUID().toString().replace("-", "")+fileName.substring(fileName.lastIndexOf("."));
        File dest = new File(realPath);
        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        // 保存文件
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest.getName();
    }

}
