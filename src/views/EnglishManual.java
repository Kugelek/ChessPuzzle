package views;

import javax.swing.*;

public class EnglishManual extends JTextField implements UserManual{

    @Override
    public String getManualText(){
        String text ="Welcome to 'GHOSTS' game.\n Squares with red digits\n ARE SPECIAL!\n\n"+
                "Those red digits\n refer to the number of attacks\n the square should get\n"+
                "Each special square\n should be attacked by EXACTLY 3 PIECES\n\n"+
                "You should move the pieces\n in a way to make special squares be ZEROes.\n\n"+
                "You can use some of the buttons,\n dbra juz nie chce mi sie pisac\n\n\n"+
                "Good luck & have fun!";
        return text;
    }
}
