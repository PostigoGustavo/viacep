package br.com.consulta_cep;

import br.com.consulta_cep.client.ViaCepClient;
import br.com.consulta_cep.model.Endereco;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ConsultaCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaCepApplication.class, args);
	}

    {
        System.out.println("Escolha uma das opções de consulta a seguir: ");
        System.out.println("1. Consultar endereços por CEP");
        System.out.println("2. Descobrir CEP");
        Scanner sc = new Scanner(System.in);
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
               usarCep();
            case 2:
                break;
        }



    }

    private void usarCep() {
        System.out.println("Digite o CEP: ");
        String cep = new Scanner(System.in).nextLine();


        Endereco endereco;
        try {
            endereco = ViaCepClient.buscarCep(cep);
            if(endereco.getCep() != null){
                System.out.println("CEP:" + endereco.getCep());
                System.out.println("Logradouro:" + endereco.getLogradouro());
                System.out.println("Complemento:" + endereco.getComplemento());
                System.out.println("Bairro:" + endereco.getBairro());
                System.out.println("Cidade:" + endereco.getLocalidade());
                System.out.println("Estado:" + endereco.getUf());

            }else{
                System.out.println("CEP não encontrado!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar o CEP:" + e.getMessage());
            throw new RuntimeException(e);
        }
        menuInicial();
    }

    private void menuInicial() {
    }

}
