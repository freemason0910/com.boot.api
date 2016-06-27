package com.yixin.js.configure;

import java.io.IOException;
import java.util.*;

import com.yixin.js.filter.MsgFilter;
import com.yixin.js.security.MyShiroRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

/**
 * Shiro 配置
 */
@Configuration
public class ShiroConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(EhCacheManager cacheManager) {
        MyShiroRealm realm = new MyShiroRealm();
        realm.setCacheManager(cacheManager);
        return realm;
    }

    /**
     * 注册DelegatingFilterProxy（Shiro）
     * 集成Shiro有2种方法：
     * 1. 按这个方法自己组装一个FilterRegistrationBean（这种方法更为灵活，可以自己定义UrlPattern，
     * 在项目使用中你可能会因为一些很但疼的问题最后采用它， 想使用它你可能需要看官网或者已经很了解Shiro的处理原理了）
     * 2. 直接使用ShiroFilterFactoryBean（这种方法比较简单，其内部对ShiroFilter做了组装工作，无法自己定义UrlPattern，
     * 默认拦截 /*）
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");// 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
        return filterRegistration;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(myShiroRealm);
//      <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
        dwsm.setCacheManager(getEhCacheManager());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    @Bean
    public MsgFilter getMsgFilter() {
        MsgFilter msgFilter = new MsgFilter();
        return msgFilter;
    }

    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     *
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        /////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//         authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
//        filterChainDefinitionMap.put("/user", "authc");// 这里为了测试，只限制/user，实际开发中请修改为具体拦截的请求规则
//         anon：它对应的过滤器里面是空的,什么都没做
        logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");
//        filterChainDefinitionMap.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取
//        filterChainDefinitionMap.put("/**", "anon");//anon 可以理解为不拦截
//        路径权限配置
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/admin/manage", "anon");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * ShiroFilter<br/>
     * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
     *
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) throws ClassNotFoundException {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new MShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/successlogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("msg", getMsgFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

    /**
     * 继承 ShiroFilterFactoryBean 处理拦截资源文件问题。
     */
    public class MShiroFilterFactoryBean extends ShiroFilterFactoryBean {

        // 对ShiroFilter来说，需要直接忽略的请求
        private Set<String> ignoreExt;

        public MShiroFilterFactoryBean() {
            super();
            ignoreExt = new HashSet<>();
            ignoreExt.add(".jpg");
            ignoreExt.add(".png");
            ignoreExt.add(".gif");
            ignoreExt.add(".bmp");
            ignoreExt.add(".js");
            ignoreExt.add(".css");
        }

        @Override
        protected AbstractShiroFilter createInstance() throws Exception {

            SecurityManager securityManager = getSecurityManager();
            if (securityManager == null) {
                String msg = "SecurityManager property must be set.";
                throw new BeanInitializationException(msg);
            }

            if (!(securityManager instanceof WebSecurityManager)) {
                String msg = "The security manager does not implement the WebSecurityManager interface.";
                throw new BeanInitializationException(msg);
            }

            FilterChainManager manager = createFilterChainManager();

            PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
            chainResolver.setFilterChainManager(manager);

            return new MSpringShiroFilter((WebSecurityManager) securityManager, chainResolver);
        }

        private final class MSpringShiroFilter extends AbstractShiroFilter {

            protected MSpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
                super();
                if (webSecurityManager == null) {
                    throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
                }
                setSecurityManager(webSecurityManager);
                if (resolver != null) {
                    setFilterChainResolver(resolver);
                }
            }

            @Override
            protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse,
                                            FilterChain chain) throws ServletException, IOException {
                HttpServletRequest request = (HttpServletRequest) servletRequest;
                String str = request.getRequestURI().toLowerCase();
                // 因为ShiroFilter 拦截所有请求（在上面我们配置了urlPattern 为 * ，当然你也可以在那里精确的添加要处理的路径，这样就不需要这个类了），而在每次请求里面都做了session的读取和更新访问时间等操作，这样在集群部署session共享的情况下，数量级的加大了处理量负载。
                // 所以我们这里将一些能忽略的请求忽略掉。
                // 当然如果你的集群系统使用了动静分离处理，静态资料的请求不会到Filter这个层面，便可以忽略。
                boolean flag = true;
                int idx = 0;
                if ((idx = str.indexOf(".")) > 0) {
                    str = str.substring(idx);
                    if (ignoreExt.contains(str.toLowerCase()))
                        flag = false;
                }
                if (flag) {
                    super.doFilterInternal(servletRequest, servletResponse, chain);
                } else {
                    chain.doFilter(servletRequest, servletResponse);
                }
            }

        }
    }

}


