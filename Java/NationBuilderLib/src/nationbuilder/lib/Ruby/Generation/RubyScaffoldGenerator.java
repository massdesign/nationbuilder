package nationbuilder.lib.Ruby.Generation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Association.annotation.IgnoreInRails;
import nationbuilder.lib.data.map.xml.Configuration;
import nationbuilder.lib.file.FileHelper;

/**
 * Created by patrick on 10/1/14.
 */
public class RubyScaffoldGenerator
{
	private String scaffoldGenerateCommand = "rails generate scaffold";
	private String scaffoldDestroyCommand = "rails destroy scaffold";
	private String rablidfilename = "id.json.rabl";
	private String cpCommand = "cp";
	// id.json.rabl file template
	private String rablTemplate = "Rabl.configure do |config|\n"
								  + " config.include_json_root = false\n"
								  + " config.enable_json_callbacks = true\n"
								  + "end\n"
								  + "object {object}\n"
								  + "attributes :id\n";
	// create method template
	private String createTemplate = "    @user = User.new(user_params)\n"
									+ "   respond_to do |format|\n" + "\t\tif @user.save\n"
									+ "\t\t\tformat.json { render action: 'id', status: :created, location: @user }\n"
									+ "\t\telse\n" + "\t\t  render action: 'new'\n" + "\t\tend\n" + "   end\n   end";

	// forgery method for every method
	private String forgeryTemplate = "  protect_from_forgery :secret => 'salty_phrase',\n"
									 + "\t\t       :except => :create";


	public String createScaffold(Class<?> clazz)
	{
		String result = scaffoldGenerateCommand;
	    result += " " + clazz.getSimpleName() + " ";

		for(String p : getParameters(clazz))
		{
			result += p + " ";
		}
		result = result.substring(0,result.length()-1);
		return result;
	}
	public String destroyScaffold(Class<?> clazz)
	{
		String result = scaffoldDestroyCommand;
		result += " " + clazz.getSimpleName() + " ";

		return result;
	}

	public String createRablIdFilePath(Class<?> clazz)
	{
		String modelname =   clazz.getSimpleName().toLowerCase();
		String result = "";
		result += Configuration.RubyHomeDir + "/views/" + pluralize(modelname) + "/" + rablidfilename;
		return  result;
	}
	public String createControllerFilePath(Class<?> clazz)
	{
		String tempPath = "/home/patrick/Thuis/Scratchpad/tests_controller.rb";
		String result = tempPath;

		 String objectName =  clazz.getSimpleName().toLowerCase();
		 String filename = objectName + "_controller.rb";
		return result;
	}

	public void writeControllerToFile(Class<?> clazz)
	{
		String file = createControllerFilePath(clazz);

		try
		{
		  String data = FileHelper.readFile(file,true);


			final Pattern createPattern = Pattern.compile("def create(.+?)def update",Pattern.MULTILINE);
			final Matcher createMatcher = createPattern.matcher(data);
			createMatcher.find();
			String functionContents = createMatcher.group(1);

			data =  data.replace(functionContents,"\n\t" + createTemplate + "\n  ");
			data = FileHelper.removelinebreakPlaceholder(data);
			FileHelper.writeString("/tmp/test.rb",data);
			System.out.println();

		}
		catch (IOException e)
		{
			Log.write(e, LogType.ERROR);
		}

	}

	public void writeRablidFileToTemp(Class<?> clazz)
	{
		try
		{
			String objectName = clazz.getSimpleName().toLowerCase();
			String rabldata =   rablTemplate.replace("{object}","@" + objectName);
			FileHelper.writeString("/tmp/" + rablidfilename,rabldata);
		}
		catch (FileNotFoundException e)
		{
			Log.write(e, LogType.ERROR);
		}
		catch (UnsupportedEncodingException e)
		{
			Log.write(e, LogType.ERROR);
		}
	}
	public String createCpcommand(Class<?> clazz)
	{
		return cpCommand +  " /tmp/" + rablidfilename + " " + createRablIdFilePath(clazz);
	}
	// TODO: all english words ending with y need also to be converted to ie
	private String pluralize(String s)
	{
		return s + "s";
	}
	private List<String> getParameters(Class<?> clazz)
	{
		List<String> result = new ArrayList<>();




		Class<?> superclass = clazz;
		do {

			Field[] fields = superclass.getDeclaredFields();
		for(int i=0;i<fields.length;i++)
		{
			if(!fields[i].isAnnotationPresent(IgnoreInRails.class))
			{
				String name = fields[i].getName();
				String type = fields[i].getType().getSimpleName();
				String rubyType = getRubyType(type);
				result.add(name + ":" + rubyType);
			}

		}
			superclass = superclass.getSuperclass();
		}while(superclass != null);
		return result;
	}
	private String getRubyType(String type)
	{
		String result;

		if(type.equals("String"))
		{
			result = "string";
		}
		else
		{
			result = "string";
		}
		return result;
	}



}
