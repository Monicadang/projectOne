package com.stx.exercise.p9.fourtanyinhong.dao;

import com.stx.exercise.p9.fourtanyinhong.entity.Missile;

import java.util.List;

/**
 * ClassName:IMissileDao
 * Package:com.stx.exercise.p9.fourtanyinhong.dao
 * Description:
 *
 * @Date:2022/10/12 15:53
 * @Author:tanyinhong
 */
public interface IMissileDao {

    void addMissile(Missile missile);

    void updateMissile(Missile missile);

    void delMissile(int id);

    List<Missile> findAll();

    Missile findById(int id);
}
