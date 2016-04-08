package com.online.bos.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.online.bos.dao.IFunctionDao;
import com.online.bos.dao.IUserDao;
import com.online.bos.domain.Function;
import com.online.bos.domain.User;

/**
 * 自定义Realm
 * @author YQ
 *
 */
@Component("bosRealm")
public class BOSRealm extends AuthorizingRealm {
	
	// 注入DAO
	@Resource(name="userDao")
	private IUserDao userDao;
	@Resource(name="functionDao")
	private IFunctionDao functionDao;
	
	// 认证方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		//返回空当前账号不存在
		
		//toke强转
		UsernamePasswordToken usernamePasswordToken = 
				(UsernamePasswordToken) authenticationToken;
		String username = usernamePasswordToken.getUsername();
		//根据用户名查询密码，有安全管理器负责对比查询出的数据库中的密码和页面输入的密码是否一致
		User user = userDao.findUserByUsername(username);
		if (user == null) {
			return null;
		}
		String passwordFromDB = user.getPassword();
		
		//最后的比对需要交给安全管理器
		//三个参数进行初步的简单认证信息对象的包装
		AuthenticationInfo info = 
				new SimpleAuthenticationInfo(user,
						passwordFromDB, //由安全管理器进行包装运行 
						this.getClass().getSimpleName());
		
		return info;
	}
	
	// 授权方法
	// 执行的时期
	/**
	 * 在访问需要控制的时候需要权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalCollection) {
		
		//根据当前登录用户，查询用户的角色，根据角色对应获得的权限添加到信息对象中
		
		//程序任何位置都可以拿到user对象
		
		//方法一：
		User user = (User) principalCollection.getPrimaryPrincipal();
		
		//方法二：
//		Subject subject = SecurityUtils.getSubject();
//		User _user = (User) subject.getPrincipal();
//		System.out.println("subject"+_user.getUsername());
		
		//方法三：从context中获取BOSContext中获取
		
		//所有的过程都是动态从数据库中取出来
		
		//为所有的用户授予staff权限（模拟）
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<Function> list = null;
		//根据当前登录用户，查询用户角色，返回用户权限，将权限添加到当前的权限信息中
		
		//如果是超级管理员应该赋予全部权限的操作
		if (user.getUsername().equals("admin")) {
			//如果是超级管理员，授予所有权限
			list = functionDao.findAll();
		} else {
			//普通用户，根据用户查询对应的权限
			list = functionDao.findFunctionByUserId(user.getId());
		}
		
		for (Function function : list) {
			//权限关键字
			String code = function.getCode();
			info.addStringPermission(code);
		}
		
		return info;
	}
	
}
