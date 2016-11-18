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
            pathToEmotionLexicon = "../resources/NRC-emotion-lexicon-wordlevel-alphabetized-v0.92.txt";
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
            if (!pathToEmotionLexicon.isEmpty()) {
                HashMap<String, Emotion> emotionHashMap = readEmotionLexicon(pathToEmotionLexicon);
                if (!pathToTextFile.isEmpty()){
                    FileInputStream fis = new FileInputStream(pathToTextFile);
                    text = new Scanner(fis,"UTF-8").useDelimiter("\\A").next();
                }
                else {
                    text = new Scanner(System.in,"UTF-8").useDelimiter("\\A").next();
                }
                if (!text.isEmpty()) {
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
