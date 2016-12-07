import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by piek on 07/12/2016.
 */
public class TagTextStreamWithEmotionsAndExpression {

    static public void main (String[] args) {
                HashMap<String, Emotion> emotionHashMap = new HashMap<String, Emotion>();
                Vector<String> intensifiers = new Vector<String>();
                Vector<String> weakeners = new Vector<String>();
                String pathToIntensifiers = "";
                String pathToWeakeners = "";
                String pathToEmotionLexicon = "";
                String text = "";
                text = "I am really angry with the government not taking care of things";
                Scanner scanner =null;
                if (args.length==0) {
                    pathToIntensifiers = "../resources/intensifiers.txt";
                    pathToWeakeners = "../resources/weakeners.txt";
                    pathToEmotionLexicon = "/Code/vu/EmotionTagger/release/resources/NRC-emotion-lexicon-wordlevel-alphabetized-v0.92.txt";
                    text = "I am really angry with the government not taking care of things";
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
                        }
                    }
                    scanner = new Scanner(System.in);
                    text = scanner.nextLine().toLowerCase();
                    /*while (scanner.hasNext()) {
                        text += scanner.next().toLowerCase() +" ";
                    }*/
                }

                //System.out.println("text = " + text);
                if (!pathToWeakeners.isEmpty()) {
                    weakeners = Expression.readWordList(pathToWeakeners);
                }
                if (!pathToIntensifiers.isEmpty()) {
                    intensifiers = Expression.readWordList(pathToIntensifiers);
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

                }
                System.out.print(textExpression.toString()+textEmotion.toString());
                //System.out.print(textEmotion.toString());
        }


}
