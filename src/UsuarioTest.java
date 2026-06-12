import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UsuarioTest {
    @Test
    public void crearUsuario(){
        Usuario u = new Usuario("Andy Vallejos", "email", 69442181);
        assertEquals("Andy Vallejos", u.getNombre());
        assertEquals("email", u.getCorreo());
        assertEquals(69442181, u.getTelefono());
    }

    @Test
    public void crearUsuarioSinNombre(){
        Usuario u = new Usuario("   ", "email", 69442181);
        assertEquals("email", u.getCorreo());
        assertFalse(u.getNombre().isEmpty());
    }

    @Test
    public void crearUsuarioSinTelefonoReal(){
        Usuario u = new Usuario("Andy Vallejos", "email", -69442181);
        assertEquals("Andy Vallejos", u.getNombre());
        assertEquals("email", u.getCorreo());
        assertFalse(u.getTelefono()> 0 );
    }

}