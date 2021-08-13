package com.jaycao.datasource.jdbctemplate;


import org.springframework.jdbc.core.JdbcTemplate;


import com.jaycao.datasource.utils.JDBCUtils;

import javax.sql.DataSource;

/**
 * @ClassName: JdbcTemplate
 * @Description:
 * @Author: CAO JAY
 * @Date: 2021/8/14 4:23
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //导入包,add libraries
        //创建JdbcTemaplate对象。依赖于DataSource
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update account set balance = 123 where id =?";
        int count = template.update(sql, 3);
        System.out.println(count);

    }
}
