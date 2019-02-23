package com.example.pinche.repository;


import com.example.pinche.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * user表持久层
 * @author wangchang
 */
public interface Userrepo extends JpaRepository<User,String> {


    public List<User> findAllByUserid(String userid);

    @Query(value = "update User user set user.name=?2,user.phone=?3,user.learnid=?4 ,user.qq=?5 where user.userid=?1")
    @Modifying
    @Transactional
    public void updatePassword(String userid,String  name,String phone,String learnid,String qq);

    /**
     * 通过groupid获取这个carlist里面所有的用户信息
     * @return
     */
    @Query(value = "select * from user WHERE userid IN (SELECT userid from team where groupid = ?1)", nativeQuery = true)
    @Modifying
    public List<User> getUserByGroupId(String groupId);


    @Modifying
    public User getUserByUserid(String userid);



}
