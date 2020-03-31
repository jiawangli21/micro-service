package com.htqyjsw1.searchpage.aspect;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class WebLogAspect {
    private String userIp;
    private String acessInterface;
    private String browserType;
    private String acessTime;
    private int state;

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 设置切面监控该微服务下的所有controller接口
     */
    @Pointcut("execution( * com.htqyjsw1.searchpage..controller.*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logPointCut() {
    }

    /***
     * 在进入controller接口前利用该方法获取本次请求的用户IP、访问时间、浏览器类型等信息
     * @param joinPoint
     * @throws Throwable
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //用户端ip
        userIp = request.getRemoteAddr();

        //访问时间
        Date nowTime = new Date();
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        acessTime = sdFormatter.format(nowTime);

        //访问接口
        acessInterface = request.getRequestURL().toString();

        //访问浏览器类型
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        browserType = browser.getName();

    }

    /**
     * 访问完controller接口后利用该方法获取访问状态
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容(返回值太复杂时，打印的是物理存储空间的地址)
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        state = response.getStatus();
        logger.info(String.format("flume@%s %s %s %s %d -", userIp, acessInterface, browserType, acessTime, state));

        logger.debug("返回值 : " + ret);
    }

    /**
     * 环绕方法，用于控制切面的流程并打印日志
     *
     * @param pjp
     * @throws Throwable
     * @return：controller接口的返回值
     */
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        logger.info("耗时 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }
}