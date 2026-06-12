
public class LugarInteres {
    private String nombre;
    private String categoria;
    private String direccion;
    private String horarioAtencion;

    public LugarInteres(String nombre, String categoria, String direccion, String horarioAtencion) {
        this.nombre = nombre;
        this.categoria = PuntosInteres.normalizarCategoria(categoria);
        this.direccion = direccion;
        this.horarioAtencion = horarioAtencion;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getHorarioAtencion() {
        return horarioAtencion;
    }
}
