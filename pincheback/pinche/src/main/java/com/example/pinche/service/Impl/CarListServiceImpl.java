package com.example.pinche.service.Impl;

import com.example.pinche.domain.CarList;
import com.example.pinche.domain.Team;
import com.example.pinche.repository.Pincherepo;
import com.example.pinche.repository.Teamrepo;
import com.example.pinche.service.CarListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拼车功能
 * 业务处理层实现
 * @author wanchang
 */
@Service
public class CarListServiceImpl implements CarListService {

    @Autowired
    private Teamrepo teamrepo;

    @Autowired
    private Pincherepo pincherepo;

    @Override
    @Transactional
    public Map<String, Object> joinCarList(Team team){
        Map<String, Object> resultMap =  new HashMap<>();

        //查询是否用数据，即已经加入了该team，无法继续加入
        List<Team> teams = teamrepo.getAllByGroupidAndAndUserid(team.getGroupid(), team.getUserid());
        List<CarList> carLists =  pincherepo.findByGroupId(team.getGroupid());
        if (teams.size() > 0){
            resultMap.put("success", false);
            resultMap.put("errMsg", "have exist");
            return resultMap;
        }
        //判断还有没有座位
        if (carLists.get(0).getSeats() <= 0){
            resultMap.put("success", false);
            resultMap.put("errMsg", "The seat is full");
            return resultMap;
        }
        //有座位
        teamrepo.save(team);
        //座位-1，并更新座位信息
        carLists.get(0).setSeats(carLists.get(0).getSeats() - 1);
        pincherepo.save(carLists.get(0));
        if (team.getId() > 0){
            resultMap.put("success", true);
        }else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

}
