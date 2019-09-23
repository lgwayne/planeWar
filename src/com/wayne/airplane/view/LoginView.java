package com.wayne.airplane.view;

import com.wayne.airplane.entity.User;
import com.wayne.airplane.service.IUserService;
import com.wayne.airplane.service.UserServiceImpl;
import com.wayne.airplane.util.MyLoginPanel;
import com.wayne.airplane.util.Tookit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginView extends JFrame implements MouseListener {

	/**
	 *序列号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 昵称输入框
	 */
	private JTextField nickNameField = null;
	/**
	 * 密码输入框
	 */
	private JPasswordField passwordField = null ;

	private JPanel jp;

	/**
	 * 登录的用户
	 */
	private User login = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LoginView().setVisible(true);
	}

	public LoginView() {
		UIManager.put("TextField.font", Tookit.getFont1()) ;
		UIManager.put("Label.font", Tookit.getFont1()) ;
		UIManager.put("Button.font", Tookit.getFont1()) ;
		this.init();
	}

	private void init() {
		JPanel buttom = new JPanel(new BorderLayout()) ;
		buttom.add(this.loginPanel()) ;
		this.add(buttom) ;
		this.setSize(340, 320) ;
		this.setLocationRelativeTo(null) ;
		this.setResizable(false) ;
		this.setIconImage(new ImageIcon("D:\\IdeaProjects\\飞机大战\\src\\image\\hero0.png").getImage()) ;
		this.setTitle("飞机大战") ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * 处理登录面板
	 * @return
	 */
	public JPanel loginPanel(){

		JPanel jPanel = new JPanel(new BorderLayout()) ;
		/**
		 * 在panel（北面）
		 */
		JLabel jLogin = new JLabel(new ImageIcon("D:\\IdeaProjects\\飞机大战\\src\\image\\login.png"), JLabel.CENTER);
		jPanel.add(jLogin, BorderLayout.NORTH) ;//加一个jlable
		/**
		 * 在panel（center）
		 */
		MyLoginPanel myPanel = new MyLoginPanel();
		myPanel.setLayout(null);
		final JLabel nickName = new JLabel("账    号：", JLabel.CENTER);
		nickName.setBounds(60, 60, 65, 15);
		nickName.setFont(Tookit.getFont1()) ;
		myPanel.add(nickName) ;

		//加一个面板   可以 存入多个jlabel
		jp = new JPanel() ;
		jp.setOpaque(false) ;
		jp.setBounds(130, 100, 140, 200);
		myPanel.add(jp);
		nickNameField = new JTextField(12) ;
		nickNameField.setBounds(130, 60, 140, 21) ;
		myPanel.add(nickNameField);

		JLabel password = new JLabel("密    码：", JLabel.CENTER) ;
		password.setFont(Tookit.getFont1()) ;
		password.setBounds(60, 120, 65, 15) ;
		passwordField = new JPasswordField(12) ;
		passwordField.setBounds(130, 120, 140, 21) ;
		myPanel.add(password) ;
		myPanel.add(passwordField);

		JButton loginButton = new JButton("登录");
		loginButton.setBounds(33, 160, 81, 30) ;
		myPanel.add(loginButton) ;
		loginButton.addActionListener(new ActionListener() {
			// 登录逻辑处理
			@Override
			public void actionPerformed(ActionEvent e) {
				// 文本框中昵称的值
				String nickName = nickNameField.getText().trim();
				// 文本框中密码的值
				String password = String.valueOf(passwordField.getPassword()).trim();

				if (nickName.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "请输入账号！");
					return;
				}
				if (password.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "请输入密码！");
					return;
				}
				// 进行登录验证逻辑判断
				IUserService service = new UserServiceImpl();
				login = service.selectUser(nickName, password);
				if (login != null) { //验证成功
					JOptionPane.showMessageDialog(LoginView.this, "欢迎" + login.getNickName() + "进入游戏！");
					new ShootGameView(login).setVisible(true);
					LoginView.this.dispose();  // 关闭当前窗口
				} else {  //验证不成功
					JOptionPane.showMessageDialog(LoginView.this, "账号或密码输入错误！") ;
					return;
				}

			}
		});
		loginButton.setPreferredSize(new Dimension(80, 30));
		loginButton.setBackground(new Color(0x71B8EC));

		//注册按钮
		JButton regist = new JButton("注册");
		regist.setBounds(125, 160, 81, 30);
		myPanel.add(regist);
		regist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new RegistView().setVisible(true);
				LoginView.this.dispose();
			}
		});
		regist.setPreferredSize(new Dimension(80, 30));
		regist.setBackground(new Color(0x71B8EC));

		//找回密码按钮
		JButton updatePass = new JButton("找回密码");
		updatePass.setBounds(217, 160, 90, 30);
		myPanel.add(updatePass);
		updatePass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdatePassView().setVisible(true);
				LoginView.this.dispose();
			}
		});
		updatePass.setPreferredSize(new Dimension(80, 30));
		updatePass.setBackground(new Color(90, 177, 234));


		/**
		 * 查看密码按钮
		 */
		JButton scores = new JButton("查看排行榜");
		scores.setBounds(110, 200, 120, 30) ;
		myPanel.add(scores);
		scores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ScoreListView(login).setVisible(true);
				LoginView.this.dispose();
			}
		});
		scores.setPreferredSize(new Dimension(80, 30));
		scores.setBackground(new Color(127, 197, 245));


		jPanel.add(myPanel) ;
		return jPanel;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
