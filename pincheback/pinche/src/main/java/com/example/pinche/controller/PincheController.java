package com.example.pinche.controller;

import com.example.pinche.domain.CarList;
import com.example.pinche.domain.Team;
import com.example.pinche.domain.User;
import com.example.pinche.repository.Pincherepo;
import com.example.pinche.repository.Teamrepo;
import com.example.pinche.repository.Userrepo;
import com.example.pinche.service.CarListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangchang
 */
@RestController
@RequestMapping
public class PincheController {

    @Autowired
    private Pincherepo pincherepo;

    @Autowired
    private Teamrepo teamrepo;

    @Autowired
    private CarListService carListService;

    @Autowired
    private Userrepo userrepo;

    /**
     * 查找所有的carList
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findallcarlist", method = RequestMethod.GET)
    public List<CarList> findAllCarList(){
        return pincherepo.findAll();
    }

    /**
     * 通过groupId 精确查询某个carlist
     * @param groupId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findcarlistbygroupid", method = RequestMethod.GET)
    public List<CarList> findAllCarListByGroupId(@RequestParam(value = "groupId", required = true) String groupId){
        return pincherepo.findByGroupId(groupId);
    }

    /**
     * 通过用户的id查询他关注或者加入了的carList
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findcarlistbyuserid", method = RequestMethod.GET)
    public List<CarList> findCarListByUserId(@RequestParam(value = "userId", required = true) String userId){
        return pincherepo.findByUserId(userId);
    }

    /**
     * 创建carlist并插入数据库
     * @param captainid
     * @param BillDate
     * @param Beginposition
     * @param Arriveposition
     * @param Palyerrequir
     * @param Carintroduce
     * @param ClearTime
     * @param Captain
     * @param seats
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertcarslist", method = RequestMethod.POST)
    public Map<String,Object> publicCarList(@RequestParam(value = "captainid",required = true) String captainid,
                                            @RequestParam(value = "BillDate", required = true) String BillDate,
                                            @RequestParam(value = "Beginposition", required = true) String Beginposition,
                                            @RequestParam(value = "Arriveposition", required = true) String Arriveposition,
                                            @RequestParam(value = "Palyerrequir", required = true) String Palyerrequir,
                                            @RequestParam(value = "Carintroduce", required = true) String Carintroduce,
                                            @RequestParam(value = "ClearTime", required = true) String ClearTime,
                                            @RequestParam(value = "Captain", required = true) String Captain,
                                            @RequestParam(value = "Seats", required = true) int seats,
                                            @RequestParam(value = "phone", required = true) String phone){
        Map<String,Object> resultMap = new HashMap<>();
//        if (pincherepo)
        CarList carList = new CarList();
        carList.setCaptainid(captainid);
        carList.setBilldate(BillDate);
        carList.setBeginposition(Beginposition);
        carList.setArriveposition(Arriveposition);
        carList.setPalyerrequir(Palyerrequir);
        carList.setCarintroduce(Carintroduce);
        carList.setCleartime(ClearTime);
        carList.setCaptain(Captain);
        carList.setSeats(seats);
        carList.setPhone(phone);
        carList = pincherepo.save(carList);
        if (carList.getGroupid() > 0) {
            Team team = new Team();
            team.setGroupid(String.valueOf(carList.getGroupid()));
            team.setUserid(captainid);
            team.setIfcaptain(1);
            teamrepo.save(team);
            if (team.getId() > 0){
                resultMap.put("groupId", carList.getGroupid());
                resultMap.put("success", true);
            }else{
                resultMap.put("success", false);
            }

        }else{
            resultMap.put("success", false);
        }
        return resultMap;
    }

    /**
     * 加入carList
     * @param userId
     * @param groupId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/joincars", method = RequestMethod.POST)
    public Map<String,Object> joinCarList(@RequestParam(value = "userId", required = true) String userId,
                                          @RequestParam(value = "groupId", required = true) String groupId){
        Map<String,Object> resultMap = new HashMap<>();
        Team team = new Team();
        team.setIfcaptain(0);
        team.setUserid(userId);
        team.setGroupid(groupId);
        return carListService.joinCarList(team);
    }

    /**
     * 查看所有团队成员
     * @param groupId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getteamMember",method = RequestMethod.GET)
    public List<User> getAllTeamMember(@RequestParam(value = "groupId", required = true) String groupId){
        return userrepo.getUserByGroupId(groupId);
    }

    /**
     * 查询carlist
     * @param billdate
     * @param arriveposition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchCar", method = RequestMethod.GET)
    public List<CarList> searchCar(@RequestParam(value = "billdate", required = true) String billdate,
                                   @RequestParam(value = "arriveposition", required = true) String arriveposition){
        return pincherepo.findAllByArrivepositionAndBilldate(arriveposition, billdate);
    }







}
