import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Created by piek on 12/06/15.
 */
public class Expression {

    private int nExclamation;
    private int nIntensifiers;
    private int nWeakeners;
    private int nCapitals;
    private int nShifters;


    public Expression() {
        nExclamation = 0;
        nIntensifiers = 0;
        nWeakeners = 0;
        nCapitals = 0;
        nShifters = 0;
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

    public int getnShifters() {
        return nShifters;
    }

    public void setnShifters(int nShifters) {
        this.nShifters = nShifters;
    }

    public void incrementShifters() {
        this.nShifters++;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("exclamation", getnExclamation());
        jsonObject.put("capitals", getnCapitals());
        jsonObject.put("weakeners", getnWeakeners());
        jsonObject.put("intensifiers", getnIntensifiers());
        jsonObject.put("shifters", getnShifters());
        return jsonObject;
    }

    public String toString () {
        String str = "";
        str += "[";
        str += "\"exclamation\":\""+getnExclamation()+"\",";
        str += "\"capitals\":\""+getnCapitals()+"\",";
        str += "\"weakeners\":\""+getnWeakeners()+"\",";
        str += "\"intensifiers\":\""+getnIntensifiers()+"\"";
        str += "\"shifters\":\""+getnShifters()+"\"";
        str += "]";
        return str;
    }

    public int getStrength () {
        int s = 0;
        s += nCapitals+nIntensifiers+nExclamation-nWeakeners;
        return s;
    }

    static Vector<String> readWordList(String pathToFile) {
        Vector<String>  words = new Vector<String>();

        try {
            FileInputStream fis = new FileInputStream(pathToFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader in = new BufferedReader(isr);
            String inputLine = "";
            while (in.ready()&&(inputLine = in.readLine()) != null) {
                if (inputLine.trim().length()>0) {
                    words.add(inputLine.trim());
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Could not find the file = " + pathToFile);
           // e.printStackTrace();
        }
        return words;
    }


}
