package com.daojia.open.confluence.worker.common;

import com.daojia.open.confluence.worker.utils.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

import java.io.Serializable;

public class GuavaCache implements Cache {

    private static Logger log = LoggerFactory.getLogger(GuavaCache.class);

    private final JsonMapper mapper = new JsonMapper();

    private static final Object NULL_HOLDER = new NullHolder();

    private final String name;

    private final com.google.common.cache.Cache<Object, Object> cache;

    private final boolean allowNullValues;

    public GuavaCache(String name, com.google.common.cache.Cache<Object, Object> cache) {
        this(name, cache, true);
    }

    public GuavaCache(String name, com.google.common.cache.Cache<Object, Object> cache, boolean allowNullValues) {
        Assert.notNull(name, "Name must not be null");
        Assert.notNull(cache, "Cache must not be null");
        this.name = name;
        this.cache = cache;
        this.allowNullValues = allowNullValues;
    }



    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        key = getKey(key.toString());
        Object value = this.cache.getIfPresent(key);
        log.info("获取缓存key={},获取对象={}", key, mapper.toJson(value));
        return toWrapper(value);
    }

    @Override
    public void put(Object key, Object value) {
        key = getKey(key.toString());
        this.cache.put(key, toStoreValue(value));
        log.info("存入缓存key=={},存入对象={}", key, mapper.toJson(value));
    }

    @Override
    public void evict(Object key) {
        this.cache.invalidate(key);
        log.info("删除缓存key={}",key);
    }

    @Override
    public void clear() {
        this.cache.invalidateAll();
        log.info("清空guavacache所有缓存");
    }

    private ValueWrapper toWrapper(Object value) {
        return (value != null ? new SimpleValueWrapper(fromStoreValue(value)) : null);
    }


    protected Object fromStoreValue(Object storeValue) {
        if (this.allowNullValues && storeValue == NULL_HOLDER) {
            return null;
        }
        return storeValue;
    }

    public final boolean isAllowNullValues() {
        return this.allowNullValues;
    }

    @SuppressWarnings("serial")
    private static class NullHolder implements Serializable {
    }

    protected Object toStoreValue(Object userValue) {
        if (this.allowNullValues && userValue == null) {
            return NULL_HOLDER;
        }
        return userValue;
    }

    /**实现key增加cache名称**/
    private String getKey(String key) {
        return name + "_" + key;
    }
}