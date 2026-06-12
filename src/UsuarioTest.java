import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {
    Usuario usuario;

    @BeforeEach
    public void setup(){
        usuario = new Usuario("Andy Vallejos", "email", 69442181);
    }
    @Test
    public void crearUsuario(){
        assertEquals("Andy Vallejos", usuario.getNombre());
        assertEquals("email", usuario.getCorreo());
        assertEquals(69442181, usuario.getTelefono());
    }

    @Test
    public void crearUsuarioSinNombre(){
        assertEquals("email", usuario.getCorreo());
        assertTrue(usuario.esNombreValido());
    }

    @Test
    public void crearUsuarioSinTelefonoReal(){
        Usuario u = new Usuario("Andy Vallejos", "email", -69442181);
        assertEquals("Andy Vallejos", u.getNombre());
        assertEquals("email", u.getCorreo());
        assertTrue(usuario.esTelefonoValido());
    }
}