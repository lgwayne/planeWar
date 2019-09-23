package com.wayne.airplane.award;

import com.wayne.airplane.flyingObject.FlyingObject;
import com.wayne.airplane.ownplane.OwnPlane;
import com.wayne.airplane.util.FinalInterface;
import com.wayne.airplane.util.ImageIOUtil;

import java.util.Random;

/**
 * 增加双倍火力的奖励掉落物类
 */

public class DoubleFire extends FlyingObject {
	Random r = new Random();
	/**
	 * x坐标走动步数
	 */
    private int xSpeed = 1;
	/**
	 *  y坐标走动步数
	 */
	private int ySpeed = 2;
    
    public DoubleFire() {
    	image = ImageIOUtil.doubleFire;
    	width = image.getWidth();
    	height = image.getHeight();
    	x = r.nextInt(FinalInterface.WIDTH - this.width);
    	y = -this.height;
    }

	@Override
	public void step(OwnPlane ownPlane) {
        if (x >= FinalInterface.WIDTH - this.width) 
			xSpeed = -1;
		if (x <= 0) 
			xSpeed = 1;
		x += xSpeed;
		if (ownPlane.getScore() < 300000) {
			ySpeed = 2; 
		} else if (ownPlane.getScore() >= 300000 
				&& ownPlane.getScore() < 600000) {
			ySpeed = 4;
		} else if (ownPlane.getScore() >= 600000 
				&& ownPlane.getScore() < 1000000) {
			ySpeed = 6;
		} else {
			ySpeed = 8;
		}
		y += ySpeed;
	}

	@Override
	public boolean outOfBounds() {
		return this.y > FinalInterface.HEIGHT;
	}

}
