package com.common.powertech;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

import com.common.powertech.exception.CrashHandler;

public class PowertechApplication extends Application {
    private static Stack<Activity> activityStack;
    private static PowertechApplication singleton;
    private static String MINRECHARGE; //全局变量 最小充值金额
    
	public String getMINRECHARGE() {
		return MINRECHARGE;
	}
	public void setMINRECHARGE(String mINRECHARGE) {
		MINRECHARGE = mINRECHARGE;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		CrashHandler catchHandler = CrashHandler.getInstance();
		catchHandler.init(getApplicationContext());
		singleton=this;
	}
	  // Returns the application instance
    public static PowertechApplication getInstance() {
        return singleton;
    }

    /**
     * add Activity 添加Activity到栈
     */
    public void addActivity(Activity activity){
        if(activityStack ==null){
            activityStack =new Stack<Activity>();
        }
        activityStack.add(activity);
    }
    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }
    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }

}