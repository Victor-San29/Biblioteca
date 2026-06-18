import java.time.LocalDate;

public class Emprestimo {


    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private boolean devolvido;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.devolvido = false;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void devolver() {
        devolvido = true;
        livro.setDisponivel(true);
    }

    @Override
    public String toString() {
        return livro.getTitulo() +
                " | Usuário: " +
                usuario.getNome() +
                " | Data: " +
                dataEmprestimo +
                " | Devolvido: " +
                devolvido;
    }


}
