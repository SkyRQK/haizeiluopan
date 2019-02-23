package com.example.pinche.repository;

import com.example.pinche.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * team表持久层
 * @author wangchang
 */
public interface Teamrepo extends JpaRepository<Team,String> {


    public List<Team> getAllByGroupidAndAndUserid(String groupid, String userid);

}
