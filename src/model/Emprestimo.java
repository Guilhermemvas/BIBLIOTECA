package model;

import java.util.Date;

public class Emprestimo {
    private String nomeUsuario;
    private Livro livro;
    private boolean finalizado;
    private Date dataEmprestimo;

    public Emprestimo(String nomeUsuario, Livro livro) {
        this.nomeUsuario = nomeUsuario;
        this.livro = livro;
        this.finalizado = false;
        this.dataEmprestimo = new Date(); // Data atual
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
}
