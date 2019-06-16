package phion.onlineexam.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import phion.onlineexam.bean.Msg;
import phion.onlineexam.bean.Student;

/**
 * 实现上传与下载文件的功能
 * 
 * @author 15037
 *
 */
public class FileHelper {

	/**
	 * 文件上传功能
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean upload(MultipartFile file, HttpServletRequest request, String path,String newFileName) {
		try {
			String absPath = request.getSession().getServletContext().getRealPath(path);
			String fileName = file.getOriginalFilename();
			//如果有自定义的文件名，则使用
			if(newFileName!=null) fileName = newFileName;
			
			File dir = new File(absPath, fileName);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			// MultipartFile自带的解析方法
			file.transferTo(dir);
			System.out.println("上传文件---->路径：："+absPath);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 文件下载功能
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	public static Msg download(HttpServletRequest request, HttpServletResponse response,String folderPath,String fileName) throws Exception {
		folderPath = request.getSession().getServletContext().getRealPath(File.separator)+folderPath;
		System.out.println("folderPath:"+folderPath);
		File folder = new File(folderPath);
		
		//检查文件夹是否存在
		if (!folder.isDirectory()) {
		    folder.mkdirs();
		    System.out.println("创建文件夹:"+folder.getAbsolutePath());
		    return Msg.fail().setMsg("文件目录不存在!");
		}
		
		//检查文件是否存在
		System.out.println("filepath:"+folderPath+fileName);
		File file = new File(folderPath+fileName);
		if(!file.exists()) {
			System.out.println("下载文件：文件不存在！");
			return Msg.fail().setMsg("文件不存在!");
		}
		
		// 获取输入流
		InputStream bis = new BufferedInputStream(new FileInputStream(file));
		// 假如以中文名下载的话
		//String filename = "下载文件.txt";
		// 转码，免得文件名中文乱码
		fileName = URLEncoder.encode(fileName, "UTF-8");
		// 设置文件下载头
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = 0;
		while ((len = bis.read()) != -1) {
			out.write(len);
			out.flush();
		}
		out.close();
		return Msg.success();
	}
	
	/**
	 * 下载压缩文件夹
	 * @throws IOException 
	 */
	
	public static Msg downloadZip(HttpServletRequest request, HttpServletResponse response,String folderPath) throws IOException {
		folderPath = request.getSession().getServletContext().getRealPath(File.separator)+folderPath;
		System.out.println("folderPath:"+folderPath);
		File folder = new File(folderPath);
		
		//检查文件夹是否存在
		while (!folder.isDirectory()) {
		    folder.mkdirs();
		}
		
		
		String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
		//创建临时文件夹用于存放压缩文件
		 File temDir = new File(rootPath + "/" + UUID.randomUUID().toString().replaceAll("-", ""));
		 if(!temDir.exists()){
			  temDir.mkdirs();
		 }
		 
		 //生成需要下载的文件，存放在临时文件夹内
		 File srcFile = folder;
		 File desFile = new File(temDir.getAbsolutePath()+File.separator+srcFile.getName()+".zip");
		 FileOutputStream fos = new FileOutputStream(desFile);
		 ZipUtils.toZip(srcFile.getAbsolutePath(), fos, true);
		 
		// 设置文件下载头
		response.addHeader("Content-Disposition", "attachment;filename=anwsers.zip");
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		
		ZipUtils.toZip(temDir.getAbsolutePath(), response.getOutputStream(),true);

		//删除临时文件和文件夹
		File[] listFiles = temDir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            listFiles[i].delete();
        }
        temDir.delete();
        
        
        return Msg.success();
	}
	
	/**
	 * 提供文件，下载压缩文件夹
	 * @throws IOException 
	 */
	
	public static Msg downloadZipForExport(HttpServletRequest request, HttpServletResponse response,File file) throws IOException {
		
		String rootPath = file.getParent();
		System.out.println(rootPath);
		//创建临时文件夹用于存放压缩文件
		 File temDir = new File(rootPath + "/" + UUID.randomUUID().toString().replaceAll("-", ""));
		 if(!temDir.exists()){
			  temDir.mkdirs();
		 }
		 
		 //生成需要下载的文件，存放在临时文件夹内
		 File srcFile = file;
		 File desFile = new File(temDir.getAbsolutePath()+File.separator+srcFile.getName()+".zip");
		 FileOutputStream fos = new FileOutputStream(desFile);
		 ZipUtils.toZip(srcFile.getAbsolutePath(), fos, true);
		 
		// 设置文件下载头
		response.addHeader("Content-Disposition", "attachment;filename=export.zip");
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		
		ZipUtils.toZip(temDir.getAbsolutePath(), response.getOutputStream(),true);

		//删除临时文件和文件夹
		File[] listFiles = temDir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            listFiles[i].delete();
        }
        temDir.delete();
        
        
        return Msg.success();
	}
	
	

	/**
	 * 删除某个路径下的文件与文件夹
	 * 
	 */
	public static void deleteFile(HttpServletRequest request, String path) {
		String absPath = request.getSession().getServletContext().getRealPath(path);

		System.out.println("absPath:" + absPath);
		File file = new File(absPath);
		cycleDelete(file);
	}

	/**
	 * 循环删除文件或文件夹
	 * 
	 * @param file
	 * 
	 */
	public static void cycleDelete(File file) {
		if (!file.exists()) {
			System.out.println("文件不存在!");
		}
		if (file.isFile()) {
			System.out.println("文件：" + file.getPath());
			file.delete();
			System.out.println("----删除文件：" + file.getName() + "------");
		}
		if (file.isDirectory()) {
			System.out.println("文件夹：" + file.getPath());
			File[] fs = file.listFiles();
			for (File f : fs) {
				cycleDelete(f);
			}
			if (file.delete()) {
				System.out.println("----删除文件夹：" + file.getName() + "------");
			} else {
				System.out.println("文件夹删除失败");
			}
		}
	}

	public static File M2F(MultipartFile file) throws IOException {
		File f = null;
		if (file.equals("") || file.getSize() <= 0) {
			file = null;
		} else {
			InputStream ins = file.getInputStream();
			f = new File(file.getOriginalFilename());
			inputStreamToFile(ins, f);
		}
		return f;
	}

	/**
	 * 输入流转换为文件
	 * @param ins
	 * @param file
	 */
	public static void inputStreamToFile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 学生提交信息导出到文件
	 * @param ins
	 * @param file
	 */
	public static void dataToXmlFile(List students, File file) {
		StringBuilder dataSb = new StringBuilder();
		Iterator<Student> itor = students.iterator();
		dataSb.append("学号").append("\t")
			.append("姓名").append("\t")
			.append("班级").append("\t")
			.append("最后提交时间").append("\r\n");
		
		while(itor.hasNext()) {
			Student s = itor.next();
			String[] infos = s.getCommitinfo()==null?new String[]{"未提交"}:
				s.getCommitinfo().split(" ");
			String info = infos[0];
			
			dataSb.append(s.getStuNumber()).append("\t")
				.append(s.getStuName()).append("\t")
				.append(s.getStuClass()).append("\t")
				.append(info).append("\t")
				.append("\r\n");
		}
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(dataSb.toString());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得utf8字符串
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String getUTF8String(String str) throws IOException{
		byte[] bytes;
		bytes = str.getBytes();
		String temp = new String(bytes,"utf8");
		return temp;
	}
	
	public static void main(String[] args) {
		String in = "大王叫我來巡山！";
		
	}

}
