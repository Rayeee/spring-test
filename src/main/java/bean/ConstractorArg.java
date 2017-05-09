package bean;

/**
 * Created by zhugongyi on 2017/4/27.
 */
public class ConstractorArg {

    private String arg0;

    private String arg1;

    public ConstractorArg() {
    }

    public ConstractorArg(String arg0, String arg1) {
        this.arg0 = arg0;
        this.arg1 = arg1;
    }

    public String getArg0() {
        return arg0;
    }

    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }
}
