package com.asteroid.expression.common.util.generate;

import com.asteroid.expression.sys.entity.User;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: YuSai
 * @date: 2020-11-18 15:32
 */
public class GenUser {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://47.113.191.24:3306/expression?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC",
                "root", "123456");
        insert(conn);
    }

    private static List<User> generateUser() throws Exception {
        String[] province = "澳门特别行政区,广东省,陕西省,河南省,江西省,海南省,上海市,吉林省,安徽省,天津市,河北省,重庆市,甘肃省,宁夏回族自治区,山西省,江苏省,四川省,福建省,湖南省,浙江省,贵州省,台湾省,辽宁省,山东省,北京市,新疆维吾尔自治区,内蒙古自治区,西藏自治区,香港特别行政区,广西壮族自治区,湖北省,黑龙江省,云南省,青海省".split(",");
        Integer[] sex  = {1, 2};
        String[] image  = {"h1.png", "h2.png", "h3.png", "h4.png", "h5.png", "h6.png", "h7.png"};
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            User user = new User();
            user.setId(i);
            user.setName(GenDataUtil.getName());
            user.setUsername(GenDataUtil.getEnglishName());
            user.setPassword("3969fd358939509411c62b1ddde686fa");
            user.setMobile(GenDataUtil.getMobilePhone());
            user.setEmail(GenDataUtil.getEmail());
            user.setProvince(GenDataUtil.getEmail());
            int x = ((int)Math.ceil( Math.random() * 33));
            user.setProvince(province[x]);
            user.setAddress(GenDataUtil.getAddress());
            user.setStatus(1);
            int y = ((int)Math.ceil( Math.random() * 1));
            user.setSex(sex[y]);
            user.setBirthday(new Date());
            user.setLabel(user.getEmail());
            int z = ((int)Math.ceil( Math.random() * 6));
            user.setImageName(image[z]);
            user.setImagePath("F:\\workspace\\upload\\" + user.getUsername() + "\\head\\" + image[z]);
            user.setLastLoginDate(new Date());
            System.out.println(user.toString());
            File fileIn = new File("F:\\workspace\\expression\\src\\main\\webapp\\static\\image\\header\\" + image[z]);
            File fileOut = new File(user.getImagePath());
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
            users.add(user);
        }
        return users;
    }

    public static void insert(Connection conn) throws Exception {
        for (User user: generateUser()) {
            String sql = "insert into t_user(id, name, username, password, mobile, email, province," +
                    " address, status, sex, label, image_name, image_path) " +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getMobile());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getProvince());
            ps.setString(8, user.getAddress());
            ps.setInt(9, user.getStatus());
            ps.setInt(10, user.getSex());
            ps.setString(11, user.getLabel());
            ps.setString(12, user.getImageName());
            ps.setString(13, user.getImagePath());
            ps.execute();
            ps.close();
        }
        conn.close();

    }

}
