package com.online.bos.shiro;

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

import com.online.bos.dao.IUserDao;
import com.online.bos.domain.User;

/**
 * 自定义Realm
 * @author YQ
 *
 */
public class BOSRealm extends AuthorizingRealm {
	
	// 注入DAO
	@Resource(name="userDao")
	private IUserDao userDao;
	
	// 认证方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		//返回空当前账号不存在
//		System.out.println("========================================");
//		System.out.println("2、开始认证顺序");
		
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
		
		//所有的过程都是动态从数据库中取出来
		
		//为所有的用户授予staff权限（模拟）
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermission("staff");
		
		return info;
	}
	
	
}
