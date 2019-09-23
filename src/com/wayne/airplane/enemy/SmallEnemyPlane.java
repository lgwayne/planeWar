package com.wayne.airplane.enemy;

import com.wayne.airplane.flyingObject.FlyingObject;
import com.wayne.airplane.ownplane.OwnPlane;
import com.wayne.airplane.util.FinalInterface;
import com.wayne.airplane.util.ImageIOUtil;

import java.util.Random;


/**
 *小型敌机属于飞行物，须继承飞行物类
 */
public class SmallEnemyPlane extends FlyingObject implements Enemy {
	private Random r = new Random();

	/**
	 *小型敌机走步的步数，随着英雄机得分的变化而变化
	 */
	private int speed = r.nextInt(3) + 1;

	/**
	 *小型敌机生命数
	 */
	private int smallLife = 1;

	/**
	 *
	 */
	public SmallEnemyPlane() {
		image = ImageIOUtil.smallEnemyPlane;
		width = image.getWidth();
		height = image.getHeight();
		x = r.nextInt(FinalInterface.WIDTH - this.width);
		y = -this.height; //负的小型敌机的高
	}

	/**
	 *  得到小型敌机生命数
	 * @return
	 */
	public int getSmallLife() {
		return smallLife;
	}

	/**
	 * 设置小型敌机生命数
	 * @param smallLife
	 */
	public void setSmallLife(int smallLife) {
		this.smallLife = smallLife;
	}
	@Override
	public int getScore() {
		return 1000;
	}

	@Override
	public void step(OwnPlane ownPlane) {
		if (ownPlane.getScore() < 200000) {
			speed = r.nextInt(3) + 1;
		} else if (ownPlane.getScore() >= 200000
				&& ownPlane.getScore() < 400000) {
			speed = r.nextInt(3) + 2;
		} else if (ownPlane.getScore() >= 400000
				&& ownPlane.getScore() < 600000) {
			speed = r.nextInt(3) + 3;
		} else if (ownPlane.getScore() >= 600000
				&& ownPlane.getScore() < 800000) {
			speed = r.nextInt(3) + 4;
		} else if (ownPlane.getScore() >= 800000
				&& ownPlane.getScore() < 1000000) {
			speed = r.nextInt(3) + 5;
		} else {
			speed = r.nextInt(3) + 6;
		}
		y += speed;
	}

	@Override
	public boolean outOfBounds() {
		return this.y > FinalInterface.HEIGHT;      //敌机的y坐标大于窗口的高
	}

}
