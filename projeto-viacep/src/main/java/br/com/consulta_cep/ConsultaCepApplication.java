package br.com.consulta_cep;

import br.com.consulta_cep.client.ViaCepClient;
import br.com.consulta_cep.model.Endereco;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ConsultaCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaCepApplication.class, args);
	}

    {
        menuInicial();



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
        System.out.println("Escolha uma das opções de consulta a seguir: ");
        System.out.println("1. Consultar endereços por CEP");
        System.out.println("2. Descobrir CEP");
        System.out.println("3. Sair");
        Scanner sc = new Scanner(System.in);
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                usarCep();
            case 2:
                usarEndereco();
            case 3:
                System.exit(0);
        }
    }

    private void usarEndereco() {
        System.out.println("Digite o Estado (UF): ");
        String uf = new Scanner(System.in).nextLine();

        System.out.println("Digite a Cidade: ");
        String cidade = new Scanner(System.in).nextLine().trim();

        System.out.println("Digite o Logradouro: ");
        String logradouro = new Scanner(System.in).nextLine().trim();


        try {
            List<Endereco> enderecos = ViaCepClient.descobrirCep(uf, cidade, logradouro);

            if (enderecos != null && !enderecos.isEmpty()) {
                for (Endereco endereco : enderecos) {
                    System.out.println("CEP: " + endereco.getCep());
                    System.out.println("Logradouro: " + endereco.getLogradouro());
                    System.out.println("Bairro: " + endereco.getBairro());
                    System.out.println("Cidade: " + endereco.getLocalidade());
                    System.out.println("Estado: " + endereco.getUf());
                    System.out.println("-------------------------");
                }
            } else {
                System.out.println("Nenhum CEP encontrado!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar o CEP: " + e.getMessage());
        }

        menuInicial();
    }

}
