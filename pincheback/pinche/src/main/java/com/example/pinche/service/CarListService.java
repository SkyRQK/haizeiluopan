package com.example.pinche.service;

import com.example.pinche.domain.Team;

import java.util.Map;
/**
 * 拼车功能
 * 业务处理接口
 * @author wanchang
 */
public interface CarListService {

    /**
     * 学生加入某个carList
     * @param team
     * @return
     */
    public abstract Map<String, Object> joinCarList(Team team);
}
