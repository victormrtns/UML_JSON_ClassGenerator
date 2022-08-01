package br.unesp.rc.Utils;

import br.unesp.rc.Modelos.GeradorProjeto;
import com.google.gson.Gson;

public class GsonUtils {

    private GsonUtils() {}

    public static String objetoToXML(GeradorProjeto projeto) {
        Gson gson = new Gson();

        return gson.toJson(projeto);
    }

    public static Object xmlToObjeto(String json, Class classe) {
        Gson gson = new Gson();

        return gson.fromJson(json, classe);
    }
}
