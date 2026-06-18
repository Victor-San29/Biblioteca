import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaBiblioteca extends JFrame {


    private JPanel menuLateral;
    private JPanel painelConteudo;

    private Biblioteca biblioteca;
    private GerenciadorUsuarios gerenciadorUsuarios;
    private GerenciadorEmprestimos gerenciadorEmprestimos;

    public TelaBiblioteca() {

        biblioteca = new Biblioteca();
        gerenciadorUsuarios = new GerenciadorUsuarios();
        gerenciadorEmprestimos = new GerenciadorEmprestimos();

        setTitle("Sistema Gerencial de Biblioteca");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        criarInterface();
    }

    private void criarInterface() {

        setLayout(new BorderLayout());

        menuLateral = new JPanel();
        menuLateral.setPreferredSize(new Dimension(220, 0));
        menuLateral.setLayout(new GridLayout(10,1,5,5));
        menuLateral.setBackground(new Color(44,62,80));

        JButton btnLivros = new JButton("Livros");
        JButton btnUsuarios = new JButton("Usuários");
        JButton btnEmprestimos = new JButton("Empréstimos");
        JButton btnRelatorio = new JButton("Relatório");
        JButton btnSair = new JButton("Sair");

        estilizar(btnLivros);
        estilizar(btnUsuarios);
        estilizar(btnEmprestimos);
        estilizar(btnRelatorio);
        estilizar(btnSair);

        btnLivros.addActionListener(e -> telaLivros());
        btnUsuarios.addActionListener(e -> telaUsuarios());
        btnEmprestimos.addActionListener(e -> telaEmprestimos());
        btnRelatorio.addActionListener(e -> telaRelatorio());

        btnSair.addActionListener(e -> System.exit(0));

        menuLateral.add(btnLivros);
        menuLateral.add(btnUsuarios);
        menuLateral.add(btnEmprestimos);
        menuLateral.add(btnRelatorio);
        menuLateral.add(btnSair);

        painelConteudo = new JPanel(new BorderLayout());

        JLabel bemvindo =
                new JLabel(
                        "Bem-vindo ao Sistema Bibliotecário",
                        SwingConstants.CENTER);

        bemvindo.setFont(new Font("Arial", Font.BOLD, 26));

        painelConteudo.add(bemvindo);

        add(menuLateral, BorderLayout.WEST);
        add(painelConteudo, BorderLayout.CENTER);
    }

    private void estilizar(JButton btn){

        btn.setBackground(new Color(52,73,94));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial",Font.BOLD,14));
    }

    private void telaLivros(){

        painelConteudo.removeAll();

        JPanel painel = new JPanel(new BorderLayout());

        JPanel formulario =
                new JPanel(new GridLayout(7,2,5,5));

        JTextField txtCodigo = new JTextField();
        JTextField txtTitulo = new JTextField();
        JTextField txtAutor = new JTextField();
        JTextField txtAno = new JTextField();
        JTextField txtPesquisa = new JTextField();

        JLabel lblTotal =
                new JLabel("Total de Livros: "
                        + biblioteca.getLivros().size());

        JTextArea area = new JTextArea();

        JButton btnCadastrar =
                new JButton("Cadastrar");

        JButton btnListar =
                new JButton("Listar");

        JButton btnPesquisar =
                new JButton("Pesquisar");

        JButton btnRemover =
                new JButton("Remover");

        JButton btnLimpar =
                new JButton("Limpar");

        formulario.add(new JLabel("Código"));
        formulario.add(txtCodigo);

        formulario.add(new JLabel("Título"));
        formulario.add(txtTitulo);

        formulario.add(new JLabel("Autor"));
        formulario.add(txtAutor);

        formulario.add(new JLabel("Ano"));
        formulario.add(txtAno);

        formulario.add(new JLabel("Pesquisar"));
        formulario.add(txtPesquisa);

        formulario.add(btnCadastrar);
        formulario.add(btnListar);

        formulario.add(btnPesquisar);
        formulario.add(btnRemover);

        btnCadastrar.addActionListener(e -> {

            try{

                Livro livro = new Livro(
                        Integer.parseInt(txtCodigo.getText()),
                        txtTitulo.getText(),
                        txtAutor.getText(),
                        Integer.parseInt(txtAno.getText())
                );

                biblioteca.adicionarLivro(livro);

                lblTotal.setText(
                        "Total de Livros: "
                                + biblioteca.getLivros().size());

                JOptionPane.showMessageDialog(
                        this,
                        "Livro cadastrado!"
                );

            }catch(Exception ex){

                JOptionPane.showMessageDialog(
                        this,
                        "Dados inválidos!"
                );
            }
        });

        btnListar.addActionListener(e -> {

            area.setText("");

            for(Livro livro :
                    biblioteca.getLivros()){

                area.append(
                        livro.toString() + "\n");
            }
        });

        btnPesquisar.addActionListener(e -> {

            Livro encontrado = null;

            for(Livro livro :
                    biblioteca.getLivros()){

                if(livro.getTitulo()
                        .toLowerCase()
                        .contains(
                                txtPesquisa
                                        .getText()
                                        .toLowerCase())){

                    encontrado = livro;
                    break;
                }
            }

            if(encontrado != null){

                JOptionPane.showMessageDialog(
                        this,
                        encontrado.toString()
                );

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Livro não encontrado"
                );
            }
        });

        btnRemover.addActionListener(e -> {

            int codigo =
                    Integer.parseInt(
                            txtCodigo.getText());

            biblioteca.removerLivro(codigo);

            lblTotal.setText(
                    "Total de Livros: "
                            + biblioteca.getLivros().size());

            JOptionPane.showMessageDialog(
                    this,
                    "Livro removido!"
            );
        });

        btnLimpar.addActionListener(e -> {

            txtCodigo.setText("");
            txtTitulo.setText("");
            txtAutor.setText("");
            txtAno.setText("");
            txtPesquisa.setText("");
        });

        JPanel sul = new JPanel();

        sul.add(btnLimpar);
        sul.add(lblTotal);

        painel.add(formulario,
                BorderLayout.NORTH);

        painel.add(new JScrollPane(area),
                BorderLayout.CENTER);

        painel.add(sul,
                BorderLayout.SOUTH);

        painelConteudo.add(painel);

        painelConteudo.revalidate();
        painelConteudo.repaint();
    }

    private void telaUsuarios(){

        painelConteudo.removeAll();

        JPanel painel = new JPanel(
                new BorderLayout());

        JTextField txtId =
                new JTextField();

        JTextField txtNome =
                new JTextField();

        JTextArea area =
                new JTextArea();

        JPanel form =
                new JPanel(
                        new GridLayout(3,2));

        JButton btnCadastrar =
                new JButton("Cadastrar");

        JButton btnListar =
                new JButton("Listar");

        form.add(new JLabel("ID"));
        form.add(txtId);

        form.add(new JLabel("Nome"));
        form.add(txtNome);

        form.add(btnCadastrar);
        form.add(btnListar);

        btnCadastrar.addActionListener(e -> {

            Usuario u = new Usuario(
                    Integer.parseInt(
                            txtId.getText()),
                    txtNome.getText());

            gerenciadorUsuarios
                    .adicionarUsuario(u);

            JOptionPane.showMessageDialog(
                    this,
                    "Usuário cadastrado!"
            );
        });

        btnListar.addActionListener(e -> {

            area.setText("");

            for(Usuario u :
                    gerenciadorUsuarios
                            .getUsuarios()){

                area.append(
                        u.toString()+"\n");
            }
        });

        painel.add(form,
                BorderLayout.NORTH);

        painel.add(new JScrollPane(area),
                BorderLayout.CENTER);

        painelConteudo.add(painel);

        painelConteudo.revalidate();
        painelConteudo.repaint();
    }

    private void telabiblioteca(){

        painelConteudo.removeAll();

        JPanel painel =
                new JPanel(
                        new GridLayout(5,2,5,5));

        JTextField txtLivro =
                new JTextField();

        JTextField txtUsuario =
                new JTextField();

        JButton btnEmprestar =
                new JButton("Emprestar");

        JButton btnDevolver =
                new JButton("Devolver");

        painel.add(
                new JLabel("ID Livro"));

        painel.add(txtLivro);

        painel.add(
                new JLabel("ID Usuário"));

        painel.add(txtUsuario);

        painel.add(btnEmprestar);

        painel.add(btnDevolver);

        btnEmprestar.addActionListener(e -> {

            int idLivro =
                    Integer.parseInt(
                            txtLivro.getText());

            int idUsuario =
                    Integer.parseInt(
                            txtUsuario.getText());

            Usuario usuario =
                    gerenciadorUsuarios
                            .buscarUsuario(
                                    idUsuario);

            boolean ok =
                    gerenciadorEmprestimos
                            .emprestarLivro(
                                    biblioteca,
                                    idLivro,
                                    usuario
                            );

            if(ok){

                JOptionPane.showMessageDialog(
                        this,
                        "Livro emprestado!"
                );

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Livro indisponível!"
                );
            }

        });

        btnDevolver.addActionListener(e -> {

            int idLivro =
                    Integer.parseInt(
                            txtLivro.getText());

            boolean ok =
                    gerenciadorEmprestimos
                            .devolverLivro(
                                    idLivro);

            if(ok){

                JOptionPane.showMessageDialog(
                        this,
                        "Livro devolvido!"
                );

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Livro não encontrado!"
                );
            }

        });

        painelConteudo.add(painel);

        painelConteudo.revalidate();
        painelConteudo.repaint();
    }

    private void telaRelatorio(){

        painelConteudo.removeAll();

        JTextArea area =
                new JTextArea();

        for(Emprestimo e :
                gerenciadorEmprestimos
                        .getEmprestimos()){

            area.append(
                    e.toString()
                            + "\n");
        }

        painelConteudo.add(
                new JScrollPane(area));

        painelConteudo.revalidate();
        painelConteudo.repaint();
    }


    private void telaEmprestimos() {

        painelConteudo.removeAll();

        JPanel painel = new JPanel(new BorderLayout());

        JComboBox<Usuario> cbUsuarios =
                new JComboBox<>();

        JComboBox<Livro> cbLivros =
                new JComboBox<>();

        for (Usuario u :
                gerenciadorUsuarios.getUsuarios()) {

            cbUsuarios.addItem(u);
        }

        for (Livro l :
                biblioteca.getLivros()) {

            if (l.isDisponivel()) {
                cbLivros.addItem(l);
            }
        }

        JButton btnEmprestar =
                new JButton("Realizar Empréstimo");

        JTextArea area =
                new JTextArea();

        JPanel form =
                new JPanel(new GridLayout(3, 2, 5, 5));

        form.add(new JLabel("Usuário"));
        form.add(cbUsuarios);

        form.add(new JLabel("Livro"));
        form.add(cbLivros);

        form.add(new JLabel(""));
        form.add(btnEmprestar);

        btnEmprestar.addActionListener(e -> {

            Usuario usuario =
                    (Usuario) cbUsuarios.getSelectedItem();

            Livro livro =
                    (Livro) cbLivros.getSelectedItem();

            if (usuario == null || livro == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cadastre usuários e livros primeiro!"
                );

                return;
            }

            gerenciadorEmprestimos.emprestar(
                    livro,
                    usuario
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Empréstimo realizado com sucesso!"
            );

            telaEmprestimos();
        });

        painel.add(form, BorderLayout.NORTH);

        painel.add(
                new JScrollPane(area),
                BorderLayout.CENTER
        );

        painelConteudo.add(painel);

        painelConteudo.revalidate();
        painelConteudo.repaint();
    }


}
