package com.exam.service;


import com.exam.dao.DemoDao;
import com.exam.exception.RollbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {

    @Autowired
    private DemoDao demoDao;
    private Logger log = LoggerFactory.getLogger(getClass());
    /**
     *Transactional注解用于开启事务，抛出异常则事务回滚，否则执行结束则提交
     * 只有操作多表的写操作使用此注解，并抛出RollbackException
     */
    @Transactional(rollbackFor = RollbackException.class)
    public String test(String key)throws RollbackException{
            return demoDao.count(key);
    }
    @Cacheable(value = "videos",key = "methodName")
    public List<String> getAll(){
        //假装这是一个dao返回值
        return new ArrayList<>();
    }

    @CacheEvict(value = "videos",allEntries = true)
    public void clear(){
        //对于数据的写操作 需要用这种注解清除对应缓存
    }
}
