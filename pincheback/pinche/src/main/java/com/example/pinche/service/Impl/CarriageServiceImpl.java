package com.example.pinche.service.Impl;

import com.example.pinche.Result.CarriageResult;
import com.example.pinche.domain.CarriageList;
import com.example.pinche.domain.User;
import com.example.pinche.repository.Carriagerepo;
import com.example.pinche.repository.Userrepo;
import com.example.pinche.service.CarriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 同车的你
 * 业务处理层实现曾
 * @author wangchang
 */
@Service
public class CarriageServiceImpl implements CarriageService {

    @Autowired
    private Carriagerepo carriagerepo;

    @Autowired
    private Userrepo userrepo;

    @Override
    @Transactional
    public Map<String, Object> updateOrInsertCarriage(CarriageList carriageList) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //TODO  判断车号是否正确
        if (true){
            //判断该用户是否已经有行程
            List<CarriageList> carriageLists = carriagerepo.findAllByUserid(carriageList.getUserid());
            //获取当前时间的时间戳
            carriageList.setTimestamp(String.valueOf(System.currentTimeMillis()));
            //获取用户的qq
            List<User> user = userrepo.findAllByUserid(carriageList.getUserid());
            carriageList.setQq(user.get(0).getQq());

            if (carriageLists.size() > 0) {
                //用户已经有行程
                //判断该用户是否修改时间是否过于频繁
               long ifFrequence = (Long.valueOf(carriageList.getTimestamp()) -
                       Long.valueOf(carriageLists.get(0).getTimestamp())) / 1000 / 60 / 60 / 24;
//               if (ifFrequence <= 1){
                if (false){
                   //过于频繁
                   resultMap.put("errMsg", "你的操作过于频繁，请明天再进行修改");
                   resultMap.put("success", false);
               }else {
                   //准备更新行程信息
                   carriageLists.get(0).setRemarks(carriageList.getRemarks());
                   carriageLists.get(0).setCarnum(carriageList.getCarnum());
                   carriageLists.get(0).setStarttime(carriageList.getStarttime());
                   carriagerepo.save(carriageLists.get(0));
                   if (carriageLists.get(0).getId() > 0){
                       resultMap.put("success", true);
                       resultMap.put("msg", "行程信息修改成功,期待同行的小伙伴吧");
                       //查询新的通信小伙伴信息
                        List<CarriageList> carriageResults = carriagerepo.findAllByCarnumAndStarttimeLike(carriageList.getCarnum(),
                                carriageList.getStarttime()+ "%");
                        resultMap.put("carriageResults", carriageResults);
                   }else {
                       //更新失败，字符串非法输入
                       //todo  得想办法加一个过滤器，把所有传进来的输入都给过滤一下
                       resultMap.put("errMsg", "输入有误，例如字段过于长，超过了数据库允许的长度");
                       resultMap.put("success", false);
                   }
               }
            }else {
                //用户没有行程，准备插入行程
                carriagerepo.save(carriageList);
                if (carriageList.getId() > 0){
                    //查询新的通信小伙伴信息
                    List<CarriageList> carriageResults = carriagerepo.findAllByCarnumAndStarttimeLike(carriageList.getCarnum(),
                            carriageList.getStarttime() + "%");
                    resultMap.put("carriageResults", carriageResults);
                    resultMap.put("success", true);
                    resultMap.put("msg", "行程信息发布成功,期待同行的小伙伴吧");
                }else {
                    //更新失败，字符串非法输入
                    //todo  得想办法加一个过滤器，把所有传进来的输入都给过滤一下
                    resultMap.put("errMsg", "输入有误，例如字段过于长，超过了数据库允许的长度");
                    resultMap.put("success", false);
                }
            }
        }else {
            resultMap.put("success", false);
            resultMap.put("errMsg", "输入的车次号不存在，请检查后重新输入");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getSameCarriageList(String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<CarriageList> carriageLists = carriagerepo.findAllByUserid(userId);
        if (carriageLists.size() <= 0){
            //该用户没有发布个人行程，无法获取同行的人
            resultMap.put("success", false);
            resultMap.put("errMsg", "请先发布你的行程");
        }else {
            List<CarriageList> carriageResults = carriagerepo.findAllByCarnumAndStarttimeLike(carriageLists.get(0).getCarnum(),
                    carriageLists.get(0).getStarttime() + "%");
            //判断是否有同车的同学
            if(carriageResults.size() <= 0){
                resultMap.put("success", false);
                resultMap.put("errMsg","暂时没有与您同车的小伙伴信息");
            }else {
                for (CarriageList carriage : carriageResults){
                    carriage.setTrueName(userrepo.findAllByUserid(carriage.getUserid()).get(0).getName());
                }

                resultMap.put("success", true);
                resultMap.put("carriageResults", carriageResults);
            }
        }

        return resultMap;
    }

    @Override
    public Map<String,Object> daysForCarriageList(String userId) {
        List<CarriageList> carriageList = carriagerepo.findAllByUserid(userId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> resultMap = new HashMap<>();
        long dayMill = 0;
        //把出发时间转为Date
        if (carriageList.size() <= 0 ){
            resultMap.put("days", -1);
            return resultMap;
        }
        try {
            Date startDate = df.parse(carriageList.get(0).getStarttime());
            Date nowDate = new Date();
            //转为毫秒
             dayMill = startDate.getTime() - nowDate.getTime();
            dayMill = dayMill / 1000 / 60 / 60 / 24;
            //
            long yuDayMill = (startDate.getTime() - nowDate.getTime()) / 1000 / 60 / 60 % 24;
            //天数+1
            if (dayMill <= 0){
//                resultMap.put("ifReach", true);
                resultMap.put("days", 0);
            }else{
                if (yuDayMill > 0) {
                    //剩余天数+1
                    dayMill++;
                }
                resultMap.put("days", dayMill);
                resultMap.put("carNum",carriageList.get(0).getCarnum());
//                resultMap.put("ifReach")
            }


        } catch (ParseException e) {
//            出错处理
            e.printStackTrace();
            System.out.println("xxxx---------------------------------------------------------------------");
            resultMap.put("days", -1);
            return resultMap;
        }
        return resultMap;
    }


}
