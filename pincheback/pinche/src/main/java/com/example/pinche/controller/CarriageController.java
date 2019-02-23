package com.example.pinche.controller;

import com.example.pinche.domain.CarriageList;
import com.example.pinche.repository.Carriagerepo;
import com.example.pinche.service.CarriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 同车的你功能相关Controller
 * @author wangchang
 */
@CrossOrigin
@RestController
public class CarriageController {

    @Autowired
    private CarriageService carriageService;

    @Autowired
    private Carriagerepo carriagerepo;

    /**
     * 更新或发布行程信息
     * @param userId
     * @param carNum
     * @param startTime
     * @param remarks
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatecarriagelist", method = RequestMethod.POST)
    public Map<String,Object> updateOrInsertCarriage(@RequestParam(value = "userId", required = true) String userId,
                                                     @RequestParam(value = "carNum", required = true) String carNum,
                                                     @RequestParam(value = "startTime", required = true) String startTime,
                                                     @RequestParam(value = "remarks", required = true) String remarks){
    CarriageList carriageList = new CarriageList();
    carriageList.setUserid(userId);
    carriageList.setStarttime(startTime);
    carriageList.setCarnum(carNum);
    carriageList.setRemarks(remarks);

    return carriageService.updateOrInsertCarriage(carriageList);
    }

    /**
     * 查找同车小伙伴
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getsamecarriagelist", method = RequestMethod.GET)
    public Map<String, Object> getSameCarriageList(@RequestParam(value = "userId", required = true) String userId){
    return carriageService.getSameCarriageList(userId);
    }

    /**
     * 获取自己的行程信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getcarriagelist", method = RequestMethod.GET)
    public List<CarriageList> getcarriagelist(@RequestParam(value = "userId", required = true) String userId){
        return carriagerepo.findAllByUserid(userId);
    }

    /**
     * 同车列表距离设定的出行日期距离现在还有多少天
     * @param userId
     * @return
     */
    @RequestMapping(value = "/daysForCarriageList", method = RequestMethod.GET)
    public Map<String,Object> daysForCarriageList(@RequestParam(value = "userId", required = true) String userId){
        return carriageService.daysForCarriageList(userId);
    }
}
