package util;

import model.Pet;
import model.SexoPet;
import model.TipoPet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Busca {
    public static List<Pet> carregarPets() {
        List<Pet> pets = new ArrayList<>();
        File pasta = new File("petsCadastrados");

        if (!pasta.exists() || !pasta.isDirectory()) {
            System.out.println("Nenhum pet cadastrado ainda.");
            return pets;
        }

        File[] arquivos = pasta.listFiles((dir, name) -> name.endsWith(".txt"));
        if (arquivos == null) return pets;

        try {
            for (File arquivo : arquivos) {
                try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                    Pet pet = new Pet();
                    String linha;

                    linha = reader.readLine();
                    if (linha != null && linha.contains(" - ")) {
                        pet.setNome(linha.split(" - ")[1]);
                    }

                    linha = reader.readLine();
                    if (linha != null && linha.contains(" - ")) {
                        pet.setTipo(TipoPet.valueOf(linha.split(" - ")[1].toUpperCase(Locale.ROOT)));
                    }

                    linha = reader.readLine();
                    if (linha != null && linha.contains(" - ")) {
                        pet.setSexo(SexoPet.valueOf(linha.split(" - ")[1].toUpperCase(Locale.ROOT)));
                    }

                    linha = reader.readLine();
                    if (linha != null && linha.contains(" - ")) {
                        pet.setEndereco(linha.split(" - ")[1]);
                    }

                    linha = reader.readLine();
                    if (linha != null && linha.contains(" - ")) {
                        pet.setIdade(Integer.valueOf(linha.split(" - ")[1]));
                    }

                    linha = reader.readLine();
                    if (linha != null && linha.contains(" - ")) {
                        pet.setPeso(Double.valueOf(linha.split(" - ")[1]));
                    }

                    linha = reader.readLine();
                    if (linha != null && linha.contains(" - ")) {
                        pet.setRaca(linha.split(" - ")[1]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pets;
    }

    public static List<Pet> buscarPets(List<Pet> pets, TipoPet tipo, String criterio1, String valor1, String criterio2, String valor2) {
        List<Pet> resultados = new ArrayList<>();

        for (Pet pet : pets) {
            if (!pet.getTipo().equals(tipo)) continue;

            boolean match1 = criterio1 == null || verificarCriterio(pet, criterio1, valor1);
            boolean match2 = criterio2 == null || verificarCriterio(pet, criterio2, valor2);

            if (match1 && match2) {
                resultados.add(pet);
            }
        }
        return resultados;
    }

    private static boolean verificarCriterio(Pet pet, String criterio, String valor) {
        valor = valor.toLowerCase(Locale.ROOT);

        switch (criterio.toLowerCase()) {
            case "nome":
                return pet.getNome().toLowerCase().contains(valor);
            case "sexo":
                return pet.getSexo().name().toLowerCase().equals(valor);
            case "idade":
                return String.valueOf(pet.getIdade()).contains(valor);
            case "peso":
                return String.valueOf(pet.getPeso()).contains(valor);
            case "raca":
                return pet.getRaca().toLowerCase().contains(valor);
            case "endereco":
                return pet.getEndereco().toLowerCase().contains(valor);
            default:
                return false;
        }
    }


    public static void exibirResultados(List<Pet> resultados) {
        if (resultados.isEmpty()) {
            System.out.println("Nenhum pet encontrado.");
            return;
        }

        for (Pet pet : resultados) {
            System.out.printf("%s - %s - %s - %s - %s - %s - %s%n",
                    pet.getNome(),
                    pet.getTipo(),
                    pet.getSexo(),
                    pet.getEndereco(),
                    pet.getIdade(),
                    pet.getPeso(),
                    pet.getRaca());
        }
    }
}
