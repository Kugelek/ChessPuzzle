package views;

import controllers.*;

import javax.swing.*;
import java.awt.*;

public class CenterMenuSingleton extends JPanel {
    private static CenterMenuSingleton instance = new CenterMenuSingleton();

    private CenterMenuSingleton(){

    }
    public static CenterMenuSingleton getInstance(){
        return instance;
    }

    public void setInitLayout(){
        this.setLayout(new GridLayout(3,2));
    }

    public void addMenuElements(ResetController btnReset, MoveBackwardController mbc, MoveForwardController mfc,  JTextField fileName, SaveToFileController saver, LoadFromFileController loader, JTextArea ruchy, JTextArea ruchyReturnable){
        this.add(btnReset);
        this.add(mbc);
        this.add(mfc);
        this.add(fileName);
        this.add(saver);
        this.add(loader);
        this.add(ruchy);
        this.add(ruchyReturnable);
    }

}
