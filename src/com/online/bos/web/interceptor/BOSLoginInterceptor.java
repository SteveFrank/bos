package com.online.bos.web.interceptor;

import com.online.bos.domain.User;
import com.online.bos.utils.BOSContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class BOSLoginInterceptor extends MethodFilterInterceptor {
	
	private static final long serialVersionUID = 2L;
	//拦截方法
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
//		ActionProxy proxy = invocation.getProxy();
//		String namespace  = proxy.getNamespace();
//		String actionName = proxy.getActionName();
//		String url        = namespace + actionName;
//		System.out.println("拦截的URL为：" + url);
		
		//从session中获取用户
		User user = BOSContext.getLoginUser();
		if (user == null) {
			//没有登录
			return "login";
		} else {
			//放行
			return invocation.invoke();
		}
	}

}
