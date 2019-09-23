package com.wayne.airplane.flyingObject;

import com.wayne.airplane.bullet.Bullet;
import com.wayne.airplane.ownplane.OwnPlane;

import java.awt.image.BufferedImage;

/**
 * 创建抽象类飞行物
 */
public abstract class FlyingObject {
	/**
	 * 图片命名
	 */
	public BufferedImage image;
	/**
	 *  宽度
	 */
	public int width;
	/**
	 * 高度
	 */
	public int height;
	/**
	 * 横坐标
	 */
	public int x;
	/**
	 * 纵坐标
	 */
	public int y;
	/**
	 *飞行物移动
	 */
	public abstract void step(OwnPlane ownPlane);
	/**
	 *是否越界的判断方法
	 */
	public abstract boolean outOfBounds();
	/**
	 *敌人与子弹之间碰撞检测
	 */
	public boolean shootBy(Bullet b) {
		/**
		 *敌人的半径
		 */
		int flyingR = Math.max(this.width, this.height) / 2;
		/**
		 *子弹的半径
		 */
		int bulletR = Math.max(b.width, b.height) / 2;
		/**
		 *敌人中心横坐标
		 */
		int flyingX = this.x + flyingR;
		/**
		 *敌人中心纵坐标
		 */
		int flyingY = this.y + flyingR;
		/**
		 *子弹中心横坐标
		 */
		int bulletX = b.x + bulletR;
		/**
		 *子弹中心纵坐标
		 */
		int bulletY = b.y + bulletR;
		/**
		 * 如果敌人和子弹的中心距小于半径的和，则发生了碰撞，敌人和子弹中心距采用
		 * 两点间距离公式：
		 * 平方根[(敌人横坐标中心-子弹横坐标中心)平方+(敌人纵坐标中心-子弹纵坐标中心)平方]
		 */
		return Math.sqrt(Math.pow(flyingX - bulletX, 2) + Math.pow(flyingY - bulletY, 2))
				< ((flyingR + bulletR) / 3 * 2);
	}
}
