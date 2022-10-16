package com.stx.exercise.p9.fourtanyinhong.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * ClassName:Missile
 * Package:com.stx.exercise.p9.fourtanyinhong
 * Description:
 *
 * @Date:2022/9/26 10:32
 * @Author:tanyinhong
 */
public class Missile {
    private long id;
    private String model;//导弹型号
    private String name;//导弹名称
    private String source;//导弹生产国家
    private double distance;//导弹射程
    private int num;//导弹数量
    private LocalDate updateDate;

    public Missile() {
    }

    public Missile(long id, String model, String name, String source, double distance, int num, LocalDate updateDate) {
        this.id = id;
        this.model = model;
        this.name = name;
        this.source = source;
        this.distance = distance;
        this.num = num;
        this.updateDate = updateDate;
    }

    public Missile(long id, String model, String name, String source, double distance, int num) {
        this.id = id;
        this.model = model;
        this.name = name;
        this.source = source;
        this.distance = distance;
        this.num = num;
    }

    public Missile(String model, String name, String source, double distance, int number) {
        this.model = model;
        this.name = name;
        this.source = source;
        this.distance = distance;
        this.num = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public LocalDate getDate() {
        return updateDate;
    }

    public void setDate(LocalDate date) {
        this.updateDate = date;
    }

    public Missile(String model, int number) {
        this.model = model;
        this.num = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    /*@Override
    public String toString() {
        return model + "\t\t" + name+"\t\t"+source+"\t\t"+distance+"\t\t"+number+"\t\t"+date;

    }*/

    public void show(){
        System.out.printf("%-8s",model);
        System.out.printf("%-8s",name);
        System.out.printf("%-8s\t",source);
        System.out.printf("%-11d",num);
        System.out.printf("%.2f\t",distance);
        System.out.printf("\t%-10s",updateDate);
        System.out.println();
    }
}
