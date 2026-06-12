import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


class PuntosInteresTest {
    private ArrayList<LugarInteres> lugares;
    private PuntosInteres puntos;

    @BeforeEach

    public void setUp(){
        lugares = new ArrayList<>();
        lugares.add(new LugarInteres("BlackSoul Café","Cafeteria",
                "Av. Heroínas #942","Lunes a sábado de 8:30 a 12:30"));
        lugares.add(new LugarInteres("Del/Isramen","Restaurante",
                "Av.Oquendo esq. Calama","Lunes a viernes de 12:00 a 18:00"));
        puntos = new PuntosInteres(lugares);
    }

    @Test
    public void testDevuelveResultadoRelacionadoCategoriaBuscada() {
        String categoriaBuscada = "Cafeteria";
        ArrayList<LugarInteres> resultado = puntos.buscarCategoria(categoriaBuscada);

        assertTrue(puntos.verificarCategoria(categoriaBuscada, resultado));
    }

    @Test
    public void testCategoriaInexistenteResultadoVacio() {
        String categoriaBuscada = "Libreria";
        ArrayList<LugarInteres> resultado = puntos.buscarCategoria(categoriaBuscada);

        assertFalse(puntos.verificarCategoria(categoriaBuscada, resultado));
    }

    @Test
    public void testCategoriaMinusculasResultadoEncontrado() {
        String categoriaBuscada = "restaurante";
        ArrayList<LugarInteres> resultado = puntos.buscarCategoria(categoriaBuscada);

        assertTrue(puntos.verificarCategoria(categoriaBuscada, resultado));
    }

    @Test
    public void testCategoriaMayusculasResultadoEncontrado() {
        String categoriaBuscada = "RESTAURANTE";
        ArrayList<LugarInteres> resultado = puntos.buscarCategoria(categoriaBuscada);

        assertTrue(puntos.verificarCategoria(categoriaBuscada, resultado));
    }

    @Test
    public void testQuitarEspaciosExtremosParaBusqueda() {
        String categoriaBuscada = "  Cafeteria ";
        ArrayList<LugarInteres> resultado = puntos.buscarCategoria(categoriaBuscada);

        assertTrue(puntos.verificarCategoria(categoriaBuscada, resultado));
    }

    @Test
    public void testCategoriaVaciaResultadoVacio() {
        String categoriaBuscada = "";
        ArrayList<LugarInteres> resultado = puntos.buscarCategoria(categoriaBuscada);

        assertEquals(0, resultado.size());
    }

    @Test
    public void testCategoriaSoloEspaciosResultadoVacio() {
        String categoriaBuscada = "    ";
        ArrayList<LugarInteres> resultado = puntos.buscarCategoria(categoriaBuscada);

        assertEquals(0, resultado.size());
    }
}

