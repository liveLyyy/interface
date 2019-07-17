Mybatis接口绑定
==============
1、mybatis.xml中namespace必须和接口全限定路径（包名+类名）一致<br>
2、id值必须和接口的方法名相同<br>
3、如果接口中方法为多个参数可以省略parameterType<br>
4、多参数实现：
```java
List<Log> findAcc(@Param("accout") int accout, @Param("accin") int accin);
```
>>mybatis把参数转换为map，其中@Param（“key”）参数内容就是map中的value
