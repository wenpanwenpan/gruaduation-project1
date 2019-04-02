# gruaduation-project1
2019年毕业项目（二手交易平台）
# 项目介绍

- 该项目是作为本人2019年六月毕业设计而编写
- 首先该项目主题是二手交易系统，在该程序中可以实现两种角色权限进行登录
  - 用户权限登录：使用用户权限登录时可以进行购物、将喜欢的商品加入自己的购物车、购物后进行结账、直接购买、出售自己的二手商品、修改个人信息、管理自己出售的商品（取消出售、修改出售信息）、查看关于系统介绍、查看使用大数据分析后的今日排行结果。
  - 管理员权限登录：使用管理员权限进行登录后可以进行测试商品购买、加入购物车、立即购买等操作、也可以查看使用大数据分析后的今日排行结果、同时可以进行管理员工、管理所有商品以及查看某一员工出售的所有商品。
  - 登录界面做了国际化操作和注册功能。
- 该项目所使用的技术栈：
  - 框架：SSM、springboot
  - 数据库：MySQL
  - 前端框架：Bootstrap快速搭	搭建html页面     thymeleaf模板引擎    echarts显示
  - 项目管理：MAVEN
  - 开发工具：Intellij IDEA，centos7
  - 开发环境：Windows
  - 版本控制：Git
  - 大数据技术：flume、kafka、MapReduce、HBASE等
- 项目详情请见：[github项目链接](https://github.com/wenpanwenpan/gruaduation-project1)



## 一、准备

准备部分包括数据库建表、项目整体框架搭建和启动。

### 1.数据库建表

#### 1.*本项目一共使用了5张表分别是：*

1. admin:用于保存管理员账号信息
2. commodity：用于保存商品的基本属性
3. commoditype：用于保存商品的类型
4. customer：用于保存用户的相关信息
5. analysis：用于保存经过大数据分析后的结果信息，表中数据主要是用于图表显示 (暂未涉及)

#### 2.表的sql：

1. admin表：

```mysql
-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `uname` varchar(255) NOT NULL,
  `uid` int(8) NOT NULL AUTO_INCREMENT,
  `tel` varchar(11) DEFAULT NULL,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `sex` int(2) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('wenpanfeng', '1', '13548112164', 'wenpanfeng', '123', '四川成都双流', '610039', '1', '23');

```

2. commodity表：

```mysql
-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `name` varchar(255) NOT NULL,
  `price` double(10,2) NOT NULL,
  `pid` int(8) NOT NULL AUTO_INCREMENT,
  `note` varchar(255) DEFAULT NULL,
  `count` int(8) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `tid` int(8) DEFAULT NULL,
  `author_id` int(8) DEFAULT NULL,
  `date` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('二手联想笔记本', '2999.90', '3', '7层新，i5+15g内存', '1', null, '2', '2', null);
INSERT INTO `commodity` VALUES ('二手球鞋', '1999.90', '4', '9层新AJ', '3', 'shoe.png', '5', '2', null);
INSERT INTO `commodity` VALUES ('二手摩托车', '5999.00', '5', '日本哈雷摩托', '1', 'motuo.jpg', '4', '2', null);
INSERT INTO `commodity` VALUES ('二手java书籍', '36.00', '6', '出售一本java开发实战', '5', null, '1', '2', null);
INSERT INTO `commodity` VALUES ('二手华为手机', '900.00', '7', '二手华为荣耀10一台', '3', 'rongyao10.jpg', '6', '2', null);
INSERT INTO `commodity` VALUES ('二手hadoop权威指南', '60.00', '8', '正品hadoop权威指南第四版', '1', 'hadoopbook.jpg', '1', '1', null);
INSERT INTO `commodity` VALUES ('保温杯', '160.00', '10', '适合泡枸杞的保温杯', '1', '19216800100120190224150813047408.jpg', '6', '1', null);
INSERT INTO `commodity` VALUES ('二手oracle从入门到精通', '50.00', '11', 'oracle入门必读书籍', '3', '19216800100120190224153214588147.jpg', '1', '1', null);
INSERT INTO `commodity` VALUES ('黑客攻防从入门到精通', '69.90', '12', '黑客攻防从入门到入狱书籍', '5', '19216800100120190224153959820309.jpg', '1', '1', null);
INSERT INTO `commodity` VALUES ('文攀锋', '99.90', '17', 'good person', '5', '19216800100120190312103929271404.jpg', null, '1', null);
INSERT INTO `commodity` VALUES ('二手自行车', '600.00', '18', '九层新二手自行车', '2', '19216800100120190312104014882017.jpg', null, '1', '2019-02-17 12:44:26.050');
INSERT INTO `commodity` VALUES ('足力健老人鞋', '299.00', '19', '全新足力健老人鞋', '2', '19216800100120190312104117774598.jpg', null, '1', '2018-03-17 12:44:26.050');
INSERT INTO `commodity` VALUES ('测试上传商品时间', '2.00', '24', '测试', '2', null, null, '1', '2019-03-17 12:44:26.050');
INSERT INTO `commodity` VALUES ('刘挺测试出售', '9000.00', '26', '测试上传', '11', null, null, '4', '2019-03-18 20:15:11.360');
INSERT INTO `commodity` VALUES ('刘挺测试出售', '9000.00', '27', '测试上传', '11', null, null, '4', '2019-03-18 20:15:23.818');
INSERT INTO `commodity` VALUES ('测试上传2', '78.00', '28', '测试上传2', '2', null, null, '4', '2019-03-18 20:17:37.182');

```

3.commodityType表：

```mysql
-- ----------------------------
-- Table structure for commoditytype
-- ----------------------------
DROP TABLE IF EXISTS `commoditytype`;
CREATE TABLE `commoditytype` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commoditytype
-- ----------------------------
INSERT INTO `commoditytype` VALUES ('1', '书籍类');
INSERT INTO `commoditytype` VALUES ('2', '家电类');
INSERT INTO `commoditytype` VALUES ('3', '食品类');
INSERT INTO `commoditytype` VALUES ('4', '交通工具类');
INSERT INTO `commoditytype` VALUES ('5', '服饰类');
INSERT INTO `commoditytype` VALUES ('6', '生活日用品类');

```
4.customer表：

```mysql
-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `uid` int(8) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) NOT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `commodities` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '文攀', '13548112146', 'wenpan', 'wenpan', '四川省成都市', '610079', '8_10_11_12_17_18_19_24');
INSERT INTO `customer` VALUES ('2', '张三', '15202869691', '张三', '123456', '北京市朝阳区', '210052', '3_4_5_6_7');
INSERT INTO `customer` VALUES ('4', '刘挺', '19952589631', 'liuting', 'liuting', '四川郫县红光镇', '610039', '26_27_28');

```



## 2.项目搭建

1. 由于本次项目是使用的springboot快速开发，所以相对的SSM配置文件较少，但还是用到一些但是是使用配置类来代替的配置文件(详见：com.wp.demo.config.MyMvcConfig类)
2. 本次项目使用的是MAVEN进行JAR包的管理，首先导入项目中可能用到的依赖包见 pom.xml
3. Spring容器配置文件：applicationContext.xml：见 resources/applicationContext.xml
4. springboot 配置：application.propertis：见resources/application.properties和application.yml文件



:happy:项目介绍完毕！

## 3.项目展示

### 用户登录显示

#### 1.用户登录界面
![1552984827277](https://github.com/wenpanwenpan/gruaduation-project1/blob/dev/images/userlogin.png)

#### 2.管理员登录界面展示

![1552984827277](https://github.com/wenpanwenpan/gruaduation-project1/blob/dev/images/adminlogin.png)

#### 3.用户购物界面展示

![1552984964231](https://github.com/wenpanwenpan/gruaduation-project1/blob/dev/images/shopping-main.png)

#### 4.用户加入购物车展示

![1552985040655](https://github.com/wenpanwenpan/gruaduation-project1/blob/dev/images/myshoppingcar.png)

#### 5.展示今日排行

![1552985101900](https://github.com/wenpanwenpan/gruaduation-project1/blob/dev/images/anasisly1.png)

### 管理员登录

#### 1.管理员管理所有商品展示

![1552985193673](https://github.com/wenpanwenpan/gruaduation-project1/blob/dev/images/adminmanagecommodity.png)

#### 2.管理员管理用户展示

![1552985232386](https://github.com/wenpanwenpan/gruaduation-project1/blob/dev/images/adminmanageuser.png)

#### 3.重置用户密码

![1552985300186](https://github.com/wenpanwenpan/gruaduation-project1/blob/dev/images/resetpassword.png)

#### 4.用户分布位置图

![1554129699087](https://github.com/wenpanwenpan/gruaduation-project1/blob/dev/images/anasisly2.png)



### 页面介绍

- 由于springboot支持的默认是thymeleaf模板引擎，所以本项目使用的是thymeleaf模板引擎进行编写，而不再是jsp进行编写。

- 主要的HTML页面放在resources/templates下。将可以重复利用的页面（包括导航栏，侧边栏）抽取出来放在bar.html文件里。在需要使用导航栏和侧边栏的地方直接进行引入。

  ![1552986892988](https://github.com/wenpanwenpan/gruaduation-project1/blob/dev/images/programintroduction.png)

### 分页条设计

**这里使用pageHelper来做分页！在springboot中使用pageHelper来实现分页就很容易了！**

1. 在pom.xml配置文件中引入pageHelper的依赖

   ```xml
   		<dependency>
   			<groupId>com.github.pagehelper</groupId>
   			<artifactId>pagehelper-spring-boot-starter</artifactId>
   			<version>1.2.5</version>
   		</dependency>
   ```

2. 在控制层controller中仅需要将你查询出来的要进行分页的商品集合，通过new pageInfo（all,5）类的时候通过构造函数参数的形式传入即可。

   ```java
   //分页显示商品
       @GetMapping("/shopping/goshoppingbypage")
       public String getCommoditiesByPage(@RequestParam(defaultValue = "1") int pageNo,
                                          @RequestParam(defaultValue = "6") int pageSize,
                                          Model model,
                                          HttpSession session) throws Exception {
           PageHelper.startPage(pageNo, pageSize,true);
           Page<Commodity> all = productService.findAll();
   
           //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
           //封装了详细的分页信息，包括我们查询出来的数据,传入连续显示的页数eg:5  表示连续显示5页
           PageInfo<Commodity> page = new PageInfo<>(all,5);
           model.addAttribute("pageInfo",page);
           if(session.getAttribute("adminLoginUser") != null){
               return "/adminPage/myshopping";
           }
           return "/shopping/myshopping";
       }
   ```

3. 在service服务层中所调用的查询方法的返回值也应该是Page<Commodity>类型

   ```java
   public Page<Commodity> findAll() throws Exception {
           //直接调用mapper中的方法，进行操作
           Page<Commodity> allCommodity = productMapper.getAllCommodity();
   
           return allCommodity;
       }
   ```

   

4. 在mapper层也数据库查询后的返回值也应该是Page<Commodity>类型

   ```java
   //查询出所有的商品，并且保证该商品的数量不等于0
       @Select("select * from commodity where count != 0")
       public Page<Commodity> getAllCommodity();
   ```

5. 最后在页面取出pageInfo中保存，通过pageInfo的list属性来取出集合

   ```html
   <!--取出保存在pageInfo中的商品集合-->
               <div class="col-md-4" th:each="commodity:${pageInfo.list}">
                 <div class="card mb-4 shadow-sm">
   
                   <!--使用th标签的src属性拼接-->
                   <span th:if="${commodity.photo != null }">
                     <img th:src="@{'/images/'+${commodity.photo}}"/>
                   </span>
                  </div>
                </div>
   ```

   

   ### 大数据分析模块介绍

   #### 1.bigdataanasisly模块介绍

   该大数据分析模块主要抽取在一个名为bigdataanasisly的模块中进行编写实现，该模块主要是将用户购买商品后在指定的文件中输出购买商品的日志文件信息进行数据分析，然后将分析结果写入到mysql数据库中，以便在web模块中将分析后的结果取出使用echarts图进行可视化显示。

   :hand:*注：由于本次开发idea是搭建在Windows环境中的，由于物理机的配置原因也不支持同时开启IDEA和由三台虚拟机搭建的hadoop集群，所以将使用手动的将物理机Windows日志文件上传到虚拟机中指定的目录下的方式进行数据分析！( :happy:简单来说：即日志文件是输出到Windows上面的某一个目录中的，我们需要手动的将该目录中的日志文件导入到我们的虚拟机中，然后在虚拟机中执行打包好的分析jar包进行分析，将分析结果保存到MySQL数据库中)*

   #### 2.功能流程介绍

   1. 顾客购买操作或者购买商品时，程序会自动的将购买商品的日志信息输出到windows下的指定目录中。我们将该日志信息手动的导入到linux虚拟机中的某个指定目录下！

   2. 注：:hammer:*:hammer:本次模块采用MapReduce进行数据分析*​

      正常分析流程应该是使用flume做文件目录的监控，将监控的文件信息写入到kafka的某一个topic中，然后使用自己编写的消费者将该topic中的数据消费到HBASE中或者HDFS中，然后再由我们自己编写的MapReduce程序进行数据分析，将分析结果写入MySQL数据库中。**但是由于我这里只是做了一个简单的MapReduce数据分析，所以就不采用上述的复杂操作，仅仅是编写一个执行jar包的脚本文件，然后开启一个定时任务，每天晚上12:00开始进行数据分析，然后清空以前的数据表，将分析结果写入！**

   

   ### 用户操作顺序图：

   ```sequence
   title:用户操作流程：
   用户操作->上传商品:发布
   用户操作->购买商品:购物
   用户操作->管理出售:管理出售商品
   用户操作->查看排行:排行
   用户操作->修改个人信息:修改个人信息
   用户操作->搜索商品:搜索商品
   用户操作->关于我们:关于我们
   购买商品->加入购物车:加入购物车
   购买商品->立即购买:立即购买
   购买商品->到订单结账:结账
   管理商品->修改商品:修改商品
   管理出售->删除商品:删除商品
   用户操作->用户登录:登录
   用户操作->用户注册:用户注册
   Note left of 用户操作:顺序图
   ```

   

   ### 管理员操作顺序图：

   ```sequence
   管理员->登录注册:注册登录
   管理员->模拟购物:购物
   模拟购物->立即购买:立即购买
   模拟购物->加入购物车:加入购物车
   管理员->查看购物车:查看购物车
   查看购物车->购物车结账:结账
   管理员->查看订单:订单查看
   管理员->搜索商品:检索商品
   管理员->查看今日排行:今日排行
   管理员->管理用户:用户管理
   管理用户->增删改查:管理
   管理商品->增删改查:管理
   管理员->管理商品:商品管理
   管理员->查看首页:首页查看
   
   ```

   

   

   

   

   
