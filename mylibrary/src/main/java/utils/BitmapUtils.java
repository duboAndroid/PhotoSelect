package utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import application.MyApplication;

public class BitmapUtils {

	public static Bitmap getScaleBitmap(String filename, int viewWidth,
			int viewHeight) {
		Bitmap bitmap = null;
		Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filename, options);
		options.inSampleSize = getSimpleSize(options, viewWidth, viewHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filename, options);

	}
	public static Bitmap getLoacalBitmap(String url) {
	     try {
	          FileInputStream fis = new FileInputStream(url);
	          return BitmapFactory.decodeStream(fis);
	     } catch (FileNotFoundException e) {
	          e.printStackTrace();
	          return null;
	     }
	}
	/**
	 * 软引用创建，必要时释放
	 *
	 * @param id
	 * @param b
	 * @return
	 */
	private static Options options;
	public static Bitmap creatBitmap(int id) {
		if (options == null) {
			options = new Options();
			// options.inJustDecodeBounds = false;
			options.inPreferredConfig = Config.RGB_565;
			options.inPurgeable = true;
			options.inInputShareable = true;
		}

		InputStream is = MyApplication.getInstance().getResources()
				.openRawResource(id);
		return BitmapFactory.decodeStream(is, null, options);
	}


	public static int getSimpleSize(Options options,
			int viewWidth, int viewHeight) {
		int simpleSize = 1;
		int imgHeight = options.outHeight;
		int imgWidth = options.outWidth;

		int widthRatio = (int) Math.ceil(imgWidth / viewWidth);
		int heightRatio = (int) Math.ceil(imgHeight / viewHeight);
		if (widthRatio > 1 && heightRatio > 1) {
			if (widthRatio > heightRatio) {
				simpleSize = widthRatio;
			} else {
				simpleSize = heightRatio;
			}
		}
		return simpleSize;
	}

//	public static String getThumbUploadPath(String imagePath,
//			int maxBorderLenght) {
//		Bitmap bitmap = null;
//		try{
//			BitmapFactory.Options options = new BitmapFactory.Options();
//			options.inJustDecodeBounds = true;
//			BitmapFactory.decodeFile(imagePath, options);
//			int height = options.outHeight;
//			int width = options.outWidth;
//	
//			int trueHeight = options.outHeight;
//			int trueWidth = options.outWidth;
//	
//			options.inSampleSize = 1;
//	
//			if (height > maxBorderLenght || width > maxBorderLenght) {
//				if (width > height) {
//					trueHeight = maxBorderLenght * height / width;
//					trueWidth = maxBorderLenght;
//					options.inSampleSize =  width / maxBorderLenght;
//				} else {
//					trueHeight = maxBorderLenght;
//					trueWidth = maxBorderLenght * width / height;
//					options.inSampleSize = height /maxBorderLenght;
//				}
//				if (options.inSampleSize < 0) {
//					options.inSampleSize = 1;
//				}
//			}
//			options.inJustDecodeBounds = false;
//			options.inPreferredConfig = Bitmap.Config.RGB_565;
//			try {
//				bitmap = BitmapFactory.decodeFile(imagePath, options);
//			} catch (OutOfMemoryError e) {
//				return getThumbUploadPath(imagePath, (int) (maxBorderLenght * 0.8));
//			}
//			try {
//				bitmap = compressImage(Bitmap.createScaledBitmap(bitmap, trueWidth,
//						trueHeight, false));
//			} catch (OutOfMemoryError e) {
//			} catch (Exception e) {
//			}
//			bitmap = rotaingImageView(readPictureDegree(imagePath), bitmap);
//			bitmap = compressImage(bitmap);
//		}catch(Exception e){
//		}
//		return saveBitmap(bitmap, imagePath);
//	}

	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 200) { // 循环判断如果压缩后图片是否大于300kb,大于继续压缩
			options -= 10;// 每次都减少10
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

//	public static String saveBitmap(Bitmap bm, String imagePath) {
//		String imageP = imagePath;
//
//		try {
//			File oF = new File(imagePath);
//			File f = new File(Constants.ImageCachePath, "uploadtemp");
//			imageP = f.getAbsolutePath();
//			if (f.exists()) {
//				f.delete();
//			}
//			FileOutputStream out = new FileOutputStream(f);
//			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
//			out.flush();
//			out.close();
//		} catch (OutOfMemoryError e) {
//			imageP = imagePath;
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			imageP = imagePath;
//			e.printStackTrace();
//		} catch (IOException e) {
//			imageP = imagePath;
//			e.printStackTrace();
//		}
//		return imageP;
//	}

//	/**
//	 * 读取图片属性：旋转的角度
//	 * 
//	 * @param path
//	 * 
//	 * 
//	 *            图片绝对路径
//	 * @return degree旋转的角度
//	 */
//	public static int readPictureDegree(String path) {
//		int degree = 0;
//		try {
//			ExifInterface exifInterface = new ExifInterface(path);
//			int orientation = exifInterface.getAttributeInt(
//					ExifInterface.TAG_ORIENTATION,
//					ExifInterface.ORIENTATION_NORMAL);
//			switch (orientation) {
//			case ExifInterface.ORIENTATION_ROTATE_90:
//				degree = 90;
//				break;
//			case ExifInterface.ORIENTATION_ROTATE_180:
//				degree = 180;
//				break;
//			case ExifInterface.ORIENTATION_ROTATE_270:
//				degree = 270;
//				break;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return degree;
//	}

	/**
	 * 旋转图片
	 * 
	 * @param angle
	 * @param bitmap
	 * @return Bitmap
	 */
	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		// 旋转图片 动作
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		if (matrix != null) {
			// 创建新的图片
			try {
				bitmap = Bitmap.createBitmap(bitmap, 0, 0,
						(int) (bitmap.getWidth()), (int) (bitmap.getHeight()),
						matrix, true);
			} catch (OutOfMemoryError err) {
				err.printStackTrace();
			}
		}
		return bitmap;
	}
	
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        //保证是方形，并且从中心画
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int w;
        int deltaX = 0;
        int deltaY = 0;
        if (width <= height) {
            w = width;
            deltaY = height - w;
        } else {
            w = height;
            deltaX = width - w;
        }
        final Rect rect = new Rect(deltaX, deltaY, w, w);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        //圆形，所有只用一个
        
        int radius = (int) (Math.sqrt(w * w * 2.0d) / 2);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

}
