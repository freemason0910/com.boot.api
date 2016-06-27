package com.yixin.js.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session监听器
 *
 * @author lvbq
 */

public class SessionListener implements HttpSessionListener {
    //private static int activeSessions = 0;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /* Session创建事件 */
    public void sessionCreated(HttpSessionEvent se) {

    }

    /* Session失效事件 */
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("session一已经销毁,sessionId is " + se.getSession().getId());
//	    Object o = se.getSession().getAttribute(Constants.SESSION_USER);
//	    User user = (User)o;
//	    String username = null;
//	    if(user != null){
//	    	username = user.getUsername();
//	    }
//	    logger.info("username is " + username);
//	    if(StringUtils.isNotEmpty(username)){
//	    	//清除与绑定session信息的缓存
//	    	UserUtils.removeShiroKickoutSessionInfo(username);
//	    }
        //用户信息放到userfilter中清楚
        //清除当前用户缓存
        //UserUtils.clearCache();
        //清楚shiro授权认证缓存等 信息
        //UserUtils.getSubject().logout();
    }
}
