package com.holub.database;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

public class XMLExporter implements Table.Exporter {
	private final Writer out;
	private String[] columnHeads;
	private String tableName;
	private int height;
	private int width;

	public XMLExporter(Writer out) {
		this.out = out;
	}

	public void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException

	{
		this.height = height;
		this.width = width;
		this.tableName = tableName;
		// xml 시작 형식
		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?> \n");

		// 각각의 Column들의 문자열을 columnHeads 배열에 넣어
		columnHeads = new String[width];
		int columnIndex = 0;
		while (columnNames.hasNext())
			columnHeads[columnIndex++] = columnNames.next().toString();

		// 테이블 이름 추가
		out.write("<");
		out.write(tableName == null ? "anonymous" : tableName);
		out.write("> \n");

		// 행을 하나씩 읽어
		storeRow(columnNames); // comma separated list of columns ids
	}

	public void storeRow(Iterator data) throws IOException {
		int columnIndex = 0;
	
		// 하나의 행에 열 개수만큼 추가
		while (data.hasNext()) {
			Object datum = data.next();

			if (datum != null) {
				if (columnIndex == 0) {
					// 하나의 행 시작
					out.write("<row ");
				}
				out.write(columnHeads[columnIndex++] + "=");
				out.write("\"" + datum.toString() + "\" ");
				if (columnIndex == width) {
					// 다음 행으로 넘어감
					out.write("/>\n");
				}
			}
		}
		
		// 마지막 행일 때, 테이블 닫아줌 
		if (height-- == 0) {
			out.write("</");
			out.write(tableName == null ? "anonymous" : tableName);
			out.write("> \n");
		}
	
	}

	public void startTable() throws IOException {
		/* nothing to do */}

	public void endTable() throws IOException {
		/* nothing to do */}

	public static class Test {
		public static void main(String[] args) throws IOException {
			Table people = TableFactory.create("people", new String[] { "First", "Last" });
			people.insert(new String[] { "Allen", "Holub" });
			people.insert(new String[] { "Ichabod", "Crane" });
			people.insert(new String[] { "Rip", "VanWinkle" });
			people.insert(new String[] { "Goldie", "Locks" });

			// 경로를 설정해준 후, 이 곳에 파일을 생성
			Writer out = new FileWriter("/Users/jomin-a/git/2020CAU_Design_Pattern/HolubSQL/testdata/people.xml");
			people.export(new XMLExporter(out));
			out.close();
		}
	}
}
