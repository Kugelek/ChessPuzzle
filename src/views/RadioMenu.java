package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class RadioMenu extends JPanel {
    ButtonGroup radioBtns;
    JTextArea manualHolder;

    public RadioMenu(){
        this.manualHolder = new JTextArea();
        this.manualHolder.setEditable(false);
        this.manualHolder.setLayout(null);
        this.manualHolder.setPreferredSize(new Dimension(450, 650));
        this.manualHolder.setFont(new Font("Courier New", Font.ITALIC, 16));
        this.add(manualHolder);

        this.radioBtns = new ButtonGroup();
        JRadioButton btnPL = new JRadioButton("PL", true);

        JRadioButton btnENG = new JRadioButton("ENG", false);



        //początkowo zaznaczamy PL więc ustawiamy początkowo polską instrukcje
        ManualFactory factory = new ManualFactory();
        UserManual manual = factory.getManual(btnPL.getText());
        manualHolder.setText(manual.getManualText());

        this.radioBtns.add(btnPL);
        this.radioBtns.add(btnENG);

        this.add(btnPL);
        this.add(btnENG);

        this.addClickListener(btnENG);
        this.addClickListener(btnPL);

    }

    public void addClickListener(JRadioButton btn){
        ActionListener sluchacz = new ActionListener() {
            public void actionPerformed(ActionEvent event)
            {
                ManualFactory factory = new ManualFactory();
                UserManual manual = factory.getManual(btn.getText());
                manualHolder.setText(manual.getManualText());
            }
        };
        btn.addActionListener(sluchacz);
    }
}
