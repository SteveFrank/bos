package com.online.bos.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 把securityManager和bosRealm装配到ApplicationContext
 */
@Configuration
public class ShiroConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
    	final Realm bosRealm = (Realm) ac.getBean("bosRealm");
        final DefaultWebSecurityManager defaultWebSecurityManager = (DefaultWebSecurityManager) ac.getBean("securityManager");
        defaultWebSecurityManager.setRealm(bosRealm);
    }
}
