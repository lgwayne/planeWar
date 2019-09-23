package com.wayne.airplane.view;

import com.wayne.airplane.award.DoubleFire;
import com.wayne.airplane.award.LifeAdd;
import com.wayne.airplane.bullet.Bullet;
import com.wayne.airplane.enemy.BigEnemyPlane;
import com.wayne.airplane.enemy.Enemy;
import com.wayne.airplane.enemy.MiddleEnemyPlane;
import com.wayne.airplane.enemy.SmallEnemyPlane;
import com.wayne.airplane.entity.User;
import com.wayne.airplane.explode.BigEnemyExplode;
import com.wayne.airplane.explode.MiddleEnemyExplode;
import com.wayne.airplane.explode.OwnPlaneExplode;
import com.wayne.airplane.explode.SmallEnemyExplode;
import com.wayne.airplane.flyingObject.FlyingObject;
import com.wayne.airplane.ownplane.OwnPlane;
import com.wayne.airplane.service.IUserService;
import com.wayne.airplane.service.UserServiceImpl;
import com.wayne.airplane.util.AudioClipUtil;
import com.wayne.airplane.util.FinalInterface;
import com.wayne.airplane.util.ImageIOUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class ShootGameView extends JFrame implements MouseListener {
    private static final long serialVersionUID = 1L;
    /**
     * 当前状态
     */
    public static int state;

    /**
     * 记录两张背景图片在屏幕滚动变化时的动态Y坐标
     */
    private int groundY0 = 0;
    private int groundY1 = -FinalInterface.HEIGHT;

    /**
     * 英雄机
     */
    private OwnPlane ownPlane = new OwnPlane();

    /**
     * 子弹数组
     */
    private Bullet[] bullets = {};

    /**
     * 敌人数组
     */
    private FlyingObject[] flyings = {};

    /**
     * 小型敌机爆炸对象集
     */
    private List<SmallEnemyExplode> sees = new ArrayList<SmallEnemyExplode>();

    /**
     * 中型敌机爆炸对象集
     */
    private List<MiddleEnemyExplode> mees = new ArrayList<MiddleEnemyExplode>();

    /**
     * 大型敌机爆炸对象集
     */
    private List<BigEnemyExplode> bees = new ArrayList<BigEnemyExplode>();

    /**
     * 英雄机爆炸对象集
     */
    private List<OwnPlaneExplode> oes = new ArrayList<OwnPlaneExplode>();

    /**
     * 计时器
     */
    private Timer timer;
    /**
     * 间隔时间：单位：毫秒
     */
    private int intervel = 10;

    /**
     * 当前登录用户
     */
    private User login = null;

    public ShootGameView(User login) {
        this.login = login;
        this.init();
    }

    /**
     *游戏程序的初始化启动
     */
    private void init() {
        this.setSize(FinalInterface.WIDTH, FinalInterface.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("D:\\IdeaProjects\\飞机大战\\src\\image\\hero0.png").getImage());
        this.setTitle("飞机大战");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.action();
    }

    /**
     * 设置敌机，奖励的飞入频率
     * @return
     */
    public FlyingObject nextOne() {
        Random r = new Random();

        int type = r.nextInt(100);
        if (type < 2) {
            //生成数小于2，给一个up
            System.out.println("up来了");
            return new LifeAdd();
        } else if (type >= 2 && type < 5) {
            //给一个双倍火力
            System.out.println("双倍火力");
            return new DoubleFire();
        } else if (type >= 5 && type < 10) {
            //来一架中型敌机
            System.out.println("中型敌机...");
            return new MiddleEnemyPlane();
        } else if (type >= 10 && type < 15) {
            //来一架大型敌机
            System.out.println("大型敌机...");
            return new BigEnemyPlane();
        } else {
            //其余的时候小型敌机
            System.out.println(type);
            return new SmallEnemyPlane();
        }
    }

    /**
     * 屏幕实现纵向卷轴效果
     */
    public void moveBackGroundAction() {
        /*
         * 两张图片每张纵坐标下移2px，即图片背景向下走，则以我方飞机为参考系， 看到的是地图“向上”卷轴的错觉。
         * 两张照片的内容相似，只要有衔接，就是不断滚动的效果
         */
        groundY0 += 4;
//        groundY1 += 2;//如果两张图片滚动不一致，背景图会出现断层。
        groundY1 += 4;
        /**
         * 若纵坐标刚好等于游戏界面高度，则纵坐标设为负的界面高度。
         */
        if (groundY0 == FinalInterface.HEIGHT) {
            groundY0 = -FinalInterface.HEIGHT;
        }
        if (groundY1 == FinalInterface.HEIGHT) {
            groundY1 = -FinalInterface.HEIGHT;
        }
    }

    int flyEnteredIndex = 0;

    /**
     * 敌机来袭
     */
    public void enterAction() {
        // 定时器中设定10毫秒走一次
        flyEnteredIndex++;
        // 每10毫秒增1
        if (ownPlane.getScore() < 300000) {

            if (flyEnteredIndex % 40 == 0) {
                FlyingObject obj = nextOne();
                // 如果登场的是大飞机，则播放大飞机登场音乐
                if (obj instanceof BigEnemyPlane) {
                    AudioClipUtil.bigEnemyComeOnMusic.loop();
                }
                flyings = Arrays.copyOf(flyings, flyings.length + 1);
                flyings[flyings.length - 1] = obj; // 将敌人赋值给flyings数组的最后一个元素
            }
        } else if (ownPlane.getScore() >= 300000
                && ownPlane.getScore() < 600000) {
            if (flyEnteredIndex % 30 == 0) {
                FlyingObject obj = nextOne();
                // 如果登场的是大飞机，则播放大飞机登场音乐
                if (obj instanceof BigEnemyPlane) {
                    AudioClipUtil.bigEnemyComeOnMusic.loop();
                }
                flyings = Arrays.copyOf(flyings, flyings.length + 1);
                flyings[flyings.length - 1] = obj; // 将敌人赋值给flyings数组的最后一个元素
            }
        } else if (ownPlane.getScore() >= 600000
                && ownPlane.getScore() < 1000000) {
            if (flyEnteredIndex % 20 == 0) {
                FlyingObject obj = nextOne();
                // 如果登场的是大飞机，则播放大飞机登场音乐
                if (obj instanceof BigEnemyPlane) {
                    AudioClipUtil.bigEnemyComeOnMusic.loop();
                }
                flyings = Arrays.copyOf(flyings, flyings.length + 1);
                flyings[flyings.length - 1] = obj; // 将敌人赋值给flyings数组的最后一个元素
            }
        } else {
            if (flyEnteredIndex % 10 == 0) {
                FlyingObject obj = nextOne();
                // 如果登场的是大飞机，则播放大飞机登场音乐
                if (obj instanceof BigEnemyPlane) {
                    AudioClipUtil.bigEnemyComeOnMusic.loop();
                }
                flyings = Arrays.copyOf(flyings, flyings.length + 1);
                flyings[flyings.length - 1] = obj; // 将敌人赋值给flyings数组的最后一个元素
            }
        }
    }

    /**
     * 更新敌人、子弹、英雄机、爆炸动画、中弹动画等坐标
     */
    public void stepAction() { // 10毫秒走一次
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject fo = flyings[i];
            fo.step(ownPlane);
        }
        for (OwnPlaneExplode oe : oes) { // 英雄机爆炸图片播放
            if (!oe.explodeEnd()) {
                oe.step(ownPlane);
            }
        }
        for (SmallEnemyExplode see : sees) { // 小飞机爆炸图片播放
            if (!see.explodeEnd()) {
                see.step(ownPlane);
            }
        }
        for (MiddleEnemyExplode mee : mees) { // 中飞机爆炸图片播放
            if (!mee.explodeEnd()) {
                mee.step(ownPlane);
            }
        }
        for (BigEnemyExplode bee : bees) { // 大飞机爆炸动画播放
            if (!bee.explodeEnd()) {
                bee.step(ownPlane);
            }
        }
        for (int i = 0; i < bullets.length; i++) {
            bullets[i].step(ownPlane); // 子弹走一步
        }
        ownPlane.step(ownPlane); // 英雄机走一步
    }

    int shootIndex = 0;
    /**
     * 子弹动画
     */
    public void shootAction() { // 10毫秒走一次
        shootIndex++; // 每10毫秒增1
        if (shootIndex % 25 == 0) { // 250毫秒发射一次子弹
            Bullet[] bs = ownPlane.shoot(); // 获取子弹对象
            bullets = Arrays.copyOf(bullets, bullets.length + bs.length);
            System.arraycopy(bs, 0, bullets, bullets.length - bs.length,
                    bs.length);
            AudioClipUtil.bulletMusic.play(); // 播放子弹发射音乐
        }
    }

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

    /**
     * 删除播放完的敌人、英雄机爆炸动画
     */
    public void deleteExplodeAction() {
        for (int i = 0; i < sees.size(); i++) {
            if (sees.get(i).explodeEnd()) {
                sees.remove(i);
            }
        }
        for (int i = 0; i < mees.size(); i++) {
            if (mees.get(i).explodeEnd()) {
                mees.remove(i);
            }
        }
        for (int i = 0; i < bees.size(); i++) {
            if (bees.get(i).explodeEnd()) {
                bees.remove(i);
            }
        }
        for (int i = 0; i < oes.size(); i++) {
            if (oes.get(i).explodeEnd()) {
                oes.remove(i);
            }
        }
    }

    /**
     * 所有子弹与所有敌人撞,删除发生碰撞的子弹
     */
    public void bangAction() {
        int index = -1;
        for (int i = 0; i < bullets.length; i++) {
            if (bang(bullets[i])) {// 记录被撞子弹的索引
                index = i;
            }
            if (index != -1) {// 删除被撞的子弹
                Bullet temp = bullets[index];
                bullets[index] = bullets[bullets.length - 1];
                bullets[bullets.length - 1] = temp;
                bullets = Arrays.copyOf(bullets, bullets.length - 1);
            }
        }
    }

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
                    flag = true;
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

    /**
     * 游戏结束
     */
    public void checkGameOverAction() {
        if (ownPlane.getLife() <= 0) { // 结束游戏
            AudioClipUtil.gameoverMusic.play(); // 播放游戏结束音乐
            AudioClipUtil.backgroundMusic.stop(); // 停止播放游戏背景音乐
            AudioClipUtil.bigEnemyComeOnMusic.stop(); // 停止播放大飞机登场音乐
            AudioClipUtil.warningGameOverMusic.stop();  // 停止播放警报音乐
            int selectButton = JOptionPane.showConfirmDialog(ShootGameView.this, "游戏结束！得分："
                            + ownPlane.getScore() + "\n点击”是“再玩一次！\n点击“否”查看排行榜！",
                    "请选择", JOptionPane.YES_NO_OPTION);
            if (selectButton == JOptionPane.YES_OPTION) {
                // 修改用户得分
                IUserService service = new UserServiceImpl();
                service.updateScore(login.getNickName(), ownPlane.getScore());
                clearFlyingObject(); // 清空现场飞行物
                restart(); // 重新开始游戏
            } else {
                state = FinalInterface.GAME_OVER;
                JOptionPane.showMessageDialog(ShootGameView.this, "欢迎" + login.getNickName() + "下次再玩!");
                // 修改用户得分
                IUserService service = new UserServiceImpl();
                service.updateScore(login.getNickName(), ownPlane.getScore());
                // 跳转至排行榜页面
                new ScoreListView(login).setVisible(true);
                ShootGameView.this.dispose();
            }
        }
    }

    public void clearFlyingObject() { // 清空现场飞行物
        flyings = new FlyingObject[0];  // 清空敌人
        bullets = new Bullet[0]; // 清空子弹
        // 清空爆炸动画
        sees.clear();
        mees.clear();
        bees.clear();
        oes.clear();
    }

    public void restart() { // 重新开始游戏的方法
        ownPlane = new OwnPlane();
        state = FinalInterface.START;
        AudioClipUtil.backgroundMusic.loop();
    }

    /**
     * 启动执行代码
     */
    public void action() {
        AudioClipUtil.backgroundMusic.loop(); // 循环播放背景音乐          调背景音乐的音量
        MouseAdapter ma = new MouseAdapter() {
            public void mouseMoved(MouseEvent me) {
                if (state == FinalInterface.RUNNING) { // 运行状态下执行
                    int x = me.getX(); // 鼠标X坐标
                    int y = me.getY(); // 鼠标Y坐标
                    ownPlane.moveTo(x, y); // 英雄机随着鼠标移动而移动
                }
            }
            // 鼠标的点击事件
            public void mouseClicked(MouseEvent me) {
                switch (state) {
                    case FinalInterface.START:
                        state = FinalInterface.RUNNING;
                        break;
                    case FinalInterface.RUNNING:
                        state = FinalInterface.PAUSE;
                        AudioClipUtil.backgroundMusic.stop(); // 停止播放游戏背景音乐
                        AudioClipUtil.bigEnemyComeOnMusic.stop(); // 停止播放大飞机登场音乐
                        AudioClipUtil.warningGameOverMusic.stop();  // 停止播放警报音乐
                        break;
                    case FinalInterface.PAUSE:
                        state = FinalInterface.RUNNING;
                        AudioClipUtil.backgroundMusic.play(); // 停止播放游戏背景音乐
                        AudioClipUtil.bigEnemyComeOnMusic.play(); // 停止播放大飞机登场音乐
                        AudioClipUtil.warningGameOverMusic.play();  // 停止播放警报音乐
                        break;
                    case FinalInterface.GAME_OVER:
                        ownPlane = new OwnPlane(); // 清理现场
                        flyings = new FlyingObject[0];
                        bullets = new Bullet[0];
                        state = FinalInterface.START;
                        break;
                }
            }
        };
        this.addMouseListener(ma); // 处理鼠标操作事件
        this.addMouseMotionListener(ma);// 处理鼠标移动事件
            /**
             * 启动定时器
             */
        new Timer().schedule(new TimerTask() {
            public void run() { // 定时器事件--10毫秒走一次
                if (state == FinalInterface.RUNNING) { // 运行状态下执行
                    moveBackGroundAction(); // 图像纵向卷轴
                    enterAction(); // 敌人登场
                    stepAction(); // 飞行物走一步
                    shootAction(); // 子弹入场
                    outOfBoundsAction(); // 删除越界飞行物
                    deleteExplodeAction(); // 删除播放完的敌人、英雄机爆炸动画
                    bangAction(); // 子弹与敌人撞
                    hitAction();  // 英雄机与敌人撞
                    checkGameOverAction(); // 游戏结束检测
                }
                repaint(); // 重画，调用paint()
            }
        }, intervel, intervel);
    }

    // 重写paint()方法 g：表示画笔
    @Override
    public void paint(Graphics g) {
        BufferedImage paint = new BufferedImage(FinalInterface.WIDTH, FinalInterface.HEIGHT,
                BufferedImage.TYPE_3BYTE_BGR);
        Graphics gs = paint.getGraphics();
        paintBackGround(gs); // 画背景图
        paintOwnPlane(gs); // 画英雄机
        paintFlyingObjects(gs);  // 画敌人
        paintExplodes(gs);  // 画爆炸效果
        paintBullets(gs);  // 画子弹
        paintScore(gs); // 画说明、分、命、双倍火力时间
        paintState(gs);  // 画状态
        g.drawImage(paint, 0, 0, this);
    }

    public void paintBackGround(Graphics g) {
        g.drawImage(ImageIOUtil.background0, 0, groundY0, this);
        g.drawImage(ImageIOUtil.background1, 0, groundY1, this);
    }

    // 画状态
    public void paintState(Graphics g) {
        switch (state) {
            case FinalInterface.START: // 启动状态画启动图
                g.drawImage(ImageIOUtil.start, 0, 0, this);
                break;
            case FinalInterface.PAUSE: // 暂停状态画暂停图
                g.drawImage(ImageIOUtil.pause, 0, 0, this);
                break;
            case FinalInterface.GAME_OVER: // 结束状态画结束图
                g.drawImage(ImageIOUtil.gameover, 0, 0, this);
                break;
        }
    }

    public void paintOwnPlane(Graphics g) { // 画英雄机对象
        g.drawImage(ownPlane.image, ownPlane.x, ownPlane.y, this);
    }

    public void paintFlyingObjects(Graphics g) { // 画敌人对象
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject fo = flyings[i];
            g.drawImage(fo.image, fo.x, fo.y, this);
        }
    }

    public void paintExplodes(Graphics g) { // 画敌人、英雄机爆炸动画
        for (SmallEnemyExplode see : sees) {
            g.drawImage(see.image, see.x, see.y, this);
        }
        for (MiddleEnemyExplode mee : mees) {
            g.drawImage(mee.image, mee.x, mee.y, this);
        }
        for (BigEnemyExplode bee : bees) {
            g.drawImage(bee.image, bee.x, bee.y, this);
        }
        for (OwnPlaneExplode oe : oes) {
            g.drawImage(oe.image, oe.x, oe.y, this);
        }
    }

    public void paintBullets(Graphics g) {
        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            g.drawImage(b.image, b.x, b.y, this); // 画子弹对象

        }
    }

    public void paintScore(Graphics g) { // 画说明、分数、生命数、双倍火力时间、警报
        g.setColor(Color.WHITE);
        g.setFont(new Font("楷体", Font.BOLD, 25));
        g.drawString("玩家" + login.getNickName() + "分数：" + ownPlane.getScore(),
                10, 55);
        g.drawString("玩家" + login.getNickName() + "生命：" + ownPlane.getLife(),
                10, 80);
        if (ownPlane.getLife() == 1) {
            g.drawString("危险！危险！", 274, 80);
        }
        if (ownPlane.getDoubleFireTime() > 0) {
            g.drawString("恭喜获得双倍火力！", 10, 105);
            g.drawString("双倍火力时间：" + ownPlane.getDoubleFireTime(), 10, 130);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) {
        new ShootGameView(new User("scott", "123456")).setVisible(true);
    }
}
