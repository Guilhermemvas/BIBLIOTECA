package view;

import controller.BibliotecaController;
import model.Livro;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CatalogoPanel extends JPanel {
    private BibliotecaController controller;

    public CatalogoPanel(BibliotecaController controller) {
        this.controller = controller;
        setLayout(new GridLayout(0, 3, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(245, 245, 245)); // Fundo claro

        List<Livro> livros = controller.obterLivros();
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                add(criarPainelLivro(livro));
            }
        }
    }

    private JPanel criarPainelLivro(Livro livro) {
        JPanel painelLivro = new JPanel();
        painelLivro.setLayout(new BorderLayout());
        painelLivro.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        painelLivro.setBackground(Color.WHITE);
        painelLivro.setPreferredSize(new Dimension(220, 330));

        // Imagem do livro
        JLabel labelImagem = new JLabel();
        if (livro.getImagem() != null) {
            ImageIcon icon = new ImageIcon(livro.getImagem().getScaledInstance(220, 250, Image.SCALE_SMOOTH));
            labelImagem.setIcon(icon);
        } else {
            labelImagem.setText("Sem imagem");
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
            labelImagem.setVerticalAlignment(SwingConstants.CENTER);
        }

        // Informações do livro
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BorderLayout());
        painelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelTitulo = new JLabel(livro.getTitulo(), SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel labelAutor = new JLabel("Autor: " + livro.getAutor(), SwingConstants.CENTER);
        labelAutor.setFont(new Font("Arial", Font.PLAIN, 12));

        painelInfo.add(labelTitulo, BorderLayout.NORTH);
        painelInfo.add(labelAutor, BorderLayout.CENTER);

        painelLivro.add(labelImagem, BorderLayout.CENTER);
        painelLivro.add(painelInfo, BorderLayout.SOUTH);

        return painelLivro;
    }
}
