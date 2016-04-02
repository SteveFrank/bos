package com.online.bos.web.action;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.online.bos.domain.User;
import com.online.bos.utils.BOSContext;
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
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public UserAction() throws InstantiationException, IllegalAccessException {
		super();
	}
	
	/**
	 * 登录系统
	 * @return
	 */
	public String login() {
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
}
