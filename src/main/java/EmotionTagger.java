import eu.kyotoproject.kaf.*;
import org.apache.tools.bzip2.CBZip2InputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by piek on 15/06/15.
 */
public class EmotionTagger {
    static final public String CAPITALS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static final String layer = "emotion";
    static final String name = "vua-emotion-tagger";
    static final String version = "1.0";

    static HashMap<String, Emotion> emotionHashMap = new HashMap<String, Emotion>();
    static Vector<String> intensifiers = new Vector<String>();
    static Vector<String> weakeners = new Vector<String>();

    static public void main (String[] args) {
        String pathToNafFile = "/Users/piek/Desktop/78496_Airbus_A380_test_flight_delayed_after_accident.naf";
        String pathToEmotionLexicon = "";
        String pathToIntensifiers = "";
        String pathToWeakeners = "";
        pathToEmotionLexicon = "/Code/vu/EmotionTagger/release/resources/NRC-emotion-lexicon-wordlevel-alphabetized-v0.92.txt";
        pathToIntensifiers = "/Code/vu/EmotionTagger/release/resources/intensifiers.txt";
        pathToWeakeners = "/Code/vu/EmotionTagger/release/resources/weakeners.txt";
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if ((arg.equalsIgnoreCase("--naf")) && (args.length > (i + 1))) {
                pathToNafFile = args[i + 1];
            }
            else if (arg.equals("--emotion-lexicon") && args.length>(i+1)) {
                pathToEmotionLexicon = args[i+1];
            }
            else if (arg.equals("--intensifiers") && args.length>(i+1)) {
                pathToIntensifiers = args[i+1];
            }
            else if (arg.equals("--weakeners") && args.length>(i+1)) {
                pathToWeakeners = args[i+1];
            }
        }
        if (!pathToEmotionLexicon.isEmpty()) {
            emotionHashMap = Emotion.readEmotionLexicon(pathToEmotionLexicon);
        }
        if (!pathToIntensifiers.isEmpty()) {
            intensifiers = Expression.readWordList(pathToIntensifiers);
        }
        if (!pathToWeakeners.isEmpty()){
            weakeners = Expression.readWordList(pathToWeakeners);
        }
        String strBeginDate = createTimestamp();
        String strEndDate = null;

        KafSaxParser kafSaxParser = new KafSaxParser();
        if (pathToNafFile.isEmpty()) {
            //kafSaxParser.encoding = "UTF-8";
            kafSaxParser.parseFile(System.in);
        }
        else {
            if (pathToNafFile.toLowerCase().endsWith(".gz")) {
                try {
                    InputStream fileStream = new FileInputStream(pathToNafFile);
                    InputStream gzipStream = new GZIPInputStream(fileStream);
                    kafSaxParser.parseFile(gzipStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (pathToNafFile.toLowerCase().endsWith(".bz2")) {
                try {
                    InputStream fileStream = new FileInputStream(pathToNafFile);
                    InputStream gzipStream = new CBZip2InputStream(fileStream);
                    kafSaxParser.parseFile(gzipStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                kafSaxParser.parseFile(pathToNafFile);
            }
        }

        addEmotionsAndExpressiveness(kafSaxParser);
        strEndDate = createTimestamp();
        String host = "";
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        LP lp = new LP(name,version, strBeginDate, strBeginDate, strEndDate, host);
        kafSaxParser.getKafMetaData().addLayer(layer, lp);

        kafSaxParser.writeNafToStream(System.out);


        
    }

    static void addEmotionsAndExpressiveness (KafSaxParser kafSaxParser) {
        int eSetCount = 0;
        Set keySet = kafSaxParser.SentenceToTerm.keySet();
        Iterator<String> keys = keySet.iterator();
        while (keys.hasNext()) {
            String sentenceId = keys.next();
            ArrayList<String> termsIds = kafSaxParser.SentenceToTerm.get(sentenceId);
            KafEmotionSet kafEmotionSet = new KafEmotionSet();
            eSetCount++;
            kafEmotionSet.setId("emoSet"+eSetCount);
            kafEmotionSet.setSentenceId(sentenceId);
            Expression expression = new Expression();
            Emotion emotion = new Emotion();
            for (int i = 0; i < termsIds.size(); i++) {
                String termId = termsIds.get(i);
                KafTerm kafTerm = kafSaxParser.getTerm(termId);
                if (emotionHashMap.containsKey(kafTerm.getLemma())) {
                     Emotion termEmotion = emotionHashMap.get(kafTerm.getLemma());
/*                     ArrayList<String> emotions = termEmotion.getDominantEmotions();
                     for (int j = 0; j < emotions.size(); j++) {
                         String emotionString =  emotions.get(j);
                         int score = termEmotion.getEmotion(emotionString);
                         KafEmotion kafEmotion = new KafEmotion();
                         kafEmotion.setEmotion(emotionString);
                         kafEmotion.addSpan(termId);
                         kafEmotion.setCount(score);
                         kafEmotion.setTokenString(kafSaxParser);
                         kafEmotionSet.addKafEmotionArrayList(kafEmotion);
                     }*/
                     emotion.addEmotion(termEmotion);
                }
                if (intensifiers.contains(kafTerm.getLemma())) {
                    expression.incrementIntensifiers();
                }
                if (intensifiers.contains(kafTerm.getLemma())) {
                    expression.incrementWeakeners();
                }
                for (int j = 0; j < kafTerm.getLemma().length(); j++) {
                    String character = kafTerm.getLemma().substring(j, j + 1);
                    if (character.equals("!")) {
                        expression.incrementExclamation();
                    }
                    else {
                        if (CAPITALS.indexOf(character)>-1) {
                            expression.incrementCapitals();
                        }
                    }

                }
                if (kafTerm.getLemma().indexOf("!")>-1) {
                    expression.incrementExclamation();
                }
            }
            ArrayList<String> emotions = emotion.getDominantEmotions();
            String emo = "";
            for (int i = 0; i < emotions.size(); i++) {
                String s = emotions.get(i);
                if (!emo.isEmpty()) emo+=";";
                emo += s;
            }
            if (!emo.isEmpty()) {
                kafEmotionSet.setStrength(new Integer(expression.getStrength()).toString());
                kafEmotionSet.setEmotion(emo);
                kafSaxParser.kafEmotionSetArrayList.add(kafEmotionSet);
            }
        }
    }

    static public ArrayList<File> makeFileList(File inputFile, String extension) {
        ArrayList<File> acceptedFileList = new ArrayList<File>();
        File[] theFileList = null;
        if ((inputFile.canRead()) && inputFile.isDirectory()) {
            theFileList = inputFile.listFiles();
            for (int i = 0; i < theFileList.length; i++) {
                File newFile = theFileList[i];
                if (!newFile.isDirectory() && newFile.getName().endsWith(extension)) {
                    acceptedFileList.add(newFile);
                }
            }
        } else {
            System.out.println("Cannot access file:" + inputFile + "#");
            if (!inputFile.exists()) {
                System.out.println("File does not exist!");
            }
        }
        return acceptedFileList;
    }

    public static String createTimestamp() {
        Date var0 = new Date();
        SimpleDateFormat var1 = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ssZ");
        String var2 = var1.format(var0);
        return var2;
    }
}
