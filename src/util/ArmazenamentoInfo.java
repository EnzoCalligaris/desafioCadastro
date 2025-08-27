package util;

import model.Pet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArmazenamentoInfo {

    public static void salvarPet(Pet pet) {
        try {
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
            String dataHora = agora.format(formatter);

            String nomeDono = pet.getNome().toUpperCase().replace(" ", "");

            File pasta = new File("petsCadastrados");
            if (!pasta.exists()) {
                    pasta.mkdirs();
            }

            File arquivo = new File(pasta, dataHora + "-" + nomeDono + ".txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                writer.write("1 - " + pet.getNome());
                writer.newLine();
                writer.write("2 - " + pet.getTipo().name()); // TipoPet ENUM
                writer.newLine();
                writer.write("3 - " + pet.getSexo().name()); // SexoPet ENUM
                writer.newLine();
                writer.write("4 - " + pet.getEndereco());
                writer.newLine();
                writer.write("5 - " + pet.getIdade());
                writer.newLine();
                writer.write("6 - " + pet.getPeso());
                writer.newLine();
                writer.write("7 - " + pet.getRaca());
                writer.newLine();
            }

            System.out.println("✅ Arquivo salvo em: " + arquivo.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
