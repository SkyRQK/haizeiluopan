package com.example.pinche.repository;
import com.example.pinche.domain.CarriageList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * carriage表持久层
 * @author wangchang
 */
public interface Carriagerepo extends JpaRepository<CarriageList, String> {

    /**
     * 通过userid获取用户的行程信息
     * @param userid
     * @return
     */
    public List<CarriageList> findAllByUserid(String userid);


    /**
     * 通过车次号和发车时间
     * @param carnum
     * @param starttime
     * @return
     */
    @Query(value = "select * from carriagelist where carnum = ?1 and userid != 1 and starttime like ?2", nativeQuery = true)
    public List<CarriageList> findAllByCarnumAndStarttimeLike(String carnum, String starttime);


}
