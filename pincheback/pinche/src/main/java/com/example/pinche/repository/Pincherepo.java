package com.example.pinche.repository;

import com.example.pinche.domain.CarList;
import com.example.pinche.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * pinche表持久层
 * @author wangchang
 */
public interface Pincherepo extends JpaRepository<CarList,String> {
    /**
     * 通过carptainid获取carlist
     * @param captainid
     * @return
     */
    public List<CarList> findAllByCaptainid(String captainid);

    /**
     * 通过userid获取所有的carlist
     * @param userId
     * @return
     */
    @Query(value = "select * from carlist where groupid in (select groupid from team where userid = ?1)", nativeQuery = true)
    @Modifying
    public List<CarList> findByUserId(String userId);

    /**
     * 通过groupid获取carlist
     * @param groupId
     * @return
     */
    @Query(value = "select * from carlist where groupid = ?1", nativeQuery = true)
    @Modifying
    public List<CarList> findByGroupId(String groupId);


    /**
     * 通过目的地和出发时间获取carList
     * @param arriveposition
     * @param billdate
     * @return
     */
    public List<CarList> findAllByArrivepositionAndBilldate(String arriveposition, String billdate);






}
