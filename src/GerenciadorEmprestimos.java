import java.util.ArrayList;

public class GerenciadorEmprestimos {

    private ArrayList<Emprestimo> emprestimos;

    public GerenciadorEmprestimos() {
        emprestimos = new ArrayList<>();
    }

    public void emprestar(Livro livro, Usuario usuario) {

        if (livro != null &&
                usuario != null &&
                livro.isDisponivel()) {

            livro.setDisponivel(false);

            Emprestimo emprestimo =
                    new Emprestimo(
                            livro,
                            usuario
                    );

            emprestimos.add(emprestimo);
        }
    }

    public void devolver(Livro livro) {

        for (Emprestimo e : emprestimos) {

            if (e.getLivro().equals(livro)
                    && !e.isDevolvido()) {

                e.devolver();
                break;
            }
        }
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public int totalEmprestimos() {
        return emprestimos.size();
    }

    public boolean emprestarLivro(
            Biblioteca biblioteca,
            int codigoLivro,
            Usuario usuario){

        Livro livro =
                biblioteca.buscarPorCodigo(
                        codigoLivro);

        if(livro == null){
            return false;
        }

        if(!livro.isDisponivel()){
            return false;
        }

        livro.setDisponivel(false);

        emprestimos.add(
                new Emprestimo(
                        livro,
                        usuario
                )
        );

        return true;
    }

    public boolean devolverLivro(int codigo){

        for(Emprestimo e : emprestimos){

            if(
                    e.getLivro().getCodigo()
                            == codigo
                            &&
                            !e.isDevolvido()
            ){

                e.devolver();

                return true;
            }
        }

        return false;
    }

    public String gerarRelatorio(){

        StringBuilder relatorio =
                new StringBuilder();

        for(Emprestimo e : emprestimos){

            relatorio.append(
                    e.toString()
            ).append("\n");
        }

        return relatorio.toString();
    }

}
