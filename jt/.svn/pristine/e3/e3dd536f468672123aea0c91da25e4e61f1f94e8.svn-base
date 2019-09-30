package com.jt.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.vo.EasyUIImage;

import lombok.extern.slf4j.Slf4j;
/**
 * 正则表达式
 * @author 王先生
 *	
 *
 */
@Service
@PropertySource("classpath:properties/image.properties") //将配置文件交给spring容器管理
@Slf4j
public class FileServiceImpl implements FileService{
	@Value("${image.localDirPath}")
	private String localDirPath ; //= "F:/jt/jt/image/";
	@Value("${image.localUrlPath}")
	private String localUrlPath ; //= "http://image.jt.com/";
	/**
	 * 要求 :
	 * 	1.校验图片类型
	 * 2.木马.exe.jpg
	 * 3.份文件存储
	 * 4.防止文件的重名 UUID.jpg
	 */
	/**
	 *
	 */
	@Override
	public EasyUIImage updateFile(MultipartFile uploadFile)  {
		EasyUIImage image = new EasyUIImage();
		// 判断文件的类型, 将字符统一转化为小写
		String fileName = uploadFile.getOriginalFilename().toLowerCase();
		if(!fileName.matches("^.+\\.(png|jpg|gif|jpeg)$")) {
			image.setError(1);
			return image;
		}
		//判断文件是否为恶意程序 利用宽 高 判断
		//使用bufferedImage 来操作图片
		try {
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			if(width  == 0 || height == 0) {
				image.setError(1);
				return image;
			}
			//创建文件夹 yyyy/MM/dd
			String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			String localDir = localDirPath+date;
			File dirFile = new File(localDir);
			if(!dirFile.exists() )
				dirFile.mkdirs();
			//防止文件重名 修改文件的名称
			String uuid = UUID.randomUUID().toString();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			String realName = uuid+fileType;
			//实现文件的上传
			String realFilePath = localDir+"/" +realName; 
			File realFile = new File(realFilePath);
			uploadFile.transferTo(realFile);
			//System.out.println("文件上传成功");
			log.info("文件上传成功");
			image.setHeight(height).setWidth(width);
			//为页面准备一个图片的url的地址
			String url = localUrlPath+date+"/" +realName;
			image.setUrl(url);
			
			
		}catch(IOException e) {
			e.printStackTrace();
			image.setError(1);
			return image;
		}
		return image;
	}

}
