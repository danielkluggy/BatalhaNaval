/*
Batalha Naval
Introdução à Programação
Engenharia de Software - UDESC
Acadêmico: Daniel Larion Klug
*/

//package batalhanaval;

import java.util.Scanner;

public class BatalhaNaval {
    
    public static String[] tipo = {"Avião","Navio","Submarino","Canhão","Metralhadora"};
    public static int ntipo = 0;
    public static String limpatela = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    public static String espacotela = "\n\n\n\n\n";
    private static Scanner entrada;
    
    public static void main(String[] args) {
        int[][] tabuleiro1 = new int[5][5];
        int[][] tabuleiro2 = new int[5][5];
        int[][] equipamentos1 = new int[5][2];
        int[][] equipamentos2 = new int[5][2];
        int[] tiro1 = new int[2];
        int[] tiro2 = new int[2];
        int tentativas1 = 0, tentativas2 = 0;
        int acertos1 = 0, acertos2 = 0;
        int vez = 1;
        
        criarTabuleiro1(tabuleiro1);
        criarTabuleiro2(tabuleiro2);
        System.out.println("Bem vindo ao Batalha Naval.\nJogando: Jogador 1.");
        mostrarTabuleiro1(tabuleiro1);
        definirTabuleiro1(equipamentos1);
        System.out.println("Jogando: Jogador 2.");
        mostrarTabuleiro2(tabuleiro2);
        definirTabuleiro2(equipamentos2);
        
        do {
            if (vez == 1) {
                if (acertos1 == 5) {
                    break;
                }
                System.out.println("\nJogando: Jogador 1.");
                mostrarTabuleiro2(tabuleiro2);
                darTiro2(tiro2,tabuleiro2);
                tentativas1++;
            if (acertou2(tiro2,equipamentos2)) {
                alteraTabuleiro2(tiro2,equipamentos2,tabuleiro2,ntipo);
                System.out.printf("\nJogador 1 acertou o tiro (%d,%d).\n",tiro2[0]+1,tiro2[1]+1);
                acertos1++;
            } else {
                alteraTabuleiro2(tiro2,equipamentos2,tabuleiro2,ntipo);
                mostrarTabuleiro2(tabuleiro2);
                System.out.println(espacotela);
                System.out.printf("Jogador 1 errou o tiro (%d,%d).\n",tiro2[0]+1,tiro2[1]+1);
                vez++;
                }
            }
            if (vez == 2) {
                if (acertos2 == 5) {
                    break;
                }
                System.out.println("\nJogando: Jogador 2.");
                mostrarTabuleiro1(tabuleiro1);
                darTiro1(tiro1,tabuleiro1);
                tentativas2++;
            if (acertou1(tiro1,equipamentos1)) {
                alteraTabuleiro1(tiro1,equipamentos1,tabuleiro1,ntipo);
                System.out.printf("\nJogador 2 acertou o tiro (%d,%d).\n",tiro1[0]+1,tiro1[1]+1);
                acertos2++;
            } else {
                alteraTabuleiro1(tiro1,equipamentos1,tabuleiro1,ntipo);
                mostrarTabuleiro1(tabuleiro1);
                System.out.println(espacotela);
                System.out.printf("\nJogador 2 errou o tiro (%d,%d).\n",tiro1[0]+1,tiro1[1]+1);
                vez--;
                }
            }
        } while(acertos1 != 5 || acertos2 != 5);
        
        if (acertos1 == 5) {
            mostrarTabuleiro1(tabuleiro2);
            System.out.println("\n\nJogador 1 ganhou, acertando os 5 navios em "+tentativas1+" tentativas.\nJogador 2 acertou " + acertos2 + " navios em " + tentativas2 + " tentativas.");
        } else if (acertos2 == 5) {
            mostrarTabuleiro2(tabuleiro1);
            System.out.println("\n\nJogador 2 ganhou, acertando os 5 navios em "+tentativas2+" tentativas.\nJogador 1 acertou " + acertos1 + " navios em " + tentativas1 + " tentativas.");
        }
    }
    
    public static void criarTabuleiro1(int[][] tabuleiro1) {
        for(int linha = 0 ; linha < 5 ; linha++ )
            for(int coluna = 0 ; coluna < 5 ; coluna++ )
                tabuleiro1[linha][coluna] = 0;
    }
    
    public static void criarTabuleiro2(int[][] tabuleiro2) {
        for(int linha = 0 ; linha < 5 ; linha++ )
            for(int coluna = 0 ; coluna < 5 ; coluna++ )
                tabuleiro2[linha][coluna] = 0;
    }
    
    public static void definirTabuleiro1(int[][] equipamentos1) {
        String linha;
        String coluna;
        entrada = new Scanner(System.in);
        
        for (int equipamento = 0; equipamento < 5; equipamento++) {
            System.out.print("\nJogador 1, onde você quer colocar seu " + tipo[equipamento] + "?\n");
            System.out.print("Linha: ");
            linha = entrada.nextLine();
            System.out.print("Coluna: ");
            coluna = entrada.nextLine();
            
            if (linha.matches("[1-5]+") && coluna.matches("[1-5]+")) {
                for (int teste = 0; teste < equipamento; teste++) {
                    if(equipamentos1[teste][0] == (Integer.parseInt(linha) - 1) && equipamentos1[teste][1] == (Integer.parseInt(coluna) - 1)) {
                        System.out.print("Você já incluiu um equipamento neste espaço, tente outro!\n");
                        equipamento--;
                    } else {
                        equipamentos1[equipamento][0] = Integer.parseInt(linha) - 1;
                        equipamentos1[equipamento][1] = Integer.parseInt(coluna) - 1;
                    }
                }
            } else {
                System.out.print("ERRO: Informe valores entre 1 e 5 para definir linha e coluna\n");
                equipamento--;
            }
        }
        System.out.println(limpatela);
    }
    
    public static void definirTabuleiro2(int[][] equipamentos2) {
        String linha;
        String coluna;
        entrada = new Scanner(System.in);
        
        for (int equipamento = 0; equipamento < 5; equipamento++) {
            System.out.print("\nJogador 2, onde você quer colocar seu " + tipo[equipamento] + "?\n");
            System.out.print("Linha: ");
            linha = entrada.nextLine();
            System.out.print("Coluna: ");
            coluna = entrada.nextLine();
                
            if (linha.matches("[1-5]+") && coluna.matches("[1-5]+")) {
                for (int teste = 0; teste < equipamento; teste++) {
                    if(equipamentos2[teste][0] == (Integer.parseInt(linha) - 1) && equipamentos2[teste][1] == (Integer.parseInt(coluna) - 1)) {
                        System.out.print("Você já incluiu um equipamento neste espaço, tente outro!\n");
                        equipamento--;
                    } else {
                        equipamentos2[equipamento][0] = Integer.parseInt(linha) - 1;
                        equipamentos2[equipamento][1] = Integer.parseInt(coluna) - 1;
                    }
                }
            } else {
                System.out.print("ERRO: Informe valores entre 1 e 5 para definir linha e coluna\n");
                equipamento--;
            }
        }
        System.out.println(limpatela);
    }
    
    public static void mostrarTabuleiro1(int[][] tabuleiro1) {
        System.out.println("\t1 \t2 \t3 \t4 \t5");
        
        for(int linha = 0 ; linha < 5 ; linha++) {
            System.out.print((linha+1) + "");
            for(int coluna = 0 ; coluna < 5 ; coluna++) {
                switch (tabuleiro1[linha][coluna]) {
                    case -1 -> System.out.print("\t"+"x");
                    case 0 -> System.out.print("\t"+"*");
                    case 1 -> System.out.print("\t"+"A");
                    case 2 -> System.out.print("\t"+"N");
                    case 3 -> System.out.print("\t"+"S");
                    case 4 -> System.out.print("\t"+"C");
                    case 5 -> System.out.print("\t"+"M");
                }
            }
            System.out.println();
        }
    }
    
    public static void mostrarTabuleiro2(int[][] tabuleiro2) {
        System.out.println("\t1 \t2 \t3 \t4 \t5");
        
        for(int linha = 0 ; linha < 5 ; linha++) {
            System.out.print((linha+1)+"");
            for(int coluna = 0 ; coluna < 5 ; coluna++) {
                switch (tabuleiro2[linha][coluna]) {
                    case -1 -> System.out.print("\t"+"x");
                    case 0 -> System.out.print("\t"+"*");
                    case 1 -> System.out.print("\t"+"A");
                    case 2 -> System.out.print("\t"+"N");
                    case 3 -> System.out.print("\t"+"S");
                    case 4 -> System.out.print("\t"+"C");
                    case 5 -> System.out.print("\t"+"M");
                }
            }
            System.out.println();
        }
    }
    
    public static void darTiro1(int[] tiro1, int[][] tabuleiro1) {
        String linha;
        String coluna;
        entrada = new Scanner(System.in);
        boolean jaescolhido = false;
        
        do {
            System.out.print("Jogador 2, onde você quer atirar? \n");
            System.out.print("Linha: ");
            linha = entrada.nextLine();
            System.out.print("Coluna: ");
            coluna = entrada.nextLine();
                
            if (linha.matches("[1-5]+") && coluna.matches("[1-5]+")) {
                if(tabuleiro1[(Integer.parseInt(linha) - 1)][(Integer.parseInt(coluna) - 1)] == 0) {
                    tiro1[0] = (Integer.parseInt(linha) - 1);
                    tiro1[1] = (Integer.parseInt(coluna) - 1);
                    jaescolhido = true;
                } else
                System.out.print("Você já atirou nessa posição, escolha outra!\n\n");
            } else
            System.out.print("ERRO: Informe valores entre 1 e 5 para definir linha e coluna\n\n");
        } while (jaescolhido == false);
    }
    
    public static void darTiro2(int[] tiro2, int[][] tabuleiro2) {
        String linha;
        String coluna;
        entrada = new Scanner(System.in);
        boolean jaescolhido = false;
        
        do {
            System.out.print("Jogador 1, onde você quer atirar? \n");
            System.out.print("Linha: ");
            linha = entrada.nextLine();
            System.out.print("Coluna: ");
            coluna = entrada.nextLine();
                
            if (linha.matches("[1-5]+") && coluna.matches("[1-5]+")) {
                if(tabuleiro2[(Integer.parseInt(linha) - 1)][(Integer.parseInt(coluna) - 1)] == 0) {
                    tiro2[0] = (Integer.parseInt(linha) - 1);
                    tiro2[1] = (Integer.parseInt(coluna) - 1);
                    jaescolhido = true;
                } else
                System.out.print("Você já atirou nessa posição, escolha outra!\n\n");
            } else
            System.out.print("ERRO: Informe valores entre 1 e 5 para definir linha e coluna\n\n");
        } while (jaescolhido == false);
    }
    
    public static boolean acertou1(int[] tiro1, int[][] equipamentos1) {
        for(int equipamento1 = 0; equipamento1 < equipamentos1.length; equipamento1++) {
            if (tiro1[0] == equipamentos1[equipamento1][0] && tiro1[1] == equipamentos1[equipamento1][1]) {
                ntipo = equipamento1;
                ntipo++;
                return true;
            }
        }
        return false;
    }
    
    public static boolean acertou2(int[] tiro2, int[][] equipamentos2) {
        for(int equipamento2 = 0; equipamento2 < equipamentos2.length; equipamento2++) {
            if (tiro2[0] == equipamentos2[equipamento2][0] && tiro2[1] == equipamentos2[equipamento2][1]) {
                ntipo = equipamento2;
                ntipo++;
                return true;
            }
        }
        return false;
    }
    
    public static void alteraTabuleiro1(int[] tiro1, int[][] equipamentos1, int[][] tabuleiro1, int tipo1) {
        if(acertou1(tiro1,equipamentos1))
            tabuleiro1[tiro1[0]][tiro1[1]]=ntipo;
        else
            tabuleiro1[tiro1[0]][tiro1[1]]=-1;
    }
    
    public static void alteraTabuleiro2(int[] tiro2, int[][] equipamentos2, int[][] tabuleiro2, int tipo2) {
        if(acertou2(tiro2,equipamentos2))
            tabuleiro2[tiro2[0]][tiro2[1]]=ntipo;
        else
            tabuleiro2[tiro2[0]][tiro2[1]]=-1;
    }
    
}