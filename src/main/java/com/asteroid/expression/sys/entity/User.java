package com.asteroid.expression.sys.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author: YuSai
 * @date: 2020-10-21 17:03
 */
public class User implements UserDetails {

    private Integer id;

    private String name;

    private String username;

    private String password;

    private String mobile;

    private String email;

    private String province;

    private String city;

    private String district;

    private String address;

    private Integer status;

    private Integer sex;

    private String birthStr;

    private Date birthday;

    private String label;

    private String imageName;

    private String imagePath;

    private Date lastLoginDate;

    private Date createDate;

    private Date deleteDate;
    // 用户角色信息
    private List<Role> roles;
    // 权限集合数据
    private String roleArray;
    // 权限集合数据
    private MultipartFile file0;
    // 权限集合数据
    private String headFile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthStr() {
        return birthStr;
    }

    public void setBirthStr(String birthStr) {
        this.birthStr = birthStr;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String ImageName) {
        this.imageName = ImageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getRoleArray() {
        return roleArray;
    }

    public void setRoleArray(String roleArray) {
        this.roleArray = roleArray;
    }

    public MultipartFile getFile0() {
        return file0;
    }

    public void setFile0(MultipartFile file0) {
        this.file0 = file0;
    }

    public String getHeadFile() {
        return headFile;
    }

    public void setHeadFile(String headFile) {
        this.headFile = headFile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        if(this.getRoles()!=null){
            List<Role> roles=this.getRoles();
            for(Role role:roles){
                if(role.getEnName()!=null){
                    auths.add(new SimpleGrantedAuthority(role.getEnName()));
                }
            }
        }
        return auths;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 功能描述：组装角色数据集合
     * @param roleArray
     */
    public void packagingRoles(String roleArray){
        List<Role> roles = new ArrayList<Role>();
        if(roleArray!=null){
            Role role = null;
            for(String roleId:roleArray.split(",")){
                if(!roleId.isEmpty()){
                    role = new Role();
                    role.setId(Long.parseLong(roleId));
                    roles.add(role);
                }
            }
        }
        this.setRoles(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", sex=" + sex +
                ", birthStr='" + birthStr + '\'' +
                ", birthday=" + birthday +
                ", label='" + label + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", createDate=" + createDate +
                ", deleteDate=" + deleteDate +
                ", roles=" + roles +
                ", roleArray='" + roleArray + '\'' +
                ", file0=" + file0 +
                ", headFile='" + headFile + '\'' +
                '}';
    }

}