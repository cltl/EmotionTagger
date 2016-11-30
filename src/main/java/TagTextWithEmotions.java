import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by piek on 12/06/15.
 */
public class TagTextWithEmotions {


    static public void main (String[] args) {
        try {
            String pathToTextFile = "";
            String pathToEmotionLexicon = "";
            pathToEmotionLexicon = "/Code/vu/EmotionTagger/release/resources/NRC-emotion-lexicon-wordlevel-alphabetized-v0.92.txt";
            String text = "I am really angry with the government not taking care of things";
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg.equals("--inputfile") && args.length>(i+1)) {
                    pathToTextFile = args[i+1];
                }
                else if (arg.equals("--emotion-lexicon") && args.length>(i+1)) {
                    pathToEmotionLexicon = args[i+1];
                }
            }
            //noinspection Since15
            if (pathToEmotionLexicon.isEmpty()) {
                   //// we do nothing
            } else {
                HashMap<String, Emotion> emotionHashMap = Emotion.readEmotionLexicon(pathToEmotionLexicon);
                if (pathToTextFile.isEmpty()) {
                    text = new Scanner(System.in,"UTF-8").useDelimiter("\\A").next();
                } else {
                    FileInputStream fis = new FileInputStream(pathToTextFile);
                    text = new Scanner(fis,"UTF-8").useDelimiter("\\A").next();
                }
                if (text.isEmpty()) {
                    //// we do nothing
                } else {
                    Emotion textEmotion = new Emotion();
                    String [] words = text.split(" ");
                    for (int i = 0; i < words.length; i++) {
                        String word = words[i].trim().toLowerCase();
                        if (!emotionHashMap.containsKey(word)) {
                            if (word.length() > 2) {
                                word = word.substring(0, word.length() - 2); //// chop off one character
                            }
                        }
                        if (!emotionHashMap.containsKey(word)) {
                            if (word.length() > 2) {
                                word = word.substring(0, word.length() - 2); //// chop off one more character
                            }
                        }

                        if (emotionHashMap.containsKey(word)) {
                            Emotion wordEmotion = emotionHashMap.get(word);
                            textEmotion.addEmotion(wordEmotion);
                        }
                    }
                    System.out.println(textEmotion.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
