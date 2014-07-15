package nationbuilder.lib.data.map.enums;

/**
 * Created by patrick on 7/14/14.
 */
public enum RESOURCELOCATION {

    SURFACE("Surface"),SUBSURFACE("Subsurface"),EMBEDDEDROCK("Embeddedrock")
    ,DEEPEMBEDEDDROCK("Deepembeddedrock"),SUBTERRAINIAN("Subterrainian"),CRUST("Crust");

    private String name;
    RESOURCELOCATION(String name)
    {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
