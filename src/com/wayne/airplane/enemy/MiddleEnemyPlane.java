package com.wayne.airplane.enemy;

import com.wayne.airplane.flyingObject.FlyingObject;
import com.wayne.airplane.ownplane.OwnPlane;
import com.wayne.airplane.util.FinalInterface;
import com.wayne.airplane.util.ImageIOUtil;

import java.util.Random;
/**
 * 中型敌机属于飞行物，须继承飞行物类
 */
public class MiddleEnemyPlane extends FlyingObject implements Enemy {
	private Random r = new Random();
	/**
	 * 中型敌机走步的步数，随着英雄机得分的变化而变化
	 */
	private int speed = r.nextInt(2) + 1;
	/**
	 * 中型敌机生命数
	 */
	private int middleLife = 4;
	/**
	 *得到中型敌机生命数
	 */
	public int getMiddleLife() {
		return middleLife;
	}

	/**
	 *设置中型敌机生命数
	 */
	public void setMiddleLife(int middleLife) {
		this.middleLife = middleLife;
	}

	/**
	 *
	 */
	public MiddleEnemyPlane() {
		image = ImageIOUtil.middleEnemyPlane;
		width = image.getWidth();
		height = image.getHeight();
		x = r.nextInt(FinalInterface.WIDTH - this.width);
		y = -this.height; //负的中型敌机的高
	}

	@Override
	/**
	 *
	 */
	public int getScore() {
		return 20000;
	}

	/**
	 *中飞机中弹后，主角得的分
	 */
	public int getShootScore() {
		return 200;
	}

	@Override
	/**
	 *
	 */
	public void step(OwnPlane ownPlane) {
		if (ownPlane.getScore() < 200000) {
			speed = r.nextInt(2) + 1;
		} else if (ownPlane.getScore() >= 200000
				&& ownPlane.getScore() < 400000) {
			speed = r.nextInt(2) + 2;
		} else if (ownPlane.getScore() >= 400000
				&& ownPlane.getScore() < 600000) {
			speed = r.nextInt(2) + 3;
		} else if (ownPlane.getScore() >= 600000
				&& ownPlane.getScore() < 800000) {
			speed = r.nextInt(2) + 4;
		} else if (ownPlane.getScore() >= 800000
				&& ownPlane.getScore() < 1000000) {
			speed = r.nextInt(2) + 5;
		} else {
			speed = r.nextInt(2) + 6;
		}
		y += speed;
	}

	@Override
	/**
	 *中型敌机的y坐标大于窗口的高
	 */
	public boolean outOfBounds() {
		return this.y > FinalInterface.HEIGHT;
	}
}
