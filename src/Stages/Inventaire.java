package Stages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Inventaire {

    private Parser p = new Parser();

    public Inventaire() {}

    public void notSelectAll() throws IOException {
        String[] strings = p.readIn("inventaire.txt");
        resetInventory();
        for (int i = 0 ; i < strings.length ; i++) {
            if(!strings[i].equals("")){
                if(strings[i].charAt(0) == '#') {
                    strings[i] = strings[i].replace("#", "");
                }
            }
        }
        modifyInventory(strings);
    }

    public void modifyInventory(Object[] s) throws IOException {
        FileWriter fileWriter = new FileWriter(new File("src/Stages/Fichiers/inventaire.txt"));
        for (int i = 0 ; i<s.length ; i++){
            if(i!=s.length-1) fileWriter.write(s[i]+"\n");
            else fileWriter.write((String) s[i]);
        }
        fileWriter.close();
    }

    public void addInventory(String id) throws IOException {
        AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/objet.wav"));
        audioPlayer.play();
        boolean addToInventory = false;
        String[] strings = p.readIn("inventaire.txt");
        if(strings != null){
            ArrayList<String> strings1 = new ArrayList<>();
            for (int i = 0 ; i<strings.length ; i++) {
                if(strings[i].equals("") && !addToInventory) {
                    strings[i] = id;
                    addToInventory = true;
                }
                strings1.add(i,strings[i]);
            }
            if(!strings1.contains(id)) strings1.add(id);
            modifyInventory(strings1.toArray());
        }
        else{
            modifyInventory(new String[]{id});
        }
    }

    public void resetInventory() throws IOException {
        File inventaireFile = new File("src/Stages/Fichiers/inventaire.txt");
        FileWriter fw = new FileWriter(inventaireFile);
        fw.write("");
        fw.close();
    }

    public boolean isSelected(String objectName) {
        String[] strings = p.readIn("inventaire.txt");
        if(strings != null){
            for (String s : strings) {
                if (!s.equals("") && s.charAt(0) == '#' && s.contains(objectName)) return true;
            }
        }
        return false;
    }

    public void removeInventory(String objectName) throws IOException {
        String[] strings = p.readIn("inventaire.txt");
        if(strings != null){
            for (int i = 0 ; i<strings.length ; i++) {
                    if (strings[i].equals("") || (strings[i].charAt(0) == '#' && strings[i].contains(objectName))) {
                        strings[i] = "";
                    }
            }
            modifyInventory(strings);
        }
    }
}
