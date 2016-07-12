import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import nationbuilder.lib.Ruby.configuration.RubyConfiguration;
import nationbuilder.lib.data.map.xml.RubyDIPropertyLoader;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {

      /*  long startTime  = System.currentTimeMillis();

        final RubyContext context = new DefaultRubyContextFactory()
        {
            @Override
            public void loadCustomServices()
            {
                // NOTE: Geen custom  services atm
            }
        }.createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT,Main.class);
        WorldLoader worldLoader = new WorldLoader(context);
      //  worldLoader.Run();
        long endtime = System.currentTimeMillis();

        Date date = new Date((endtime - startTime));
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted = formatter.format(date);
        System.out.println("Total running time " + dateFormatted);

        */
        // java assist test code

                /* Create object of ClassPool */
    //    ClassPool objClassPool = ClassPool.getDefault();
        /**
         * Create an object of CtClass.
         * Get the instance of class you want to inject the code.
         * Be careful while accessing the class. You have to specify whole the package name.
         */
      //  CtClass objCtClass = objClassPool.get("CommonUtil");
        /**
         * Create an object of CtMethod.
         * Get the method where you want to inject the code.
         */
      //  CtMethod objCtMethod = objCtClass.getDeclaredMethod("printToConsole");
        /* Inject the code at the begging of the method */
       // objCtMethod.insertBefore(
         //"{ System.out.println(\"Hello World! at the beggining of method [Runtime from Test class]\"); }");
        /* Inject the code at the end of the method */
      //  objCtMethod.insertAfter("{ System.out.println(\"Hello World! at the end of method [Runtime from Test class]\"); }");
        /* Create an object of Class */
       // Class objClass = objCtClass.toClass();
        /* Create an instance of CommonUtil class using objClass */
      //  CommonUtil objCommonUtil = (CommonUtil) objClass.newInstance();
        /* Execute method printToConsole */
     //   objCommonUtil.printToConsole();

        /*ClassPool objClassPool = ClassPool.getDefault();
        CtClass objectCtClass = objClassPool.get("nationbuilder.lib.data.map.entities.Tile");
        CtMethod objCtMethod = objectCtClass.getDeclaredMethod("setXposition");
        objCtMethod.insertAfter("this.setDirty(true);");

        Class newClassDef = objectCtClass.toClass();
        Tile tile = (Tile)newClassDef.newInstance();
        */


        RubyDIPropertyLoader propertyLoader = new RubyDIPropertyLoader();
        propertyLoader.load(RubyConfiguration.class);

        RubyDiClassLoader rubyDiClassLoader = new RubyDiClassLoader(Main.class.getClassLoader(),propertyLoader.getRubyConfiguration().getClassloader_packages());
        Class clazz = rubyDiClassLoader.loadClass("RubyDiMain");
        Class mainArgType[] = { (new String[0]).getClass() };
        Method main = clazz.getMethod("main", mainArgType);
        main.invoke(null, new String [1]);
    }

}



