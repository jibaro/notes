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
