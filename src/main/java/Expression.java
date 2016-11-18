/**
 * Created by piek on 12/06/15.
 */
public class Expression {

    private int nExclamation;
    private int nIntensifiers;
    private int nWeakeners;
    private int nCapitals;


    public Expression() {
        nExclamation = 0;
        nIntensifiers = 0;
        nWeakeners = 0;
        nCapitals = 0;
    }

    public int getnCapitals() {
        return nCapitals;
    }

    public void setnCapitals(int nCapitals) {
        this.nCapitals = nCapitals;
    }

    public void incrementCapitals() {
        this.nCapitals++;
    }

    public int getnExclamation() {
        return nExclamation;
    }

    public void setnExclamation(int nExclamation) {
        this.nExclamation = nExclamation;
    }
    public void incrementExclamation() {
        this.nExclamation++;
    }

    public int getnIntensifiers() {
        return nIntensifiers;
    }

    public void setnIntensifiers(int nIntensifiers) {
        this.nIntensifiers = nIntensifiers;
    }

    public void incrementIntensifiers() {
        this.nIntensifiers++;
    }

    public int getnWeakeners() {
        return nWeakeners;
    }

    public void setnWeakeners(int nWeakeners) {
        this.nWeakeners = nWeakeners;
    }

    public void incrementWeakeners() {
        this.nWeakeners++;
    }

    public String toString () {
        String str = "";
        str += "[";
        str += "\"exclamation\":\""+getnExclamation()+"\",";
        str += "\"capitals\":\""+getnCapitals()+"\",";
        str += "\"weakeners\":\""+getnWeakeners()+"\",";
        str += "\"intensifiers\":\""+getnIntensifiers()+"\"";
        str += "]";
        return str;
    }


}
