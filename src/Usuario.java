import java.util.ArrayList;
import java.util.Objects;

public class Usuario {
    private String nombre;
    private String correo;
    private int telefono;
    private ArrayList<Alojamiento> alojamientos;

    public Usuario(String nombre, String correo, int telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        alojamientos = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public int getTelefono() { return telefono; }
    public ArrayList<Alojamiento> getAlojamientos(){return alojamientos; }
    public void agregarAlojamiento(Alojamiento alojamiento){ alojamientos.add(alojamiento); }

    public boolean esNombreValido(){
        return !nombre.isEmpty();
    }
    public boolean esTelefonoValido(){
        return telefono > 0;
    }

}
