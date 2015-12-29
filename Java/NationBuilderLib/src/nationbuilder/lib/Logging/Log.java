package nationbuilder.lib.Logging;

/**
 * Created by patrick on 7/9/14.
 */
public class Log {

    private static boolean disableAllLogging = false;


    public static void write(String message,LogType type)
    {
        if(!disableAllLogging)
        {
            String logline = "";

            switch (type)
            {
            case WARNING:
                logline += "WARNING: ";
                break;
            case ERROR:
                logline += "ERROR: ";
                break;
            case INFO:
                logline += "INFO: ";
                break;
            }
            logline += message;
            System.out.println(logline);
        }
    }
    public static void write(Exception ex,LogType type)
    {
        write(ex.getMessage(),type);
    }
    public static void writeError(String message)
    {
        write(message,LogType.ERROR);
    }
    public static void writeWarning(String message)
    {
        write(message,LogType.WARNING);
    }
    public static void writeInfo(String message)
    {
        write(message,LogType.INFO);
    }


    public static boolean isDisableAllLogging()
    {
        return disableAllLogging;
    }

    public static void setDisableAllLogging(boolean disableAllLogging)
    {
        Log.disableAllLogging = disableAllLogging;
    }
}
