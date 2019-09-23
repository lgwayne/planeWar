package com.wayne.airplane.util;

import javax.swing.*;
import java.awt.*;

public class MyUpdatePassPanel extends JPanel{

	/**
	 *更改密码的面板
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//获得资源
		ImageIcon iIcon = new ImageIcon("D:\\IdeaProjects\\飞机大战\\src\\image\\timg (1).jpg") ;
		//画一个背景图
		g.drawImage(iIcon.getImage(), 0, 0, null) ;
	}


}
