package com.stx.exercise.p9.fourtanyinhong.dao.impl;


import com.stx.exercise.p9.fourtanyinhong.dao.IMissileDao;
import com.stx.exercise.p9.fourtanyinhong.entity.Missile;
import com.stx.exercise.p9.fourtanyinhong.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:MissileDaoImpl
 * Package:com.stx.exercise.p9.fourtanyinhong.dao.impl
 * Description:
 *
 * @Date:2022/10/12 15:57
 * @Author:tanyinhong
 */
public class MissileDaoImpl implements IMissileDao {
    @Override
    public void addMissile(Missile missile) {
        JDBCUtil instance = JDBCUtil.getInstance();
        Connection connection = instance.getConnection();
        PreparedStatement ps = null;
        try {
            /*String sql ="insert into missile(id,model,name,source,distance,num,updateDate)" +
                    " values ("+1001+","+missile.getModel()+","+missile.getName()+","+missile.getSource()+","
                    +missile.getDistance()+","+missile.getNumber()+",sysdate)";*/

            /*Statement statement = connection.createStatement();
            statement.executeUpdate(sql);*/
            String sql = "insert into missile" +
                    " values (seq_missile.nextval,?,?,?,?,?,sysdate)";
            ps = connection.prepareStatement(sql);
            ps.setObject(1,missile.getModel());
            ps.setObject(2,missile.getName());
            ps.setObject(3,missile.getSource());
            ps.setObject(4,missile.getDistance());
            ps.setObject(5,missile.getNum());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            instance.close(null,ps,connection);
        }
    }

    @Override
    public void updateMissile(Missile missile) {
        JDBCUtil instance = JDBCUtil.getInstance();
        Connection connection = instance.getConnection();
        PreparedStatement ps = null;
        String sql = "update missile set model=?,name=?,source=?,distance=?,num=?,updateDate=sysdate where id=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1,missile.getModel());
            ps.setObject(2,missile.getName());
            ps.setObject(3,missile.getSource());
            ps.setObject(4,missile.getDistance());
            ps.setObject(5,missile.getNum());
            ps.setObject(6,missile.getId());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            instance.close(null,ps,connection);
        }
    }

    @Override
    public void delMissile(int id) {
        JDBCUtil instance = JDBCUtil.getInstance();
        Connection connection = instance.getConnection();
        PreparedStatement ps = null;
        String sql = "delete from missile where id=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1,id);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            instance.close(null,ps,connection);
        }
    }

    @Override
    public List<Missile> findAll() {
        JDBCUtil instance = JDBCUtil.getInstance();
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = instance.getConnection();
        try {
            statement = connection.createStatement();
            String sql = "select * from missile";
            resultSet = statement.executeQuery(sql);
            ArrayList<Missile> list = new ArrayList<>();
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String model = resultSet.getString("model");
                String name = resultSet.getString("name");
                String source = resultSet.getString("source");
                double distance = resultSet.getDouble("distance");
                int num = resultSet.getInt("num");
                LocalDate updateDate = resultSet.getDate("updateDate").toLocalDate();
                Missile missile = new Missile(id,model,name,source,distance,num,updateDate);
                list.add(missile);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            instance.close(resultSet,statement,connection);
        }
        return null;
    }

    @Override
    public Missile findById(int missileId) {
        JDBCUtil instance = JDBCUtil.getInstance();
        Connection connection = instance.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sql = "select * from missile where id=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1,missileId);
            resultSet = ps.executeQuery();
            Missile missile = null;
            if (resultSet.next()){
                long id = resultSet.getLong("id");
                String model = resultSet.getString("model");
                String name = resultSet.getString("name");
                String source = resultSet.getString("source");
                double distance = resultSet.getDouble("distance");
                int num = resultSet.getInt("num");
                LocalDate updateDate = resultSet.getDate("updateDate").toLocalDate();
                missile = new Missile(id,model,name,source,distance,num,updateDate);
            }
            return missile;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            instance.close(resultSet,ps,connection);
        }
        return null;
    }
}
