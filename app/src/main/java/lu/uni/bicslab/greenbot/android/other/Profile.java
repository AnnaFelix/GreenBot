package lu.uni.bicslab.greenbot.android.other;

import java.io.Serializable;

public class Profile implements Serializable {

    String name;
    String image;
    String id;
    String serialscanner;
    int isLogedin;

    public Profile() {

    }

    public Profile(String name, String image, String id, String serialscanner, int isLogedin) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.serialscanner = serialscanner;
        this.isLogedin = isLogedin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialscanner() {
        return serialscanner;
    }

    public void setSerialscanner(String serialscanner) {
        this.serialscanner = serialscanner;
    }

    public int isLogedin() {
        return isLogedin;
    }

    public void setLogedin(int logedin) {
        isLogedin = logedin;
    }
}
