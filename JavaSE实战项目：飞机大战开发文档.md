

# JavaSE实战项目：飞机大战开发文档

### 一，项目演示

### 二，需求分析

#### 2.1体系结构

​		根据微信小游戏“飞机大战”进行开发

#### 2.2系统流程

​				<img src="C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568881617113.png" alt="1568881617113" style="zoom:150%;" />

#### 2.3功能描述

对于本款游戏，用户用鼠标操作英雄机对敌机进行子弹打击，同时需要对飞来的敌机进行躲避；根据每打击到相应的敌机，或者击毁相应的敌机都有相应的积分增加，最后根据得分的高低进行排名。

主要分用户和游戏进行开发。

​		对于用户，有注册功能，可以注册账号开始游戏；有找回密码（更新密码）的功能，可以根据nickname改密码；还有根据游戏的得分进行排名等功能。

​		对于游戏界面就是根据飞机大战的游戏规则，针对 英雄机，大中小三种敌机等进行开发。

#### 2.4 软件页面

登录页面：

<br/><img src="C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568882492067.png" alt="1568882492067" style="zoom:80%;" />

游戏页面：<br/><img src="C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568882330853.png" alt="1568882330853" style="zoom:80%;" />



排行榜页面<br><img src="C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568882365740.png" alt="1568882365740" style="zoom:80%;" />

### 三，概要设计

#### 3.1，运行环境

​	Windows+Oracle+(Intellij Idea)

#### 3.2，游戏基本功能

​	用户：可以注册，登录，修改密码，得分等

​	英雄机：可以提升生命值，可以获得两倍火力；

​					击毁敌机积分+1000；

​					击中中型敌机积分+200，击毁敌机积分+2000；

​					击中大型敌机积分+300，击毁敌机积分+3000；

​	多种敌机之间的出现及出现的频率；

​	子弹的控制；

​	飞行物体和子弹的生命周期控制等；

- #### 3.2.1，游戏的难点分析

  - **动画的实现。**英雄机的飞行效果和敌机的爆炸效果，还有子弹的发射，可以利用图片数组切换，加上Java的timer.schedule()设置较短的时间差，从而实现电影中帧的概念，实现动画效果。
  - **解决子弹碰撞问题。**需要综合数学知识设置图片的与子弹的半径，解决碰撞问题。
  - **窗口的美化。**包括字体和背景图。

- #### 3.2.2，游戏整体的设计思路

  - 0.把一个游戏近似一个web应用，**前端**是窗口设计及通过重写Component的paint方法可以将写好的组件渲染到窗口；**后台**是对应组件的逻辑处理，与组件的行为相应绑定。
  - 1.窗口设计，
  - 2.设计飞行物大类（英雄机，子弹，敌机，奖励等都是飞行物大类的子类）
  - 3.分别给飞行物增加方法并绑定，丢到action()启动方法。
  - 4.把写好的小类组件通过重写paint()渲染到页面。

#### 3.3，功能模块设计

- 用户模块

  - 用户注册

  - 用户登录

  - 用户信息修改

  - 用户得分修改

    

- 游戏飞行物模块

  - 英雄机

  - 大中小型敌机

  - 子弹

  - 奖励

    

- 游戏工具类模块

  - 爆炸图

  - 音频播放

  - 图片导入<br/><br/>项目层次结构图

    <img src="C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568891662041.png" alt="1568891662041" style="zoom:80%;" />

#### 3.4，程序流程图

- #### 3.4.1用户模块

![1568884584996](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568884584996.png)

- #### 3.4.2游戏飞行物模块(UML图)

<img src="D:\io\enemy.png" alt="enemy" style="zoom: 200%;" />

#### 3.5，游戏整体思路实现过程

##### 1，窗口类：

- 以登录窗口为例（注册及修改密码窗口类似）：

  - 先写一个面板容器准备装窗口的组件<br/>![1568947471739](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568947471739.png)

  - 将输入文本框及按钮组装起来<br/>![1568948202440](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568948202440.png)

  - 给按钮注册点击事件<br/>

    ![1568948986191](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568948986191.png)

  - 给按钮键美化

    ![1568949614050](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568949614050.png)

- 排行榜

  - 先写面板容器准备存放组件

    ![1568953699567](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568953699567.png)

  - 面板上组装组件

    ![1568953848746](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568953848746.png)

  - 遍历nickname和score到面板上

    ![1568953999926](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568953999926.png)

- 游戏窗口

  - 绘制动画需要调用paint()

    ![1568954587088](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568954587088.png)

##### 2，飞行物类

- 飞行物大类

  - 定义

    <img src="C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568955299789.png" alt="1568955299789" style="zoom:80%;" />

<br/>

```java
public boolean shootBy(Bullet b) {  
  /***敌人的半径*/   int flyingR = Math.max(this.width, this.height) / 2; 
  /***子弹的半 */   int bulletR = Math.max(b.width, b.height) / 2;   
    /**    *敌人中心横坐标    */   int flyingX = this.x + flyingR;   
    /**    *敌人中心纵坐标    */   int flyingY = this.y + flyingR;   
    /**    *子弹中心横坐标    */   int bulletX = b.x + bulletR;   
    /**    *子弹中心纵坐标    */   int bulletY = b.y + bulletR;   
    /**    * 如果敌人和子弹的中心距小于半径的和，则发生了碰撞，敌人和子弹中心距采用    * 两点间距离公式：    * 平方根[(敌人横坐标中心-子弹横坐标中心)平方+(敌人纵坐标中心-子弹纵坐标中心)平方]    */   
    return Math.sqrt(
        Math.pow(flyingX - bulletX, 2) + Math.pow(flyingY - bulletY, 2))< 															((flyingR + bulletR)/3*2);}
```

- 飞行物飞入频率

  ```Java
  /** * 设置敌机，奖励的飞入频率 * @return */
  public FlyingObject nextOne() {   
      Random r = new Random();    
      int type = r.nextInt(100);    
      if (type < 2) {        //生成数小于2，给一个up        
          System.out.println("up来了");        
          return new LifeAdd();    } 
      else if (type >= 2 && type < 5) {        //给一个双倍火力 
          System.out.println("双倍火力");       
          return new DoubleFire();    } 
      else if (type >= 5 && type < 10) {        //来一架中型敌机 
          System.out.println("中型敌机...");       
          return new MiddleEnemyPlane();    } 
      else if (type >= 10 && type < 15) {        //来一架大型敌机
          System.out.println("大型敌机...");    
          return new BigEnemyPlane();    } else {        //其余的时候小型敌机    
          System.out.println(type);        return new SmallEnemyPlane();    }}
  ```

- 删除出界的飞行物

  ```java 
     /**
       * 删除越界飞行物
       */
      public void outOfBoundsAction() {
          int index = 0;
          FlyingObject[] flyingLives = new FlyingObject[flyings.length];
          for (int i = 0; i < flyings.length; i++) {
              FlyingObject f = flyings[i];
              if (!f.outOfBounds()) {
                  flyingLives[index] = f; // 不越界，将其装入flyingLives[]数组中
                  index++;
              }
              // 如果是越界的大飞机，停止播放大飞机登场音乐
              if (f instanceof BigEnemyPlane && f.outOfBounds()) {
                  AudioClipUtil.bigEnemyComeOnMusic.stop();
              }
          }
          flyings = Arrays.copyOf(flyingLives, index);
  
          index = 0;
          Bullet[] bulletsLives = new Bullet[bullets.length];
          for (int i = 0; i < bullets.length; i++) {
              Bullet bs = bullets[i];
              if (!bs.outOfBounds()) {
                  bulletsLives[index] = bs;// 不越界，将其装入bulletsLives[]数组中
                  index++;
              }
          }
          bullets = Arrays.copyOf(bulletsLives, index);
      }
  ```

##### 3，英雄机

- 英雄机的设置（动画效果）<br/>

(ownplane中step的方法放到stepaction一起通过定时器执行)<br/><img src="C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568955758680.png" alt="1568955758680" style="zoom: 67%;" />

![1568956081181](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568956081181.png)

- 发射子弹方法

  ![1568956609560](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568956609560.png)

- 英雄机跟敌机比头铁

  ```java
  /**
       * 撞击敌机效果
       */
   public void hitAction() {
         int index = -1; // 被撞敌人的索引
         for (int i = 0; i < flyings.length; i++) {
             FlyingObject fo = flyings[i]; // 获取所有的敌人
             // 如果英雄机撞上敌机并且非无敌状态时
             if (fo instanceof Enemy && ownPlane.hit(fo) && ownPlane.isInvincible() == false) {
                 ownPlane.setLife(ownPlane.getLife() - 1); // 生命数减1
                 ownPlane.setDoubleFire(0); // 火力值清零
                 AudioClipUtil.ownplaneDeathMusic.play(); // 播放主角死亡音乐
    OwnPlaneExplode oe = new OwnPlaneExplode(ownPlane.x, ownPlane.y); // 播放主角爆炸动画
                 oes.add(oe);
                 if (ownPlane.getLife() == 1) {
                   AudioClipUtil.warningGameOverMusic.loop();  // 播放游戏快结束警报
                  }
                  ownPlane.setInvincible(true); // 新命出来时暂时设置无敌状态
                  ownPlane.setInvincibleTime(0); // 初始化无敌时间
                  if (fo instanceof SmallEnemyPlane) { // 撞上小敌机
                      // 播放小敌机爆炸动画
                      SmallEnemyExplode see = new SmallEnemyExplode(fo.x, fo.y);
                      sees.add(see);
                  }
                  if (fo instanceof MiddleEnemyPlane) { // 撞上中敌机
                      // 播放中敌机爆炸动画
                    MiddleEnemyExplode mee = new MiddleEnemyExplode(fo.x, fo.y);
                      mees.add(mee);
                  }
                  if (fo instanceof BigEnemyPlane) {  // 撞上大敌机
                      // 播放大敌机爆炸动画
                      BigEnemyExplode bee = new BigEnemyExplode(fo.x, fo.y);
                      bees.add(bee);
                  }
                  index = i;
              }
             
             
             	//撞击奖励
              // 如果英雄机撞上双导弹掉落物，加火力，不论是无敌状态还是非无敌状态都可以
              if (fo instanceof DoubleFire && ownPlane.hit(fo)) {
                 ownPlane.addDoubleFire();
                 AudioClipUtil.addDoubleFireMusic.play(); // 播放加火力音乐
                 index = i;
              }
              // 如果英雄机撞上加生命掉落物，加生命，不论是无敌状态还是非无敌状态都可以
              if (fo instanceof LifeAdd && ownPlane.hit(fo)) {
                  ownPlane.setLife(ownPlane.getLife() + 1); // 英雄机增命
                  AudioClipUtil.addOwnplaneLifeMusic.play(); // 播放加生命音乐
                  if (ownPlane.getLife() == 2) {                                            //前面的1原来是2
                    AudioClipUtil.warningGameOverMusic.stop();  // 停止播放警报
                  }
                  index = i;
              }
          }
          
       
       		if (index != -1) { // 撞上了，将被撞敌人删除
              // 交换缩容
              FlyingObject t = flyings[index];
              flyings[index] = flyings[flyings.length - 1];
              flyings[flyings.length - 1] = t;
              flyings = Arrays.copyOf(flyings, flyings.length - 1);
          }
      }
  ```

  

##### 4，子弹

- 子弹实体类

  ```java
  public class Bullet extends FlyingObject {
  	/**
  	 * 子弹走步步数，只有y坐标在变
  	 */
  	private int speed = 3;
  
  	public Bullet(int x, int y) {
  		/**
  		 * 子弹的步数随着英雄机的变化而变化
  		 */
  		image = ImageIOUtil.bullet;
  		width = image.getWidth();
  		height = image.getHeight();
  		this.x = x;
  		this.y = y;
  	}
  
  	@Override
  	public void step(OwnPlane ownPlane) {
  		y -= speed;
  	}
  
  	@Override
  	public boolean outOfBounds() {
  		return this.y < -this.height;
  	}
  
  }
  ```

  

- 子弹撞击后删除

  ![1568957701077](C:\Users\Wayne\AppData\Roaming\Typora\typora-user-images\1568957701077.png)

- 子弹与敌人碰撞（核心代码）

  ```Java
  /**
  * 一个子弹与所有敌人撞
        */
      public boolean bang(Bullet b) {
          boolean flag = false; //假设所有敌人没有给撞
          int index = -1; // 被撞敌人的索引
  
          for (int i = 0; i < flyings.length; i++) { // 遍历所有的敌人
              FlyingObject obj = flyings[i];
              if (obj instanceof SmallEnemyPlane) { // 被撞的是小飞机
                  if (obj.shootBy(b)) {
                      SmallEnemyPlane sep = (SmallEnemyPlane) obj;
                      sep.setSmallLife(sep.getSmallLife() - 1);
                      if (sep.getSmallLife() == 0) {
                          // 播放小飞机爆炸动画
                          SmallEnemyExplode see = new SmallEnemyExplode(sep.x, sep.y);
                          sees.add(see);
                          index = i;
                          break;
                      }
                      flag = true;//子弹撞上了，删除
                  }
              }
              if (obj instanceof MiddleEnemyPlane) {// 被撞的是中飞机
                  if (obj.shootBy(b)) {
                      AudioClipUtil.enemyCollisionBulletMusic.play(); // 播放敌人中弹音乐
                      MiddleEnemyPlane mep = (MiddleEnemyPlane) obj;
                      mep.setMiddleLife(mep.getMiddleLife() - 1);
                      if (mep.getMiddleLife() == 0) {
                          // 播放中飞机爆炸动画
                          MiddleEnemyExplode mee = new MiddleEnemyExplode(mep.x, mep.y);
                          mees.add(mee);
                          index = i;
                          break;
                      }
                      ownPlane.setScore(ownPlane.getScore() + mep.getShootScore());//击打分+击毁分
                      flag = true;
                  }
              }
              if (obj instanceof BigEnemyPlane) {// 被撞的是大飞机
                  if (obj.shootBy(b)) {
                      AudioClipUtil.enemyCollisionBulletMusic.play(); // 播放敌人中弹音乐
                      BigEnemyPlane bep = (BigEnemyPlane) obj;
                      bep.setBigLife(bep.getBigLife() - 1);
                      bep.image = ImageIOUtil.bigEnemyPlaneClicked; // 播放大飞机挨打图
                      if (bep.getBigLife() == 0) {
                          // 播放大飞机爆炸动画
                          BigEnemyExplode bee = new BigEnemyExplode(bep.x, bep.y);
                          bees.add(bee);
                          index = i;
                          break;
                      }
                      ownPlane.setScore(ownPlane.getScore() + bep.getShootScore());//击打分+击毁分
                      flag = true;
                  }
              }
          }
          if (index != -1) { // 撞上了,处理除了爆炸图的音乐操作
              FlyingObject one = flyings[index];
              if (one instanceof Enemy) {
                  Enemy e = (Enemy) one;
                  if (e instanceof BigEnemyPlane) { // 大飞机死亡
                     AudioClipUtil.middleAndBigDeathMusic.play(); // 播放中、大飞机死亡音乐
                     AudioClipUtil.bigEnemyComeOnMusic.stop(); // 停止播放大飞机登场音乐
                     ownPlane.setScore(ownPlane.getScore() + e.getScore());
                  }
                  if (e instanceof MiddleEnemyPlane) { // 中飞机死亡
                     AudioClipUtil.middleAndBigDeathMusic.play(); // 播放中、大飞机死亡音乐
                      ownPlane.setScore(ownPlane.getScore() + e.getScore());
                  }
                  if (e instanceof SmallEnemyPlane) {
                      AudioClipUtil.smallDeathMusic.play(); // 播放小飞机死亡音乐
                      ownPlane.setScore(ownPlane.getScore() + e.getScore());
                  }
              }
              // 被撞敌人与flyings数组中的最后一个元素交换
              FlyingObject t = flyings[index];
              flyings[index] = flyings[flyings.length - 1];
               flyings[flyings.length - 1] = t;  // 缩容，删除被撞的敌人对象
              //重新赋值，实现删除
              flyings = Arrays.copyOf(flyings, flyings.length - 1);
          }
          return flag;
      }
  ```

- 发射双倍火力的方法

  ```java 
  /**
       * 发射子弹的方法
       * @return
       */
      public Bullet[] shoot() {
          int xStep = this.width / 32;
          if (doubleFire > 0) {           //当与双击奖励碰撞后，双发
              Bullet[] bullets = new Bullet[2];
              bullets[0] = new Bullet(this.x + 5 * xStep, this.y);
              bullets[1] = new Bullet(this.x + 21 * xStep, this.y);
              doubleFire -= 2;      //发射双倍火力，每次减2，限制双倍火力的持续时间
              return bullets;
          } else {                        //单发
              Bullet[] bullets = new Bullet[1];
              bullets[0] = new Bullet(this.x + 13 * xStep, this.y);
              return bullets;
          }
      }
  ```

##### 5，奖励

- 实体类(以双发为例)

  ```java 
  	public class DoubleFire extends FlyingObject {
  	Random r = new Random();
  	/**
  	 * x坐标走动步数
  	 */
      private int xSpeed = 1;
  	/**
  	 *  y坐标走动步数
  	 */
  	private int ySpeed = 2;
      
      public DoubleFire() {
      	image = ImageIOUtil.doubleFire;
      	width = image.getWidth();
      	height = image.getHeight();
      	x = r.nextInt(FinalInterface.WIDTH - this.width);
      	y = -this.height;
      }
  
  	@Override
  	public void step(OwnPlane ownPlane) {
          if (x >= FinalInterface.WIDTH - this.width) 
  			xSpeed = -1;
  		if (x <= 0) 
  			xSpeed = 1;
  		x += xSpeed;
  		if (ownPlane.getScore() < 300000) {
  			ySpeed = 2; 
  		} else if (ownPlane.getScore() >= 300000 
  				&& ownPlane.getScore() < 600000) {
  			ySpeed = 4;
  		} else if (ownPlane.getScore() >= 600000 
  				&& ownPlane.getScore() < 1000000) {
  			ySpeed = 6;
  		} else {
  			ySpeed = 8;
  		}
  		y += ySpeed;
  	}
  
  	@Override
  	public boolean outOfBounds() {
  		return this.y > FinalInterface.HEIGHT;
  	}
  
  }
  ```

  

- 撞击效果（比头铁的时候出现）

##### 6，敌机

- 以大型敌机为例(核心代码)

  ```java 
  public BigEnemyPlane() {
          image = ImageIOUtil.bigEnemyPlane0;
          width = image.getWidth();
          height = image.getHeight();
          bigIndex = 0;
          bigImages = new BufferedImage[]{
                  ImageIOUtil.bigEnemyPlane0,
                  ImageIOUtil.bigEnemyPlane1};
          x = r.nextInt(FinalInterface.WIDTH - this.width);
          y = -this.height; //负的大型敌机的高
      }
  
      @Override
      public int getScore() {//击毁后得分
          return 3000;
      }
  
      public int getShootScore() {//大飞机中弹后，主角得的分
          return 300;
      }
  
      @Override
  	/**
       * 大型敌机走步的速度，随着英雄机得分的变化而变化
       */
      public void step(OwnPlane ownPlane) {
          image = bigImages[bigIndex++ / 10 % bigImages.length];
          if (ownPlane.getScore() < 200000) {
              speed = 1;
          } else if (ownPlane.getScore() >= 200000
                  && ownPlane.getScore() < 400000) {
              speed = 2;
          } else if (ownPlane.getScore() >= 400000
                  && ownPlane.getScore() < 600000) {
              speed = 3;
          } else if (ownPlane.getScore() >= 600000
                  && ownPlane.getScore() < 800000) {
              speed = 4;
          } else if (ownPlane.getScore() >= 800000
                  && ownPlane.getScore() < 1000000) {
              speed = 5;
          } else {
              speed = 6;
          }
          y += speed;
      }
  
      @Override
      public boolean outOfBounds() {
          return this.y > FinalInterface.HEIGHT;      //大型敌机的y坐标大于窗口的高
      }
  }
  ```

  

##### 7， 用户类

- ###### 实体类

  (此处注意数据库sql.Date 和Java中的util.Date之间的转换)

  ```java
  private static final long serialVersionUID = 1L;
  	private String userId; // 用户编号
  	private String nickName; // 用户昵称
  	private String password; // 用户密码
  	private Date registTime; // 用户注册时间
  	private Date createShoreRecordTime; // 用户最高分纪录创建时间
  	private Integer score; // 用户得分
  ```

- ###### 用户DAO方法

  

- ###### 用户service方法

  

##### 8，调用paint()将各类画到ShootGameView上







