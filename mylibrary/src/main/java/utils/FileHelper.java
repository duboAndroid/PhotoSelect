package utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import application.MyApplication;

public class FileHelper {

	/**
	 * 获取缓存
	 */
	public static Object getEntity(String fileName) {
		Object object = null;
		try {
			FileInputStream fs = MyApplication.getInstance()
					.openFileInput(fileName);
			ObjectInputStream in = new ObjectInputStream(fs);
			object = in.readObject();

			in.close();
			fs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 保存实体缓存文件
	 */
	public static void saveEntity(Object object, String fileName) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fs;
			fs = MyApplication.getInstance().openFileOutput(fileName,
					Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fs);
			out.writeObject(object);
			out.close();
			fs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断path路径下是否存在文件
	 * 
	 * @param path
	 * @param name
	 * @return
	 */
	public static boolean checkFileExists(String path, String name) throws Exception {
		boolean status = false;
		if (!name.equals("")) {
			File newPath = new File(path + name);
			status = newPath.exists();
		}
		return status;
	}
	
	/**
	 * 判断path路径下是否存在文件
	 * 
	 * @param path
	 * @param name
	 * @return
	 */
	public static boolean checkFileExists(String filepath) throws Exception {
		boolean status = false;
		if (filepath==null||"".equals(filepath)) {
			File newPath = new File(filepath);
			status = newPath.exists();
		}
		return status;
	}
	
	/**
	 * 读取文件中的字符串
	 * 
	 * @param path
	 *            文件路径
	 * @param FileName
	 *            文件名
	 * @return
	 */
	public static String getStringFromFile(String path, String FileName) throws Exception {
		File file = new File(path, FileName);
		if (checkFileExists(path, FileName)) {
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[512];
				int length = -1;
				while ((length = fileInputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, length);
				}
				outStream.close();
				fileInputStream.close();
				return outStream.toString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}

	}
}