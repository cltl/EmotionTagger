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


}
