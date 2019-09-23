package com.wayne.airplane.view;

import com.sun.xml.internal.bind.v2.TODO;
import com.wayne.airplane.entity.User;
import com.wayne.airplane.service.IUserService;
import com.wayne.airplane.service.UserServiceImpl;
import com.wayne.airplane.util.FinalInterface;
import com.wayne.airplane.util.ScoreListPanel;
import com.wayne.airplane.util.Tookit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.List;

public class ScoreListView extends JFrame implements MouseListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<User> list;
	private JPanel jp;
	private User login = null; // 用来判断是游戏结束跳转到该页还是登录前跳转到该页

	public ScoreListView(User login) {
		this.login = login;
		this.init();
	}

	private void init() {
		System.out.println("排行榜已启动");
		JPanel buttom = new JPanel(new BorderLayout());
		buttom.add(this.ScoreListPanel());
		this.add(buttom);
		this.setSize(FinalInterface.WIDTH, FinalInterface.HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("D:\\IdeaProjects\\飞机大战\\src\\image\\hero0.png").getImage());
		this.setTitle("飞机大战");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 处理排行榜面板
	 *
	 * @return
	 */
	public JPanel ScoreListPanel() {
		JPanel jPanel = new JPanel(new BorderLayout());

		ScoreListPanel listPanel = new ScoreListPanel();
		listPanel.setLayout(null);//未设置Layout时，java默认为flowLayout布局的，设置为null即为清空布局管理器，之后添加组件，常常是设置组件左上角坐标相对于容器左上角（0，0）的x,y值来确定组件的位置，即使更改容器大小也不会改变位置。这种方式常常用于窗体大小固定的容器里。
		JLabel title = new JLabel("本周飞机大战排行榜", JLabel.CENTER);
		title.setForeground(Color.white);
		title.setBounds(30, 120, 400, 50);
		title.setFont(new Font("楷体", Font.BOLD, 42));
		listPanel.add(title);

		// 加一个面板 可以存入多个jlabel
		jp = new JPanel();
		jp.setOpaque(false);
		jp.setBounds(0, 0, 500, 800);
		listPanel.add(jp);

		JLabel nick = new JLabel("玩  家", JLabel.CENTER);
		nick.setForeground(Color.LIGHT_GRAY);
		nick.setFont(Tookit.getFont5());
		nick.setBounds(80, 180, 100, 40);
		listPanel.add(nick);

		JLabel score = new JLabel("分  数", JLabel.CENTER);
		score.setForeground(Color.LIGHT_GRAY);
		score.setFont(Tookit.getFont5());
		score.setBounds(304, 180, 100, 40);
		listPanel.add(score);

		IUserService service = new UserServiceImpl();
		list = service.selectAll();
		Collections.sort(list);

		System.out.println(list.toString());

		// 遍历前十条记录
		for (int i = 0; i < list.size(); i++) {
			if (i < 10) {
				JLabel nickName = new JLabel(list.get(i).getNickName(),
						JLabel.CENTER);

				System.out.println(list.get(i).getNickName());//打印名字

				nickName.setForeground(Color.white);
				nickName.setFont(Tookit.getFont4());
				nickName.setBounds(80, 180 + (i + 1) * 40, 80, 30);
				listPanel.add(nickName);

				JLabel userScore = new JLabel(list.get(i).getScore()
						.toString(), JLabel.CENTER);

				System.out.println(list.get(i).getScore());//打印分数

				userScore.setForeground(Color.white);
				userScore.setFont(Tookit.getFont4());
				userScore.setBounds(304, 180 + (i + 1) * 40, 80, 30);
				listPanel.add(userScore);
			}
		}

		/**
		 * 当玩家未登录进入该页时，该钮为返回按钮，点击返回登录页
		 * 当玩家登录后结束游戏进入该页时，该钮为退出按钮，点击退出程序
		 */
		final JButton reverseExit = new JButton(login == null ? "返回" : "退出");
		reverseExit.setBounds(192, 630, 80, 30);
		listPanel.add(reverseExit);
		reverseExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String context = reverseExit.getText(); // 获取按钮中的文字内容
				if (login == null && context.equals("返回")) {
					new LoginView().setVisible(true);
					ScoreListView.this.dispose();
				}
				if (login != null && context.equals("退出")) {
					JOptionPane.showMessageDialog(ScoreListView.this, login.getNickName() + "已成功退出！");
					System.exit(0);
				}
			}
		});

		jPanel.add(listPanel);
		return jPanel;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	public static void main(String[] args) {
		new ScoreListView(new User("scott","12345")).setVisible(true);

	}
}
