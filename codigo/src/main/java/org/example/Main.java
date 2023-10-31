package org.example;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        MockItens mockItens = new MockItens(biblioteca);
        System.out.println("Bem vindo ao sistema de turismo");
        System.out.println("1 - Add mock de itens para teste");
        System.out.println("2 - Adicionar Cliente");
        System.out.println("3 - Adicionar Item");
        System.out.println("4 - Verificar Cliente");
        System.out.println("5 - Verificar Item");
        System.out.println("0 - Encerrar");
        String repostaUsuario = sc.nextLine();
        while(!repostaUsuario.equals("0")){
            switch (repostaUsuario){
                case "1" -> {
                    System.out.println("Adicionando...");
                }
                case "2" -> {
                    System.out.println("Digite o cpf:");
                    String cpf = sc.nextLine();
                    System.out.println("Digite o nome:");
                    String nome = sc.nextLine();
                    boolean resp = biblioteca.cadastrarCliente(nome, cpf);
                    String resposta = resp ? "Cliente cadastrado" : "Cliente já cadastrado no sistema";
                    System.out.println(resp);
                }
                case "3" -> {
                    System.out.println("Que tipo de item gostaria de adicionar? ");
                    String tipo = sc.nextLine();
                    System.out.println("Nome:");
                    String nome = sc.nextLine();
                    System.out.println("Quantidade:");
                    int quantidade = sc.nextInt();
                    System.out.println("Autor: ");
                    String autor = sc.nextLine();
                    System.out.println("Ano: ");
                    int ano = sc.nextInt();
                    biblioteca.cadastrarItem(tipo, nome, quantidade, autor, ano);

                }
                case "4" -> {
                    System.out.println("Digite o cpf: ");
                    String cpf = sc.nextLine();
                    System.out.println(biblioteca.verificarCadastrados(cpf));
                }
                case "5" -> {
                    System.out.println("Como gostaria de encontrar seu item?");
                    System.out.println("1 - Ano");
                    System.out.println("2 - Autor");
                    System.out.println("3 - Nome");
                    System.out.println("4 - Tipo");
                    String resposta2 = sc.nextLine();
                    switch (resposta2) {
                        case "1" -> {
                            System.out.println("Digite o ano");
                            int resposta2 = sc.nextInt();
                            biblioteca.findByAno(resposta2);
                        }
                        case "2" -> {
                            System.out.println("Digite o autor");
                            String resposta2 = sc.nextLine();
                            biblioteca.findByAutor(resposta2);
                        }
                        case "3" -> {
                            System.out.println("Digite o nome");
                            String resposta2 = sc.nextLine();
                            biblioteca.findByNome(resposta2);
                        }
                        case "4" -> {
                            System.out.println("Digite o tipo");
                            String resposta2 = sc.nextLine();
                            biblioteca.findByTipo(resposta2);
                        }
                    }
                }

            }
            repostaUsuario = sc.nextLine();
        }

    }

}