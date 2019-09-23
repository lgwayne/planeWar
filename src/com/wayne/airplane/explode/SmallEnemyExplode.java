package com.wayne.airplane.explode;

import com.wayne.airplane.flyingObject.FlyingObject;
import com.wayne.airplane.ownplane.OwnPlane;
import com.wayne.airplane.util.FinalInterface;
import com.wayne.airplane.util.ImageIOUtil;

import java.awt.image.BufferedImage;

/**
 * 该类用来播放小飞机死后爆炸的动画
 */
public class SmallEnemyExplode extends FlyingObject {
	/**
	 * 小型敌机爆炸图片数组
	 */
	private BufferedImage[] smallExplodeImages;

	/**
	 *小型敌机爆炸图片切换器
	 */
	private int smallExplodeFrameId;

	/**
	 * 小型敌机爆炸位置随着小型敌机移动而移动
	 * @param smallEnemyX
	 * @param smallEnemyY
	 */
	public SmallEnemyExplode(int smallEnemyX, int smallEnemyY) {
		image = ImageIOUtil.smallEnemyPlaneExplode1;
		smallExplodeImages = new BufferedImage[]
				{ImageIOUtil.smallEnemyPlaneExplode1,
						ImageIOUtil.smallEnemyPlaneExplode2,
						ImageIOUtil.smallEnemyPlaneExplode3,
						ImageIOUtil.smallEnemyPlaneExplode4};
		smallExplodeFrameId = 0;
		width = image.getWidth();
		height = image.getHeight();
		x = smallEnemyX;
		y = smallEnemyY;
	}
	@Override
	public void step(OwnPlane ownPlane) {
		// 每100毫秒切换一次图片
		image = smallExplodeImages[smallExplodeFrameId++ / 10 % smallExplodeImages.length];
	}

	@Override
	public boolean outOfBounds() {
		return this.y > FinalInterface.HEIGHT;
	}

	/**
	 * 控制小飞机爆炸动画播放一周目
 	 */
	public boolean explodeEnd() {
		return smallExplodeFrameId == 39;
	}

}
