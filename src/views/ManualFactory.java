package views;

public class ManualFactory {

    public UserManual getManual(String lang){
        if(lang == null){
            return null;
        }else if(lang.equalsIgnoreCase("PL")){
            return new PolishManual();
        }else if(lang.equalsIgnoreCase("ENG")){
            return new EnglishManual();
        }
        return null;
    }
}
