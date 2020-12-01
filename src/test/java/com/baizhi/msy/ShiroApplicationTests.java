package com.baizhi.msy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShiroApplicationTests {

    //后台认证方法
    public static void testlogin(String username,String password) {

        //初始化安全管理器工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //根据安全管理器工厂初始化安全管理器
        SecurityManager securityManager = factory.createInstance();

        //将安全管理器交给安全工具类
        SecurityUtils.setSecurityManager(securityManager);

        //根据安全工具类获取主体对象
        Subject subject = SecurityUtils.getSubject();

        //创建令牌 token=身份信息(username)+凭证信息(password)
        AuthenticationToken token=new UsernamePasswordToken(username,password);

        //认证
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("未知的账号异常   用户名不正确");
        }catch (IncorrectCredentialsException e) {
            System.out.println("不正确的凭证异常   密码错误");
        }

        /*
        * UnknownAccountException: 未知的账号异常   用户名不正确
        * IncorrectCredentialsException:不正确的凭证异常   密码错误
        * */

        boolean authenticated = subject.isAuthenticated();

        System.out.println("认证状态: "+authenticated);


    }

    public static void main(String[] args) {
        testlogin("xiaohei","123456");
    }

}
