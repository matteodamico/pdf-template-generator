package it.mattdmc;

public class Property {
   
    public String name;
    public String value;

    public String getName(){
        return this.name;
    }
    public String getValue(){
        return this.value;
    }
	public void setValue(String value2) {
        this.value=value2;
	}
	public void setName(String name2) {
        this.name=name2;
	}
}
