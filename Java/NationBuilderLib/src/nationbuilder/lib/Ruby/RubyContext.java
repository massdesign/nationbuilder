package nationbuilder.lib.Ruby;

import com.google.gson.Gson;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.http.data.HttpData;
import nationbuilder.lib.http.data.ID;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Created by patrick on 7/8/14.
 */
public class RubyContext {

    public RubyService getRubyService() {
        return rubyService;
    }
        Gson gson = new Gson();
    public void setRubyService(RubyService rubyService) {
        this.rubyService = rubyService;
    }

    private RubyService rubyService;

    public RubyContext(RubyService service)
    {
     this.rubyService = service;
    }

    public<T extends RubyModel> T createRubyModel(Class<?> clazz)
    {
        try {
            Constructor<?> ctor = clazz.getConstructor();
            T instance =  (T)ctor.newInstance();
           instance.setRubyContext(this);
            return instance;

        } catch (NoSuchMethodException e) {
            Log.write(e, LogType.ERROR);
        } catch (InvocationTargetException e) {
            Log.write(e,LogType.ERROR);
        } catch (InstantiationException e) {
            Log.write(e,LogType.ERROR);
        } catch (IllegalAccessException e) {
            Log.write(e,LogType.ERROR);
        }
        return null;
    }

    public boolean SaveObject(RubyModel object,String resourceUrl) throws IOException {
        Gson gson = new Gson();
        HttpData data =  this.rubyService.postObject(object,resourceUrl);

        ID resultObject  =  gson.fromJson(data.getBody(),ID.class);
        object.setId(resultObject);
        return resultObject != null ? false : true;

    }
    public boolean SaveResource(BaseRubyResourceModel object,String resourceUrl)
    {
        try {
            int fileStatusCode = this.rubyService.postFile(object, "/uploads/");
            return fileStatusCode != 200 ? false : true;
        } catch (IOException e) {
            Log.write(e,LogType.ERROR);
            return  false;
        }
    }

}
