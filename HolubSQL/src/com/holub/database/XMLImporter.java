package com.holub.database;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.holub.tools.ArrayIterator;

public class XMLImporter implements Table.Importer {
	private String[] columnNames;
	private String tableName;

	// Get Document Builder
	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document document;
	Element root;

	// 현재 읽고 있는 행번호를 나타내는 
	int loadRowNum = 0;

	public XMLImporter(File file) throws SAXException, IOException, ParserConfigurationException {
		this.factory = DocumentBuilderFactory.newInstance();
		this.builder = factory.newDocumentBuilder();

		document = builder.parse(file);
		document.getDocumentElement().normalize();
	}

	public void startTable() throws IOException {
		// table 이름을 얻음
		root = document.getDocumentElement();
		tableName = root.getNodeName();
		// 첫번째 행에서 row tag 이용하여 column의 이름을 가져옴
		NodeList children = document.getElementsByTagName("row");
		Node node = children.item(0);
		Element element = (Element) node;
		// column의 수를 지정해줌
		columnNames = new String[element.getAttributes().getLength()];
		for (int i = 0; i < element.getAttributes().getLength(); i++) {
			columnNames[i] = element.getAttributes().item(i).getNodeName();
		}
	}

	public String loadTableName() throws IOException {
		return tableName;
	}

	public int loadWidth() throws IOException {
		return columnNames.length;
	}

	public Iterator loadColumnNames() throws IOException {
		return new ArrayIterator(columnNames);
	}

	// 하나의 행씩 반환해주는 함수 
	public Iterator loadRow() throws IOException {
		Iterator row = null;
		NodeList children = document.getElementsByTagName("row");
		
		//하나의 행에 들어있는 데이터를 받아줄 문자열 배열 
		String[] tableData = new String[columnNames.length];

		// 행을 다 읽었을 경우 Null을 반환해줌
		if (loadRowNum == children.getLength()) {
			return null;
		}

		// 현재 행을 읽어옴 
		Node node = children.item(loadRowNum);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			if (element.getNodeName().equals("row")) {
				for (int j = 0; j < columnNames.length; j++) {
					tableData[j] = element.getAttribute(columnNames[j]);
				}
				row = new ArrayIterator(tableData);
			}
		}
		
		// 현재 읽고 있는 행의 수를 증가시켜줌 
		loadRowNum++;

		return row;
	}

	public void endTable() throws IOException {
	}

	public static class Test {
		public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
			File file = new File("/Users/jomin-a/git/2020CAU_Design_Pattern/HolubSQL/testdata/people.xml");
			Table people = new ConcreteTable(new XMLImporter(file));
	
			System.out.println(people.toString());
		}
	}
}
