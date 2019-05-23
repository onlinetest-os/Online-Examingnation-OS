package phion.onlineexam.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import phion.onlineexam.bean.Msg;

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
			System.out.println(absPath);
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

	public static void down(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 模拟文件，myfile.txt为需要下载的文件
		String fileName = request.getSession().getServletContext().getRealPath("test/download") + "/myfile.txt";
		// 获取输入流
		InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
		// 假如以中文名下载的话
		String filename = "下载文件.txt";
		// 转码，免得文件名中文乱码
		filename = URLEncoder.encode(filename, "UTF-8");
		// 设置文件下载头
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = 0;
		while ((len = bis.read()) != -1) {
			out.write(len);
			out.flush();
		}
		out.close();
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

	public static void main(String[] args) {

	}

}
