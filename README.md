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
>>2.3、set语句:去掉最后一个逗号,如果set里面有内容生成set关键字，如果没有就不生成,id=#{id}是防止set中没有内容mybatis不生成关键字<br>
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
>>2.4、trim语句:prefix:在前面添加内容、prefixOverrides在前面删除内容、suffix在后面添加内容、suffixOverrides在后面删除内容<br>
```xml
<select id="findtrim" resultType="Log" parameterType="Log">
        select * from log
        <trim prefix="where" prefixOverrides="and">
            and accout=#{accOut}
        </trim>
    </select>
<update id="updatetrim" parameterType="Log">
        update log
            <trim prefix="set" suffixOverrides=",">
                <if test="accOut != null and accOut != ''">
                    accout=#{accOut},
                </if>
                <if test="accIn != null and accIn != ''">
                    accin=#{accIn},
                </if>
            </trim>
        where id=#{id}
    </update>
```
>>2.5、bind语句：给参数重新赋值<br>
```xml
 <select id="findbind" resultType="Log" parameterType="Log">
        <bind name="money" value="'$'+money"/>
        <bind name="accin" value="'%'+accin+'%'"/>
    </select>
```
>>2.6、foreach语句：循环参数内容，还具备在内容前后添加内容，添加分隔符功能，适用in查询、批量新增中（mybatis中foreach的效率比较低）<br>
>>collection:要遍历的集合，item：迭代遍历、#{迭代变量名}获取内容，open：循环后左侧添加的内容，close：循环后右边添加的内容，separator：循环是的分隔符<br>
```xml
 <select id="findIn" parameterType="list" resultType="Log">
      select * from log where id in
      <foreach collection="list" item="abc" open="(" close=")" separator=",">
          #{abc}
      </foreach>
    </select>
```
>>2.7、sql和include语句：适用于多表联合查询，提高代码的复用性<br>
```xml
  <sql id="mysql">
        id,accout,accin,money
    </sql>
  <select id="findinclude" parameterType="log" resultType="Log">
        select
        <include refid="mysql"></include>
        from log
    </select>
```