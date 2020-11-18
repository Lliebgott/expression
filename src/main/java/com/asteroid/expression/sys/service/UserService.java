package com.asteroid.expression.sys.service;

import com.asteroid.expression.common.base.constant.SystemStaticConst;
import com.asteroid.expression.common.base.dao.GenericDao;
import com.asteroid.expression.common.base.service.GenericService;
import com.asteroid.expression.common.config.properties.YamlProps;
import com.asteroid.expression.common.config.security.CustomPasswordEncoder;
import com.asteroid.expression.common.config.security.CustomUserService;
import com.asteroid.expression.common.util.DateUtil;
import com.asteroid.expression.common.util.JsonUtil;
import com.asteroid.expression.sys.dao.UserDao;
import com.asteroid.expression.sys.entity.*;
import com.asteroid.expression.sys.sysenum.DefaultGroupEnum;
import com.asteroid.expression.sys.sysenum.StatusEnum;
import com.asteroid.expression.sys.sysenum.UserRoleEnum;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author: YuSai
 * @date: 2020-10-23 09:47
 */
@Service("UserService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class UserService extends GenericService<User, QueryUser> {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserDao userDao;

    @Autowired
    private YamlProps yamlProps;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private LoginService loginService;

    @Override
    protected GenericDao<User, QueryUser> getDao() {
        return userDao;
    }

    @Transactional
    public JSONObject saveUser(User user) throws Exception {
        user.setStatus(1);
        user.setCreateDate(new Date());
        user.setBirthday(DateUtil.parseDate(DateUtil.DATE_FORMAT_STRING1, user.getBirthStr()));
        user.setPassword(new CustomPasswordEncoder().encode(user.getPassword()));
        String headFile = user.getHeadFile();
        String dirPath = yamlProps.getProps().getUploadPath();
        if (headFile.startsWith("__")) {
            String fileName =  headFile.replace("_", "");
            String fileInPath;
            if (dirPath.contains("/usr")) {
                fileInPath = "/usr/local/head/" + fileName;
            } else {
                fileInPath = System.getProperty("user.dir") + "\\src\\main\\webapp\\static\\image\\header\\" + fileName;
            }
            File fileIn = new File(fileInPath);
            String imagePath;
            if (dirPath.contains("/usr")) {
                imagePath = dirPath + user.getUsername() + "/head/" + fileName;
            } else {
                imagePath = dirPath + user.getUsername() + "\\head\\" + fileName;
            }
            user.setImageName(fileName);
            user.setImagePath(imagePath);
            File fileOut = new File(imagePath);
            // 判断文件父目录是否存在
            if (!fileOut.getParentFile().exists()) {
                fileOut.getParentFile().mkdirs();
            }
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileIn));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileOut));
            byte[]bys = new byte[1024];
            int len;
            while((len= bis.read(bys)) != -1){
                bos.write(bys,0,len);
            }
            bos.close();
            bis.close();
        } else {
            MultipartFile file = user.getFile0();
            String fileName = file.getOriginalFilename();
            user.setImageName(file.getOriginalFilename());
            String imagePath;
//            String imageName = UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
            if (dirPath.contains("/usr")) {
                imagePath = dirPath + user.getUsername() + "/head/" + fileName;
            } else {
                imagePath = dirPath + user.getUsername() + "\\head\\" + fileName;
            }
            user.setImagePath(imagePath);
            upload(null, user.getUsername(), file, fileName, "head");
        }
        boolean success = super.save(user);
        JSONObject result = new JSONObject();
        if(success){
            UserRole userRole = new UserRole();
            userRole.setRoleId(UserRoleEnum.NORMAL.getId());
            userRole.setUserId(user.getId());
            userRole.setCreateDate(new Date());
            userRoleService.save(userRole);
            result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
            result.put(SystemStaticConst.MSG, "注册成功！");
        }else{
            result.put(SystemStaticConst.RESULT,SystemStaticConst.FAIL);
            result.put(SystemStaticConst.MSG, "注册失败！");
        }
        return result;
    }

    @Transactional
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

    @Transactional
    public JSONObject publish(String content, MultipartFile files[]) {
        Content model = new Content();
        // 设置user_id
        model.setUser_id(CustomUserService.user.getId());
        model.setContent(content);
        model.setCreate_date(new Date());
        model.setStatus(StatusEnum.EFFECTIVE.getId());
        int n = userDao.publish(model);
        String userName = CustomUserService.user.getUsername();
        for (MultipartFile file: files) {
            upload(model.getId(), userName, file, file.getOriginalFilename(), "image");
        }
        JSONObject result = new JSONObject();
        if (n > 0) {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
            result.put("contents", loginService.queryAllContent(0));
            result.put("msg", "发布成功！");
        } else {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
            result.put("msg", "发布失败！");
        }
        return result;
    }

    @Transactional
    public void upload(Integer contentId, String userName, MultipartFile file, String fileName, String type) {
        try {
            String dirPath = yamlProps.getProps().getUploadPath();
//            String imageName = UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
            String imagePath;
            if (dirPath.contains("/usr")) {
                imagePath = dirPath + userName + "/" + type + "/" + fileName;
            } else {
                imagePath = dirPath + userName + "\\" + type + "\\" + fileName;
            }
            File dest = new File(imagePath);
            // 判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            // 保存文件
            file.transferTo(dest);
            if (null != contentId) {
                ContentFile contentFile = new ContentFile();
                contentFile.setContent_id(contentId);
                contentFile.setFile_name(fileName);
                contentFile.setFile_path(imagePath);
                userDao.saveContextFile(contentFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public JSONObject collect(Integer contentId, Integer collectId, Integer friendId, boolean state) {
        CollectContent model = new CollectContent();
        model.setUser_id(CustomUserService.user.getId());
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
        JSONObject result = new JSONObject();
        if (n > 0) {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
            result.put("msg", state ? "收藏成功！": "取消收藏成功！");
        } else {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
            result.put("msg", state ? "收藏失败！": "取消收藏失败！");
        }
        return result;
    }

    @Transactional
    public JSONObject forward(Integer contentId, String contentText) {
        Content model = new Content();
        model.setUser_id(CustomUserService.user.getId());
        model.setContent(contentText);
        model.setP_id(contentId);
        model.setCreate_date(new Date());
        model.setStatus(StatusEnum.EFFECTIVE.getId());
        int n = userDao.publish(model);
        JSONObject result = new JSONObject();
        if (n > 0) {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
            result.put("contents", loginService.queryAllContent(model.getUser_id()));
            result.put("msg", "转发成功！");
        } else {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
            result.put("msg", "转发失败！");
        }
        return result;
    }

    @Transactional
    public JSONObject comment(Integer contentId, Integer friendId, String coment) {
        Comment comment = new Comment();
        comment.setUser_id(CustomUserService.user.getId());
        comment.setComment_text(coment);
        comment.setFriend_id(friendId);
        comment.setContent_id(contentId);
        comment.setCreate_date(new Date());
        int n = userDao.saveComment(comment);
        JSONObject result = new JSONObject();
        if (n > 0) {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
            result.putAll(loginService.getComment(contentId));
            result.put("msg", "评论成功！");
        } else {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
            result.put("msg", "评论失败！");
        }
        return result;
    }

    @Transactional
    public JSONObject thumb(Integer contentId, Integer friendId, boolean state) {
        Thumb model = new Thumb();
        model.setUser_id(CustomUserService.user.getId());
        model.setFriend_id(friendId);
        model.setContent_id(contentId);
        model.setCreate_date(new Date());
        int n;
        if (state) {
            n = userDao.saveThumb(model);
        } else {
            n = userDao.cancelThumb(model);
        }
        JSONObject result = new JSONObject();
        if (n > 0) {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
            result.put("msg", state ? "点赞成功！": "取消点赞成功！");
        } else {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
            result.put("msg", state ? "点赞失败！": "取消点赞失败！");
        }
        return result;
    }

    @Transactional
    public JSONArray searchUser(String name) {
        String dirPath = yamlProps.getProps().getUploadPath();
        User user = new User();
        user.setId(CustomUserService.user.getId());
        user.setStatus(dirPath.length());
        user.setName(name);
        return JsonUtil.listMapToArray(userDao.searchUser(user));
    }

    @Transactional
    public JSONArray queryUserGroup() {
        List<Group> groups = loginService.queryGroupByUserId();
        JSONArray array = new JSONArray();
        if (groups.size() == 0) {
            JSONObject json = new JSONObject();
            json.put("id", "1");
            json.put("name", "我的好友");
            array.add(json);
        } else {
            for (Group userGroup: groups) {
                JSONObject json = new JSONObject();
                json.put("id", userGroup.getId());
                json.put("name", userGroup.getGroup_name());
                array.add(json);
            }
        }
        return array;
    }

    @Transactional
    public JSONObject saveFriend(Integer friendId, String userNote, Integer userGroup, String userCheckMsg) {
        Friend friend = new Friend();
        friend.setUser_id(CustomUserService.user.getId());
        friend.setFriend_id(friendId);
        friend.setGroup_id(userGroup);
        friend.setStatus(StatusEnum.EFFECTIVE.getId());
        friend.setCreate_date(new Date());
        int n = userDao.saveFriend(friend);
        friend = new Friend();
        friend.setUser_id(friendId);
        friend.setFriend_id(CustomUserService.user.getId());
        friend.setGroup_id(DefaultGroupEnum.MY_FRIEND.getId());
        friend.setStatus(StatusEnum.EFFECTIVE.getId());
        friend.setCreate_date(new Date());
        userDao.saveFriend(friend);
        JSONObject result = new JSONObject();
        if (n > 0) {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
            result.put("msg", "添加成功！");
        } else {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
            result.put("msg", "添加失败！");
        }
        return result;
    }

}