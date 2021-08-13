package com.jaycao.datasource.jdbctemplate;

import com.jaycao.datasource.domain.Emp;
import com.jaycao.datasource.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: JDBCTemplateDemo2
 * @Description:
 * @Author: CAO JAY
 * @Date: 2021/8/14 4:34
 */
public class JDBCTemplateDemo2 {
    //使用Junit单元测试，可以让方法独立运行

    /*
    修改1号数据的 salary 为 10000
     */
    @Test
    public void test1(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update emp set salary = 10000 where id =1001";
        int count = template.update(sql);
        System.out.println(count);
    }
    /*
    2. 添加一条记录
     */
    @Test
    public void test2(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "insert into emp(id,ename,dept_id) values(?,?,?)";
        template.update(sql,1015,"曹杰",10);
    }
    /*
    3.删除刚才添加的记录
     */
    @Test
    public void test3(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "delete from emp where id = 1015";
        template.update(sql);
    }
    /*
    4.查询id为1的记录，将其封装为Map集合
     */
    @Test
    public void test4(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from emp where id =? ";
        Map<String, Object> map = template.queryForMap(sql, 1001);
        System.out.println(map);
        //{id=1001, ename=孙悟空, job_id=4, mgr=1004, joindate=2000-12-17, salary=10000.00, bonus=null, dept_id=20}
    }
    /*
    5.查询所有记录，将其封装为List
     */
    @Test
    public void test5(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from emp  ";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
//        {id=1001, ename=孙悟空, job_id=4, mgr=1004, joindate=2000-12-17, salary=10000.00, bonus=null, dept_id=20}
//        {id=1002, ename=卢俊义, job_id=3, mgr=1006, joindate=2001-02-20, salary=16000.00, bonus=3000.00, dept_id=30}
//        {id=1003, ename=林冲, job_id=3, mgr=1006, joindate=2001-02-22, salary=12500.00, bonus=5000.00, dept_id=30}
//        {id=1004, ename=唐僧, job_id=2, mgr=1009, joindate=2001-04-02, salary=29750.00, bonus=null, dept_id=20}
//        {id=1005, ename=李逵, job_id=4, mgr=1006, joindate=2001-09-28, salary=12500.00, bonus=14000.00, dept_id=30}
//        {id=1006, ename=宋江, job_id=2, mgr=1009, joindate=2001-05-01, salary=28500.00, bonus=null, dept_id=30}
//        {id=1007, ename=刘备, job_id=2, mgr=1009, joindate=2001-09-01, salary=24500.00, bonus=null, dept_id=10}
//        {id=1008, ename=猪八戒, job_id=4, mgr=1004, joindate=2007-04-19, salary=30000.00, bonus=null, dept_id=20}
//        {id=1009, ename=罗贯中, job_id=1, mgr=null, joindate=2001-11-17, salary=50000.00, bonus=null, dept_id=10}
//        {id=1010, ename=吴用, job_id=3, mgr=1006, joindate=2001-09-08, salary=15000.00, bonus=0.00, dept_id=30}
//        {id=1011, ename=沙僧, job_id=4, mgr=1004, joindate=2007-05-23, salary=11000.00, bonus=null, dept_id=20}
//        {id=1012, ename=李逵, job_id=4, mgr=1006, joindate=2001-12-03, salary=9500.00, bonus=null, dept_id=30}
//        {id=1013, ename=小白龙, job_id=4, mgr=1004, joindate=2001-12-03, salary=30000.00, bonus=null, dept_id=20}
//        {id=1014, ename=关羽, job_id=4, mgr=1007, joindate=2002-01-23, salary=13000.00, bonus=null, dept_id=10}
//
//        Process finished with exit code 0

    }
    @Test
    public void test6(){
        String sql ="select *from emp";
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp : list) {
            System.out.println(emp);
        }
//        List<Emp> list = template.query(sql, new RowMapper<Emp>() {
//
//            @Override
//            public Emp mapRow(ResultSet rs, int i) throws SQLException {
//                Emp emp = new Emp();
//                int id = rs.getInt("id");
//                String ename = rs.getString("ename");
//                int job_id = rs.getInt("job_id");
//                int mgr = rs.getInt("mgr");
//                Date joindate = rs.getDate("joindate");
//                double salary = rs.getDouble("salary");
//                double bonus = rs.getDouble("bonus");
//                int dept_id = rs.getInt("dept_id");
//                emp.setId(id);
//                emp.setBonus(bonus);
//                emp.setEname(ename);
//                emp.setDept_id(dept_id);
//                emp.setSalary(salary);
//                emp.setJoindate(joindate);
//                emp.setMgr(mgr);
//                emp.setJob_id(job_id);
//                return emp;
//            }
//        });
//        for (Emp emp : list) {
//            System.out.println(emp);
//        }
    }
    /*
    7.查询总记录数
     */
    @Test
    public void test7(){
        String sql ="select count(id) from emp";
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        Integer total = template.queryForObject(sql, Integer.class);
        System.out.println(total);
    }}
