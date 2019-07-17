Mybatis接口绑定
==============
1、mybatis.xml中namespace必须和接口全限定路径（包名+类名）一致<br>
2、id值必须和接口的方法名相同<br>
3、如果接口中方法为多个参数可以省略parameterType<br>
4、多参数实现：
```java
List<Log> findAcc(@Param("accout") int accout, @Param("accin") int accin);
```
>>mybatis把参数转换为map，其中@Param（“key”）参数内容就是map中的value<br>


动态SQL
======
1、根据不同的条件执行不同的SQL命令<br>
2、mybatis中动态SQL<br>
>>2.1、if语句<br>
```xml
<select id="findAccoutAccin" resultType="Log">
        select * from log where 1=1
        <!--ognl表达式，直接写key或对象的属性,不需要添加任何特殊符号-->
        <if test="accout !=null and accout != ''">
            and accout=#{accout}
        </if>
        <if test="accin != null and accin != ''">
            and accin=#{accin}
        </if>
    </select>
```
>>2.2、where语句：当编写where标签时如果内容第一个是and会去掉第一个and，如果<where>中有内容生成where关键字，如果没有内容不生成关键字<br>
```xml
 <select id="findwhen" resultType="Log">
        select * from log
        <where>
            <choose>
                <when test="accout !=null and accout != ''">
                    and accout=#{accout}
                </when>
                <when test="accin != null and accin != ''">
                    and accin=#{accin}
                </when>
            </choose>
        </where>
    </select>
```
>>2.3、set语句:去掉最后一个逗号,如果set里面有内容生成set关键字，如果没有就不生成,id=#{id}是防止set中没有内容mybatis不生成关键字
```xml
<update id="update" parameterType="Log">
        update log
            <set>
                <if test="accOut != null and accOut != ''">
                    accout=#{accOut},
                </if>
                <if test="accIn != null and accIn != ''">
                    accin=#{accIn}
                </if>
            </set>
        where id=#{id}
    </update>
```
>>2.4、trim语句: