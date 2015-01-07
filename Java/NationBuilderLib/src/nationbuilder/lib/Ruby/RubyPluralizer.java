package nationbuilder.lib.Ruby;

/**
 * Created by patrick on 1/8/15.
 */
public class RubyPluralizer {

    public static String Pluralize(String name)
    {
        return name + "s";
    }
    public static String DePluralize(String name)
    {
        String result = "";
        if(name.substring(name.length()-3,name.length()).equals("ies"))
        {
            result =  name.replaceAll("ies$","y");
        }
        else
        {
            return name.substring(0,name.length()-1);
        }

        return result;

    }
}
