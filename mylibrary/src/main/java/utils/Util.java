package utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.MyApplication;

/**
 * 工具类
 */
public class Util {

	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}


	/**
	 * 四舍五入 */
	public static double rounding(double f) {
		// TODO Auto-generated method stub
		double f1 = 0.00;
		try {
			BigDecimal b = new BigDecimal(f);
			f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (Exception e) {
			// TODO: handle exception
			f1 = 0.00;
		}
		return f1;
	}
	/**
	 * 动态调整设置ListView高度
	 * 
	 * @param listView
	 * @param mAdapter
	 */
	public static void getTotalHeightofListView(ListView listView,
			Adapter mAdapter) {
		if (mAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < mAdapter.getCount(); i++) {
			View mView = mAdapter.getView(i, null, listView);
			mView.measure(
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			// mView.measure(0, 0);
			totalHeight += mView.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (mAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}
	/**
	 * 拨打电话
	 * 
	 * @param phone
	 */
	public static LayoutInflater mInflater;

	public static LayoutInflater getLayoutInflater() {
		if (mInflater == null) {
			mInflater = LayoutInflater.from(MyApplication.getInstance());
		}
		return mInflater;
	}

	/**
	 * 拨打电话
	 * 
	 * @param phone
	 */
	public static void call(String phone) {
		try {
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
					+ phone));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			MyApplication.getInstance().startActivity(intent);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static double getDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	static Drawable drawable = null;
	private static final String TAG = "Util";

	/**
	 * dip转像素
	 * 
	 * @param context
	 * @param dip
	 * @return
	 */
	public static int formatDipToPx(Context context, int dip) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		int dip1 = (int) Math.ceil(dip * dm.density);
		return dip1;
	}

	/**
	 * md5转换
	 * 
	 * @param plainText
	 * @return
	 */
	public static String Md5(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buf.toString();
	}

	/**
	 * 应付金额
	 * 
	 * @return
	 */
	public static int totalAmount(String saleCost, String amount) {
		Double preCostDouble;
		try {
			preCostDouble = Double.parseDouble(saleCost);
		} catch (Exception e) {
			// TODO: handle exception
			preCostDouble = 0.0;
		}

		Double amountDouble;
		try {
			amountDouble = Double.parseDouble(amount);
		} catch (Exception e) {
			// TODO: handle exception
			amountDouble = 0.0;
		}

		int total;
		try {
			total = Util.StringToRounding((preCostDouble * amountDouble) + "");
		} catch (Exception e) {
			// TODO: handle exception
			total = 0;
		}
		return total;
	}

	/**
	 * 是否是手机号
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static boolean isPhone(String phoneNumber) {
		Pattern p = Pattern.compile("^[1][2-9]\\d{9}");
		Matcher m = p.matcher(phoneNumber);
		return m.matches();
	}

	/**
	 * 匹配是否是中文 */
	public static boolean isChinese(String chinese) {
		Pattern p = Pattern.compile("[\u4E00-\u9FFF]{2,10}");
		Matcher m = p.matcher(chinese);
		return m.matches();
	}

	/**
	 * 是否是空
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean isNull(String str) {
		String regStartSpace = "^[　 ]*";
		String regEndSpace = "[　 ]*$";
		String strDelSpace = str.replaceAll(regStartSpace, "").replaceAll(
				regEndSpace, "");
		if (strDelSpace.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 匹配分行信息 1-10 汉字
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean isBranchBank(String str) {
		Pattern p = Pattern.compile("[\u4E00-\u9FA5]{1,10}");
		Matcher m = p.matcher(str);
		return m.matches();

	}

	public static boolean isQQ(String QQNumber) {
		Pattern p = Pattern.compile("^[1-9]\\d{4,9}$");
		Matcher m = p.matcher(QQNumber);
		return m.matches();
	}

	public static boolean isPassword(String password) {
		Pattern p = Pattern.compile("[0-9]{6,10}");
		Matcher m = p.matcher(password);
		return m.matches();
	}

	private static final String ID_CARD_PATTERN = "[0-9]{6}(1[9]|2[0])\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}(\\d|\\w)";

	public static boolean isIDCARD(String ID) {
		Pattern p = Pattern.compile(ID_CARD_PATTERN);
		Matcher m = p.matcher(ID);
		return m.matches();
	}

	public static String getDate() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
		Date dd = new Date();
		return ft.format(dd);
	}

	public static long getQuot(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 图片加载优化
	 * 
	 * @param options
	 * @param minSideLength
	 * @param maxNumOfPixels
	 * @return
	 */
	public static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	/**
	 * 微信唯一标识
	 * 
	 * @param type
	 * @return
	 */
	public static String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis())
				: type + System.currentTimeMillis();
	}

	/**
	 * 转换图片成圆形
	 * 
	 * @param bitmap
	 *            传入Bitmap对象
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}

	/**
	 * 图片转为灰色
	 * 
	 * @param bmSrc
	 * @return
	 */
	public static Bitmap bitmap2Gray(Bitmap bmSrc) {
		int width, height;
		height = bmSrc.getHeight();
		width = bmSrc.getWidth();
		Bitmap bmpGray = null;
		bmpGray = Bitmap.createBitmap(width, height, Config.RGB_565);
		Canvas c = new Canvas(bmpGray);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmSrc, 0, 0, paint);

		return bmpGray;
	}

	/**
	 * 去除所有空格制表符 换行
	 *
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 圆角矩形 */
	public static Bitmap createFramedPhoto(Bitmap image, int rat) {
		int x = image.getWidth();
		int y = image.getHeight();
		float outerRadiusRat;
		if (rat == 0)
			outerRadiusRat = 20;
		else
			outerRadiusRat = rat;

		// 根据源文件新建一个darwable对象
		Drawable imageDrawable = new BitmapDrawable(image);

		// 新建一个新的输出图片
		Bitmap output = Bitmap.createBitmap(x, y, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		// 新建一个矩形
		RectF outerRect = new RectF(0, 0, x, y);

		// 产生一个红色的圆角矩形
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);
		canvas.drawRoundRect(outerRect, outerRadiusRat, outerRadiusRat, paint);

		// 将源图片绘制到这个圆角矩形上
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		imageDrawable.setBounds(0, 0, x, y);
		canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);
		imageDrawable.draw(canvas);
		canvas.restore();

		return output;
	}

	/***
	 * 计算百分比
	 *
	 * @param y
	 * @param z
	 * @return
	 */
	public static double myPercent(double y, double z) {
		String baifenbi = "";// 接受百分比的值
		String baifenbiTemp = "";// 接受百分比的值
		double baiy = y * 1.0;
		double baiz = z * 1.0;
		double fen = baiy / baiz;
		NumberFormat nf = NumberFormat.getPercentInstance(); // 注释掉的也是一种方法
		nf.setMinimumFractionDigits(2); // 保留到小数点后几位
		DecimalFormat df1 = new DecimalFormat("##%"); // ##.00%
		// 百分比格式，后面不足2位的用0补齐
		baifenbiTemp = nf.format(fen);

		System.out.println("baifenbiTemp------" + baifenbiTemp);
		baifenbi = df1.format(fen);
		double temp = 0;
		try {
			temp = Double.parseDouble(baifenbi.replace("%", "").trim());
		} catch (Exception e) {
			// TODO: handle exception
			temp = 0;
		}
		return temp;
	}

	/***
	 * 计算百分比小数点后两位
	 *
	 * @param y
	 * @param z
	 * @return
	 */
	public static String myPercentDecimal(double y, double z) {
		String baifenbi = "";// 接受百分比的值
		String baifenbiTemp = "";// 接受百分比的值
		double baiy = y * 1.0;
		double baiz = z * 1.0;
		double fen = baiy / baiz;
		NumberFormat nf = NumberFormat.getPercentInstance(); // 注释掉的也是一种方法
		nf.setMinimumFractionDigits(2); // 保留到小数点后几位
		DecimalFormat df1 = new DecimalFormat("##%"); // ##.00%
		// 百分比格式，后面不足2位的用0补齐
		baifenbiTemp = nf.format(fen);

		return baifenbiTemp;
	}

	/***
	 * 计算百分比
	 *
	 * @param y
	 * @param z
	 * @return
	 */
	public static String myPercentDecimalTwo(double y, double z) {
		String baifenbi = "";// 接受百分比的值
		String baifenbiTemp = "";// 接受百分比的值
		double baiy = y * 1.0;
		double baiz = z * 1.0;
		double fen = baiy / baiz;
		NumberFormat nf = NumberFormat.getPercentInstance(); // 注释掉的也是一种方法
		nf.setMinimumFractionDigits(2); // 保留到小数点后几位
		DecimalFormat df1 = new DecimalFormat("##%"); // ##.00%
		// 百分比格式，后面不足2位的用0补齐
		baifenbiTemp = nf.format(fen);

		return baifenbiTemp;
	}

	public static BitmapFactory.Options options;

	/**
	 * 根据id获取图片资源
	 *
	 * @param id
	 * @return
	 */
	public static Bitmap creatBitmap(int id) {
		if (options == null) {
			// options = new BitmapFactory.Options();
			// options.inJustDecodeBounds = false;
			options = new BitmapFactory.Options();
			// options.inJustDecodeBounds = false;
			options.inPreferredConfig = Config.RGB_565;
			options.inPurgeable = true;
			options.inInputShareable = true;
		}

		InputStream is = MyApplication.instance.getResources()
				.openRawResource(id);

		Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// options.inSampleSize = 2; // width，hight设为原来的十分一
		return bitmap;
	}

	/**
	 * 计算剩余时间 小时天
	 *
	 * @param time
	 * @return
	 */
	public static String getQuot(long time) {
		long dd = 0;
		long hh = 0;
		long mm = 0;
		long ss = 0;
		dd = time / 1000 / (60 * 60 * 24);
		hh = time / 1000 / (60 * 60);
		mm = time / 1000 / 60;
		ss = time / 1000;
		if (dd != 0) {
			return dd + "天";
		} else if (hh != 0) {
			return hh + "小时";
		} else if (mm != 0) {
			return mm + "分钟";
		} else if (ss != 0) {
			return 1 + "分钟";
		}
		String strTime = dd + "天" + hh + "时" + mm + "分" + ss + "秒";
		return strTime;

	}

	/**
	 * 计算剩余时间 小时
	 *
	 * @param time
	 * @return
	 */
	public static String getHour(long time) {
		long dd = 0;
		long hh = 0;
		long mm = 0;
		long ss = 0;
		// dd = time / 1000 / (60 * 60 * 24);
		hh = time / 1000 / (60 * 60);
		mm = time / 1000 / 60;
		ss = time / 1000;
		if (dd != 0) {
			return dd + "天";
		} else if (hh != 0) {
			return hh + "小时";
		} else if (mm != 0) {
			return mm + "分钟";
		} else if (ss != 0) {
			return 1 + "分钟";
		}
		String strTime = dd + "天" + hh + "时" + mm + "分" + ss + "秒";
		return strTime;

	}

	// 四舍五入
	public static int StringToRounding(String value) {
		// BigDecimal temp = null;
		// try {
		// temp = new BigDecimal(value + "").setScale(0,
		// BigDecimal.ROUND_HALF_UP);
		// } catch (Exception e) {
		// temp = temp = new BigDecimal("1").setScale(0,
		// BigDecimal.ROUND_HALF_UP);
		// }

		Double temp1 = 0.0;
		int temp2 = 0;
		try {
			temp1 = Double.parseDouble(value);
			temp2 = (int) Math.ceil(temp1);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return temp2;
	}

	/**
	 * 加0
	 *
	 * @param temp
	 * @return
	 */
	public static String addZeros(int temp) {
		// TODO Auto-generated method stub
		if (temp <= 0) {
			return "00";
		}
		if (temp < 10) {
			return "0" + temp;
		}
		return temp + "";
	}

	/**
	 * 生成一个15位的随机数订单号
	 *
	 * @return
	 */
	public static String creatRequestId() {
		// TODO Auto-generated method stub
		String id = System.currentTimeMillis() + "";
		if (id.equals("") || id.length() < 12)
			id = "88888888888";

		if (id.length() > 15) {
			id = id.substring(0, 15);
		} else if (id.length() < 15) {
			int tmep = 15 - id.length();
			id = id + getRandomCodeStr(tmep);
		}
		return id;
	}


	/**
	 * 随机四位
	 *
	 * @param length
	 * @return
	 */
	public static String getRandomCodeStr(Integer length) {
		Set<Integer> set = getRandomNumber(length);
		// 使用迭代器
		Iterator<Integer> iterator = set.iterator();
		// 临时记录数据
		String temp = "";
		while (iterator.hasNext()) {
			temp += iterator.next();
		}
		return temp;
	}


	/**
	 * 软引用创建，必要时释放
	 *
	 * @param id
	 * @param b
	 * @return
	 */
	private static HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();

	public static Bitmap creatBitmap(int id, boolean b) {
		try {
			if (options == null) {
				options = new BitmapFactory.Options();
				// options.inJustDecodeBounds = false;
				options.inPreferredConfig = Config.RGB_565;
				options.inPurgeable = true;
				options.inInputShareable = true;
			}

			if (imageCache.containsKey(id + "")
					&& imageCache.get(id + "").get() != null
					&& !imageCache.get(id + "").get().isRecycled()) {
				return imageCache.get(id + "").get();
			}
			InputStream is = MyApplication.instance.getResources()
					.openRawResource(id);
			imageCache.put(
					id + "",
					new SoftReference<Bitmap>(BitmapFactory.decodeStream(is,
							null, options)));
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageCache.get(id + "").get();
	}


	/**
	 * 获取一个四位随机数，并且四位数不重复
	 *
	 * @return Set<Integer>
	 */
	private static Set<Integer> getRandomNumber(Integer length) {
		// 使用SET以此保证写入的数据不重复
		Set<Integer> set = new HashSet<Integer>();
		// 随机数
		Random random = new Random();

		while (set.size() < length) {
			// nextInt返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）
			// 和指定值（不包括）之间均匀分布的 int 值。
			set.add(random.nextInt(10));
		}
		return set;
	}

	/**
	 * 缩放图片
	 *
	 * @param scaleWidth
	 * @param scaleHeight
	 * @return
	 */
	static Matrix gameMatrix;

	public static Bitmap scaleBitmap(Bitmap bitmap, float scaleWidth,
			float scaleHeight) {
		// LogUtils.debug(Tag,"scaleWidth---" + scaleWidth);
		// LogUtils.debug(Tag,"scaleHeight---" + scaleHeight);
		if (scaleWidth == 0) {
			scaleWidth = 1;
		}
		if (scaleHeight == 0) {
			scaleHeight = 1;
		}
		float gameTopBgWidth = scaleWidth;
		float gameTopBgHeight = scaleHeight;
		// 取得想要缩放的matrix参数
		gameMatrix = new Matrix();
		gameMatrix.postScale(gameTopBgWidth, gameTopBgHeight);
		// 得到新的图片
		// Bitmap newBitMap = Bitmap.createBitmap(bitmap, 0, 0,
		// bitmap.getWidth(),
		// bitmap.getHeight(), gameMatrix, true);
		// if (!bitmap.isRecycled())
		// bitmap.recycle();
		Bitmap temp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), gameMatrix, true);
		if (bitmap != null && !bitmap.isRecycled()) {
			bitmap.isRecycled();
			bitmap = null;
		}
		long freeStart = Runtime.getRuntime().freeMemory();
		LogUtils.debug("freeStart", freeStart + "------temp-------");
		return temp;
	}

	/**
	 * 获取网络状态
	 *
	 * @param context
	 * @return
	 */
	public static boolean getNetConnectState(Context context) {
		boolean netConnect = false;
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo infoM = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (info.isConnected() || infoM.isConnected()) {
			netConnect = true;
		}
		return netConnect;
	}

	/**
	 * 去零计算
	 *
	 * @param temp1
	 * @return
	 */
	public static String stringProcessing(String temp1) {
		// TODO Auto-generated method stub
		if (temp1.trim().equals("") || temp1.trim().equals("0.0")
				|| Double.parseDouble(temp1.trim()) == 0) {
			return "0.00";
		} else {
			double amountCount;
			try {
				amountCount = Double.parseDouble(temp1);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				amountCount = 0;
			}
			String temp = new DecimalFormat(".00")
					.format(amountCount);
			if (temp.equals("0") || temp.equals("0.0") || temp.equals("0.00")
					|| temp.equals(".00") || temp.equals(".0")) {
				temp = "";
			}
			if (temp.indexOf(".") == 0) {
				temp = temp.replace(".", "0.");
			}
			return temp;
		}
	}

	public static double add(double d1, double d2) { // 进行加法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.add(b2).doubleValue();
	}

	public static double sub(double d1, double d2) { // 进行减法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		BigDecimal bg = new BigDecimal(b1.subtract(b2).doubleValue());
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	public static double mul(double d1, double d2) { // 进行乘法运算
		LogUtils.debug("BigDecimal", d1 + "----" + d2);

		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		BigDecimal bg = new BigDecimal(b1.multiply(b2).doubleValue());
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		LogUtils.debug("BigDecimal", "-----" + f1);
		return f1;
	}

	public static double div(double d1, double d2, int len) {// 进行除法运算
		if (len < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		BigDecimal bg = new BigDecimal(b1.divide(b2, len,
				BigDecimal.ROUND_HALF_UP).doubleValue());
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	public static double round(double d, int len) { // 进行四舍五入
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal(1);
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量，
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	private static final int DEF_DIV_SCALE = 10;

	

//	public static double div(double d1, double d2) {
//
//		return div(d1, d2, DEF_DIV_SCALE);
//
//	}

}
