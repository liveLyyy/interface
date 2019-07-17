package com.liyan.mapper;

import com.liyan.pojo.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {
    List<Log> findAll();

    /*
    * mybatis把参数转换为map，其中@Param（“key”）参数内容就是map中的value*/

    List<Log> findAcc(@Param("accout") int accout, @Param("accin") int accin);
}
