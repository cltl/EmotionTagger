import java.io.File;
import java.util.*;

/**
 * Created by piek on 15/06/15.
 */
public class ProfileTagger {
    
    
    static public HashMap<String, ArrayList<String>> profileMap = new HashMap<String, ArrayList<String>>();
    static public HashMap<String,Integer> wordMap = new HashMap<String, Integer>();
    static Vector<String> trainingWords = new Vector<String>();
    
    static public void main (String[] args) {
        String inputFolderPath = "";
        File inputFolder = new File(inputFolderPath);
        ArrayList<File> files = makeFileList(inputFolder, "json");
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            String text = "";
            String profile = "";

            String [] words = text.split(" ");
            //// doe iets met de punctuation
            String trainingsData = profile;
            for (int j = 0; j < words.length; j++) {
                String word = words[j].trim().toLowerCase();
                int idx = trainingWords.indexOf(word);
                if (idx==-1) {
                    trainingWords.add(word);
                    idx = trainingWords.size()-1;
                }
                trainingsData += " "+idx+":1";
            }
            trainingsData += "\n";
            System.out.println("trainingsData = " + trainingsData);
            System.out.println("trainingWords.toString() = " + trainingWords.toString());
            /*for (int j = 0; j < words.length; j++) {
                String word = words[j].toLowerCase();
                if (wordMap.containsKey(word) ) {
                    Integer count = wordMap.get(word);
                    count++;
                    wordMap.put(word, count);
                }
                else {
                    wordMap.put(word,1);
                }
            }
            
            if (profileMap.containsKey(profile)) {
                ArrayList<String> profileWords = profileMap.get(profile);
                for (int j = 0; j < words.length; j++) {
                    String word = words[j].trim().toLowerCase();
                    if (!profileWords.contains(word)) {
                        profileWords.add(word);
                    }
                }
                profileMap.put(profile, profileWords);
            }
            else {
                ArrayList<String> profileWords = new ArrayList<String>();
                for (int j = 0; j < words.length; j++) {
                    String word = words[j].trim().toLowerCase();
                    if (!profileWords.contains(word)) {
                        profileWords.add(word);
                    }
                }
                profileMap.put(profile, profileWords); 
            }*/
        }
        
        
        
        
        
     /*   Set keySet = wordMap.keySet();
        Iterator<String> keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = keys.next();    /// word
            Integer count = wordMap.get(key);
        }
        
        keySet = profileMap.keySet();
        keys = keySet.iterator();
        while (keys.hasNext()) {
            String key = keys.next(); /// profile
            ArrayList<String> words = profileMap.get(key);
            for (int i = 0; i < words.size(); i++) {
                String word = words.get(i);
                
            }
        }*/
        
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
}
