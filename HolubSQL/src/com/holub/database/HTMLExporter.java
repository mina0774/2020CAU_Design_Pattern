package com.holub.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;

public class HTMLExporter implements Table.Exporter {
	private final Writer out;
	private int	width;
	private int height;

	public HTMLExporter( Writer out )
	{	this.out = out;
	}

	public void storeMetadata( String tableName,
							   int width,
							   int height,
							   Iterator columnNames ) throws IOException

	{	this.width = width;
	    this.height = height;
	    // html 시작 형식 
		out.write("<html><head>");
		// 헤더에 테이블 이름 추가 
		out.write(tableName == null ? "<anonymous>" : tableName );
		out.write("</head>");
		// 테이블 시작
		out.write("<table>\n");
		// 행을 하나씩 읽어
		storeRow( columnNames ); // comma separated list of columns ids
	}

	public void storeRow( Iterator data ) throws IOException
	{	int i = width;
		// 하나의 행 시작 
		out.write("<tr>\n");
		// 하나의 행에 열 개수만큼 추가 
		while( data.hasNext() )
		{	Object datum = data.next();

			if( datum != null )	{
				out.write("<td>");
				out.write( datum.toString());
				out.write("</td>\n");
			}
		}
		// 다음 행으로 넘어감 
		out.write("</tr>\n");
		
		// 마지막 행일 때, 테이블 닫고, html 형식도 닫아
		if(height--==0) {
			out.write("</table></html>");
		}
	}

	public void startTable() throws IOException {/*nothing to do*/}
	public void endTable()   throws IOException {/*nothing to do*/}
	
	public static class Test
	{ 	
		public static void main( String[] args ) throws IOException
		{	
			
	        
	        
		}
	}
	
	
}



