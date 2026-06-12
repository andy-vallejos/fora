public class Resenia {
    protected int puntaje;
    private String comentario;
    public Resenia(int puntaje) {
        this.puntaje = puntaje;
    }
    public Resenia(int puntaje, String comentario){
        this.puntaje = puntaje;
        this.comentario = comentario;
    }
    public int getPuntaje() {
        return puntaje;
    }

    public String getComentario() {
        return comentario;
    }
    public boolean esPuntuacionValida() {
        return puntaje >= 1 && puntaje <= 5;
    }
    public boolean esValida(){
        return esPuntuacionValida() && esComentarioValido();
    }

    public boolean esComentarioValido(){
        return comentario!=null&&comentario.length()<=300;
    }

}