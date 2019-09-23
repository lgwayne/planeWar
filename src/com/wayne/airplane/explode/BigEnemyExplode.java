package com.wayne.airplane.explode;

import com.wayne.airplane.flyingObject.FlyingObject;
import com.wayne.airplane.ownplane.OwnPlane;
import com.wayne.airplane.util.FinalInterface;
import com.wayne.airplane.util.ImageIOUtil;

import java.awt.image.BufferedImage;

/**
 * 该类用来播放大飞机死后爆炸的动画
 */

public class BigEnemyExplode extends FlyingObject {
	/**
	 * 大型敌机爆炸图片数组
	 */
	private BufferedImage[] bigExplodeImages;

	/**
	 *大型敌机爆炸图片切换器
	 */
	private int bigExplodeFrameId;

	/**
	 *大型敌机爆炸位置随着大型敌机移动而移动
	 */
	public BigEnemyExplode(int bigEnemyX, int bigEnemyY) {
		image = ImageIOUtil.bigEnemyPlaneExplode1;
		bigExplodeImages = new BufferedImage[]
				{ImageIOUtil.bigEnemyPlaneExplode1,
						ImageIOUtil.bigEnemyPlaneExplode2,
						ImageIOUtil.bigEnemyPlaneExplode3,
						ImageIOUtil.bigEnemyPlaneExplode4,
						ImageIOUtil.bigEnemyPlaneExplode5,
						ImageIOUtil.bigEnemyPlaneExplode6};
		bigExplodeFrameId = 0;
		width = image.getWidth();
		height = image.getHeight();
		x = bigEnemyX;
		y = bigEnemyY;
	}
	@Override
	public void step(OwnPlane ownPlane) {
		// 每100毫秒切换一次图片
		image = bigExplodeImages[bigExplodeFrameId++ / 10 % bigExplodeImages.length];
	}

	@Override
	public boolean outOfBounds() {
		return this.y > FinalInterface.HEIGHT;
	}

	/**
	 *控制大飞机爆炸动画播放一次结束
	 */
	public boolean explodeEnd() {
		return bigExplodeFrameId == 59;
	}

}
