import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.javaassist.CodeInjector;

/**
 * @author patrick.ekkel
 */
public class RubyDiClassLoader extends ClassLoader
{

	private String namespace;
	public RubyDiClassLoader(ClassLoader parent,String namespace) {
		super(parent);

		this.namespace = namespace;
	}

	private byte[] loadClassFileData(String name) throws IOException
	{
		InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
		int size = stream.available();
		byte buff[] = new byte[size];
		DataInputStream in = new DataInputStream(stream);
		in.readFully(buff);
		in.close();
		return buff;
	}
	private Class getClass(String name) throws ClassNotFoundException
	{
		String file = name.replace('.', File.separatorChar) + ".class";
		byte[] b = null;
		try
		{
			// This loads the byte code data from the file
			b = loadClassFileData(file);
			// defineClass is inherited from the ClassLoader class
			// that converts byte array into a Class. defineClass is Final
			// so we cannot override it
			Class c = defineClass(name, b, 0, b.length);
			resolveClass(c);
			return c;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Class loadClass(String name) throws ClassNotFoundException
	{

		Log.writeDebug("Loading Class '" + name + "'");
		// Alleen deze klasse laden met de speciale classloader
		if (name.endsWith("RubyDiMain"))
		{
			Log.writeDebug("Loading Class using RubyDiClassLoader");
			return getClass(name);
		}
		else if(name.startsWith(namespace)) {
			Log.writeDebug("Loading Class '" + name + "' using JavaAssist CodeInjector");
			CodeInjector testje = new CodeInjector(name);
			Class newClassDef = testje.injectAfterMethod("this.setDirty(true);");
			return  newClassDef;
		}
		return super.loadClass(name);
	}

}
