package com.wayne.airplane.dao;

import com.wayne.airplane.entity.User;
import com.wayne.airplane.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class UserDaoImpl implements IUserDao {
    private static final String READ_ALL ="SELECT USERID,NICKNAME,PASSWORD,REGISTTIME,CREATETIME,SCORE FROM PLANEUSER";

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<User> readAll()
    {
        List<User> list = new ArrayList<>();

        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;

        conn=DBUtils.getConnection();
        try {
            stmt=conn.prepareStatement(READ_ALL);
            rs=stmt.executeQuery();

            while (rs.next()){
                User user = new User();
                user.setUserId(rs.getString("USERID"));
                user.setNickName(rs.getString("NICKNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setRegistTime(new Date(rs.getTimestamp("REGISTTIME").getTime()));
                user.setCreateShoreRecordTime(new Date(rs.getTimestamp("CREATETIME").getTime()));
                user.setShore(rs.getInt("SCORE"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.release(conn,stmt,rs);
        }
        return  list;

    }

    /**
     * 给用户赋值id信息
     * @return
     */
    @Override
    public String getNewID() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        char[] numbers = new char[]
                {'0', '1', '2', '3', '4', '5',
                        '6', '7', '8', '9', 'a', 'b',
                        'c', 'd', 'e', 'f', 'g', 'h',
                        'i', 'j', 'k', 'l', 'm', 'n',
                        'o', 'p', 'q', 'r', 's', 't',
                        'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < numbers.length; i++) {
            sb.append(numbers[r.nextInt(numbers.length)]);
        }
        return sb.toString();
    }

}
