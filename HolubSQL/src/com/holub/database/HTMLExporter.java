package com.holub.database;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

public class HTMLExporter implements Table.Exporter {

	public HTMLExporter()
	{	
	}

	public void storeMetadata( String tableName,
							   int width,
							   int height,
							   Iterator columnNames ) throws IOException

	{	
	}

	public void storeRow( Iterator data ) throws IOException
	{	
	}

	public void startTable() throws IOException {/*nothing to do*/}
	public void endTable()   throws IOException {/*nothing to do*/}
}
