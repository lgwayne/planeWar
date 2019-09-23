package com.wayne.airplane.ownplane;

import com.wayne.airplane.bullet.Bullet;
import com.wayne.airplane.flyingObject.FlyingObject;
import com.wayne.airplane.util.ImageIOUtil;
import com.wayne.airplane.view.ShootGameView;

import java.awt.image.BufferedImage;
/**
 *英雄机是飞行物
 */
public class OwnPlane extends FlyingObject {
    /**
     *生命值
     */
    private int life;
    /**
     *火力值
     */
    private int doubleFire;
    /**
     *英雄机图片数组
     */
    private BufferedImage[] images;
    /**
     *协助图片切换
     */
    private int index;
    /**
     *打死敌人后得分
     */
    private int score;
    /**
     *是否为无敌状态
     */
    private boolean isInvincible;
    /**
     * 控制无敌时间
     */
    private int invincibleTime;
    /**
     *英雄机的设置
     */
    public OwnPlane() {
        image = ImageIOUtil.ownPlane0;
        width = image.getWidth();
        height = image.getHeight();
        x = (ShootGameView.WIDTH - this.width) / 2;
        y = ShootGameView.HEIGHT - this.height;
        life = 3;     //设置生命数为3
        doubleFire = 0;        //设置火力值为单倍
        images = new BufferedImage[]{ImageIOUtil.ownPlane0, ImageIOUtil.ownPlane1};
        index = 0;
        score = 0;
        isInvincible = true;
        invincibleTime = 0;
    }

    @Override
    /**
     * 每100毫秒切换一次图片，并同时给1s的无敌时间
     */
    public void step(OwnPlane ownPlane) {
        image = images[index++ / 10 % images.length];
        invincibleTime++;
        if (invincibleTime == 99) { // 设置1s无敌时间
            isInvincible = false;
        }
    }

    /**
     * 发射子弹的方法
     * @return
     */
    public Bullet[] shoot() {
        int xStep = this.width / 32;

        if (doubleFire > 0) {           //双发
            Bullet[] bullets = new Bullet[2];
            bullets[0] = new Bullet(this.x + 5 * xStep, this.y);
            bullets[1] = new Bullet(this.x + 21 * xStep, this.y);
            doubleFire -= 2;      //发射双倍火力，每次减2，限制双倍火力的持续时间
            return bullets;
        } else {                        //单发
            Bullet[] bullets = new Bullet[1];
            bullets[0] = new Bullet(this.x + 13 * xStep, this.y);
            return bullets;
        }
    }

    public void moveTo(int x, int y) {
        this.x = x - this.width / 2;
        this.y = y - this.height / 2;
    }

    @Override
    /**
     *英雄机永不越界
     */
    public boolean outOfBounds() {
        return false;
    }

    /**
     *设置生命值
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     *得到生命值
     */
    public int getLife() {
        return life;
    }

    /**
     *增加火力值
     */
    public void addDoubleFire() {
        doubleFire += 60;
    }

    /**
     *火力值清零
     */
    public void setDoubleFire(int doubleFire) {
        this.doubleFire = doubleFire;
    }

    /**
     *得到双倍火力的剩余时间
     */
    public int getDoubleFireTime() {
        return doubleFire / 2;
    }

    /**
     *英雄机和敌人之间碰撞检测
     */
    public boolean hit(FlyingObject fo) {
        /**
         *敌人的半径
         */
        int flyingR = Math.max(fo.width, fo.height) / 2;
        /**
         *英雄机的半径
         */
        int ownPlaneR = Math.max(this.width, this.height) / 2;
        /**
         * 敌人中心横坐标
         */
        int flyingX = fo.x + flyingR;
        /**
         *敌人中心纵坐标
         */
        int flyingY = fo.y + flyingR;
        /**
         *英雄机中心横坐标
         */
        int ownPlaneX = this.x + ownPlaneR;
        /**
         *英雄机中心纵坐标
         */
        int ownPlaneY = this.y + ownPlaneR;
        /* 如果敌人和英雄机的中心距小于半径的和，则发生了碰撞，敌人和英雄机中心距采用两点间距离公式：
         * 平方根[(敌人横坐标中心-英雄机横坐标中心)平方+(敌人纵坐标中心-英雄机纵坐标中心)平方]
         */
        return Math.sqrt(Math.pow(flyingX - ownPlaneX, 2)
                + Math.pow(flyingY - ownPlaneY, 2))
                < ((flyingR + ownPlaneR) / 3 * 2);
    }

    /**
     *得到分数
     */
    public int getScore() {
        return score;
    }

    /**
     *设置分数
     */
    public void setScore(int score) {
        this.score = score;
    }
    /**
     *
     */
    public boolean isInvincible() {
        return isInvincible;
    }
    /**
     *
     */
    public void setInvincible(boolean isInvincible) {
        this.isInvincible = isInvincible;
    }
    /**
     *
     */
    public int getInvincibleTime() {
        return invincibleTime;
    }
    /**
     *
     */
    public void setInvincibleTime(int invincibleTime) {
        this.invincibleTime = invincibleTime;
    }
}
