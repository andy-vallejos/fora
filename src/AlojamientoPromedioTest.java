import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlojamientoPromedioTest
{
    @Test
    public void testAlojamientoNuevoSinReseniasDebeRetornarCero() {

        Alojamiento alojamientoNuevo = new Alojamiento("Cochabamba", "Departamento", null, 150);

        double promedioObtenido = alojamientoNuevo.promedioDeResenias();

        assertEquals(0.0, promedioObtenido);
    }

    @Test
    public void testAlojamientoConUnaReseniaDebeRetornarMismoValorExacto() {

        Alojamiento alojamientoConUnaResenia = new Alojamiento("Cochabamba", "Habitación", null, 80);
        Resenia primeraResenia = new Resenia(4, "Excelente atención del propietario");

        alojamientoConUnaResenia.agregarResenia(primeraResenia);
        double promedioObtenido = alojamientoConUnaResenia.promedioDeResenias();

        assertEquals(4.0, promedioObtenido);
    }

    @Test
    public void testMultiplesReseniasDivisionExactaDebeRetornarDecimalExacto() {

        Alojamiento alojamiento = new Alojamiento("Cochabamba", "Departamento", null, 200);
        Resenia r1 = new Resenia(5, "Increíble");
        Resenia r2 = new Resenia(4, "Bueno");

        alojamiento.agregarResenia(r1);
        alojamiento.agregarResenia(r2);
        double promedioObtenido = alojamiento.promedioDeResenias();

        assertEquals(4.5, promedioObtenido);
    }

    @Test
    public void testMultiplesReseniasPeriodicasDebeRetornarRedondeoDosDecimales() {
        Alojamiento alojamiento = new Alojamiento("Cochabamba", "Habitación", null, 90);
        Resenia r1 = new Resenia(4, "Aceptable");
        Resenia r2 = new Resenia(4, "Ok");
        Resenia r3 = new Resenia(5, "Me gustó mucho");

        alojamiento.agregarResenia(r1);
        alojamiento.agregarResenia(r2);
        alojamiento.agregarResenia(r3);
        double promedioObtenido = alojamiento.promedioDeResenias();

        assertEquals(4.33, promedioObtenido);
    }
}