package com.holub.database;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class JunitTest_XMLImporter {
	@Test
	public void test() throws IOException, SAXException, ParserConfigurationException {
		File file = new File("/Users/jomin-a/git/2020CAU_Design_Pattern/HolubSQL/testdata/people.xml");

		// xml 파일을 토대로 만든 실제 people table 결과
		Table people = new ConcreteTable(new XMLImporter(file));

		// 기대되는 테이블의 결과값
		String expectation_table = "people\n" + "First	Last	\n" + "----------------------------------------\n"
				+ "Allen	Holub	\n" + "Ichabod	Crane	\n" + "Rip	VanWinkle	\n" + "Goldie	Locks	\n";

		assertEquals(people.toString(), expectation_table);
	}
}
