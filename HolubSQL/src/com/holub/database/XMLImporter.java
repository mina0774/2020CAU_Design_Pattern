package com.holub.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import com.holub.tools.ArrayIterator;

// todo - 여기부터 시작!!!!
public class XMLImporter implements Table.Importer{
	private BufferedReader  in;			// null once end-of-file reached
	private String[]        columnNames;
	private String          tableName;

	public XMLImporter( Reader in ) 
	{	this.in = in instanceof BufferedReader
						? (BufferedReader)in
                        : new BufferedReader(in)
	                    ;
	}
	public void startTable()			throws IOException
	{	tableName   = in.readLine().trim();
		columnNames = in.readLine().split("\\s*,\\s*");
	}
	public String loadTableName()		throws IOException
	{	return tableName;
	}
	public int loadWidth()			    throws IOException
	{	return columnNames.length;
	}
	public Iterator loadColumnNames()	throws IOException
	{	return new ArrayIterator(columnNames);  //{=CSVImporter.ArrayIteratorCall}
	}

	public Iterator loadRow()			throws IOException
	{	Iterator row = null;
		if( in != null )
		{	String line = in.readLine();
			if( line == null )
				in = null;
			else
				row = new ArrayIterator( line.split("\\s*,\\s*"));
		}
		return row;
	}

	public void endTable() throws IOException {}
}
