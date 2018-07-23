package com.daojia.open.confluence.worker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.daojia.open.confluence.worker.entity.IndexReptilePJO;
import com.daojia.open.confluence.worker.service.CacheService;

@Service
public class CacheServiceImpl implements CacheService {

	private static Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

	/*
	 * 当使用到该方法的时候 spring会首先判断缓存中是否含有该key的值 ，如果有则从本地缓存中获取
	 * 并以你输入的参数indexReptileKey为key获取，否则从执行方法体中的获取 并将获取的数据放入到缓存中
	 */
	@Override
	@Cacheable(value = "indexReptileKeyValue", key = "#indexReptileKey")
	public List<IndexReptilePJO> getReptile(int indexReptileKey) throws Exception {
		LOGGER.info("不走guava cache获取参数");
		return handlerindexReptile();
	}
	
	@Override
	@Cacheable(value = "indexReptileKeyValue", key = "#indexReptileKey")
	public List<IndexReptilePJO> getReptile(String indexReptileKey) throws Exception {
		LOGGER.info("不走guava cache获取参数");
		return handlerindexReptile();
	}

	/*
	 * 设置某个值的时候，想将该值存入缓存 则用这个注解 将你需要设置的值设置到缓存中 另一个方法在获取的时候就是从缓存中获取
	 */
	@Override
	@CachePut(value = "indexReptileKeyValue", key = "#indexReptileKey")
	public List<IndexReptilePJO> setReptile(int indexReptileKey) throws Exception {
		LOGGER.info("将树节点项目路径  存入guava cache中");
		return handlerindexReptile();
	}

	private List<IndexReptilePJO> handlerindexReptile() {
		
		IndexReptilePJO indexReptilePJO01 = new IndexReptilePJO("index01", "reptile01", "PJO01");
		IndexReptilePJO indexReptilePJO02 = new IndexReptilePJO("index02", "reptile02", "PJO02");
		
		List<IndexReptilePJO> listToReturn = new ArrayList<IndexReptilePJO>();
		
		listToReturn.add(indexReptilePJO01);
		listToReturn.add(indexReptilePJO02);
		
		return listToReturn;
	}
}
