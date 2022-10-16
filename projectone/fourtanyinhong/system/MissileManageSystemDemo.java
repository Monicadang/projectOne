package com.stx.exercise.p9.fourtanyinhong.system;

import com.stx.exercise.p9.fourtanyinhong.MissileManageSystem;
import com.stx.exercise.p9.fourtanyinhong.dao.impl.MissileDaoImpl;
import com.stx.exercise.p9.fourtanyinhong.entity.Missile;

import java.util.List;
import java.util.Scanner;

/**
 * ClassName:MissileManageSystemDemo
 * Package:com.stx.exercise.p9.fourtanyinhong
 * Description:
 *
 * @Date:2022/9/26 10:35
 * @Author:tanyinhong
 */
public class MissileManageSystemDemo {

    private static MissileManageSystem system = new MissileManageSystem();
    private static MissileDaoImpl missileDao = new MissileDaoImpl();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        menu();
    }

    public static void menu(){
        boolean loop = true;
        while (loop) {
            System.out.println("*****************************\n" +
                    "******欢迎进入导弹管理系统*******\n" +
                    "******************************\n" +
                    "******请输入相应编号选择功能*****");
            System.out.println("____________________________________\n" +
                    "1.新增导弹信息\n" +
                    "2.导弹信息展示\n" +
                    "3.修改导弹\n" +
                    "4.废弃过时导弹\n" +
                    "5.退出系统\n" +
                    "____________________________________");

            int index = scanner.nextInt();
            switch (index) {
                case 1:
                    addMissile();
                    break;
                case 2:
                    findMissiles();
                    break;
                case 3:
                    updateMissile();
                    break;
                case 4:
                    delMissile();
                    break;
                case 5:
                    scanner.close();
                    loop = false;
                    System.out.println("系统已退出");
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
                    break;
            }
        }
    }

    private static void addMissile() {
        System.out.println("请输入导弹型号:");
        String model = scanner.next();
        System.out.println("请输入导弹名称:");
        String name = scanner.next();
        System.out.println("请输入导弹生产国家:");
        String source = scanner.next();
        System.out.println("请输入导弹射程(km):");
        float distance = scanner.nextFloat();
        System.out.println("请输入导弹初始入库数量:");
        int number = scanner.nextInt();
        Missile missile = new Missile(model,name,source,distance,number);
        missileDao.addMissile(missile);
        System.out.println("执行成功");
    }

    private static void updateMissile() {
        System.out.println("请输入导弹编号:");
        int id = scanner.nextInt();
        Missile missile1 = missileDao.findById(id);
        if (missile1 == null){
            System.out.println("查询的导弹编号"+id+"不存在");
            return;
        }
        System.out.println("请输入导弹型号:");
        String model = scanner.next();
        System.out.println("请输入导弹名称:");
        String name = scanner.next();
        System.out.println("请输入导弹生产国家:");
        String source = scanner.next();
        System.out.println("请输入导弹射程(km):");
        float distance = scanner.nextFloat();
        System.out.println("请输入导弹初始入库数量:");
        int number = scanner.nextInt();
        Missile missile = new Missile(id,model,name,source,distance,number);
        missileDao.updateMissile(missile);
    }

    private static void delMissile() {
        System.out.println("请输入废弃导弹的编号:");
        int id = scanner.nextInt();
        Missile missile = missileDao.findById(id);
        if (missile!=null){
            System.out.printf("确定要废弃吗，当前导弹是%s生产,射程为%f公里的强力导弹,且库里还剩余%d枚\n" +
                    "输入yes继续删除，输入其他返回主页面:",missile.getSource(),missile.getDistance(),missile.getNum());
            String flag = scanner.next();
            if (flag.equals("yes")){
                missileDao.delMissile(id);
            }else {
                return;
            }
        }else {
            System.out.println("无导弹型号,无法废弃");
        }
    }

    private static void findMissiles() {
        System.out.println("型号\t\t名称\t\t生产国家\t\t数量\t\t\t射程(km)\t\t\t最新更改时间");
        List<Missile> missileList = missileDao.findAll();
        for (Missile missile : missileList) {
            missile.show();
        }
    }
}
