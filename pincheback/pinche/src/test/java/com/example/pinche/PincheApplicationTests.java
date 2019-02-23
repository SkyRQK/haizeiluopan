package com.example.pinche;

import com.example.pinche.Result.CarriageResult;
import com.example.pinche.controller.PincheController;
import com.example.pinche.domain.CarList;
import com.example.pinche.domain.CarriageList;
import com.example.pinche.domain.User;
import com.example.pinche.repository.Carriagerepo;
import com.example.pinche.repository.Pincherepo;
import com.example.pinche.repository.Userrepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PincheApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private Pincherepo pincherepo;
    @Autowired
    private PincheController pincheController;

    @Autowired
    private Userrepo userrepo;

//    @Test
//    public void testFindAllCarList(){
//        List<CarList> carLists = pincheController.findAllCarList();
//        return ;
//    }

    @Test
    public void testFindAllCarListBy(){
        List<CarList> carLists = pincherepo.findAllByCaptainid("1");
        return;
    }

    @Test
    public void testfindByUserId(){
        List<CarList> carLists = pincherepo.findByUserId("1");
        return;
    }

//    @Test
//    public void testFindByGroupId(){
//        List<CarList> carLists = pincherepo.findByUserIdaAndGroupId("2");
//        return;
//    }

    @Test
    public void testInsertByGroupId(){
        CarList carList = new CarList();
        carList.setCaptainid("1");
        carList.setBilldate("2018-12-31");
        carList.setArriveposition("哈站");
        carList.setBeginposition("哈工程");
        carList.setPalyerrequir("有零食");
        carList.setCarintroduce("有爱");
        carList.setCaptain("李哥");
        carList.setCleartime("9:00");
        carList.setSeats(3);
//        int i = pincherepo.insertCarList("1", "2018-12-31", "哈工程",
//                "哈站", "有零食", "有爱", "9:00","lige",4);
//        int groupId = pincherepo.getInsertId();
//        int j = pincherepo.insertTeam("1", groupId, 1);
        carList = pincherepo.save(carList);
        return;
    }


    @Test
    public void testGerUserByGroupid(){
        List<User> users = userrepo.getUserByGroupId("1");
        return;
    }
    @Autowired
    private Carriagerepo carriagerepo;

    @Test
    public void testgetCarriageResult(){
       List<CarriageList> results = carriagerepo.findAllByCarnumAndStarttimeLike("Z238","2018-12-30%");
        return;
    }

}

