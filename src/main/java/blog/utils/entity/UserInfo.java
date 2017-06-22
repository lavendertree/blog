package blog.utils.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by weber on 2017/6/19.
 */
@Entity
@Table(name = "user_info", catalog = "blogdev")
public class UserInfo {
    private String username;
    private String password;
    private String name;
    private String sex;
    private Timestamp birth;
    private String interest;
    private String eMail;
    private String phone;
    private Integer qq;
    private String country;
    private String province;
    private String city;


    /** default constructor */
    public UserInfo() {
    }

    /** minimal constructor */
    public UserInfo(String username, String password){
        this.username=username;
        this.password=password;
    }
    /** full constructor */
    public UserInfo(String username, String password, String name, String sex, Timestamp birth, String interest, String eMail
            , String phone, Integer qq, String country, String province, String city){
        this.username=username;
        this.password=password;
        this.name=name;
        this.sex=sex;
        this.birth=birth;
        this.interest=interest;
        this.eMail=eMail;
        this.qq=qq;
        this.phone=phone;
        this.country=country;
        this.province=province;
        this.city=city;
    }

    @Id
    @Column(name = "username",unique = true,nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Column(name = "birth")
    public Timestamp getBirth() {
        return birth;
    }

    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }


    @Column(name = "interest")
    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }


    @Column(name = "e_mail")
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }


    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Column(name = "QQ")
    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }


    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }




}

