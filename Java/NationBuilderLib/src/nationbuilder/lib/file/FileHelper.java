package nationbuilder.lib.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by patrick on 10/1/14.
 */
public class FileHelper
{
	private static String linebreakplaceholder = "{br}";
	public static void writeString(String path,String data) throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter(path,"UTF-8");
		writer.print(data);
		writer.close();
	}
	public static String readFile(String path,boolean useLbplaceHolder) throws IOException
	{
		String result = "";

		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int nol = readLines(path);

		for(int i=0;i<nol;i++)
		{
			result += textReader.readLine();
			if(useLbplaceHolder)
			{
			  result += linebreakplaceholder;
			}

		}
		return  result;
	}
	public static String removelinebreakPlaceholder(String data)
	{
		return data.replace(linebreakplaceholder,"\n");
	}
	public static int readLines(String path) throws IOException
	{
		int result = 0;

		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);

		String aLine;

		while((aLine = bf.readLine()) != null)
		{
			result++;

		}
		return result;
	}

}
