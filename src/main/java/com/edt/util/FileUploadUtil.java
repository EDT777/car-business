package com.edt.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class FileUploadUtil {

	public static String uploadFile(MultipartFile file, String path)
			throws Exception {
		String uuid = UUID.randomUUID().toString();
//		获取真实文件名称
		String fileName = file.getOriginalFilename();
//		获取文件后缀名
		String fileType = fileName.substring(fileName.lastIndexOf("."));
//		拼接当前所在路径
		fileName = "/upload/" + uuid + fileType;
//		指定存放位置
		File targetFile = new File(path, fileName);
//		使用工具把文件内容写到目标文件
		FileUtils.copyInputStreamToFile(file.getInputStream(),targetFile);
		return fileName;
	}

	/**
	 * 删除文件
	 * @param pic
	 */
	public static void deleteFile(String pic) {
		File file=new File(pic);
		if(file.exists()) file.delete();
	}
}
