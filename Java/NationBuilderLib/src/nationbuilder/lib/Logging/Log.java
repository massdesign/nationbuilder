package nationbuilder.lib.Logging;

/**
 * Created by patrick on 7/9/14.
 */
public class Log {


    public static void write(String message,LogType type)
    {
        String logline = "";

        switch (type)
        {
           case WARNING:
               logline += "WARNING:";
               break;
           case ERROR:
               logline += "ERROR:";
               break;
           case INFO:
               logline += "INFO:";
               break;
        }

        System.out.println(logline);
    }


}
