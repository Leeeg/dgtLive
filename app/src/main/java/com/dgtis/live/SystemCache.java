package com.dgtis.live;

import android.content.Context;
import android.os.Environment;

import com.dgtis.live.model.Personal;

import java.io.File;

/**
 * Created by Lee on 2017/4/10.
 */

public class SystemCache {

    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator;
    public static final String ROOT_PATH = "/DGTLive";
    public static final String RNFOLDER = BASE_PATH + "/图片/鬼刀/1507505244469.jpg";
    public static final String DB = "/sdcard/PHLive/DB";

    private static String       versionName;             //版本号（a.a.a）
    public static String        MAIN;                    //主
    public static String        SUB;                     //次
    public static String        FUNC;                    //功能
    public static String        DEVICE = "4";            //设备
    private static int          screenWith;              //屏幕宽度
    private static int          screenHeight;            //屏幕高度
    private static int          stateHeight;             //状态栏高度
    private static Context      context;                 //Context
    private static String       serialNumber = "";       //设备号
    private static String       shareUrl;                //分享地址
    private static boolean      isFirst;                 //是否第一次打开app
    private static boolean      authed;                  //是否主播
    private static int          statusBarHeight;         //状态栏高度
    private static String       pohonVersion;            //手机型号
    private static String       SDKVersion;              //SDK版本
    private static String       systemVersion;           //系统版本


    private static Personal.OpValueBean personal;

    public static Personal.OpValueBean getPersonal() {
        return personal;
    }

    public static void setPersonal(Personal.OpValueBean personal) {
        SystemCache.personal = personal;
    }
}
