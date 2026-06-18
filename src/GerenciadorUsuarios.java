import java.util.ArrayList;

public class GerenciadorUsuarios {

    private ArrayList<Usuario> usuarios;

    public GerenciadorUsuarios() {
        usuarios = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void removerUsuario(int id) {

        usuarios.removeIf(
                usuario -> usuario.getId() == id
        );
    }

    public Usuario buscarUsuario(int id) {

        for (Usuario usuario : usuarios) {

            if (usuario.getId() == id) {
                return usuario;
            }
        }

        return null;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public int totalUsuarios() {
        return usuarios.size();
    }

}
