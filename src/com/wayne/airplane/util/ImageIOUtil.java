package com.wayne.airplane.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

// 图片工具类
public class ImageIOUtil {
	// 声明所有的图片
	public static BufferedImage background0; // 背景图1
	public static BufferedImage background1; // 背景图2
	public static BufferedImage start; // 开始图
	public static BufferedImage pause; // 暂停图
	public static BufferedImage gameover; // 游戏结束图
	public static BufferedImage smallEnemyPlane; // 小型敌机图
	public static BufferedImage smallEnemyPlaneExplode1; // 小型敌机爆炸图片第1帧
	public static BufferedImage smallEnemyPlaneExplode2; // 小型敌机爆炸图片第2帧
	public static BufferedImage smallEnemyPlaneExplode3; // 小型敌机爆炸图片第3帧
	public static BufferedImage smallEnemyPlaneExplode4; // 小型敌机爆炸图片第4帧
	public static BufferedImage middleEnemyPlane; // 中型敌机图
	public static BufferedImage middleEnemyPlaneExplode1; // 中型敌机爆炸图片第1帧
	public static BufferedImage middleEnemyPlaneExplode2; // 中型敌机爆炸图片第2帧
	public static BufferedImage middleEnemyPlaneExplode3; // 中型敌机爆炸图片第3帧
	public static BufferedImage middleEnemyPlaneExplode4; // 中型敌机爆炸图片第4帧
	public static BufferedImage bigEnemyPlane0; // 大型敌机图1
	public static BufferedImage bigEnemyPlane1; // 大型敌机图2
	public static BufferedImage bigEnemyPlaneClicked; // 大型敌机中弹图
	public static BufferedImage bigEnemyPlaneExplode1; // 大型敌机爆炸图片第1帧
	public static BufferedImage bigEnemyPlaneExplode2; // 大型敌机爆炸图片第2帧
	public static BufferedImage bigEnemyPlaneExplode3; // 大型敌机爆炸图片第3帧
	public static BufferedImage bigEnemyPlaneExplode4; // 大型敌机爆炸图片第4帧
	public static BufferedImage bigEnemyPlaneExplode5; // 大型敌机爆炸图片第5帧
	public static BufferedImage bigEnemyPlaneExplode6; // 大型敌机爆炸图片第6帧
	public static BufferedImage up_1; // 奖励生命图
	public static BufferedImage doubleFire; // 双导弹图
	public static BufferedImage bullet; // 子弹图
	public static BufferedImage ownPlane0; // 英雄机0图
	public static BufferedImage ownPlane1; // 英雄机1图
	public static BufferedImage ownPlaneExplode1; // 英雄机爆炸图片第1帧
	public static BufferedImage ownPlaneExplode2; // 英雄机爆炸图片第2帧
	public static BufferedImage ownPlaneExplode3; // 英雄机爆炸图片第3帧
	public static BufferedImage ownPlaneExplode4; // 英雄机爆炸图片第4帧
	// 由静态块加载所有的图片
	static {
		try {
			background0 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/background/background.png"));
			background1 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/background/background.png"));
			start = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/bounder/start.png"));
			pause = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/bounder/pause.png"));
			gameover = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/bounder/gameover.png"));
			smallEnemyPlane = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/plane/smallairplane0.png"));
			smallEnemyPlaneExplode1 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/smallairplaneexplode1.png"));
			smallEnemyPlaneExplode2 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/smallairplaneexplode2.png"));
			smallEnemyPlaneExplode3 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/smallairplaneexplode3.png"));
			smallEnemyPlaneExplode4 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/smallairplaneexplode4.png"));
			middleEnemyPlane = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/plane/middleairplane0.png"));
			middleEnemyPlaneExplode1 = ImageIO
					.read(ImageIOUtil.class
							.getResource("/resources/wayne/airplane/explode/middleairplaneexplode1.png"));
			middleEnemyPlaneExplode2 = ImageIO
					.read(ImageIOUtil.class
							.getResource("/resources/wayne/airplane/explode/middleairplaneexplode2.png"));
			middleEnemyPlaneExplode3 = ImageIO
					.read(ImageIOUtil.class
							.getResource("/resources/wayne/airplane/explode/middleairplaneexplode3.png"));
			middleEnemyPlaneExplode4 = ImageIO
					.read(ImageIOUtil.class
							.getResource("/resources/wayne/airplane/explode/middleairplaneexplode4.png"));
			bigEnemyPlane0 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/plane/bigairplane0.png"));
			bigEnemyPlane1 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/plane/bigairplane1.png"));
			bigEnemyPlaneClicked = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/plane/bigairplaneclicked.png"));
			bigEnemyPlaneExplode1 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/bigairplaneexplode1.png"));
			bigEnemyPlaneExplode2 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/bigairplaneexplode2.png"));
			bigEnemyPlaneExplode3 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/bigairplaneexplode3.png"));
			bigEnemyPlaneExplode4 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/bigairplaneexplode4.png"));
			bigEnemyPlaneExplode5 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/bigairplaneexplode5.png"));
			bigEnemyPlaneExplode6 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/bigairplaneexplode6.png"));
			up_1 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/award/1up.png"));
			doubleFire = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/award/doublefire.png"));
			bullet = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/bullet/bullet1.png"));
//			bullet = ImageIO.read(ImageIOUtil.class
//					.getResource("/resources/wayne/airplane/bullet/bullet.png"));
			ownPlane0 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/plane/hero0.png"));
			ownPlane1 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/plane/hero1.png"));
			ownPlaneExplode1 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/heroexplode1.png"));
			ownPlaneExplode2 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/heroexplode2.png"));
			ownPlaneExplode3 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/heroexplode3.png"));
			ownPlaneExplode4 = ImageIO.read(ImageIOUtil.class
					.getResource("/resources/wayne/airplane/explode/heroexplode4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
