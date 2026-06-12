import java.util.ArrayList;
import java.util.Objects;

public class Alojamiento {
    private String ubicacion;
    private String categoria;
    private Usuario usuario;
    private int precio;
    private ArrayList<Resenia> resenias;
    private boolean wifi;

    public Alojamiento(String ubicacion, String categoria, Usuario usuario, int precio) {
        this.ubicacion = ubicacion;
        this.categoria = categoria;
        this.usuario = usuario;
        resenias = new ArrayList<>();
        this.precio = precio;
        wifi = false;
    }

    public String getUbicacion() { return ubicacion; }
    public String getCategoria() { return categoria; }
    public Usuario getUsuario() { return usuario; }
    public int getPrecio(){ return precio; }
    public ArrayList<Resenia> getResenias() {return resenias; }
    public boolean getWifi(){ return wifi; }
    public void agregarWifi(){ wifi = true; }


    public boolean tienePrecioMenorOIgualA(int precioMaximo) {
        return this.precio <= precioMaximo;
    }

    public void agregarResenia(Resenia r) {
        if (r != null && r.esPuntuacionValida()) {
            resenias.add(r);
        }
    }

    public double promedioDeResenias(){
        double promedio = 0.0;

        if (!resenias.isEmpty()) {
            double sumaPuntajes = 0;

            for (Resenia r : resenias) {
                sumaPuntajes += r.getPuntaje();
            }

            promedio = sumaPuntajes / resenias.size();
            promedio = Math.round(promedio * 100.0) / 100.0;
        }

        return promedio;
    }
}