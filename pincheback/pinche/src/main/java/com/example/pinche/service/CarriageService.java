package com.example.pinche.service;

import com.example.pinche.domain.CarriageList;
import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanProperty;

import java.util.Map;

/**
 * 同车的你功能
 * 业务处理接口
 * @author wangchang
 */
public interface CarriageService {

    /**
     * 更新或发布新的行程信息
     * @param carriageList
     * @return
     */
  public Map<String, Object> updateOrInsertCarriage(CarriageList carriageList);


    /**
     * 获取行程信息相同的小伙伴信息
     * @param userId
     * @return
     */
  public Map<String, Object> getSameCarriageList(String userId);


    /**
     * 同车列表距离设定的出行日期距离现在还有多少天
     * @param userId
     * @return
     */
  public Map<String, Object> daysForCarriageList(String userId);
}
