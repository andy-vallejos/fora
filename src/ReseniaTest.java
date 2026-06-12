import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class ReseniaTest{
    //PUNTAJE
    @Test
    public void testPuntuacionMenorAUnoNoesValida(){
        Resenia r = new Resenia(-1,"malo");
        assertFalse(r.esPuntuacionValida());
    }
    @Test
    public void testPuntuacionMayorACincoNoEsValida(){
        Resenia r = new Resenia(6,"muy bueno");
        assertFalse(r.esPuntuacionValida());
    }
    //COMENTARIOS
    @Test
    public void testComentarioNuloNoEsValido(){
        Resenia r = new Resenia(4,null);
        assertFalse(r.esComentarioValido());
    }
    @Test
    public void testComentarioDe301CaracteresNoEsValido(){
        Resenia r = new Resenia(4,"a".repeat(301));
        assertFalse(r.esComentarioValido());
    }
    @Test
    public void testComentarioVacioEsValido(){
        Resenia r = new Resenia(4,"");
        assertTrue(r.esComentarioValido());
    }
    //RESENIA
    @Test
    public void testRegistrarReseniaValida(){
        Usuario u = new Usuario("Juan","juan@gmail.com",1234);
        Alojamiento a = new Alojamiento("Sucre","Departamento",u,500);
        Publicacion p = new Publicacion(a);

        Resenia r = new Resenia(5,"Excelente");

        p.getAlojamiento().agregarResenia(r);

        assertEquals(1,p.getAlojamiento().getResenias().size());
    }
}