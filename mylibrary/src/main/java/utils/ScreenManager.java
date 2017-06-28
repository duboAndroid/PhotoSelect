package utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.util.Stack;

/**
 * 屏幕管理控制器
 */
public class ScreenManager {
	private static Stack<Activity> activityStack;
	private static ScreenManager instance;

	public static ScreenManager getScreenManager() {
		if (instance == null) {
			instance = new ScreenManager();
		}
		return instance;
	}

	public void popActivity() {
		Activity activity = activityStack.lastElement();
		if (activity != null) {
			activity.finish();
			activity = null;
		}
	}

	public void popActivity(Activity activity) {

		if (screenWidth == 0 || screenHeight == 0) {
			WindowManager wm = (WindowManager) activity
					.getSystemService(Context.WINDOW_SERVICE);
			DisplayMetrics outMetrics = new DisplayMetrics();
			wm.getDefaultDisplay().getMetrics(outMetrics);
			screenHeight = outMetrics.heightPixels;
			screenWidth = outMetrics.widthPixels;
		}
		if (activity != null) {
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}

	public Activity currentActivity() {
		Activity activity = null;
		if (activityStack != null && !activityStack.isEmpty()) {
			activity = activityStack.lastElement();
		}
		return activity;
	}

	public void pushActivity(Activity activity) {
		initWindow(activity);
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	public void initWindow(Activity activity) {
		WindowManager wm = (WindowManager) activity
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		screenHeight = outMetrics.heightPixels;
		screenWidth = outMetrics.widthPixels;
		LogUtils.debug("main", "initWindow=" + screenHeight + "--"
				+ screenWidth);

	}

	public void startActivity(Class cls) {
		for (int i = 0; i < activityStack.size(); i++) {
			if (activityStack.get(i).getClass().equals(cls)) {
				activityStack.set(0, activityStack.get(i));
			}
		}
	}

	/**
	 * 退出所有Activity
	 * 
	 * @param cls
	 */
	public void popAllActivityExceptOne(Class at) {
//		while (true) {
//			Activity activity = currentActivity();
//			if (activity == null) {
//				break;
//			}
//			if (cls != null && activity.getClass().equals(cls)) {
//				break;
//			}
//			popActivity(activity);
//		}
		Stack<Activity> tempActivityStack = new Stack<Activity>();
		for (Activity activity : activityStack) {
			if (activity != null) {
					if (at != null && activity.getClass().equals(at)) {
						tempActivityStack.add(activity);
						// popActivity(activity);
					}
			}
		}

		for (Activity activity : tempActivityStack) {
			if (activity != null)
				popActivity(activity);
		}
		tempActivityStack.clear();
	}

	/**
	 * 退出目标所有Activity
	 * 
	 * @param cls
	 */
	public void popAllActivityExceptOne(Class... cls) {
		Stack<Activity> tempActivityStack = new Stack<Activity>();
		for (Activity activity : activityStack) {
			if (activity != null) {
				for (Class at : cls)
					if (at != null && activity.getClass().equals(at)) {
						tempActivityStack.add(activity);
						// popActivity(activity);
					}
			}
		}

		for (Activity activity : tempActivityStack) {
			if (activity != null)
				popActivity(activity);
		}
		tempActivityStack.clear();
	}

	public ScreenManager() {
		// cannot be instantiated
		// throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 返回当前屏幕是否为竖屏。
	 * 
	 * @param context
	 * @return 当且仅当当前屏幕为竖屏时返回true, 否则返回false。
	 */
	public boolean isScreenOriatationPortrait(Context context) {
		return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
	}

	// 屏幕宽度
	public int screenWidth = 0;

	/**
	 * 获得屏幕宽度
	 * 
	 * @return
	 */
	public int getScreenWidth() {

		return screenWidth;

	}

	// 屏幕宽度
	public int screenHeight = 0;

	/**
	 * 获得屏幕高度
	 * 
	 * @return
	 */
	public int getScreenHeight() {

		return screenHeight;
	}

	/**
	 * 获得状态栏的高度
	 * 
	 * @param context
	 * @return
	 */
	public int getStatusHeight(Context context) {
		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

	/**
	 * 获取当前屏幕截图，包含状态栏
	 * 
	 * @param activity
	 * @return
	 */
	public Bitmap snapShotWithStatusBar(Activity activity) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		int width = getScreenWidth();
		int height = getScreenHeight();
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
		view.destroyDrawingCache();
		return bp;
	}

	/**
	 * 获取当前屏幕截图，不包含状态栏
	 * 
	 * @param activity
	 * @return
	 */
	public Bitmap snapShotWithoutStatusBar(Activity activity) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		int width = getScreenWidth();
		int height = getScreenHeight();
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
				- statusBarHeight);
		view.destroyDrawingCache();
		return bp;
	}

	public float getDanmuHeight(Context context) {
		// return getScreenWidth(context) * 370 / 355;
		return getScreenWidth() * 75 / 72;
	}

}
