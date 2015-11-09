package nationbuilder.lib.Ruby.orm;

public class ID {

	private String id;

    private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {

        boolean result = false;

        if(obj != null && obj instanceof  ID && ((ID)obj).getType() != null ) {
            if(((ID)obj).getId().equals(this.getId()) && ((ID)obj).getType().equals(this.getType()))
            {
                result = true;
            }
        }
        else {
            result = super.equals(obj);
        }
        return  result;
    }
}
