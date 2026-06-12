import java.util.ArrayList;
public class PuntosInteres{
    private ArrayList<LugarInteres> lugares;

    public PuntosInteres(ArrayList<LugarInteres> lugares){
        this.lugares = lugares;
    }

    public ArrayList<LugarInteres> buscarCategoria(String categoriaBuscada){
        ArrayList<LugarInteres> resultado = new ArrayList<>();
        String categoriaNormalizada = normalizarCategoria(categoriaBuscada);
        if(categoriaNormalizada.length() > 0){
            for(LugarInteres lugar: lugares){
                if(lugar.getCategoria().equals(categoriaNormalizada)){
                    resultado.add(lugar);
                }
            }
        }
        return resultado;
    }

    public boolean verificarCategoria(String categoria, ArrayList<LugarInteres> lista){
        boolean resp = false;
        String categoriaNormalizada = normalizarCategoria(categoria);
        if(lista.size() > 0){
            resp = true;
            int i = 0;
            while(i < lista.size() && resp){
                if(lista.get(i).getCategoria().equals(categoriaNormalizada)){
                    i += 1;
                }else{
                    resp = false;
                }
            }
        }
        return resp;
    }

    public String normalizarCategoria(String categoria){
        String resp = "";
        String categoriaRecortada = categoria.strip();
        if(categoriaRecortada.length() > 0){
            resp = categoriaRecortada.substring(0,1).toUpperCase() +
                    categoriaRecortada.substring(1).toLowerCase();
        }
        return resp;
    }
}
