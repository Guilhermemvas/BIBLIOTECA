package view;

import controller.BibliotecaController;
import model.Emprestimo;
import model.Livro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmprestimosPanel extends JPanel {
    private BibliotecaController controller;
    private JTextField campoNomeUsuario;
    private JComboBox<Livro> comboLivros;
    private JTextArea areaEmprestimosEmAberto;
    private JTextArea areaEmprestimosFinalizados;
    private JComboBox<Emprestimo> comboEmprestimos;

    public EmprestimosPanel(BibliotecaController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Fundo claro para a área

        // Painel para adicionar empréstimos
        JPanel painelAdicionarEmprestimo = new JPanel();
        painelAdicionarEmprestimo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelNomeUsuario = new JLabel("Nome do Usuário:");
        campoNomeUsuario = new JTextField(20);
        JLabel labelLivro = new JLabel("Selecionar Livro:");
        comboLivros = new JComboBox<>(carregarLivros());
        JButton botaoAdicionar = new JButton("Adicionar Empréstimo");
        botaoAdicionar.setBackground(new Color(76, 175, 80)); // Cor verde
        botaoAdicionar.setForeground(Color.WHITE);
        botaoAdicionar.setFocusPainted(false);
        botaoAdicionar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelAdicionarEmprestimo.add(labelNomeUsuario, gbc);
        gbc.gridx = 1;
        painelAdicionarEmprestimo.add(campoNomeUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelAdicionarEmprestimo.add(labelLivro, gbc);
        gbc.gridx = 1;
        painelAdicionarEmprestimo.add(comboLivros, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelAdicionarEmprestimo.add(botaoAdicionar, gbc);

        // Painel para finalizar empréstimos
        JPanel painelFinalizarEmprestimo = new JPanel();
        painelFinalizarEmprestimo.setLayout(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel labelEmprestimo = new JLabel("Selecionar Empréstimo:");
        comboEmprestimos = new JComboBox<>(carregarEmprestimosEmAberto().toArray(new Emprestimo[0]));
        JButton botaoFinalizar = new JButton("Finalizar Empréstimo");
        botaoFinalizar.setBackground(new Color(239, 83, 80)); // Cor vermelha
        botaoFinalizar.setForeground(Color.WHITE);
        botaoFinalizar.setFocusPainted(false);
        botaoFinalizar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelFinalizarEmprestimo.add(labelEmprestimo, gbc);
        gbc.gridx = 1;
        painelFinalizarEmprestimo.add(comboEmprestimos, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        painelFinalizarEmprestimo.add(botaoFinalizar, gbc);

        // Painéis para listar empréstimos
        JPanel painelListagem = new JPanel();
        painelListagem.setLayout(new GridLayout(2, 1, 10, 10));
        painelListagem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        areaEmprestimosEmAberto = new JTextArea();
        areaEmprestimosEmAberto.setEditable(false);
        areaEmprestimosEmAberto.setBorder(BorderFactory.createTitledBorder("Empréstimos em Aberto"));
        areaEmprestimosEmAberto.setBackground(Color.WHITE);
        areaEmprestimosEmAberto.setFont(new Font("Arial", Font.PLAIN, 14));
        areaEmprestimosEmAberto.setMargin(new Insets(10, 10, 10, 10));

        areaEmprestimosFinalizados = new JTextArea();
        areaEmprestimosFinalizados.setEditable(false);
        areaEmprestimosFinalizados.setBorder(BorderFactory.createTitledBorder("Empréstimos Finalizados"));
        areaEmprestimosFinalizados.setBackground(Color.WHITE);
        areaEmprestimosFinalizados.setFont(new Font("Arial", Font.PLAIN, 14));
        areaEmprestimosFinalizados.setMargin(new Insets(10, 10, 10, 10));

        painelListagem.add(new JScrollPane(areaEmprestimosEmAberto));
        painelListagem.add(new JScrollPane(areaEmprestimosFinalizados));

        add(painelAdicionarEmprestimo, BorderLayout.NORTH);
        add(painelFinalizarEmprestimo, BorderLayout.CENTER);
        add(painelListagem, BorderLayout.SOUTH);

        // Configurar eventos
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarEmprestimo();
            }
        });

        botaoFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarEmprestimo();
            }
        });

        atualizarListas();
    }

    private void adicionarEmprestimo() {
        String nomeUsuario = campoNomeUsuario.getText();
        Livro livroSelecionado = (Livro) comboLivros.getSelectedItem();

        if (nomeUsuario.isEmpty() || livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        controller.adicionarEmprestimo(nomeUsuario, livroSelecionado);
        atualizarListas();
    }

    private void finalizarEmprestimo() {
        Emprestimo emprestimoSelecionado = (Emprestimo) comboEmprestimos.getSelectedItem();

        if (emprestimoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um empréstimo.");
            return;
        }

        controller.finalizarEmprestimo(emprestimoSelecionado);
        atualizarListas();
    }

    private void atualizarListas() {
        List<Emprestimo> emprestimosEmAberto = controller.obterEmprestimosEmAberto();
        List<Emprestimo> emprestimosFinalizados = controller.obterEmprestimosFinalizados();

        areaEmprestimosEmAberto.setText("");
        for (Emprestimo emprestimo : emprestimosEmAberto) {
            areaEmprestimosEmAberto.append(String.format("Usuário: %s, Livro: %s, Data: %s\n",
                    emprestimo.getNomeUsuario(),
                    emprestimo.getLivro().getTitulo(),
                    new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataEmprestimo())));
        }

        areaEmprestimosFinalizados.setText("");
        for (Emprestimo emprestimo : emprestimosFinalizados) {
            areaEmprestimosFinalizados.append(String.format("Usuário: %s, Livro: %s, Data: %s\n",
                    emprestimo.getNomeUsuario(),
                    emprestimo.getLivro().getTitulo(),
                    new SimpleDateFormat("dd/MM/yyyy").format(emprestimo.getDataEmprestimo())));
        }

        comboEmprestimos.setModel(new DefaultComboBoxModel<>(emprestimosEmAberto.toArray(new Emprestimo[0])));
        comboEmprestimos.setRenderer(new EmprestimoRenderer());
    }

    private Livro[] carregarLivros() {
        List<Livro> livros = controller.obterLivros();
        List<Livro> livrosDisponiveis = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                livrosDisponiveis.add(livro);
            }
        }
        return livrosDisponiveis.toArray(new Livro[0]);
    }

    private List<Emprestimo> carregarEmprestimosEmAberto() {
        return controller.obterEmprestimosEmAberto();
    }

    private class EmprestimoRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Emprestimo) {
                Emprestimo emprestimo = (Emprestimo) value;
                String text = String.format("Usuário: %s, Livro: %s", emprestimo.getNomeUsuario(), emprestimo.getLivro().getTitulo());
                return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
            }
            return this;
        }
    }
}
