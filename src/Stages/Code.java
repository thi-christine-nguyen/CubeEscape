package Stages;


public class Code {

    private String saisie = "";


    public Code() {
    }

    public void saisir(String i){
        if (saisie.length() < 10){
            saisie+=i;
        }
    }

    public void effacer(){
        saisie = "";
    }

    public String getSaisie() {
        return saisie;
    }

}
