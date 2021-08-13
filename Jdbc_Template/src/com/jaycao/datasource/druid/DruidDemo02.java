package com.jaycao.datasource.druid;

import com.jaycao.datasource.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName: DruidDemo02
 * @Description:使用新的jdbc工具类
 * @Author: CAO JAY
 * @Date: 2021/8/14 4:09
 */
public class DruidDemo02 {
    public static void main(String[] args) {
        /*
        完成添加操作，给account添加一条记录
         */
        Connection con = null;
        PreparedStatement pstmt = null;
        //1.获取连接
        try {
           con = JDBCUtils.getConnection();
            //定义sql
            String sql = "insert into account values(null,?,?)";
            //3.获取pstmt对象
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,"曹杰");
            pstmt.setDouble(2,3000);
            int count = pstmt.executeUpdate();
            System.out.println(count);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,con);
        }
    }
}
