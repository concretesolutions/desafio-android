package isaacborges.com.projetoconcrete.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.List;

import isaacborges.com.projetoconcrete.model.Repositorio;

import static isaacborges.com.projetoconcrete.ConstantesDaAplicacao.CHAVE_REPOSITORIO;

public class ConversorJson {

    public static List<?> converteJsonParaLista(String jsonString, Class<?> classe){
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeFactory typeFactory = mapper.getTypeFactory();
            return mapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, classe));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String converteListaParaJson(List<?> lista){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(lista);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
