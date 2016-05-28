package com.toptal.aspect;

import java.io.Serializable;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	@Autowired
	private net.sf.ehcache.CacheManager customCacheManager;
	
	@Around("execution(* com.toptal.blog.service..*.get*(..))")
    public Object executor(ProceedingJoinPoint point) throws Throwable {
        System.out.println("Cutting before...");
        
      //原实体类名（包括包名）
        String className=point.getTarget().getClass().getName();
        //原方法名
        String methodName =point.getSignature().getName();
        //原方法实参列表
        Object[] arguments=point.getArgs();
        Cache cache = customCacheManager.getCache("messageCache");
//        String methodName = point.getTarget().getClass().toString() + "," + point.toShortString();
        String cacheKey=getCacheKey(className,methodName,arguments);
        Element element=cache.get(cacheKey);
        if (element==null) {
            // 执行目标方法，并保存目标方法执行后的返回值
            Object resuObject=point.proceed(); 
            element=new Element(cacheKey, (Serializable)resuObject);
            cache.put(element);
            System.out.println("将查询结果放到缓存里面,缓存key="+cacheKey);
        }else {
            System.out.println("已经存在从缓存中取出来="+cacheKey);
            return element.getValue();
        }

        System.out.println("Cutting after...");
        
        return point.proceed();
    }
	
	 /**
     * @MethodName  : getCacheKey
     * @Description : 获得cache key的方法，cache key是Cache中一个Element的唯一标识 cache key包括
     * 包名+类名+方法名+各个参数的具体指，如com.co.cache.service.UserServiceImpl.getAllUser
     * @param targetName    类名
     * @param methodName    方法名
     * @param arguments     方法实参数组
     * @return                      cachekey
     */
    private String getCacheKey(String targetName, String methodName,
            Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append(".").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                if(arguments[i] instanceof String[]){
                    String[] strArray = (String[])arguments[i];
                    sb.append(".");
                    for(String str : strArray){
                        sb.append(str);
                    }
                }else{
                    sb.append(".").append(arguments[i]);
                }
            }
        }
        return sb.toString();
    }
    
//    @AfterReturning("execution(* com.toptal.blog.service..*.set*(..))")
    @AfterReturning(value="execution(* com.toptal.blog.service..*.set*(..))", argNames="rtv", returning="rtv")  
    public void afterReturning(JoinPoint point, Object rtv){
    	//原实体类名（包括包名）
        String className=point.getTarget().getClass().getName();
        Cache cache = customCacheManager.getCache("messageCache");
        List list = cache.getKeys();  
        for(int i = 0;i<list.size();i++){  
            String cacheKey = String.valueOf(list.get(i));  
            if(cacheKey.startsWith(className)){
            	System.out.println("删除缓存中的数据："+cacheKey);
                cache.remove(cacheKey);
            }  
        }  
    }
}
