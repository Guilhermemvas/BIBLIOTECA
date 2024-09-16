package main;

import controller.BibliotecaController;
import model.Usuario;
import utils.ImageUtils;
import view.MainFrame;

public class Main {
    public static void main(String[] args) {
        BibliotecaController controller = new BibliotecaController();
        // Adiciona um usuário de teste
        controller.adicionarUsuario(new Usuario("admin", "senha123"));
        // Adiciona um livro de teste
        controller.adicionarLivro("O Senhor dos Anéis", "J.R.R. Tolkien", ImageUtils.carregarImagem("resources/Senhor.jpeg"), true);
        controller.adicionarLivro("1984", "George Orwell", ImageUtils.carregarImagem("resources/1984.jpg"), true);
        controller.adicionarLivro("Dom Casmurro", "Machado de Assim", ImageUtils.carregarImagem("resources/Dom Casmurro.jpg"), true);
        controller.adicionarLivro("Ilíada", "Homero", ImageUtils.carregarImagem("resources/iliada.jpg"), true);


        javax.swing.SwingUtilities.invokeLater(() -> new MainFrame(controller).setVisible(true));
    }
}

