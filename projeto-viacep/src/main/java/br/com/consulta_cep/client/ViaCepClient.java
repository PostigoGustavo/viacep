package br.com.consulta_cep.client;


import com.google.gson.Gson;
import br.com.consulta_cep.model.Endereco;
import org.apache.http.client.fluent.Request;

import java.io.IOException;


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

    public static Endereco descobrirCep(String uf, String localidade, String logradouro) throws IOException {
        String url = "https://viacep.com.br/ws/"+uf+"/"+ localidade+ "/"+ logradouro+ "/json/";
        //Fazendo GET para descobrir o CEP
        String jsonResponse;
        jsonResponse = Request.Get(url).execute().returnContent().asString();
        //Convertendo JSON para objeto Endereco
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, Endereco.class);
    }
}
