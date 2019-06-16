package phion.onlineexam.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import phion.onlineexam.bean.StaticResources;

public class XMLHelper {
	public static String path  ;
	
	public static Map xmlToMap(String xmlFilePath){
		if(new File(xmlFilePath).exists()) path = xmlFilePath;
        Map res = null;
		// 解析xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
		try {
            // 通过reader对象的read方法加载xml文件,获取docuemnt对象。
            Document document = reader.read(new File(path));
            // 通过document对象获取根节点
            Element root = document.getRootElement();
           
            //将root转化为map
            res = getMap(root);
            
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return res;
	}
	
	/**
	 * 递归的将一个document转化为map
	 * @param root
	 * @return
	 */
	private static Map getMap(Element root) {
        // 通过element对象的elementIterator方法获取迭代器
		Iterator it = root.elementIterator();
        String name = root.getName();
        //System.out.println(name);
        Map res = new HashMap<>();//保存父元素
        Map value = new HashMap<>();//保存子元素
        
        // 遍历迭代器，获取根节点中的信息
        while(it.hasNext()) {
        	Element ele = (Element) it.next();
        	//System.out.println(ele.getName());
        	String key = ele.getName();
        	if(ele.elements().size()>0) {
        		value = getMap(ele);
            	res.put(key, value);
        	}else {
        		res.put(key, ele.getStringValue());
        	}
        }
        
		return res;
	}
	
	
	
	public static boolean updateXml(String xmlFilePath,Map<String,String> config) throws IOException {
        System.out.println("开始更新配置文件");
		File f = new File(xmlFilePath);
		if(config==null||!f.exists()) return false;
		//遍历需要修改的值
		Document doc = getDocument(f.getPath());
		Element root = doc.getRootElement();
		Iterator<Element> itor = root.elementIterator();
        while(itor.hasNext()) {
        	root = itor.next();
        	String text = "";
        	if((text=config.get(root.getName()))!=null) {
        		root.setText(text);
        		System.out.println("更新"+root.getName()+"的值为："+text);
        	}
        }
        XMLWriter output = new XMLWriter(
        	      new FileWriter(f));
        	     output.write(doc);
        	     output.close();
        System.out.println("更新配置完成");
		return true;
	}
	
	
	/**
     * 得到XML文档
     * 
     * @param xmlFile
     *            文件名（路径）
     * @return XML文档对象
     * @throws DocumentException
     */
    public static Document getDocument(String xmlFilePath) {
        SAXReader reader = new SAXReader();
        reader.setEncoding("UTF-8");
        File file = new File(xmlFilePath);
        try {
            if (!file.exists()) {
                return null;
            } else {
                return reader.read(file);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e + "->指定文件【" + xmlFilePath + "】读取错误");
        }
    }

	
	public static void main(String[] args) {
		path = System.getProperty("user.dir")+"/src/files/config.xml";
		Map m = xmlToMap("files/books.xml");
		//System.out.println(m);
		Map<String,String> config = new HashMap<>();
		config.put(StaticResources.MAX_UPLOAD_SIZE, 1+"");
		try {
			updateXml(path, config);
		} catch (IOException e) {
			System.out.println("更新配置文件失败");
			e.printStackTrace();
		}
		
	}

	
}
