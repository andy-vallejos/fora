import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class PublicacionTest {
    @Test
    public void denunciarPublicacion(){
        Usuario u = new Usuario("Andy Vallejos", "email", 69442181);
        Alojamiento alojamiento = new Alojamiento("sacaba", "departamento", u, 300);
        Publicacion publicacion = new Publicacion(alojamiento);

        assertEquals(0, publicacion.getDenuncias());
    }

    @Test
    public void reportarPublicacion2Veces(){
        Usuario u = new Usuario("Andy Vallejos", "email", 69442181);
        Alojamiento alojamiento = new Alojamiento("sacaba", "departamento", u,300);
        Publicacion publicacion = new Publicacion(alojamiento);

        publicacion.denunciar();
        publicacion.denunciar();
        assertEquals(2, publicacion.getDenuncias());
    }

    @Test
    public void publicacionDebeEstarDesbloqueadaConMenosDeCincoDenuncias() {
        Usuario u = new Usuario("Andy Vallejos", "email", 69442181);
        Alojamiento alojamiento = new Alojamiento("sacaba", "departamento", u, 300);
        Publicacion publicacion = new Publicacion(alojamiento);

        assertFalse(publicacion.estaBloqueada());
    }


    @Test
    public void publicacionDebeBloquearseConCincoDenuncias() {
        Usuario u = new Usuario("Andy Vallejos", "email", 69442181);
        Alojamiento alojamiento = new Alojamiento("sacaba", "departamento", u, 300);
        Publicacion publicacion = new Publicacion(alojamiento);

        for (int i = 0; i < 5; i++) {
            publicacion.denunciar();
        }

        assertTrue(publicacion.estaBloqueada());
    }

    @Test
    public void testDeberiaClasificarComoZonaSegura() {

        Usuario usuario = new Usuario("Alan Poma", "alanpoma@gmail.com", 76543210);
        Alojamiento alojamiento = new Alojamiento("Zona Sur", "Hotel", usuario, 300);
        Publicacion publicacion = new Publicacion(alojamiento);

        ReporteSeguridad reporte = new ReporteSeguridad(8, 8);

        publicacion.agregarReporteSeguridad(reporte);

        String resultado = publicacion.clasificarZona();

        assertEquals("Segura", resultado);
    }

    @Test
    public void testDeberiaClasificarComoZonaRegular() {

        Usuario usuario = new Usuario("Alan Poma", "alanpoma@gmail.com", 76543210);
        Alojamiento alojamiento = new Alojamiento("Zona Sur", "Hotel", usuario, 300);
        Publicacion publicacion = new Publicacion(alojamiento);

        ReporteSeguridad reporte = new ReporteSeguridad(6, 6);

        publicacion.agregarReporteSeguridad(reporte);

        String resultado = publicacion.clasificarZona();

        assertEquals("Regular", resultado);
    }

    @Test
    public void testDeberiaClasificarComoZonaPeligrosa() {

        Usuario usuario = new Usuario("Alan Poma", "alanpoma@gmail.com", 76543210);
        Alojamiento alojamiento = new Alojamiento("Zona Sur", "Hotel", usuario, 300);
        Publicacion publicacion = new Publicacion(alojamiento);

        ReporteSeguridad reporte = new ReporteSeguridad(2, 2);

        publicacion.agregarReporteSeguridad(reporte);

        String resultado = publicacion.clasificarZona();

        assertEquals("Peligrosa", resultado);
    }
}