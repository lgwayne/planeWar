package com.wayne.airplane.service;

import com.wayne.airplane.entity.User;

import java.util.List;

public interface IUserService {
	/**
	 * 注册用户
	 * @param nickName
	 * @param password
	 * @return
	 */
	int insertUser(String nickName,String password);

	/**
	 * 修改密码
	 * @param nickName
	 * @param password
	 * @return
	 */

	 int updatePassword(String nickName, String password);

	/**
	 * 修改得分
	 * @param nickName
	 * @param score
	 * @return
	 */
	int updateScore(String nickName, int score);

	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> selectAll();  // 查询所有用户

	/**
	 * 根据昵称查询指定用户
	 * @param nickName
	 * @return
	 */
	User selectUserByNick(String nickName);

	/**
	 * 登录验证
	 * @param nickName
	 * @param password
	 * @return
	 */
	User selectUser(String nickName, String password);
}
