package com.jt.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.service.FileService;
import com.jt.vo.EasyUIImage;

@Controller
public class FileController {
	@Autowired
	private FileService fileService;
	
	/**
	 * 文件上传的结构的步骤
	 * 1.页面的form表单 enctype = "multipart/form-data";
	 * 2.MultipartFile
	 * 3.
	 * 
	 * 1.准备文件的存储的根目录
	 * 2.准备文件的名称
	 * 3.实现文件的上传
	 * @param fileImage
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/file")
	public String fileImage(MultipartFile fileImage) throws IllegalStateException, IOException {
		//定义上传的文件夹
		File fileDir = new File("F:/jt/jt/image");
		if(!fileDir.exists()) {
			//如果文件不存在创建文件夹
			fileDir.mkdirs();
		}
		//动态的获取图片的名称
		String fileName = fileImage.getOriginalFilename();
		File file = new File("F:/jt/jt/image/"+fileName);
		fileImage.transferTo(file);
		return "redirect:/file.jsp";
	}
	/**
	 * 实现jt项目的图片上传操作
	 * 参数名称:uploadFile参数路径:/pic/upload
	 * 数据返回值:
	 * {error:0 ,url:"图片的访问地址",width:"" , height:"" }
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * 
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody
	public EasyUIImage uploadFile(MultipartFile uploadFile)  {
		return fileService.updateFile(uploadFile);
	}
}
