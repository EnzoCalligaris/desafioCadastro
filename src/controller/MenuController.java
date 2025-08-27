package controller;

import model.Pet;
import model.SexoPet;
import model.TipoPet;
import util.ArmazenamentoInfo;
import util.FormularioUtil;
import util.Validador;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private List<Pet> pets = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        int opcao = 0;

        while (opcao != 6) {
            System.out.println("\n===== Sistema de Adoção de Pets =====");
            System.out.println("1. Cadastrar um novo pet");
            System.out.println("2. Alterar os dados do pet cadastrado");
            System.out.println("3. Deletar um pet cadastrado");
            System.out.println("4. Listar todos os pets cadastrados");
            System.out.println("5. Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao < 1 || opcao > 6) {
                    System.out.println("⚠ Opção inválida! Digite um número entre 1 e 6.");
                    continue;
                }

                switch (opcao) {
                    case 1 -> mostrarFormulario();
                    case 2 -> System.out.println("👉 [Alterar Pet]");
                    case 3 -> System.out.println("👉 [Deletar Pet]");
                    case 4 -> System.out.println("👉 [Listar Pets]");
                    case 5 -> System.out.println("👉 [Listar por critério]");
                    case 6 -> System.out.println("👋 Saindo do sistema...");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠ Entrada inválida! Digite apenas números.");
                scanner.nextLine();
            }
        }
    }

    private void mostrarFormulario() {
        List<String> perguntas = FormularioUtil.lerPerguntas("src/util/formulario.txt");
        System.out.println("\n===== Cadastro de Novo Pet =====");
        for (String pergunta : perguntas) {
            System.out.println(pergunta);
        }
        cadastrarPet();
    }

    public void cadastrarPet(){
        try {
            System.out.println("\nDigite o nome e o sobrenome do Pet: ");
            String nome = Validador.validarNome(scanner.nextLine());

            System.out.println("Tipo do Pet (CACHORRO/GATO):");
            TipoPet tipo = TipoPet.valueOf(scanner.nextLine().toUpperCase());

            System.out.println("Sexo do Pet (MACHO/FEMEA): ");
            SexoPet sexo = SexoPet.valueOf(scanner.nextLine().toUpperCase());

            System.out.println("Numero da casa: ");
            String numero = scanner.nextLine();

            System.out.println("Nome da rua: ");
            String rua = scanner.nextLine();

            System.out.println("Idade do Pet: ");
            int idade = Validador.validarIdade(scanner.nextDouble());
            scanner.nextLine();

            System.out.println("Nome da cidade: ");
            String cidade = scanner.nextLine();
            String endereco = Validador.validarEndereco(numero, rua, cidade);

            System.out.println("Peso do pet em KG: ");
            double peso = Validador.validarPeso(scanner.nextDouble());
            scanner.nextLine();

            System.out.println("Raça do pet: ");
            String raca = Validador.validarTexto(scanner.nextLine());

            Pet pet = new Pet(nome, tipo, sexo, endereco, idade, peso, raca);
            pets.add(pet);
            ArmazenamentoInfo.salvarPet(pet);
        }
        catch (Exception e){
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }

    private void listarPets() {
        if (pets.isEmpty()) {
            System.out.println("⚠ Nenhum pet cadastrado!");
            return;
        }
        pets.forEach(System.out::println);
    }
}
