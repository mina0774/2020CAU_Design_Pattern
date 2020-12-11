package com.holub.database;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.Test;

public class JunitTest_HTMLExporter {
	@Test
	public void test() throws IOException {
		Table people = TableFactory.create("people", new String[] { "First", "Last" });
		people.insert(new String[] { "Allen", "Holub" });
		people.insert(new String[] { "Ichabod", "Crane" });

		// 경로를 설정해준 후, 이 곳에 파일을 생성
		Writer out = new FileWriter("/Users/jomin-a/git/2020CAU_Design_Pattern/HolubSQL/testdata/people.html");
		people.export(new HTMLExporter(out));
		out.close();

		// html 파일에서 생성되어야할 기대 결과값
		String expectation_str = "<html><head>people</head><table>" + "<tr>" + "<td>First</td>"
				+ "<td>Last</td>" + "</tr>" + "<tr>" + "<td>Allen</td>" + "<td>Holub</td>" + "</tr>"
				+ "<tr>" + "<td>Ichabod</td>" + "<td>Crane</td>" + "</tr>" + "</table></html>";

		// 저장된 파일을 읽어와서 기대 결과값과 실제 결과값을 비교
		BufferedReader in = new BufferedReader(
				new FileReader("/Users/jomin-a/git/2020CAU_Design_Pattern/HolubSQL/testdata/people.html"));
		String str = in.readLine();
		String result_str = "";

		while (str != null) {
			result_str += str;
			str = in.readLine();
		}

		assertEquals(expectation_str, result_str);
	}
}
