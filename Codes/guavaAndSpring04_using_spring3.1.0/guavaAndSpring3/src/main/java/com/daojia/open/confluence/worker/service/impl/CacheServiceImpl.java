package com.daojia.open.confluence.worker.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.daojia.open.confluence.worker.common.GuavaCache;
import com.daojia.open.confluence.worker.entity.IndexReptilePJO;
import com.daojia.open.confluence.worker.service.CacheService;
import com.google.common.cache.CacheBuilder;

@Service
public class CacheServiceImpl implements CacheService {

	private static Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

	/*
	 * ��ʹ�õ��÷�����ʱ�� spring�������жϻ������Ƿ��и�key��ֵ ���������ӱ��ػ����л�ȡ
	 * ����������Ĳ���indexReptileKeyΪkey��ȡ�������ִ�з������еĻ�ȡ ������ȡ�����ݷ��뵽������
	 */
	@Override
	@Cacheable(value = "indexReptileKeyValue", key = "#indexReptileKey")
	public List<IndexReptilePJO> getReptile(int indexReptileKey) throws Exception {
		LOGGER.info("����guava cache��ȡ����");
		return handlerindexReptile();
	}
	
	@Override
	@Cacheable(value = "indexReptileKeyValue", key = "#indexReptileKey")
	public List<IndexReptilePJO> getReptile(String indexReptileKey) throws Exception {
		LOGGER.info("����guava cache��ȡ����");
		return handlerindexReptile();
	}

	@Bean
	public CacheManager cacheManagerOfMine() {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		GuavaCache bookCache = new GuavaCache("book", CacheBuilder.newBuilder().build());
		GuavaCache booksExpirableCache = new GuavaCache("indexReptileKeyValue",
				CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).build());
		simpleCacheManager.setCaches(Arrays.asList(bookCache, booksExpirableCache));
		return simpleCacheManager;
	}
	
	/*
	 * ����ĳ��ֵ��ʱ���뽫��ֵ���뻺�� �������ע�� ������Ҫ���õ�ֵ���õ������� ��һ�������ڻ�ȡ��ʱ����Ǵӻ����л�ȡ
	 */
	@Override
	@CachePut(value = "indexReptileKeyValue", key = "#indexReptileKey")
	public List<IndexReptilePJO> setReptile(int indexReptileKey) throws Exception {
		LOGGER.info("�����ڵ���Ŀ·��  ����guava cache��");
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
