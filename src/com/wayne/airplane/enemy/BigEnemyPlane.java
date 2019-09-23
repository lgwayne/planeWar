package com.wayne.airplane.enemy;

import com.wayne.airplane.flyingObject.FlyingObject;
import com.wayne.airplane.ownplane.OwnPlane;
import com.wayne.airplane.util.FinalInterface;
import com.wayne.airplane.util.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 大型敌机属于飞行物，须继承飞行物类
 */

public class BigEnemyPlane extends FlyingObject implements Enemy {
    private Random r = new Random();

    /**
     * 大型敌机走步的步数，随着英雄机得分的变化而变化
     */
    private int speed = 1;

    /**
     * 大型敌机生命数
     */
    private int bigLife = 8;

    /**
     * 大型敌机图片数组
     */
    private BufferedImage[] bigImages;

    /**
     * 大型敌机图片切换器
     */
    private int bigIndex = 0;


    /**
     * 得到大型敌机生命数
     * @return
     */
    public int getBigLife() {
        return bigLife;
    }

    /**
     * 设置大型敌机生命数
     * @param bigLife
     */
    public void setBigLife(int bigLife) {
        this.bigLife = bigLife;
    }

    public BigEnemyPlane() {
        image = ImageIOUtil.bigEnemyPlane0;
        width = image.getWidth();
        height = image.getHeight();
        bigIndex = 0;
        bigImages = new BufferedImage[]{
                ImageIOUtil.bigEnemyPlane0,
                ImageIOUtil.bigEnemyPlane1};
        x = r.nextInt(FinalInterface.WIDTH - this.width);
        y = -this.height; //负的大型敌机的高
    }

    @Override
    public int getScore() {//击毁后得分
        return 3000;
    }

    public int getShootScore() {//大飞机中弹后，主角得的分
        return 300;
    }

    @Override
    public void step(OwnPlane ownPlane) {
        image = bigImages[bigIndex++ / 10 % bigImages.length];
        if (ownPlane.getScore() < 200000) {
            speed = 1;
        } else if (ownPlane.getScore() >= 200000
                && ownPlane.getScore() < 400000) {
            speed = 2;
        } else if (ownPlane.getScore() >= 400000
                && ownPlane.getScore() < 600000) {
            speed = 3;
        } else if (ownPlane.getScore() >= 600000
                && ownPlane.getScore() < 800000) {
            speed = 4;
        } else if (ownPlane.getScore() >= 800000
                && ownPlane.getScore() < 1000000) {
            speed = 5;
        } else {
            speed = 6;
        }
        y += speed;
    }

    @Override
    public boolean outOfBounds() {
        return this.y > FinalInterface.HEIGHT;      //大型敌机的y坐标大于窗口的高
    }
}
