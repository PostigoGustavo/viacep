package br.com.consulta_cep.client;


import com.google.gson.Gson;
import br.com.consulta_cep.model.Endereco;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class ViaCepClient {
    public static Endereco buscarCep(String cep) throws Exception {
        String url = "https://viacep.com.br/ws/"+cep+"/json/";
        //Fazendo a requisição GET
        String jsonResponse;
        jsonResponse = Request.Get(url).execute().returnContent().asString();

        //Convertendo JSON para objeto Endereco
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, Endereco.class);
    }

    public static List<Endereco> descobrirCep(String uf, String localidade, String logradouro) throws IOException {
        String cidadeFormatada = localidade.trim().replace(" ", "%20");
        String logradouroFormatado = logradouro.trim().replace(" ", "%20");
        String url = "https://viacep.com.br/ws/" + uf + "/" + cidadeFormatada + "/" + logradouroFormatado + "/json/";
        ;

        // Fazendo GET para descobrir o CEP
        String jsonResponse = Request.Get(url).execute().returnContent().asString();

        // Convertendo JSON (array) para lista de objetos Endereco
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Endereco>>() {}.getType();
        return gson.fromJson(jsonResponse, listType);
    }

}
