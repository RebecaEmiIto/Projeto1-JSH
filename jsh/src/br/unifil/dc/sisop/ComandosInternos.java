package br.unifil.dc.sisop;

import java.time.format.DateTimeFormatter;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Write a description of class ComandosInternos here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class ComandosInternos {

    public static int exibirRelogio() {
        DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH:mm"); // formato da hora
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // formato da data
        LocalDateTime now = LocalDateTime.now(); // pega a data e hora no momento
        System.out.println("Sao " + hour.format(now) + " de " + date.format(now)); // printa a data e hora no formato escrito
        return 0;
    }

    public static int escreverListaArquivos(Optional<String> nomeDir) {
        String[] pathnames;
        File nomeFile = new File(nomeDir.get());
        pathnames = nomeFile.list();
        for (String pathname : pathnames) {
            System.out.println(pathname);
        }
        return 0;
    }

    public static int criarNovoDiretorio(String nomeDir) {
        File nomeFile = new File(nomeDir);
        if (nomeFile.exists()) {
            System.out.println("ARQUIVO OU DIRETORIO JÁ EXISTENTE");
        } else {
            nomeFile.mkdir();
        }
        return 0;
    }

    public static int apagarDiretorio(String nomeDir) {
        File nomeFile = new File(nomeDir);
        if (nomeFile.exists()) {
            nomeFile.delete();
        } else {
            System.err.format("%s: no such file or directory%n", nomeDir);
        }
        return 0;
    }

    public static int mudarDiretorioTrabalho(String nomeDir) {
        if(nomeDir.equals("..")){
            String path = System.getProperty("user.dir");
            File caminho = new File(path);
            System.setProperty("user.dir", caminho.getParent());
        }else{
            String path = System.getProperty("user.dir") + "/" + nomeDir;   
            System.setProperty("user.dir", path);
        }
        return 0;
    }

    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {}

}