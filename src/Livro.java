public class Livro {

    private int codigo;
    private String titulo;
    private String autor;
    private int ano;
    private boolean disponivel;

    public Livro(int codigo, String titulo, String autor, int ano) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {

        String status;

        if(disponivel){
            status = "Disponível";
        }else{
            status = "Emprestado";
        }

        return "ID: " + codigo +
                " | Título: " + titulo +
                " | Autor: " + autor +
                " | Ano: " + ano +
                " | Status: " + status;
    }
}
