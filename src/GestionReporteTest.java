import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GestionReporteTest {
    @Test
    public void testAgregarReportePublicacion() {
        Usuario usuario =
                new Usuario("Juan", "juan@mail.com", 123);
        Alojamiento alojamiento =
                new Alojamiento("Cochabamba", "Casa", usuario, 100);
        Publicacion publicacion =
                new Publicacion(alojamiento);
        ReporteSeguridad reporte =
                new ReporteSeguridad(5, 5);

        publicacion.agregarReporteSeguridad(reporte);

        assertEquals(1, publicacion.getReportes().size());
    }

    @Test
    public void testNoDebeAgregarReporteSiLaPublicacionNoExiste() {

        Fora sistema = new Fora();

        Usuario usuario =
                new Usuario("Juan", "juan@mail.com", 123);

        Alojamiento alojamiento =
                new Alojamiento("Cochabamba", "Casa", usuario, 100);

        ReporteSeguridad reporte =
                new ReporteSeguridad(8, 6);

        boolean resultado =
                sistema.agregarReporteSeguridad(alojamiento, reporte);

        assertFalse(resultado);
    }

    @Test
    public void testDebeAgregarReporteSoloAlaPublicacionCorrecta() {

        Fora sistema = new Fora();

        Usuario usuario =
                new Usuario("Juan", "juan@mail.com", 123);

        Alojamiento a1 =
                new Alojamiento("Zona A", "Casa", usuario, 100);

        Alojamiento a2 =
                new Alojamiento("Zona B", "Casa", usuario, 200);

        Publicacion p1 = new Publicacion(a1);
        Publicacion p2 = new Publicacion(a2);

        sistema.agregarPublicacion(p1);
        sistema.agregarPublicacion(p2);

        ReporteSeguridad r =
                new ReporteSeguridad(9, 7);

        sistema.agregarReporteSeguridad(a2, r);

        assertEquals(0, p1.getReportes().size());
        assertEquals(1, p2.getReportes().size());
    }

    @Test
    public void testDebeCalcularPromedioDeIndicesDeReportes() {

        Publicacion publicacion =
                new Publicacion(
                        new Alojamiento("Zona","Casa",new Usuario("A","B",1), 100)
                );

        publicacion.agregarReporteSeguridad(new ReporteSeguridad(8, 6));
        publicacion.agregarReporteSeguridad(new ReporteSeguridad(10, 8));

        double resultado =
                publicacion.calcularIndicePromedio();

        assertEquals(8.0, resultado, 0.01);
    }

    @Test
    public void testDebeRetornarCeroSiNoHayReportes() {

        Publicacion publicacion =
                new Publicacion(
                        new Alojamiento("Zona","Casa",new Usuario("A","B",1), 100)
                );

        double resultado =
                publicacion.calcularIndicePromedio();

        assertEquals(0.0, resultado, 0.01);
    }

    @Test
    public void testDebeCalcularPromedioRealDeReportes() {

        Publicacion publicacion =
                new Publicacion(
                        new Alojamiento("Zona","Casa",new Usuario("A","B",1), 100)
                );

        publicacion.agregarReporteSeguridad(new ReporteSeguridad(6, 6));
        publicacion.agregarReporteSeguridad(new ReporteSeguridad(10, 8));

        double resultado =
                publicacion.calcularIndicePromedio();

        assertEquals(7.5, resultado, 0.01);
    }
}