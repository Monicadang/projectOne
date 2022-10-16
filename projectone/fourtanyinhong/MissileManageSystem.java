package com.stx.exercise.p9.fourtanyinhong;

import com.stx.exercise.p9.fourtanyinhong.entity.Missile;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * ClassName:MissileManageSystem
 * Package:com.stx.exercise.p9.fourtanyinhong
 * Description:
 *
 * @Date:2022/9/26 10:35
 * @Author:tanyinhong
 */
public class MissileManageSystem {

    private Missile[] missiles = new Missile[0];
    private static int count;

    /**
     * 添加导弹方法
     * 数组扩容
     *
     * @param missile
     */
    public void add(Missile missile) {
        for (Missile missile1 : missiles) {
            if (missile1.getModel().equals(missile.getModel())){
                System.out.println("该导弹型号存在");
                return;
            }
        }
        if (missile == null) {
            System.out.println("导弹无");
            return;
        } else if (count >= missiles.length) {
            /**
             * 数组扩容小算法
             */
            int newLen = (missiles.length * 3) / 2 + 1;
            missiles = Arrays.copyOf(missiles, newLen);
        }
        missiles[count] = missile;
        count++;
    }

    /**
     * 导弹入库方法
     * @param missile
     */
    public void missilePut(Missile missile) {
        if (missile == null) {
            return;
        }
        for (int i = 0; i < missiles.length; i++) {
            /**
             * 判断输入的导弹类型，是否存在于导弹库中
             * 并且加上入库导弹数量
             */
            if (missile.getModel().equals(missiles[i].getModel())) {
                missiles[i].setNum(missiles[i].getNum() + missile.getNum());
                return;
            }
        }
        System.out.println("无导弹型号，请先添加型号");
    }

    /**
     * 导弹出库方法
     * @param missile
     */
    public void missileOut(Missile missile) {
        if (missile == null) {
            return;
        }
        for (int i = 0; i < missiles.length; i++) {
            /**
             * 判断需要出库的导弹类型是否存在于导弹库，并且出库数量小于总数量
             */
            if (missile.getModel().equals(missiles[i].getModel()) && missile.getNum() <= missiles[i].getNum()) {
                missiles[i].setNum(missiles[i].getNum() - missile.getNum());
                return;
            }
            /**
             * 判断需要出库的导弹类型是否存在于导弹库，并且出库数量大于总数量
             */
            if (missile.getModel().equals(missiles[i].getModel()) && missile.getNum() > missiles[i].getNum()) {
                //missileDel(missile);
                System.out.println("导弹型号为"+missile.getModel()+"剩余数量不足，出库数量为"+missiles[i].getNum()+",更新时间为"+date());
                return;
            }
        }
        System.out.println("无导弹型号，请先添加型号");
    }

    /**
     * 废弃导弹方法
     * @param missile
     */
    public void missileDel(Missile missile) {
        if (missile == null) {
            return;
        }
        for (int i = 0; i < missiles.length; i++) {
            /**
             *
             * 判断类型是否相同
             */
            if (missile.getModel().equals(missiles[i].getModel())) {
                System.out.println("废弃"+missiles[i].getName()+"反舰导弹成功");
                /**
                 * 删除的元素后面对应的元素一次前移
                 */
                for (int j = i; j < count - 1; j++) {
                    missiles[j] = missiles[j + 1];
                }
                /**
                 * 定义最后一个元素为空，方便管理
                 */
                missiles[count-1]=null;
                /**
                 * 因为删除了一个元素，数组长度减一
                 */
                count--;
                break;
            }
        }
    }

    /**
     * 查询全部
     */
    public void findAll() {
        for (Missile missile : missiles) {
            if (missile != null) {
                missile.show();
            }
        }
    }

    /**
     * 根据导弹模型查询并返回导弹信息
     * @param model
     * @return
     */
    public Missile findOne(String model){
        for (Missile missile : missiles) {
            if (missile!=null){
                if (missile.getModel().equals(model)){
                    return missile;
                }
            }
        }
        return null;
    }

    /**
     * 日期格式化方法
     * @return
     */
    public String date(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
        return dateFormat.format(date);
    }
}
