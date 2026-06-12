import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class ForaTest {

    private Usuario juan;
    private Fora sistema;

    @BeforeEach
    public void setUp() {
        juan = new Usuario("Juan", "juan@gmail.com", 123456);
        sistema = new Fora();
    }

    @Test
    public void testDeberiaFiltrarPorPrecio() {
        sistema.agregarPublicacion(new Publicacion(new Alojamiento("Centro", "Cuarto", juan, 400)));
        sistema.agregarPublicacion(new Publicacion(new Alojamiento("Norte", "Departamento", juan, 1200)));

        ArrayList<Publicacion> resultado = sistema.filtrarPorPrecio(500);
        assertEquals(1, resultado.size());
    }

    @Test
    public void testNoDeberiaMostrarResultadosSiTodoEsMuyCaro() {
        sistema.agregarPublicacion(new Publicacion(new Alojamiento("Sur", "Departamento Caro", juan, 900)));

        ArrayList<Publicacion> resultado = sistema.filtrarPorPrecio(500);
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testNoDeberiaAceptarPreciosNegativos() {
        sistema.agregarPublicacion(new Publicacion(new Alojamiento("Oeste", "Cuarto Error", juan, -200)));

        ArrayList<Publicacion> resultado = sistema.filtrarPorPrecio(-100);
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testDeberiaFiltrarPorCategoriaCuarto() {
        sistema.agregarPublicacion(new Publicacion(new Alojamiento("Centro", "Cuarto", juan, 400)));
        sistema.agregarPublicacion(new Publicacion(new Alojamiento("Norte", "Departamento", juan, 1200)));

        ArrayList<Publicacion> resultado = sistema.filtrarPorCategoria("Cuarto");
        assertEquals(1, resultado.size());
    }

    @Test
    public void testNoDeberiaMostrarResultadosSiSoloHayDepartamentos() {
        sistema.agregarPublicacion(new Publicacion(new Alojamiento("Centro", "Departamento", juan, 900)));

        ArrayList<Publicacion> resultado = sistema.filtrarPorCategoria("Cuarto");
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testNoDeberiaMostrarResultadosConCategoriaInexistente() {
        sistema.agregarPublicacion(new Publicacion(new Alojamiento("Norte", "Cuarto", juan, 400)));

        ArrayList<Publicacion> resultado = sistema.filtrarPorCategoria("Mansión");
        assertTrue(resultado.isEmpty());
    }
}