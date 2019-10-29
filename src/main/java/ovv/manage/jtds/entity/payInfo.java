package ovv.manage.jtds.entity;

import java.io.Serializable;

public class payInfo implements Serializable {
    private String id;
    private String name;

    public payInfo(){
    }
    public payInfo(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
