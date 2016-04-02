package com.online.bos.utils;

import com.online.bos.domain.User;
import com.opensymphony.xwork2.ActionContext;

public class BOSContext {
	public static User getLoginUser() {
		return (User)ActionContext.getContext().getSession().get("loginUser");
	}
}
