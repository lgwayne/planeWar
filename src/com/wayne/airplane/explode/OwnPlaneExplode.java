package com.wayne.airplane.explode;

import com.wayne.airplane.flyingObject.FlyingObject;
import com.wayne.airplane.ownplane.OwnPlane;
import com.wayne.airplane.util.ImageIOUtil;

import java.awt.image.BufferedImage;

/**
 * 该类用来播放英雄机死后爆炸动画
 */
public class OwnPlaneExplode extends FlyingObject {
	/**
	 * 英雄机爆炸图片数组
	 */
	private BufferedImage[] ownplaneExplodeImages;

	/**
	 * 英雄机爆炸图片切换器
	 */
	private int ownplaneExplodeFrameId;

	/**
	 * 英雄机爆炸位置随着英雄级移动而移动
	 * @param ownplaneX
	 * @param ownplaneY
	 */
	public OwnPlaneExplode(int ownplaneX, int ownplaneY) {
		image = ImageIOUtil.ownPlaneExplode1;
		ownplaneExplodeImages = new BufferedImage[]
				{ImageIOUtil.ownPlaneExplode1,
						ImageIOUtil.ownPlaneExplode2,
						ImageIOUtil.ownPlaneExplode3,
						ImageIOUtil.ownPlaneExplode4};
		ownplaneExplodeFrameId = 0;
		width = image.getWidth();
		height = image.getHeight();
		x = ownplaneX;
		y = ownplaneY;
	}

	@Override
	public void step(OwnPlane ownPlane) {
		// 每100毫秒切换一次图片
		image = ownplaneExplodeImages[ownplaneExplodeFrameId++ / 10 % ownplaneExplodeImages.length];
	}

	// 英雄机永不越界
	@Override
	public boolean outOfBounds() {
		return false;
	}

	// 控制英雄机爆炸图片播放一周目
	public boolean explodeEnd() {
		return ownplaneExplodeFrameId == 39;
	}

}
