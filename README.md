# app interface 开发文档

## 项目说明
   项目由，并由他进行数据库设计
   项目参考同事发布的项目，但他最近比较懒，有段时间没有更新（https://github.com/luke4j/eshop）
   项目没有沿用参考项目中，每个功能一个文件夹

## 技术选型
     java
       spring springmvc hibernate

## 环境
     IntelliJ IDEA，maven3.5.2 ，JDK 1.8
     
## 目录
         ├─src
         │  ├─main                            程序代码目录
         │  │  ├─java                        java代码目录
         │  │  ├─resources                   配置文件目录
         │  │  └─webapp                      html,js代码目录
         │  │      └─WEB-INF
         │  └─test                            测试代码目录
         │      ├─java                        java测试代码目录
         │      └─resources                   测试配置文件目录   
         
### java目录中的包
```
   前缀cn.com.app
   api                            生成文档的配置类
   assistcode                     类似存储页面信息以及返回结果信息的类
   common                         公共方法
   enums                          存储常量的枚举
   form                           表单对象
   main                           程序主体，包含dao，service，controller
   others                         其他类
```

## 项目中出现的问题
   + 项目启动后，没有报错，日志有显示创建表语句，但查看数据库发现表并没有创建   ---- 由于生成创建表的语句中出现name和explain列名，这两个单词在mysql中是关键字
   + 通过getDeclaredFields获取实体类属性的时候，不能获取父类中的id属性，导致缺少id程序异常 另外getFields()似乎不能获取属性值
   + BaseDao中hql参数那块 where id =:id 其中冒号后不可以有空格
   + 由于从jsonObject取出的值都是以String类型呈现 导致执行hibernate Query传参中 参数转换异常 ----  类似这样的代码可以解决问题 map.put("id",Long.parseLong(jsonObjectbefore.get("id").toString()));
   
## Git使用出现的问题
   + 项目地址 git@github.com:MoXi2017/zcsAppInterface.git
   + ssh-keygen 生成id_rsa.pub文件 ---- https://blog.csdn.net/a419419/article/details/80021684
   + 将本地资源上传到远程仓库 ---- https://www.cnblogs.com/eoooxy/p/6075625.html
