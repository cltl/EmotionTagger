import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by piek on 07/12/2016.
 */
public class TagTextStreamWithEmotionsAndExpression {
    static final public String CAPITALS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static public void main (String[] args) {
                HashMap<String, Emotion> emotionHashMap = new HashMap<String, Emotion>();
                Vector<String> intensifiers = new Vector<String>();
                Vector<String> weakeners = new Vector<String>();
                Vector<String> shifters = new Vector<String>();
                shifters.add("not");
                shifters.add("no");
                shifters.add("never");
                shifters.add("deny");
                shifters.add("denial");
                shifters.add("aint");
                shifters.add("donot");
                shifters.add("doesnt");
                shifters.add("doesnot");
                shifters.add("wont");
                shifters.add("wouldnot");
                String pathToIntensifiers = "";
                String pathToWeakeners = "";
                String pathToPolarityShifters = "";
                String pathToEmotionLexicon = "";
                String text = "";
                text = "I am really very angry with the government not taking care of things";
                Scanner scanner =null;
                if (args.length==0) {
                    pathToIntensifiers = "/Code/vu/EmotionTagger/release/resources/intensifiers.txt";
                    pathToWeakeners = "/Code/vu/EmotionTagger/release/resources/weakeners.txt";
                    pathToPolarityShifters = "/Code/vu/EmotionTagger/release/resources/polarity_shifters.txt";
                    pathToEmotionLexicon = "/Code/vu/EmotionTagger/release/resources/NRC-emotion-lexicon-wordlevel-alphabetized-v0.92.txt";
                    text = "I am really not angry with the government not taking care of things";
                }
                else {
                    for (int i = 0; i < args.length; i++) {
                        String arg = args[i];
                        if (arg.equals("--emotion-lexicon") && args.length > (i + 1)) {
                            pathToEmotionLexicon = args[i + 1];
                        } else if (arg.equals("--intensifiers") && args.length > (i + 1)) {
                            pathToIntensifiers = args[i + 1];
                        } else if (arg.equals("--weakeners") && args.length > (i + 1)) {
                            pathToWeakeners = args[i + 1];
                        } else if (arg.equals("--shifters") && args.length > (i + 1)) {
                            pathToPolarityShifters = args[i + 1];
                        }
                    }
                    scanner = new Scanner(System.in);
                    text = scanner.nextLine().toLowerCase();
                    /*while (scanner.hasNext()) {
                        text += scanner.next().toLowerCase() +" ";
                    }*/
                }

               // System.out.print("[\"" + text+"\"]");
                if (!pathToWeakeners.isEmpty()) {
                    weakeners = Expression.readWordList(pathToWeakeners);
                }
                if (!pathToIntensifiers.isEmpty()) {
                    intensifiers = Expression.readWordList(pathToIntensifiers);
                }
                if (!pathToPolarityShifters.isEmpty()) {
                    shifters = Expression.readWordList(pathToPolarityShifters);
                }

                //noinspection Since15
                if (!pathToEmotionLexicon.isEmpty()) {

                    emotionHashMap = Emotion.readEmotionLexicon(pathToEmotionLexicon);
                }
                Emotion textEmotion = new Emotion();
                Expression textExpression = new Expression();

                String [] words = text.split(" ");
                for (int i = 0; i < words.length; i++) {
                    String nextWord = words[i].trim().toLowerCase();

                    if (emotionHashMap.containsKey(nextWord)) {
                        Emotion wordEmotion = emotionHashMap.get(nextWord);
                        textEmotion.addEmotion(wordEmotion);
                    }

                    if (intensifiers.contains(nextWord)) {
                        textExpression.incrementIntensifiers();
                    }


                    if (weakeners.contains(nextWord)) {
                        textExpression.incrementWeakeners();
                    }

                    if (shifters.contains(nextWord)) {
                        textExpression.incrementShifters();
                    }

                    ///check for "!" and capitals
                    nextWord = words[i].trim();
                    for (int j = 0; j < nextWord.length(); j++) {
                        String character = nextWord.substring(j, j + 1);
                        if (character.equals("!")) {
                            textExpression.incrementExclamation();
                        }
                        else {
                            if (j>0) {
                                if (CAPITALS.indexOf(character) > -1) {
                                    textExpression.incrementCapitals();
                                }
                            }
                        }
                    }
                }
        try {
            JSONObject json = new JSONObject();
            json.append("emotion", textEmotion.toJSONObject());
            json.append("expression", textExpression.toJSONObject());
            System.out.print(json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //System.out.print(textEmotion.toString());
        }


}
