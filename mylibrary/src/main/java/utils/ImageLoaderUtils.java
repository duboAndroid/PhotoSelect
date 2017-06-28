package utils;

public class ImageLoaderUtils {

//	private static ImageLoaderUtils mImageLoaderUtil;
//	// private int defultImageResId = R.drawable.icon;
//	// private int errorImageResId = R.drawable.icon;
//	public static final int USER_IMAGE = 3;
//	public static final int DEFAULT_ICONTYPE = 2;
//	public static final int SHOP_DEFAULTTYPE = 1;
//
//	private static ImageLoader imageLoader = ImageLoader.getInstance();
////	头像
//	private DisplayImageOptions default_options;
////	、汽车
//	private DisplayImageOptions shop_default_options;
////	首页轮播图
//	private DisplayImageOptions shop_default_options2;
//
//	public ImageLoaderUtils(Context context) {
//
//		if (context == null) {
//			LogUtils.debug("ImageLoaderUtils", "--1---null-----");
//			return;
//		} else {
//			LogUtils.debug("ImageLoaderUtils", "--2---null-----");
//		}
//
//		imageLoader = ImageLoader.getInstance();
//		try {
//			File cacheDir = StorageUtils.getOwnCacheDirectory(context, "suiyizu/Cache");
//			ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context)
//					.discCache(new UnlimitedDiscCache(cacheDir, cacheDir, new Md5FileNameGenerator())).build();
//			imageLoader.init(imageLoaderConfiguration);
//		} catch (Exception e) {
//			// TODO: handle exception
//			imageLoader.init(ImageLoaderConfiguration.createDefault(context));
//		}
//
//		// discCache
//		// 设置默认用户头像
//		default_options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.cartooncar)
//				.showImageForEmptyUri(R.drawable.hand_engine).showImageOnFail(R.drawable.cartooncar)
//				.cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
//				// .displayer(new RoundedBitmapDisplayer(20))
//				// .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
//				.bitmapConfig(Bitmap.Config.RGB_565).build();
//
//		// 设置商户头像默认头像
//		 shop_default_options = new
//		 DisplayImageOptions.Builder().showImageOnLoading(R.drawable.cartooncar)
//
//		 .showImageForEmptyUri(R.drawable.hand_engine).showImageOnFail(R.drawable.cartooncar).cacheInMemory(true)
//		 .cacheOnDisc(true).considerExifParams(true)
//		 // .displayer(new RoundedBitmapDisplayer(90))
//		 // .imageScaleType(ImageScaleType.EXACTLY_STRETCHED )
//		 .bitmapConfig(Bitmap.Config.RGB_565).build();
//
//
//
//		 shop_default_options2 = new
//				 DisplayImageOptions.Builder().showImageOnLoading(R.drawable.cartooncar)
//
//				 .showImageForEmptyUri(R.drawable.cartooncar).showImageOnFail(R.drawable.cartooncar).cacheInMemory(true)
//				 .cacheOnDisc(true).considerExifParams(true)
//				 // .displayer(new RoundedBitmapDisplayer(90))
//				 // .imageScaleType(ImageScaleType.EXACTLY_STRETCHED )
//				 .bitmapConfig(Bitmap.Config.RGB_565).build();
//
//	}
//
//	public static ImageLoaderUtils getinstance(Context context) {
//		if (mImageLoaderUtil == null) {
//			synchronized (ImageLoaderUtils.class) {
//				if (mImageLoaderUtil == null) {
//					mImageLoaderUtil = new ImageLoaderUtils(context);
//				}
//			}
//		}
//		return mImageLoaderUtil;
//	}
//
//	*
//	 * 获取图片
//	 *
//	 * @param context
//	 * @param imageView
//	 * @param url
//	 * @param maxWidth
//	 * @param maxHeight
//	 * @param defaultImage
//	 *            type 1轮播图 2默认头像
//
//	public void getImage(final ImageView imageView, String url, ImageLoadingListener imageloadinglistener, int type) {
//		try {
//
//			if (type == 1)
//				imageLoader.displayImage(url, imageView, default_options, imageloadinglistener);
//			else if (type == 2)
//				imageLoader.displayImage(url, imageView, shop_default_options, imageloadinglistener);
//			else  if (type == 3)
//				imageLoader.displayImage(url, imageView, shop_default_options2, imageloadinglistener);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//
//	*
//	 * 获取图片
//	 *
//	 * @param context
//	 * @param imageView
//	 * @param url
//	 * @param maxWidth
//	 * @param maxHeight
//	 * @param defaultImage
//
//	public void getRoundedCornerBitmap(final ImageView imageView, String url, final int defImageResId) {
//
//		// url = "www.baidu.com";
//		final DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(defImageResId)
//				.showImageForEmptyUri(defImageResId).showImageOnFail(defImageResId).cacheInMemory(true)
//				.cacheOnDisc(true).considerExifParams(true).displayer(new BitmapDisplayer() {
//
//					@Override
//					public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom arg2) {
//						if (!(imageAware instanceof ImageViewAware)) {
//							throw new IllegalArgumentException(
//									"ImageAware should wrap ImageView. ImageViewAware is expected.");
//						}
//						imageAware.setImageBitmap(BitmapUtils.getRoundedCornerBitmap(bitmap));
//					}
//				}).build();
//		imageLoader.displayImage(url, imageView, options);
//	}

}
