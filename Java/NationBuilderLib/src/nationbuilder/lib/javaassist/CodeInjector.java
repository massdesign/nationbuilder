package nationbuilder.lib.javaassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Association.annotation.Column;

/**
 * @author patrick.ekkel
 */
public class CodeInjector
{

	private String className;

	public CodeInjector(String classname) {
		this.className = classname;
	}

	/**
	 * Add code to given method
	 * @param code String that contains the code
	 * @param method method name
	 * @return
	 */

	public Class injectAfterMethod(String code,String method) {
		Class newClassDef = null;
		ClassPool objClassPool = ClassPool.getDefault();
		CtClass objectCtClass = null;
		try
		{
			objectCtClass = objClassPool.get(this.className);

			CtMethod objCtMethod = objectCtClass.getDeclaredMethod(method);
			objCtMethod.insertAfter(code);
			newClassDef = objectCtClass.toClass();
		}
		catch (NotFoundException | CannotCompileException e)
		{
			Log.write(e, LogType.ERROR);
		}

		return newClassDef;
	}

	/**
	 * Inject given code only at the methods that have this annotation
	 * @param code String that contains the code
	 * @return
	 */
	public Class injectAfterMethod(String code) {

		ClassPool objClassPool = ClassPool.getDefault();
		CtClass objectCtClass = null;
		Class newClassDef = null;
		// Dit bind de CodeInejctor vast ana het RubyDI framework
		Class annotation = Column.class;
		try
		{
			objectCtClass = objClassPool.get(this.className);

			for(CtField field : objectCtClass.getDeclaredFields()) {

				Object columnAnnotation = field.getAnnotation(Column.class);

				if(columnAnnotation != null) {
					Column column = (Column) columnAnnotation;
					CtMethod ctMethod;
					if ((column.setMethod().isEmpty()))
					{
						ctMethod = searchForCtMethod(field.getName(), objectCtClass);

					}
					else
					{
						ctMethod = objectCtClass.getDeclaredMethod(column.setMethod());
					}
					ctMethod.insertAfter(code);


				}

			}

		}
		catch (NotFoundException | CannotCompileException | ClassNotFoundException e)
		{
			Log.write(e, LogType.ERROR);
		}

		try
		{
			newClassDef = objectCtClass.toClass();
			return newClassDef;
		}
		catch (CannotCompileException e)
		{
			Log.write(e,LogType.ERROR);
		}

		return newClassDef;
	}

	public CtMethod searchForCtMethod(String name,CtClass ctClass) {

		CtMethod result = null;
		for(CtMethod method : ctClass.getMethods()) {

			if(method.getName().toLowerCase().equals("set" + name.toLowerCase())) {
				result = method;
				break;
			}
		}
		return result;
	}



	}
