package com.wayne.airplane.bullet;

import com.wayne.airplane.flyingObject.FlyingObject;
import com.wayne.airplane.ownplane.OwnPlane;
import com.wayne.airplane.util.ImageIOUtil;

/**
 * 子弹只是飞行物，因此继承飞行物类即可
 */

public class Bullet extends FlyingObject {
	/**
	 * 子弹走步步数，只有y坐标在变
	 */
	private int speed = 3;

	public Bullet(int x, int y) {
		/**
		 * 子弹的步数随着英雄机的变化而变化
		 */
		image = ImageIOUtil.bullet;
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	}

	@Override
	public void step(OwnPlane ownPlane) {
		y -= speed;
	}

	@Override
	public boolean outOfBounds() {
		return this.y < -this.height;
	}

}
