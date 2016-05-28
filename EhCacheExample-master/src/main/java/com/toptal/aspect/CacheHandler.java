package com.toptal.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheHandler {

	@Autowired
	private net.sf.ehcache.CacheManager customCacheManager;
	
}
