package view;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Bem-vindo ao Sistema de Biblioteca!", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
