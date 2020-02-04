package tests.viewsTests;

import controllers.*;
import models.Board;
import models.MoveHistory;
import org.junit.Assert;
import org.junit.Test;
import views.BoardView;
import views.CenterMenuSingleton;

import javax.swing.*;

public class CenterMenuSingletonTest {

    @Test
    public void testGetInstance(){
        CenterMenuSingleton obj = CenterMenuSingleton.getInstance();
        Assert.assertNotNull(obj);
    }

    @Test
    public void testAddMenuElements(){


        BoardController bc = new BoardController();
                Board b = new Board();
        BoardView bv = new BoardView();
        MoveHistory mh = new MoveHistory(b, bv);
        JTextArea a = new JTextArea();
        JTextArea aR = new JTextArea();


        ResetController r = new ResetController(bc, b, bv, mh,a,aR);
        MoveBackwardController mbc = new MoveBackwardController(b,bv,mh,a,aR);
        MoveForwardController mfc = new MoveForwardController(b,bv,mh,a,aR);
        JTextField f = new JTextField();
        SaveToFileController s = new SaveToFileController(f,b,mh);
        LoadFromFileController l = new LoadFromFileController(f,b,bv,mh,a,aR);



        CenterMenuSingleton obj = CenterMenuSingleton.getInstance();



        obj.addMenuElements(r,mbc,mfc,f,s,l,a,aR);
        Assert.assertNotNull(obj.getComponents());
    }
}
