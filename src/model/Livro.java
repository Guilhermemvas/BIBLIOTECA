package model;

import java.awt.Image;

public class Livro {
    private String titulo;
    private String autor;
    private Image imagem;
    private boolean disponivel;

    public Livro(String titulo, String autor, Image imagem, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.imagem = imagem;
        this.disponivel = disponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Image getImagem() {
        return imagem;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor; // Retorna uma string legível para o JComboBox
    }
}
