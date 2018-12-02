package tankWar;
///����ļ�������ȡ�����ļ��е���Ϣ
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.net.URL;

public class XMLUtil {
	// �÷������ڴ�XML�����ļ�����ȡ���������Ϣ������һ���ַ�������
	///��������
	public String getByName(String nameStr) {
		try {
			String ret;
			// ����DOM�ĵ�����
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			String XMLFileStr = "/config.xml";
			URL XMLFileURL = this.getClass().getResource(XMLFileStr);
			doc = builder.parse(XMLFileURL.openConnection().getInputStream());

			// ��ȡ�����������ı����
			NodeList nl = doc.getElementsByTagName(nameStr);
			Node classNode = nl.item(0).getFirstChild();
			ret = classNode.getNodeValue();

			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// �÷������ڴ�XML�����ļ�����ȡ���������Ϣ������һ���ַ����������
	///��������
	public String[] getByNames(String nameStr) {
		try {
			String[] ret;
			// ����DOM�ĵ�����
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			String XMLFileStr = "/config.xml";
			URL XMLFileURL = this.getClass().getResource(XMLFileStr);
			doc = builder.parse(XMLFileURL.openConnection().getInputStream());

			// ��ȡ�����������ı����
			NodeList nl = doc.getElementsByTagName(nameStr);
			ret = new String[nl.getLength()];
			for (int i = 0; i < nl.getLength(); i++) {
				Node classNode = nl.item(i).getFirstChild();
				ret[i] = classNode.getNodeValue();
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// �÷������ڴ�XML�����ļ�����ȡ������������������һ��ʵ������
	public Object getBean() {
		try {
			// ����DOM�ĵ�����
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File("src//config.xml"));

			// ��ȡ�����������ı����
			NodeList nl = doc.getElementsByTagName("className");
			Node classNode = nl.item(0).getFirstChild();
			String cName = classNode.getNodeValue();

			// ͨ����������ʵ�����󲢽��䷵��
			Class c = Class.forName(cName);
			Object obj = c.newInstance();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}