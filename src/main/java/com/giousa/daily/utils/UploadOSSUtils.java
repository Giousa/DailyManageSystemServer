package com.giousa.daily.utils;

import com.aliyun.oss.OSSClient;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.http.entity.ContentType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadOSSUtils {


	private static String endpoint = "oss-cn-shanghai.aliyuncs.com";
	private static String accessKeyId = "WWWWWWWWWWWW";
	private static String accessKeySecret = "$$$$$$$$$";
	private static String bucketName = "chdspine";
	private static String picAddressHead = "https://axk.oss-cn-hangzhou.aliyuncs.com/";

	/**
	 * 上传单张图片到云端
	 *
	 * @return
	 * @throws Exception
	 */
	public static String uploadSinglePic(MultipartFile file, String fileName, String suffix) throws Exception {


		String picId = KeyUtil.getKeyId();
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 上传文件流
		System.out.println("fileName: " + fileName);
		System.out.println("suffix: " + suffix);

		String path = fileName + "/" + picId + suffix;
 		System.out.println("picId: " + picId);
 		System.out.println("fileName " + fileName + "/");

		ossClient.putObject(bucketName, path, file.getInputStream());
		// 关闭client
		ossClient.shutdown();

System.out.println("path: " + path);
		return path;
	}


	/**
	 * url转变为 MultipartFile对象
	 *
	 * @param url
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static MultipartFile createFileItem(String url, String fileName) throws Exception {
		FileItem item = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setReadTimeout(30000);
			conn.setConnectTimeout(30000);
			//设置应用程序要从网络连接读取数据
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream is = conn.getInputStream();

				FileItemFactory factory = new DiskFileItemFactory(16, null);
				String textFieldName = "uploadfile";
				item = factory.createItem(textFieldName, ContentType.APPLICATION_OCTET_STREAM.toString(), false, fileName);
				OutputStream os = item.getOutputStream();

				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.close();
				is.close();
			}
		} catch (IOException e) {
			throw new RuntimeException("文件下载失败", e);
		}

		return new CommonsMultipartFile(item);

	}


}
