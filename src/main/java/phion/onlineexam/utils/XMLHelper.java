package phion.onlineexam.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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

	
	public static void main(String[] args) {
		Map m = xmlToMap("file/books.xml");
		System.out.println(m);
	}
}
