import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlojamientoPromedioTest
{
    private Alojamiento alojamiento;
    @BeforeEach public void setUp() {
        alojamiento = new Alojamiento("Cochabamba", "Departamento", null, 150);
    }

    @Test
    public void testAlojamientoNuevoSinReseniasDebeRetornarCero() {  double promedioObtenido = alojamiento.promedioDeResenias(); assertEquals(0.0, promedioObtenido); }

    @Test
    public void testAlojamientoConUnaReseniaDebeRetornarMismoValorExacto() {
        Resenia primeraResenia = new Resenia(4, "Excelente atención");
        alojamiento.agregarResenia(primeraResenia); double promedioObtenido = alojamiento.promedioDeResenias();
        assertEquals(4.0, promedioObtenido); }

    @Test
    public void testMultiplesReseniasDivisionExactaDebeRetornarDecimalExacto() {
        Resenia r1 = new Resenia(5, "Increíble"); Resenia r2 = new Resenia(4, "Bueno"); alojamiento.agregarResenia(r1); alojamiento.agregarResenia(r2); double promedioObtenido = alojamiento.promedioDeResenias();
        assertEquals(4.5, promedioObtenido); }

    @Test
    public void testMultiplesReseniasPeriodicasDebeRetornarRedondeoDosDecimales() {Resenia r1 = new Resenia(4, "Aceptable"); Resenia r2 = new Resenia(4, "Ok"); Resenia r3 = new Resenia(5, "Me gustó mucho"); alojamiento.agregarResenia(r1); alojamiento.agregarResenia(r2); alojamiento.agregarResenia(r3); double promedioObtenido = alojamiento.promedioDeResenias();
        assertEquals(4.33, promedioObtenido); }
}