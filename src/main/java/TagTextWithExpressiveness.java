import java.io.*;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by piek on 12/06/15.
 */
public class TagTextWithExpressiveness {

    static final String CAPITALS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static public void main (String[] args) {
        try {
            Vector<String> intensifiers = new Vector<String>();
            Vector<String> weakeners = new Vector<String>();
            String pathToTextFile = "";
            String pathToIntensifiers = "";
            String pathToWeakeners = "";
            pathToIntensifiers = "../resources/intensifiers.txt";
            pathToWeakeners = "../resources/weakeners.txt";
            String text = "I am really very angry with the government!!!!!! not taking care of things";
            boolean STREAM = false;
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg.equals("--inputfile") && args.length>(i+1)) {
                    pathToTextFile = args[i+1];
                }
                else if (arg.equals("--intensifiers") && args.length>(i+1)) {
                    pathToIntensifiers = args[i+1];
                }
                else if (arg.equals("--weakeners") && args.length>(i+1)) {
                    pathToWeakeners = args[i+1];
                }
                else if (arg.equals("--input-stream")) {
                    STREAM = true;
                }
            }
            if (!pathToWeakeners.isEmpty()) {
                weakeners = readWordList(pathToWeakeners);
            }
            if (!pathToIntensifiers.isEmpty()) {
                intensifiers = readWordList(pathToIntensifiers);
            }
            if (!pathToTextFile.isEmpty()){
                FileInputStream fis = new FileInputStream(pathToTextFile);
                text = new Scanner(fis,"UTF-8").useDelimiter("\\A").next();
            }
            else {
                text = new Scanner(System.in,"UTF-8").useDelimiter("\\A").next();
            }
            if (!text.isEmpty()) {
                Expression textExpression = new Expression();
                String [] words = text.split(" ");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i].trim().toLowerCase();


                    /// checking for intensifiers
                    if (!intensifiers.contains(word)) {
                        if (word.length() > 2) {
                            word = word.substring(0, word.length() - 2); //// chop off one character
                        }
                    }
                    if (!intensifiers.contains(word)) {
                        if (word.length() > 2) {
                            word = word.substring(0, word.length() - 2); //// chop off one more character
                        }
                    }
                    if (intensifiers.contains(word)) {
                        textExpression.incrementIntensifiers();
                    }


                    /// checking for weakeners
                    word = words[i].trim().toLowerCase();
                    if (!weakeners.contains(word)) {
                        if (word.length() > 2) {
                            word = word.substring(0, word.length() - 2); //// chop off one character
                        }
                    }
                    if (!weakeners.contains(word)) {
                        if (word.length() > 2) {
                            word = word.substring(0, word.length() - 2); //// chop off one more character
                        }
                    }

                    if (weakeners.contains(word)) {
                        textExpression.incrementWeakeners();
                    }


                    ///check for "!" and capitals
                    word = words[i].trim();
                    for (int j = 0; j < word.length(); j++) {
                        String character = word.substring(j, j + 1);
                        if (character.equals("!")) {
                            textExpression.incrementExclamation();
                        }
                        else {
                            if (CAPITALS.indexOf(character)>-1) {
                                textExpression.incrementCapitals();
                            }
                        }

                    }

                }
                System.out.println(textExpression.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
            e.printStackTrace();
        }
        return words;
    }
}
