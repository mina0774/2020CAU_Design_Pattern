package com.holub.database;

import java.io.FileWriter;
import java.io.IOException;
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
		out.write("<table>");
		// 행을 하나씩 읽어
		storeRow( columnNames ); // comma separated list of columns ids
	}

	public void storeRow( Iterator data ) throws IOException
	{	int i = width;
		int h = height;
		// 하나의 행 시작 
		out.write("<tr>");
		// 하나의 행에 열 개수만큼 추
		while( data.hasNext() )
		{	Object datum = data.next();

			if( datum != null )	{
				out.write("<td>");
				out.write( datum.toString() );
			}
			if( --i > 0 ) {
				out.write("</td>");
			}
		}
		// 다음 행으로 넘어감 
		out.write("</tr>");
		
		// 마지막 행일 때, 테이블 닫고, html 형식도 닫아
		if(--h==0) {
			out.write("</table></html>");
		}
	}

	public void startTable() throws IOException {/*nothing to do*/}
	public void endTable()   throws IOException {/*nothing to do*/}
	
	public static class Test
	{ 	public static void main( String[] args ) throws IOException
		{	
			Table people = TableFactory.create( "people",
						   new String[]{ "First", "Last"		} );
			people.insert( new String[]{ "Allen",	"Holub" 	} );
			people.insert( new String[]{ "Ichabod",	"Crane" 	} );
			people.insert( new String[]{ "Rip",		"VanWinkle" } );
			people.insert( new String[]{ "Goldie",	"Locks" 	} );

			// 경로를 설정해준 후, 이 곳에 파일을 생성
			Writer out = new FileWriter( "/Users/jomin-a/git/2020CAU_Design_Pattern/HolubSQL/testdata/people.html" );
			people.export( new HTMLExporter(out) );
			out.close();
		}
	}
}


