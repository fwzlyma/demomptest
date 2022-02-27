package com.fwzlym.demomptest.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author fwzlym
 * @Date 2022/2/19 16:08
 * @Description:
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override//mp执行添加操作的时候执行
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("version", 1, metaObject);
        this.setFieldValByName("deleted", 0, metaObject);
    }

    @Override//mp执行修改方法时执行
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
