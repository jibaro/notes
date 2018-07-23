package com.daojia.open.confluence.worker.service;

import java.util.List;

import com.daojia.open.confluence.worker.entity.IndexReptilePJO;

public interface CacheService {

	List<IndexReptilePJO> getReptile(int indexReptileKey) throws Exception;

	List<IndexReptilePJO> setReptile(int indexReptileKey) throws Exception;

	List<IndexReptilePJO> getReptile(String indexReptileKey) throws Exception;
}
