package com.lyz.security.common.dao.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lyz.security.common.dao.constant.CommonDaoConstant;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 注释:自动增加以及修改参数以及对应值
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 14:50
 */
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        setFieldValByName(CommonDaoConstant.DEFAULT_CREATE_TIME, now, metaObject);
        setFieldValByName(CommonDaoConstant.DEFAULT_UPDATE_TIME, now, metaObject);
        setFieldValByName(CommonDaoConstant.DEFAULT_DELETED, 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(CommonDaoConstant.DEFAULT_UPDATE_TIME, new Date(), metaObject);
    }
}
