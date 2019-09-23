package com.wayne.airplane.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**
 * 各类音频
 */
public class AudioClipUtil {
    /**
     * 声明各类音频
     */
    public static AudioClip backgroundMusic; // 背景音乐
    public static AudioClip bulletMusic; // 子弹发射
    public static AudioClip middleAndBigDeathMusic; // 中、大飞机死亡音乐
    public static AudioClip smallDeathMusic; // 小飞机死亡音乐
    public static AudioClip bigEnemyComeOnMusic; // 大飞机登场音乐
    public static AudioClip enemyCollisionBulletMusic; // 敌人中弹音乐
    public static AudioClip addOwnplaneLifeMusic; // 加生命音乐
    public static AudioClip addDoubleFireMusic; // 加火力音乐
    public static AudioClip ownplaneDeathMusic; // 主角死亡音乐
    public static AudioClip warningGameOverMusic; // 主角剩一条命音乐
    public static AudioClip gameoverMusic; // 游戏结束音乐

    // 由静态块加载所有的音乐
    static {
        try {
            backgroundMusic = Applet.newAudioClip(new File("D:\\IdeaProjects\\飞机大战\\src\\music\\背景音乐.wav")
                    .toURI().toURL());
            bulletMusic = Applet.newAudioClip(new File("D:\\IdeaProjects\\飞机大战\\src\\music\\子弹发射.wav").toURI().toURL());
            middleAndBigDeathMusic = Applet.newAudioClip(new File("D:\\IdeaProjects\\飞机大战\\src\\music\\中、大飞机死亡.wav").toURI().toURL());
            smallDeathMusic = Applet.newAudioClip(new File("music/小飞机死亡.wav").toURI().toURL());
            bigEnemyComeOnMusic = Applet.newAudioClip(new File("D:\\IdeaProjects\\飞机大战\\src\\music\\小飞机死亡.wav").toURI().toURL());
            enemyCollisionBulletMusic = Applet.newAudioClip(new File(
                    "D:\\IdeaProjects\\飞机大战\\src\\music\\敌人中弹.wav").toURI().toURL());
            addOwnplaneLifeMusic = Applet.newAudioClip(new File("D:\\IdeaProjects\\飞机大战\\src\\music\\加生命.wav")
                    .toURI().toURL());
            addDoubleFireMusic = Applet.newAudioClip(new File("D:\\IdeaProjects\\飞机大战\\src\\music\\得到双倍火力.wav").toURI().toURL());
            ownplaneDeathMusic = Applet.newAudioClip(new File("D:\\IdeaProjects\\飞机大战\\src\\music\\主角死亡.wav")
                    .toURI().toURL());
            warningGameOverMusic = Applet.newAudioClip(new File("D:\\IdeaProjects\\飞机大战\\src\\music\\游戏快结束警报.wav").toURI().toURL());
            gameoverMusic = Applet.newAudioClip(new File("D:\\IdeaProjects\\飞机大战\\src\\music\\游戏结束.wav").toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
