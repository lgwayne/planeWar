package com.wayne.airplane.explode;

import com.wayne.airplane.flyingObject.FlyingObject;
import com.wayne.airplane.ownplane.OwnPlane;
import com.wayne.airplane.util.FinalInterface;
import com.wayne.airplane.util.ImageIOUtil;

import java.awt.image.BufferedImage;

/**
 * 该类用来播放中飞机死后爆炸的动画
 */
public class MiddleEnemyExplode extends FlyingObject {
	/**
	 * 中型敌机爆炸图片数组
	 */
	private BufferedImage[] middleExplodeImages;
	/**
	 *小型敌机爆炸图片切换器
	 */
	private int middleExplodeFrameId;

	/**
	 *中型敌机爆炸位置随着中型敌机移动而移动
	 */
	public MiddleEnemyExplode(int middleEnemyX, int middleEnemyY) {
		image = ImageIOUtil.middleEnemyPlaneExplode1;
		middleExplodeImages = new BufferedImage[]
				{ImageIOUtil.middleEnemyPlaneExplode1,
						ImageIOUtil.middleEnemyPlaneExplode2,
						ImageIOUtil.middleEnemyPlaneExplode3,
						ImageIOUtil.middleEnemyPlaneExplode4};
		middleExplodeFrameId = 0;
		width = image.getWidth();
		height = image.getHeight();
		x = middleEnemyX;
		y = middleEnemyY;
	}
	@Override
	public void step(OwnPlane ownPlane) {
		// 每100毫秒切换一次图片
		image = middleExplodeImages[middleExplodeFrameId++ / 10 % middleExplodeImages.length];
	}

	@Override
	public boolean outOfBounds() {
		return this.y > FinalInterface.HEIGHT;
	}

	// 控制中飞机爆炸动画播放一周目
	public boolean explodeEnd() {
		return middleExplodeFrameId == 39;
	}

}
