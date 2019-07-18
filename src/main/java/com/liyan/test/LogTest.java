package com.liyan.test;

import com.liyan.mapper.LogMapper;
import com.liyan.pojo.Log;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
        List<Log> log = logMapper.findAcc(100010, 100011);
        System.out.println(log);

    }

    @Test
    public void Sql() throws Exception {
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();

        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
        List<Log> logs = logMapper.findwhen("100010", "100011");
        System.out.println(logs);
    }

    @Test
    public void update() throws Exception {
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();

        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
        Log log = new Log();
        log.setId(1);
        log.setAccOut("100011");
        log.setAccIn("100010");
        int index = logMapper.update(log);
        System.out.println(index);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void trim() throws Exception {
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();

        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
        Log log = new Log();
        log.setAccOut("100011");
        List<Log> logs = logMapper.findtrim(log);
        for (Log log1 : logs) {
            System.out.println(log1);
        }
        sqlSession.close();
    }

    @Test
    public void updatetrim() throws Exception {
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();

        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
        Log log = new Log();
        log.setId(1);
        log.setAccOut("100010");
        log.setAccIn("100011");
        int index = logMapper.updatetrim(log);
        System.out.println(index);
        sqlSession.commit();
        sqlSession.close();

    }


    @Test
    public void findIn() throws Exception {
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();

        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
        List<Integer> logs = new ArrayList<>();
        logs.add(100010);
        logs.add(100011);
        logs.add(100012);
        List<Log> list = logMapper.findIn(logs);
        for (Log log:list){
            System.out.println(log);
        }
        sqlSession.commit();
        sqlSession.close();

    }

}
