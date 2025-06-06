package ui; 

import java.awt.EventQueue;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import ui.Login2;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    UIManager.put("Button.arc", 90);

                    PantallaInicioORegistro window = new PantallaInicioORegistro();
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}