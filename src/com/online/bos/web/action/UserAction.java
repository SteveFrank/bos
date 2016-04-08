package com.online.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.online.bos.domain.User;
import com.online.bos.utils.BOSContext;
import com.online.bos.utils.MD5Utils;
import com.online.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * UserAction 保证多例
 * @author YQ
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	private static final long serialVersionUID = 1L;
	
	//提供属性接收验证码
	private String checkCode;

	private User user;
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public UserAction() throws InstantiationException, IllegalAccessException {
		super();
	}
	
	/**
	 * 登录系统,使用shiro框架操作	
	 * @return
	 */
	public String login() {
		//从session中获取自动生成的验证码
		String key = (String) ActionContext.getContext().getSession().get("key");
		//shiro的基本认证方法
		if (StringUtils.isNotBlank(key) && checkCode.equals(key)) {
			//使用shiro方式进行认证
			String username = model.getUsername();
			String password = model.getPassword();
			password = MD5Utils.md5(password);
			//主体,当前状态为没有认证的状态“未认证”
			Subject subject = SecurityUtils.getSubject();
			//登录认证令牌（用户名密码令牌）
			AuthenticationToken token = new UsernamePasswordToken(username,password);
			//登录方法（认证是否通过）
			//使用subject调用securityManager,安全管理器调用Realm
			try { //利用异常操作
				//需要开始调用到Realm中
//				System.out.println("========================================");
//				System.out.println("1、进入认证方法");
				subject.login(token);
				user = (User) subject.getPrincipal();
			} catch (UnknownAccountException e) {
				this.addActionError(this.getText("loginError"));
				return LOGIN;
			}
			
			ActionContext.getContext().getSession().put("loginUser", user);
			return "home";
		} else {
			//验证码错误
			this.addActionError(this.getText("checkCodeError"));
			return LOGIN;
		}
	}
	
	/**
	 * 登录系统,没有使用shiro框架
	 * @return
	 */
	public String login_bak() {
		//从session中获取自动生成的验证码
		String key = (String) ActionContext.getContext().getSession().get("key");
		
//		System.out.println("checkCode:"+checkCode);
//		System.out.println("key:"+key);
		
		if (StringUtils.isNotBlank(key) && checkCode.equals(key)) {
			if (StringUtils.isNotBlank(this.model.getUsername()) && StringUtils.isNotBlank(this.model.getPassword())) {
				//验证码正确
				User user = userService.login(this.model);
				if (user != null) {
					//登录成功
					ActionContext.getContext().getSession().put("loginUser", user);
					return "home";
				} else {
					//登录失败
					//1、添加错误信息
					this.addActionError(this.getText("loginError"));
					return LOGIN;
				}
			} else {
				this.addActionError(this.getText("loginEmptyError"));
				return LOGIN;
			}
		} else {
			//验证码错误
			this.addActionError(this.getText("checkCodeError"));
			return LOGIN;
		}
	}
	
	/**
	 * 注销退出
	 * @return
	 */
	public String logout() {
		ActionContext.getContext().getSession().remove("loginUser");
		return LOGIN;
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws IOException 
	 */
	public String editPassword() throws IOException{
		String password = model.getPassword();
		User user = BOSContext.getLoginUser();
		String id = user.getId();
		String flag = "1";
		try {
			userService.editPassword(password, id);
		} catch (Exception e) {
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		
		return NONE;
	}
	
	//接收角色的ID数组
	public String[] roleIds;
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	
	/**
	 * 添加用户并且保存权限
	 * @return
	 */
	public String add(){
		userService.save(model,roleIds);
		return "list";
	}
	
	/**
	 * 查询所有用户信息
	 * @return
	 */
	public String listajax() {
		List<User> list = userService.findAll();
		writeList2Json(list, new String[]{"roles","currentPage","pageSize","detachedCriteria"});
		return NONE;
	}
	
	public String pageQuery() {
		userService.pageQuery(pageBean);
		writePageBean2Json(pageBean, new String[]{"functions","users","currentPage","pageSize","detachedCriteria"});
		return NONE;
	}
}
