package view;

import controller.BibliotecaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private BibliotecaController controller;
    private JPanel painelPrincipal;
    private CardLayout cardLayout;

    public MainFrame(BibliotecaController controller) {
        this.controller = controller;
        setTitle("Sistema de Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criar a barra de navegação
        JPanel painelNavegacao = new JPanel();
        painelNavegacao.setLayout(new GridLayout(1, 3));

        JButton botaoHome = new JButton("Home");
        JButton botaoCatalogo = new JButton("Catálogo");
        JButton botaoEmprestimos = new JButton("Empréstimos");

        painelNavegacao.add(botaoHome);
        painelNavegacao.add(botaoCatalogo);
        painelNavegacao.add(botaoEmprestimos);

        // Criar o painel principal com CardLayout
        painelPrincipal = new JPanel();
        cardLayout = new CardLayout();
        painelPrincipal.setLayout(cardLayout);

        // Adicionar as diferentes seções
        painelPrincipal.add(new HomePanel(), "Home");
        painelPrincipal.add(new CatalogoPanel(controller), "Catálogo");
        painelPrincipal.add(new EmprestimosPanel(controller), "Empréstimos");

        // Exibir a tela inicial
        cardLayout.show(painelPrincipal, "Home");

        // Configurar eventos de navegação
        botaoHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelPrincipal, "Home");
            }
        });

        botaoCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelPrincipal, "Catálogo");
            }
        });

        botaoEmprestimos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(painelPrincipal, "Empréstimos");
            }
        });

        add(painelNavegacao, BorderLayout.NORTH);
        add(painelPrincipal, BorderLayout.CENTER);
    }
}
