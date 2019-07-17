package com.liyan.test;

import com.liyan.mapper.LogMapper;
import com.liyan.pojo.Log;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


public class LogTest {
    @Test
    public void Find() throws Exception {
        //使用工厂设计模式
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();
        /*
        * 接口实例化
        * 需要给一个接口实例化对象
        * 使用JDK的动态代理模式
        * 面向接口的代理设计模式（必须有接口）
        * */
//        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
//        List<Log> list=logMapper.findAll();
//       for (Log log:list){
//           System.out.println(log);
//       }
        LogMapper logMapper=sqlSession.getMapper(LogMapper.class);
        List<Log> log=logMapper.findAcc(100010,100011);
        System.out.println(log);

    }
}
