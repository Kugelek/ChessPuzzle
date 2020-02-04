package views;

import javax.swing.*;

public class PolishManual  implements UserManual{
    @Override
    public String getManualText(){
        String text = "Witaj w łamigłówce 'DUCHY'.\n Pola oznaczone czerwonymi cyframi\n to pola specjalne.\n\n"+
                        "Cyfry na polach specjalnych\n wskazują ile jeszcze figur musi je\n zaatakować, aby ukończyć grę.\n"+
                        "Każde pole specjalne ma być\n atakowane przez DOKŁADNIE 3 FIGURY.\n\n"+
                        "Przesuwaj figury tak, aby WYZEROWAC\n wszystkie cyfry na polach specjalnych.\n\n"+
                        "Masz do dyspozycji przyciski na środku ekranu,\n możesz za ich pomocą poruszać się\n po historii swoich wcześniejszych ruchów\n"+
                        "zresetować grę z losowym ustawieniem, \n a także wczytać lub zapisać stan gry do pliku.\n Nazwe pliku możesz określić w polu\n między przyciskami\n\n\n"+
                        "Powodzenia!";

        return text;
    }
}
