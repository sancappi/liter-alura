package br.com.alura.literAlura.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConsumoDadosDaAPI {
    public String obterDadosDaAPI(String enderecoURL){
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(enderecoURL))
                .build();
        HttpResponse<String> resposta = null;

        try {
            resposta = cliente
                    .send(requisicao, HttpResponse.BodyHandlers.ofString());
        } catch(IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return resposta.body();
    }
}
