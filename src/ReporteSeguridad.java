public class ReporteSeguridad extends Resenia{
    private int Iluminacion;
    private double indice;
    public ReporteSeguridad(int puntajeSeguridad, int Iluminacion){
        super(puntajeSeguridad);
        this.Iluminacion=Iluminacion;
    }

    public int getPuntajeSeguridad(){
        return puntaje;
    }

    public void setPuntajeSeguridad(int puntajeSeguridad){
        this.puntaje=puntajeSeguridad;
    }

    public int getIluminacion(){
        return Iluminacion;
    }

    public void setIluminacion(int Iluminacion){
        this.Iluminacion=Iluminacion;
    }

    private int normalizar(int valor ){
        if(valor<0){
            valor=0;
        }
        return valor;
    }

    public double calcularIndice() {
        int puntajeSeguridad=normalizar(getPuntajeSeguridad());
        int iluminacion = normalizar(getIluminacion());

        indice=(puntajeSeguridad+iluminacion)/2.0;

        if (indice > 10) {
            indice = 10;
        }
        return indice;
    }
}