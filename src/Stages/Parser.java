package Stages;

import java.io.*;
public class Parser {

    public Parser(){}

    //Renvoie un tableau de String, chaque String du tableau correspondant à une ligne (= un objet de la scène) du fichier.
    //nomFichier : nom du fichier de la scène
    public String[] readIn(String nomFichier){
        File file = new File("src/Stages/Fichiers/"+nomFichier);
        StringBuilder s = new StringBuilder();
        try {
            FileReader fr = new FileReader(file);
            LineNumberReader lnr = new LineNumberReader(fr);
            String line = lnr.readLine();
            if(line == null) return null;
            else{
                while (line != null){
                    s.append(line).append(" ");
                    line = lnr.readLine();
                }
                return s.toString().split(" ");
            }
        } catch (IOException e) {
            System.out.println("File not found");
            return null;
        }
    }

    //écrit dans le fichier de nom nomFichier que l'objet de nom nomObjet a été pris.
    //nomFichier : nom du fichier de sauvegarde de la scène
    //nomObjet : nom de l'objet qui a été pris dans la scène
    public void writeIn(String nomFichier, String nomObjet){
        try{
            String[] strings = readIn(nomFichier);
            File fichier =new File("src/Stages/Fichiers/"+nomFichier);
            FileWriter fw = new FileWriter(fichier);
            for (int i = 0 ; i<strings.length ; i++){
                if(strings[i].equals(nomObjet)){
                    strings[i] = "#"+strings[i];
                }
                if (i == strings.length-1) fw.write(strings[i]);
                else fw.write(strings[i]+"\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Fichier non trouvé");
        }

    }

    //Rénitialise le fichier dans son état de base. Enlève tous les # devant les noms de chaque objets d'un fichier.
    public void resetFile(String fileName){
        try {
            String[] fileContent = readIn(fileName);
            FileWriter fw = new FileWriter(new File("src/Stages/Fichiers/"+fileName));
            for (int i = 0 ; i<fileContent.length ; i++) {
                if (fileContent[i].charAt(0) == '#') {
                    fileContent[i] = fileContent[i].replace("#", "");
                }
                if (i == fileContent.length-1) fw.write(fileContent[i]);
                else fw.write(fileContent[i]+"\n");
            }
            fw.close();
        } catch (IOException e) {
                System.out.println("Fichier non trouvé");
        }
    }
}
