package com.wayne.airplane.service;

import com.wayne.airplane.dao.IUserDao;
import com.wayne.airplane.dao.UserDaoImpl;
import com.wayne.airplane.entity.User;
import com.wayne.airplane.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements IUserService {

    private IUserDao dao = new UserDaoImpl();

    /**
     * 注册用户
     *
     * @param nickName
     * @param passowrd
     * @return
     */
    @Override
    public int insertUser(String nickName, String passowrd) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows_insert = 0;

        //查看昵称有没重复
        List<User> list = dao.readAll();
        for (User user : list) {
            if (user.getNickName().equals(nickName)) {
                return 0;
            }
        }
        conn = DBUtils.getConnection();
        try {
//			stmt=conn.prepareStatement("insert into planeuser(userid,nickname,password,registtime,createtime,score) values('8888','bruce','123456','01-1月-2001','11-2月-2009',100000);");
            stmt = conn.prepareStatement("insert into planeuser(userid,nickname,password,registtime,createtime,score) values(?,?,?,?,?,?)");
            stmt.setString(1, dao.getNewID());
            System.out.println(dao.getNewID());
            stmt.setString(2, nickName);
            stmt.setString(3, passowrd);
            stmt.setDate(4, new java.sql.Date(new Date().getTime()));
            stmt.setDate(5, new java.sql.Date(new Date().getTime()));
            stmt.setInt(6, 10000);

            rows_insert = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, stmt, rs);
        }

        return rows_insert;
    }


    /**
     * 修改用户密码
     *
     * @param nickName
     * @param password
     * @return
     */
    @Override
    public int updatePassword(String nickName, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows_insert = 0;

        User user = selectUserByNick(nickName);
        System.out.println(user);
        if (user == null) {
            return 0;//如果不存在这样的用户，返回0；
        } else {
            conn = DBUtils.getConnection();
            try {
                stmt = conn.prepareStatement("update planeuser set password=? where nickname=?");
                stmt.setString(1, password);
                stmt.setString(2, nickName);

                rows_insert = stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtils.release(conn, stmt, rs);
            }
            return rows_insert;
        }
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<User> selectAll() {
        return dao.readAll();
    }

    /**
     * 修改用户得分
     *
     * @param nickName
     * @param score
     * @return
     */
    @Override
    public int updateScore(String nickName, int score) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int row=0;
        conn = DBUtils.getConnection();
        try {
            stmt = conn.prepareStatement("update planeuser set score=?,createtime=? where nickname=?");
            stmt.setInt(1, score);
            stmt.setDate(2, new java.sql.Date(new Date().getTime()));
            stmt.setString(3, nickName);
            row=stmt.executeUpdate();
            if (row>0){
                System.out.println("更新得分成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, stmt, rs);
        }
        return row;
    }

    /**
     * 登录验证
     *
     * @param nickName
     * @param password
     * @return
     */
    @Override
    public User selectUser(String nickName, String password) {
        User loginUser = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        conn = DBUtils.getConnection();
        try {
            stmt = conn.prepareStatement("select * from planeuser where nickname=? and password=?");
            stmt.setString(1, nickName);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            if (rs.next()) {
                loginUser = new User();
                loginUser.setNickName(rs.getString("NICKNAME"));
                loginUser.setPassword(rs.getString("PASSWORD"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, stmt, rs);
        }
        return loginUser;
    }


    /**
     * 根据昵称查找用户
     *
     * @param nickName
     * @return
     */
    @Override
    public User selectUserByNick(String nickName) {
        User user = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        conn = DBUtils.getConnection();
        try {
            stmt = conn.prepareStatement("SELECT * from PLANEUSER WHERE NICKNAME=?");
            stmt.setString(1, nickName);

            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setNickName(rs.getString("NICKNAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.release(conn, stmt, rs);
        }
        return user;
    }
}
