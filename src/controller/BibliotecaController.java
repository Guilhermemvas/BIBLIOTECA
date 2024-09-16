package controller;

import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaController {
    private List<Usuario> usuarios;
    private List<Livro> livros;
    private List<Emprestimo> emprestimos;

    public BibliotecaController() {
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    // Adiciona um usuário
    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Adiciona um livro
    public void adicionarLivro(String titulo, String autor, Image imagem, boolean disponivel) {
        livros.add(new Livro(titulo, autor, imagem, disponivel));
    }

    // Verifica credenciais
    public boolean autenticar(String nome, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    // Obtém todos os livros
    public List<Livro> obterLivros() {
        return livros;
    }

    // Adiciona um empréstimo
    public void adicionarEmprestimo(String nomeUsuario, Livro livro) {
        if (livro.isDisponivel()) {
            livro.setDisponivel(false);
            emprestimos.add(new Emprestimo(nomeUsuario, livro));
        }
    }

    // Obtém empréstimos em aberto
    public List<Emprestimo> obterEmprestimosEmAberto() {
        List<Emprestimo> emAberto = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (!emprestimo.isFinalizado()) {
                emAberto.add(emprestimo);
            }
        }
        return emAberto;
    }

    // Obtém empréstimos finalizados
    public List<Emprestimo> obterEmprestimosFinalizados() {
        List<Emprestimo> finalizados = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isFinalizado()) {
                finalizados.add(emprestimo);
            }
        }
        return finalizados;
    }

    // Finaliza um empréstimo
    public void finalizarEmprestimo(Emprestimo emprestimo) {
        emprestimo.setFinalizado(true);
        emprestimo.getLivro().setDisponivel(true);
    }
}
