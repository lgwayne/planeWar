package com.wayne.airplane.util;

import javax.swing.*;
import java.awt.*;

public class ScoreListPanel extends JPanel{

	/**
	 *排行榜面板
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//获得资源
		ImageIcon iIcon = new ImageIcon("D:\\IdeaProjects\\飞机大战\\src\\image\\timg.jpg") ;
		//画一个背景图
		g.drawImage(iIcon.getImage(), 0, 0, null) ;
	}


}
