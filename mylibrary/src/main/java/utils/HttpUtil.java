package utils;

/**
 * HTTP请求帮助类
 *
 */
public class HttpUtil {
	private static final String TAG = "HttpUtil";

//	// 发送图片跟文字
//	public static MResult postIcon(String url, String jsonParams, String user_id, String newBitmap) {
//		LogUtils.debug("MResult", url);
//		long time = System.currentTimeMillis();
//		url = url + "token=" + "" + "&" + "t=" + time;
//
//		MResult result = null;
//		try {
//			if (user_id == null || user_id.equals("")) {
//				if (MyApplication.getInstance() != null) {
//					MSystem.restData();
//				}
//				user_id = MSystem.user_id;
//				LogUtils.debug(TAG, "user_id::::" + user_id);
//			}
//			LogUtils.debug("MResult", "user_id::::" + user_id);
//			HttpClient httpclient = new DefaultHttpClient();
//			httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
//			// 读取超时
//			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000);
//			HttpPost httppost = new HttpPost(url);
//			int apn = getApnType();
//			if (apn == CMWAP_APN) {
//				HttpHost proxy = new HttpHost("10.0.0.172", 80);
//				httppost.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
//			}
//
//			// if (!jsonParams.equals(""))
//			// httppost.setHeader("organizationCode", jsonParams);
//			// httppost.setHeader("Cookie", "JSESSIONID" + "=" + sessionId);
//			LogUtils.debug("MResult", "newBitmap=" + newBitmap);
//			File file = new File(newBitmap);
//			MultipartEntity entity = new MultipartEntity();
//
//			if (!newBitmap.equals("No")) {
//				// FileEntity reqEntity = new FileEntity(file,
//				// "binary/octet-stream");
//				// // reqEntity.
//				// httppost.setEntity(reqEntity);
//				// Log.i("MResult",
//				// "----------file-" + reqEntity.getContentLength());
//				ContentBody cb = new FileBody(file, "binary/octet-stream");
//				entity.addPart("memberportrait", cb);
//			}
//			// httppost.setEntity(entity)
//			// // StringBuilder sb = new StringBuilder();
//			// // sb.append("token=" + Util.Md5(time +
//			// // "matoue_app").toUpperCase());
//			// // sb.append("&t=" + time);
//			// jsonParams
//			// .put("token", Util.Md5(time + "matoue_app").toUpperCase());
//			// jsonParams.put("t", time + "");
//			//
//			//
//			//
//			// List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//			// nvps.add(new BasicNameValuePair("token", Util.Md5(time +
//			// "matoue_app").toUpperCase());
//			// nvps.add(new BasicNameValuePair("t", ""+time));
//			// nvps.add(new BasicNameValuePair("t", ""+time));
//			// FileEntity reqEntity = new FileEntity(file,
//			// "binary/octet-stream");
//			// nvps.add(new BasicNameValuePair("t", reqEntity));
//			// httppost.setEntity(new UrlEncodedFormEntity(nvps));
//			// httpclient.execute(httppost);
//			// httpclient.getConnectionManager().shutdown();
//
//			ContentBody sb = new StringBody(Util.Md5(time + "matoue_app").toUpperCase(), Charset.forName(HTTP.UTF_8));
//			ContentBody sb1 = new StringBody(time + "", Charset.forName(HTTP.UTF_8));
//
//			entity.addPart("token", sb);
//			entity.addPart("t", sb1);
//			httppost.setEntity(entity);
//
//			HttpResponse response = httpclient.execute(httppost);
//			HttpEntity resEntity = response.getEntity();
//
//			Log.i("MResult", "----------file-" + response.getStatusLine().getStatusCode());
//
//			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//				// // 取得返回的字符串
//				String strResult = EntityUtils.toString(response.getEntity(), "UTF-8");
//				LogUtils.debug("MResult", "------getContentLength------" + strResult + "");
//				result = new MResult(strResult.replace("data=", ""));
//			}
//			if (resEntity != null) {
//				resEntity.consumeContent();
//			}
//			httpclient.getConnectionManager().shutdown();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//			result = new MResult();
//			result.setErrorMsg("网络连接异常，请检查网络设置");
//			result.setErrorCode(100000);// 标识加载失败
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	public static int getApnType() {
//		// final android.net.Uri PREFERAPN_URI = android.net.Uri
//		// .parse("content://telephony/carriers/preferapn");
//		try {
//			ConnectivityManager connMgr = (ConnectivityManager) MyApplication.getInstance().getSystemService(
//					Context.CONNECTIVITY_SERVICE);
//			if (connMgr == null)
//				return NOT_EXIST;
//			if (connMgr.getActiveNetworkInfo() == null)
//				return NOT_EXIST;
//			if (connMgr.getActiveNetworkInfo().isConnected()) {
//				NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
//				int type = activeInfo.getType();
//				if (type == ConnectivityManager.TYPE_WIFI) {
//					// wifi
//					return WIFI_APN;
//				} else if (type == ConnectivityManager.TYPE_MOBILE) {
//					String extraInfo = activeInfo.getExtraInfo();
//
//					if (extraInfo.equalsIgnoreCase("cmwap") || extraInfo.equalsIgnoreCase("cmwap:gsm")) {
//						return CMWAP_APN;
//					}
//					return CMNET_APN;
//				}
//			} else {
//				return NOT_EXIST;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//		return DEFAULT_APN;
//	}
//
//	public static final int DEFAULT_APN = 0x0;
//
//	public static final int CMWAP_APN = DEFAULT_APN + 1;
//
//	public static final int CMNET_APN = DEFAULT_APN + 2;
//
//	public static final int WIFI_APN = DEFAULT_APN + 3;
//
//	public static final int NOT_EXIST = -1;
//
//	public static final String CMCC_PROXY = "10.0.0.172";
//
//	private static final String CHARSET = "utf-8"; // 设置编码
//
//	private static final int TIME_OUT = 10 * 10000000; // 超时时间
//
//	public static MResult uploadFile(String RequestURL, String jsonParams, String user_id, String newBitmap) {
//		String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
//		String PREFIX = "--", LINE_END = "\r\n";
//		String CONTENT_TYPE = "multipart/form-data"; // 内容类型
//		LogUtils.debug(TAG, CONTENT_TYPE);
//		// String RequestURL =
//		// "http://192.168.0.100:7080/YkyPhoneService/Uploadfile1";
//		long time = System.currentTimeMillis();
//		// RequestURL = RequestURL +
//		// "token=D9A714C61EE9A1A2A2F4730D076A9AE4&t=2014031815270000";
//		LogUtils.debug(TAG, RequestURL);
//		LogUtils.debug(TAG, jsonParams);
//		LogUtils.debug(TAG, user_id);
//		LogUtils.debug(TAG, newBitmap);
////		System.out.println(RequestURL);
//		try {
//			URL url = new URL(RequestURL);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setReadTimeout(TIME_OUT);
//			conn.setConnectTimeout(TIME_OUT);
//			conn.setDoInput(true); // 允许输入流
//			conn.setDoOutput(true); // 允许输出流
//			conn.setUseCaches(false); // 不允许使用缓存
//			conn.setRequestMethod("POST"); // 请求方式
//			conn.setRequestProperty("Charset", CHARSET); // 设置编码
//			conn.setRequestProperty("connection", "keep-alive");
//			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
//			// conn.setRequestProperty("sessionId",
//			// "8D21DEDD7ABA4C25AE1CBBE9A9EF5E09");
//			conn.setRequestProperty("user_id", MSystem.user_id);
//			File file = new File(newBitmap);
//			 
//			if (!newBitmap.equals("No")) {
//				/**
//				 * 当文件不为空，把文件包装并且上传
//				 */
//				OutputStream outputSteam = conn.getOutputStream();
//				DataOutputStream dos = new DataOutputStream(outputSteam);
//				StringBuffer sb = new StringBuffer();
//				sb.append(PREFIX);
//				sb.append(BOUNDARY);
//				sb.append(LINE_END);
//				/**
//				 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
//				 * filename是文件的名字，包含后缀名的 比如:abc.png
//				 */
//				sb.append("Content-Disposition: form-data; name=\"imageFile\"; filename=\"" + file.getName() + "\""
//						+ LINE_END);
//				sb.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINE_END);
//
//				sb.append(LINE_END);
//				dos.write(sb.toString().getBytes());
//				InputStream is = new FileInputStream(file);
//				byte[] bytes = new byte[1024];
//				int len = 0;
//				while ((len = is.read(bytes)) != -1) {
//					dos.write(bytes, 0, len);
//				}
//				is.close();
//				dos.write(LINE_END.getBytes());
//				byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
//				dos.write(end_data);
//				dos.flush();
//				/**
//				 * 获取响应码 200=成功 当响应成功，获取响应的流
//				 */
//				// 得到响应码
//				int res = conn.getResponseCode();
//				// InputStream in = conn.getInputStream();
//				// StringBuilder sb2 = new StringBuilder();
//				MResult mResult = null;
//				if (res == 200) {
//					try {
//						// 先将服务器得到的流对象 包装 存入缓冲区，忽略了正在缓冲时间
//						InputStream in = new BufferedInputStream(conn.getInputStream());
//						// 得到servlet写入的头信息，response.setHeader("year", "2013");
//						// String year = conn.getHeaderField("year");
//						// System.out.println("year="+year);
//						byte[] bytes1 = readStream(in); // 封装的一个方法，通过指定的输入流得到其字节数据
//						LogUtils.debug("MResult", "newBitmap=成功" + new String(bytes1, "utf-8"));
//						mResult = new MResult(new String(bytes1, "utf-8"));
//					} catch (Exception e) {
//						// TODO: handle exception
//						e.printStackTrace();
//						LogUtils.debug("MResult", "newBitmap=读取报错");
//					}
//				} else {
//
//				}
//				conn.disconnect();
//				return mResult;
//			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return new MResult();
//	}
//
//	public static byte[] readStream(InputStream inStream) throws Exception {
//		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
//		byte[] buffer = new byte[1024];
//		int len = -1;
//		while ((len = inStream.read(buffer)) != -1) {
//			outSteam.write(buffer, 0, len);
//		}
//		outSteam.close();
//		inStream.close();
//		return outSteam.toByteArray();
//	}
}