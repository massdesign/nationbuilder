package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.data.map.converter.converterobjects.ProcessedMap;

/**
 * Created by patrick on 5/19/16.
 */
public class ConverterContext {
    private ProcessedMap processedMap;


    public ConverterContext() {

        this.processedMap = new ProcessedMap();
    }

    public ProcessedMap getProcessedMap()
    {
        return processedMap;
    }

    public void setProcessedMap(ProcessedMap processedMap)
    {
        this.processedMap = processedMap;
    }
}
