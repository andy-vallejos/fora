import java.util.ArrayList;
import java.util.Objects;

public class Publicacion {
    private Alojamiento alojamiento;
    private ArrayList<ReporteSeguridad> reportes;
    private int denuncias;

    public Publicacion(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
        this.reportes = new ArrayList<>();
        denuncias = 0;
    }

    public Alojamiento getAlojamiento() { return alojamiento; }
    public ArrayList<ReporteSeguridad> getReportes() { return reportes; }
    public int getDenuncias() { return denuncias; }
    public void agregarReporteSeguridad(ReporteSeguridad reporte) {this.reportes.add(reporte);}

    public double calcularIndicePromedio(){
        if(reportes.isEmpty()){
            return 10;
        }
        double suma=0;
        for(int i=0;i<reportes.size();i++){
            suma+=reportes.get(i).calcularIndice();
        }
        return suma/reportes.size();
    }

    public boolean estaBloqueada(){
        boolean resultado = false;
        if(denuncias >= 5){
            resultado = true;
        }

        return resultado;
    }

    public String clasificarZona() {
        double indice = calcularIndicePromedio();
        if (indice > 7) {
            return "Segura";
        }
        if (indice >= 4) {
            return "Regular";
        }
        return "Peligrosa";
    }

    public void denunciar(){
        denuncias++;
    }

}
