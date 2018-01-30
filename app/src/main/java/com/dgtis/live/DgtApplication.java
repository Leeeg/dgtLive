package com.dgtis.live;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.dgtis.live.utils.SystemUtil;

import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by Lee on 2017/4/7.
 */

public class DgtApplication extends MultiDexApplication {

    private static DgtApplication m_myApplication;
    private DbManager dbManager;
    public boolean isLiving;
    public boolean isActive;
    public boolean isFromWeb;
    public boolean isInRoom;
    private Handler handler;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 单一实例
     */
    public static DgtApplication getInstance() {
        return m_myApplication;
    }

    public DbManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DbManager dbManager){
        this.dbManager = dbManager;
    }

    /**
     * 检查程序是否退出
     * @param packageName
     * @return
     */
    public boolean checkAppExisted(String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 判断是否在主进程
     * @return
     */
    public boolean inMainProcess() {
        String packageName = getPackageName();
        String processName = SystemUtil.getProcessName(this);
        return packageName.equals(processName);
    }

    /**
     * 获取当前版本的版本号
     * @return
     */
    private String getVersion() {
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "版本号未知";
        }
    }

    /**
     * *********************************************************************************************************************************************
     */
    public void onCreate() {
        super.onCreate();
        m_myApplication = this;

        x.Ext.init(this);//xUtils初始化
        x.Ext.setDebug(true);
        // 全局默认信任所有https域名 或 仅添加信任的https域名
        // 使用RequestParams#setHostnameVerifier(...)方法可设置单次请求的域名校验
        x.Ext.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        LogUtil.d(SystemUtil.getHandSetInfo());
        LogUtil.d(SystemUtil.getCpuName());

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


}
