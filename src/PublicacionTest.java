import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class PublicacionTest {
    private Usuario usuario;
    private Alojamiento alojamiento;
    Publicacion publicacion;

    @BeforeEach
    public void setUp(){
        usuario = new Usuario("Andy Vallejos", "email", 69442181);
        alojamiento = new Alojamiento("sacaba", "departamento", usuario, 300);
        publicacion = new Publicacion(alojamiento);
    }

    @Test
    public void denunciarPublicacion(){
        assertEquals(0, publicacion.getDenuncias());
    }

    @Test
    public void denunciarPublicacion2Veces(){
        publicacion.denunciar();
        publicacion.denunciar();
        assertEquals(2, publicacion.getDenuncias());
    }

    @Test
    public void publicacionDebeEstarDesbloqueadaConMenosDeCincoDenuncias() {
        assertFalse(publicacion.estaBloqueada());
    }


    @Test
    public void publicacionDebeBloquearseConCincoDenuncias() {
        for (int i = 0; i < 5; i++) {
            publicacion.denunciar();
        }

        assertTrue(publicacion.estaBloqueada());
    }

    @Test
    public void testDeberiaClasificarComoZonaSegura() {
        ReporteSeguridad reporte = new ReporteSeguridad(8, 8);
        publicacion.agregarReporteSeguridad(reporte);
        String resultado = publicacion.clasificarZona();
        assertEquals("Segura", resultado);
    }

    @Test
    public void testDeberiaClasificarComoZonaRegular() {
        ReporteSeguridad reporte = new ReporteSeguridad(6, 6);
        publicacion.agregarReporteSeguridad(reporte);
        String resultado = publicacion.clasificarZona();
        assertEquals("Regular", resultado);
    }

    @Test
    public void testDeberiaClasificarComoZonaPeligrosa() {
        ReporteSeguridad reporte = new ReporteSeguridad(2, 2);
        publicacion.agregarReporteSeguridad(reporte);
        String resultado = publicacion.clasificarZona();
        assertEquals("Peligrosa", resultado);
    }
}