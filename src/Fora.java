import java.util.ArrayList;

public class Fora {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Alojamiento> alojamientos;
    private ArrayList<Publicacion> publicaciones;
    private ArrayList<LugarInteres> lugares;
    private PuntosInteres puntos;

    public Fora() {
        usuarios = new ArrayList<>();
        alojamientos = new ArrayList<>();
        publicaciones = new ArrayList<>();
        lugares = new ArrayList<>();
        puntos = new PuntosInteres(lugares);
    }

    public ArrayList<Usuario> getUsuarios() { return usuarios; }
    public ArrayList<Alojamiento> getAlojamientos() { return alojamientos; }
    public ArrayList<Publicacion> getPublicaciones() {return publicaciones;}
    public ArrayList<LugarInteres> getLugares() {return lugares;}


    public boolean agregarUsuario(Usuario usuario) {
        boolean resultado =true;
        if (usuarios.contains(usuario)) resultado = false;

        usuarios.add(usuario);
        return resultado ;
    }

    public boolean agregarAlojamiento(Alojamiento alojamiento) {
        boolean resultado =true;

        if (alojamientos.contains(alojamiento)) resultado = false;

        alojamientos.add(alojamiento);
        alojamiento.getUsuario().agregarAlojamiento(alojamiento);

        return resultado;
    }

    public boolean agregarPublicacion(Publicacion publicacion) {
        boolean resultado = true;

        if (publicaciones.contains(publicacion)) {
            resultado = false;
        }

        publicaciones.add(publicacion);
        return resultado;
    }

    public boolean agregarLugarInteres(LugarInteres lugar) {
        boolean resultado = true;

        if (lugares.contains(lugar)) {
            resultado = false;
        }

        lugares.add(lugar);
        return resultado;
    }

    public boolean agregarReporteSeguridad(int indice, ReporteSeguridad reporte){
        Alojamiento alojamiento = alojamientos.get(indice);
        for (Publicacion p: publicaciones){
            if (p.getAlojamiento().equals(alojamiento)){
                p.agregarReporteSeguridad(reporte);
                return true;
            }
        }
        return false;
    }



    public void mostrarInformacionLugar(String categoriaBuscada) {
        ArrayList<LugarInteres> resultado = puntos.buscarCategoria(categoriaBuscada);
        if (resultado.size() > 0) {
            for (LugarInteres lugar : resultado) {
                System.out.println("Nombre: " + lugar.getNombre());
                System.out.println("Categoria: " + lugar.getCategoria());
                System.out.println("Dirección: " + lugar.getDireccion());
                System.out.println("Horario de Atención: " + lugar.getHorarioAtencion());
            }
        } else {
            System.out.println("No hay lugares disponibles de esa categoria");
        }
    }

    public ArrayList<Publicacion> filtrarPorPrecio(int precioMaximo) {
        ArrayList<Publicacion> resultado = new ArrayList<>();
        for (Publicacion p : publicaciones) {
            if (p.getAlojamiento().tienePrecioMenorOIgualA(precioMaximo)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
    public ArrayList<Publicacion> filtrarPorCategoria(String categoriaBuscada) {
        ArrayList<Publicacion> resultado = new ArrayList<>();
        for (Publicacion p : publicaciones) {
            if (p.getAlojamiento().getCategoria().equalsIgnoreCase(categoriaBuscada)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
