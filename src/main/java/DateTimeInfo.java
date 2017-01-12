import java.io.Serializable;

/**
 * Created by ideadapt on 12.01.17.
 */
public class DateTimeInfo implements Serializable {
    private String info;
    public static final Long serialVersionUID = 1L;

    public DateTimeInfo(String info){
        this.info = info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
