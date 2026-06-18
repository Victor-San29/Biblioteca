import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros;

    public Biblioteca() {
        livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(int codigo) {

        livros.removeIf(
                livro -> livro.getCodigo() == codigo
        );
    }

    public Livro pesquisarTitulo(String titulo) {

        for (Livro livro : livros) {

            if (livro.getTitulo()
                    .toLowerCase()
                    .contains(titulo.toLowerCase())) {

                return livro;
            }
        }

        return null;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public Livro buscarPorCodigo(int codigo){

        for(Livro livro : livros){

            if(livro.getCodigo() == codigo){
                return livro;
            }
        }

        return null;
    }

}