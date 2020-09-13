package br.unifil.dc.sisop;

import java.io.File;
import java.util.*;

/**
 * Write a description of class Jsh here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class Jsh {

    /**
     * Funcao principal do Jsh.
     */
    public static void promptTerminal() {

        while (true) {
            exibirPrompt();
            ComandoPrompt comandoEntrado = lerComando();
            executarComando(comandoEntrado);
        }
    }

    /**
     * Escreve o prompt na saida padrao para o usuário reconhecê-lo e saber que o
     * terminal está pronto para receber o próximo comando como entrada.
     */
    public static void exibirPrompt() {
        currentDirectory = System.getProperty("user.dir");
        username = System.getProperty("user.name");
        UID = System.getProperty("id -u " + username);
        System.out.print(username + "#"+ UID +":" + currentDirectory + "/% ");
    }

    /**
     * Preenche as strings comando e parametros com a entrada do usuario do
     * terminal. A primeira palavra digitada eh sempre o nome do comando desejado.
     * Quaisquer outras palavras subsequentes sao parametros para o comando. A
     * palavras sao separadas pelo caractere de espaco ' '. A leitura de um comando
     * eh feita ate que o usuario pressione a tecla <ENTER>, ou seja, ate que seja
     * lido o caractere EOL (End Of Line).
     *
     * @return
     */
    public static ComandoPrompt lerComando() {
        Scanner scan = new Scanner(System.in);
        String comando = scan.nextLine();
        ComandoPrompt comandoprompt = new ComandoPrompt(comando);
        return comandoprompt;
    }
    
    /**
     * Recebe o comando lido e os parametros, verifica se eh um comando interno e,
     * se for, o executa.
     * 
     * Se nao for, verifica se é o nome de um programa terceiro localizado no atual
     * diretorio de trabalho . Se for, cria um novo processo e o executa. Enquanto
     * esse processo executa, o processo do uniterm deve permanecer em espera.
     *
     * Se nao for nenhuma das situacoes anteriores, exibe uma mensagem de comando ou
     * programa desconhecido.
     */
    public static void executarComando(ComandoPrompt comando) {
        try {
            switch (comando.getNome()) {
                
                case "relogio":
                ComandosInternos.exibirRelogio();
                    break;

                case "la":
                    ComandosInternos.escreverListaArquivos(Optional.of(currentDirectory));
                    break;

                case "cd":
                    list = comando.getArgumentos();
                    String argumento = list.get(1);  
                    ComandosInternos.criarNovoDiretorio(argumento);
                    break;

                case "ad":
                    list = comando.getArgumentos();
                    argumento = list.get(1);  
                    ComandosInternos.apagarDiretorio(argumento);
                    break;

                case "mdt":
                    list = comando.getArgumentos();
                    argumento = list.get(1);  
                    ComandosInternos.mudarDiretorioTrabalho(argumento);
                    break;

                case "encerrar":
                    System.exit(0);
                    break;

                default:
                    executarPrograma(comando);
                    System.out.println("");
                    break;
            }
        } catch (Exception e) {
            executarPrograma(comando);
        }
    }

    public static int executarPrograma(ComandoPrompt comando) {
        try{    
            list = comando.getArgumentos();
            String argumento = list.get(1);
            String[] pathnames;
            File nomeFile = new File(argumento);
            pathnames = nomeFile.list();
            String comandoString = comando.getNome();
            for (String pathname : pathnames) {
                if(pathname == comandoString){
                    
                }else{
                    System.out.println("o comando ou programa não existe");
                }
            }
            //System.out.println("Invalid arguments");
        }catch(Exception e){
            System.err.print("Invalid arguments");
        }
        return 1;
    }

    /**
     * Entrada do programa. Provavelmente você não precisará modificar esse método.
     */
    public static void main(String[] args) {
        promptTerminal();
    }

    /**
     * Essa classe não deve ser instanciada.
     */
    private Jsh() {
    }

    static String currentDirectory;
    static String username;
    static String UID;
    static List<String> list;
}