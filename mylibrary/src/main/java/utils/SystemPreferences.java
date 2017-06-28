package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Set;

import application.MyApplication;

public class SystemPreferences {
	public static SharedPreferences sharedPreferences;

	// 是否是首次进入3.0版本
	// public static final String ISWELCOME_FIRST = "isWelcomeFirst";
	// 系统信息
	// public static final String PUBLICKEY = "publicKey";
	public static final String SESSIONID = "sessionId";
	public static final String CLIENTID = "clientID";
	// public static final String SID = "sid";
	public static final String PHONE = "phone";
	public static final String NEEDPSWD = "needPswd";
	public static final String PSWDSETTED = "pswdSetted";
	public static final String NICKNAME = "nickName";
	public static final String WEIXINURL = "weixinUrl";
	public static final String HEADURL = "headUrl";

	public static final String GAMEURL = "gameUrl";

	private static SystemPreferences systemPreferences;

	// 4.0初始化
	public static final String INITCARDPACKAGE = "initCardpackage4.0";

	// 首次共享联系人
	public static final String SHAREPROMPT = "sharePrompt";

	// 是否是从welcome过来的
	// public static final String ISFROMWELCOMEACTIVITY =
	// "isFromWelcomeActivity";
	public static final String USER_ICON = "user_icon";
 
	// 移动坐标
	public static final String LASTX = "lastx";
	public static final String LASTY = "lasty";

	public static final String USER_ID = "user_id";//用户ID
	public static final String USERNUMBER = "userNumber";//用户ID

	public static final String USER_MOBILE = "user_mobile";//用户手机号码

	public static final String USER_NUMBER = "user_number";

	// 首次提示时间
	public static String OLDREFUNDAMOUNT = "oldRefundAmount";
	// 首次点击卡
//	public static String FIRSTCLIK_CARDS = "onClickfirstClikCards";
	// 首次点击券
//	public static String FIRSTCLIK_TICKET = "onClickfirstClikTicket";
	// 发送短信手机号
	public static String UPSMSNUMBER = "upSmsNumber";


	// 推送消息
	public static String NOTIFACTIONID = "notifactionId";

	// 第一次进合买详情
	public static String SHOWTOGETHERBUY_DETAILS = "showTogeTherBuyDetails";

	// 合买第一次按返回
	public static String SHOWUNFINISHED = "showUnfinished";

	// 首次购买完成返回卡提示
	public static String SHOWCOMPLETE_CARD = "showCompleteCard";
	// 首次购买完成返回券提示
	public static String SHOWCOMPLETE_TICKET = "showCompleteticket";

	// 是否是第一次绑卡
	// public static String BINDINGCARDSFRIST = "bindingCardsFrist";

	public SystemPreferences() {
		// TODO Auto-generated constructor stub
		init();
	}

	public static SystemPreferences getinstance() {
		if (systemPreferences == null) {
			synchronized (ImageLoaderUtils.class) {
				if (systemPreferences == null) {
					systemPreferences = new SystemPreferences();
				}
			}
		}
		return systemPreferences;
	}

	/**
	 * 保存数据系统配置文件
	 * 
	 * @param key
	 * @param value
	 */
	public void save(String key, Object value) {
		if (sharedPreferences != null) {
			Editor editor = sharedPreferences.edit();
			if (value instanceof String)
				editor.putString(key, (String) value);
			if (value instanceof Boolean)
				editor.putBoolean(key, (Boolean) value);
			if (value instanceof Integer) {
				editor.putInt(key, (Integer) value);
			}
			editor.commit();
		}

	}

	public void init() {
		// TODO Auto-generated method stub
		if (sharedPreferences == null) {
			sharedPreferences = MyApplication.instance
					.getSharedPreferences("duopocket", Context.MODE_PRIVATE);
		}
	}

	/**
	 * 删除
	 * 
	 * @param key
	 * @param value
	 */
	public void remove(String key) {
		if (sharedPreferences != null) {
			Editor editor = sharedPreferences.edit();
			editor.remove(key).commit();
			editor.commit();
		}

	}

	/**
	 * 读取配置信息
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		int value = 0;
		if (sharedPreferences != null) {
			value = sharedPreferences.getInt(key, 0);
		}
		return value;
	}

	/**
	 * 读取配置信息
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		String value = "";
		if (sharedPreferences != null) {
			value = sharedPreferences.getString(key, "");
		}
		return value;
	}

	public Boolean getBoolean(String key) {
		boolean value = false;
		if (sharedPreferences != null) {
			value = sharedPreferences.getBoolean(key, false);
		}
		return value;
	}

	public Set<String> getStringSet(String key) {
		Set<String> value = null;
		if (sharedPreferences != null) {
		}
		return value;
	}

}
