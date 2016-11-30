import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by piek on 12/06/15.
 */
public class Emotion {
    /**
     * abide	anger	0
     abide	anticipation	0
     abide	disgust	0
     abide	fear	0
     abide	joy	0
     abide	negative	0
     abide	positive	0
     abide	sadness	0
     abide	surprise	0
     abide	trust	0
     */
    private int     anger;
    private int 	anticipation;
    private int 	disgust;
    private int 	fear;
    private int 	joy;
    private int 	negative;
    private int 	positive;
    private int 	sadness;
    private int 	surprise;
    private int 	trust;

    public Emotion() {

        anger = 0;
        anticipation = 0;
        disgust = 0;
        fear = 0;
        joy = 0;
        negative = 0;
        positive = 0;
        sadness = 0;
        surprise = 0;
        trust = 0;
    }

    public ArrayList<String> getEmotionStringArray () {
        ArrayList<String> emotions = new ArrayList<String>();
        if (anger>0) emotions.add("anger");
        if (anticipation>0) emotions.add("anticipation");
        if (disgust>0) emotions.add("disgust");
        if (fear>0) emotions.add("fear");
        if (joy>0) emotions.add("joy");
        if (negative>0) emotions.add("negative");
        if (positive>0) emotions.add("positive");
        if (sadness>0) emotions.add("sadness");
        if (surprise>0) emotions.add("surprise");
        if (trust>0) emotions.add("trust");
        return emotions;
    }
    public ArrayList<String> getDominantEmotions () {
        ArrayList<String> emo = new ArrayList<String>();
        int top = 0;
        if (anger>top) top = anger;
        if (anticipation>top) top = anticipation;
        if (disgust>top) top = disgust;
        if (fear>top) top = fear;
        if (joy>top) top = joy;
        if (negative>top) top = negative;
        if (positive>top) top = positive;
        if (sadness>top) top = sadness;
        if (surprise>top) top = surprise;
        if (trust>top) top = trust;
        if (top>0) {
            if (anger == top) emo.add("anger");
            if (anticipation == top) emo.add("anticipation");
            if (disgust == top) emo.add("disgust");
            if (fear == top) emo.add("fear");
            if (joy == top) emo.add("joy");
            if (negative == top) emo.add("negative");
            if (positive == top) emo.add("positive");
            if (sadness == top) emo.add("sadness");
            if (surprise == top) emo.add("surprise");
            if (trust == top) emo.add("trust");
        }
        return emo;
    }

    public void setEmotion(String emotionString, int value) {
        if (emotionString.equalsIgnoreCase("anger")) {
            anger = value;
        }
        else if (emotionString.equalsIgnoreCase("anticipation")) {
            anticipation = value;
        }
        else if (emotionString.equalsIgnoreCase("disgust")) {
            disgust = value;
        }
        else if (emotionString.equalsIgnoreCase("fear")) {
            fear = value;
        }
        else if (emotionString.equalsIgnoreCase("joy")) {
            joy = value;
        }
        else if (emotionString.equalsIgnoreCase("negative")) {
            negative = value;
        }
        else if (emotionString.equalsIgnoreCase("positive")) {
            positive = value;
        }
        else if (emotionString.equalsIgnoreCase("sadness")) {
            sadness = value;
        }
        else if (emotionString.equalsIgnoreCase("surprise")) {
            surprise = value;
        }
        else if (emotionString.equalsIgnoreCase("trust")) {
            trust = value;
        }
    }

    public void incrementEmotion(String emotionString) {
        if (emotionString.equalsIgnoreCase("anger")) {
            anger++;
        }
        else if (emotionString.equalsIgnoreCase("anticipation")) {
            anticipation++;
        }
        else if (emotionString.equalsIgnoreCase("disgust")) {
            disgust++;
        }
        else if (emotionString.equalsIgnoreCase("fear")) {
            fear++;
        }
        else if (emotionString.equalsIgnoreCase("joy")) {
            joy++;
        }
        else if (emotionString.equalsIgnoreCase("negative")) {
            negative++;
        }
        else if (emotionString.equalsIgnoreCase("positive")) {
            positive++;
        }
        else if (emotionString.equalsIgnoreCase("sadness")) {
            sadness++;
        }
        else if (emotionString.equalsIgnoreCase("surprise")) {
            surprise++;
        }
        else if (emotionString.equalsIgnoreCase("trust")) {
            trust++;
        }
    }

    public int getEmotion(String emotionString) {
        if (emotionString.equalsIgnoreCase("anger")) {
            return anger;
        }
        else if (emotionString.equalsIgnoreCase("anticipation")) {
            return anticipation;
        }
        else if (emotionString.equalsIgnoreCase("disgust")) {
            return disgust;
        }
        else if (emotionString.equalsIgnoreCase("fear")) {
            return fear;
        }
        else if (emotionString.equalsIgnoreCase("joy")) {
            return joy;
        }
        else if (emotionString.equalsIgnoreCase("negative")) {
            return negative;
        }
        else if (emotionString.equalsIgnoreCase("positive")) {
            return positive;
        }
        else if (emotionString.equalsIgnoreCase("sadness")) {
            return sadness;
        }
        else if (emotionString.equalsIgnoreCase("surprise")) {
            return surprise;
        }
        else if (emotionString.equalsIgnoreCase("trust")) {
            return trust;
        }
        return -1;
    }

    public void addEmotion(Emotion emotion) {
        anger += emotion.getAnger();
        anticipation += emotion.getAnticipation();
        disgust += emotion.getDisgust();
        fear += emotion.getFear();
        joy += emotion.getJoy();
        negative += emotion.getNegative();
        positive += emotion.getPositive();
        sadness += emotion.getSadness();
        surprise += emotion.getSurprise();
        trust += emotion.getTrust();
    }


    public int getAnger() {
        return anger;
    }

    public void setAnger(int anger) {
        this.anger = anger;
    }

    public int getAnticipation() {
        return anticipation;
    }

    public void setAnticipation(int anticipation) {
        this.anticipation = anticipation;
    }

    public int getDisgust() {
        return disgust;
    }

    public void setDisgust(int disgust) {
        this.disgust = disgust;
    }

    public int getFear() {
        return fear;
    }

    public void setFear(int fear) {
        this.fear = fear;
    }

    public int getJoy() {
        return joy;
    }

    public void setJoy(int joy) {
        this.joy = joy;
    }

    public int getNegative() {
        return negative;
    }

    public void setNegative(int negative) {
        this.negative = negative;
    }

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public int getSadness() {
        return sadness;
    }

    public void setSadness(int sadness) {
        this.sadness = sadness;
    }

    public int getSurprise() {
        return surprise;
    }

    public void setSurprise(int surprise) {
        this.surprise = surprise;
    }

    public int getTrust() {
        return trust;
    }

    public void setTrust(int trust) {
        this.trust = trust;
    }

    public String toString () {
        String str = "";
        str += "[";
        str += "\"anger\":\""+getAnger()+"\",";
        str += "\"anticipation\":\""+getAnticipation()+"\",";
        str += "\"disgust\":\""+getDisgust()+"\",";
        str += "\"fear\":\""+getFear()+"\",";
        str += "\"joy\":\""+getJoy()+"\",";
        str += "\"sadness\":\""+getSadness()+"\",";
        str += "\"surprise\":\""+getSurprise()+"\",";
        str += "\"trust\":\""+getTrust()+"\",";
        str += "\"positive\":\""+getPositive()+"\",";
        str += "\"negative\":\""+getNegative()+"\"";
        str += "]";
        return str;
    }

    static HashMap<String, Emotion> readEmotionLexicon(String pathToFile) {
        HashMap<String, Emotion> emotionHashMap = new HashMap<String, Emotion>();
        /**
         * abhorrent	disgust	1
         abhorrent	fear	1
         abhorrent	joy	0
         abhorrent	negative	1
         abhorrent	positive	0
         abhorrent	sadness	0
         abhorrent	surprise	0
         abhorrent	trust	0
         */
        try {
            FileInputStream fis = new FileInputStream(pathToFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader in = new BufferedReader(isr);
            String inputLine = "";
            while (in.ready()&&(inputLine = in.readLine()) != null) {
                if (inputLine.trim().length()>0) {
                    String [] fields = inputLine.split("\t");
                    if (fields.length==3) {
                        String word = fields[0].trim();
                        String emotionString = fields[1].trim();
                        try {
                            int value = Integer.parseInt(fields[2].trim());
                            if (emotionHashMap.containsKey(word)) {
                                Emotion emotion = emotionHashMap.get(word);
                                emotion.setEmotion(emotionString, value);
                                emotionHashMap.put(word, emotion);
                            }
                            else {
                                Emotion emotion = new Emotion();
                                emotion.setEmotion(emotionString, value);
                                emotionHashMap.put(word, emotion);
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emotionHashMap;
    }
}
