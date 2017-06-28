package application;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class MyApplication extends Application {
	public MyApplication() {
		super();
	}
 
	public static MyApplication instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public static MyApplication getInstance() {
		return instance;
	}

	/*  Retrieves application's version number from the manifest */
	public String getVersion() {
		String version = "1.0.0";
		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					getPackageName(), 0);
			version = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}
}