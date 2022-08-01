package br.unesp.rc.Modelos;

import java.util.Scanner;

public class GeradorAtributo {
    private String nome;
    private String tipo;
    private String modificador;
    public GeradorAtributo() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModificador() {
        return modificador;
    }

    public void setModificador(String modificador) {
        this.modificador = modificador;
    }

    public void criarAtributo(Scanner sc) {
        boolean encontrado = false;
        int op;
        String[] tipos = { "String", "byte", "short", "int", "long", "float", "double", "boolean", "char" };

        System.out.println("*---------- Atributo ----------*");

        System.out.print("Digite o nome: ");
        sc.nextLine();
        this.nome = formatarNomeAtributo(sc.nextLine());

        do {
            System.out.println("Tipos existentes: String, byte, short, int, long, float, double, boolean, char.");
            System.out.println("Digite o tipo de '" + this.nome + "':");
            this.tipo = sc.next();

            for(String tipo : tipos)
                if(tipo.equalsIgnoreCase(this.tipo)) {
                    this.tipo = tipo;
                    encontrado = true;
                    break;
                }

            if(!encontrado)
                System.out.println("ERRO: O tipo digitado nao existe! Verifique os tipos existentes.");
        } while (!encontrado);

        do {
            System.out.println("*------------ MODIFICADOR -----------*");
            System.out.println("|  Digite     | Para                 |");
            System.out.println("|    0.       | private              |");
            System.out.println("|    1.       | protected            |");
            System.out.println("|    2.       | public               |");
            System.out.println("*------------------------------------*");
            System.out.print("Digite o modificador: ");

            op = sc.nextInt();

            switch (op) {
                case 0 -> this.modificador = "private";
                case 1 -> this.modificador = "protected";
                case 2 -> this.modificador = "public";
                default -> System.out.println("ERRO: o modificador escolhido nao existe");
            }
        } while(op != 0 && op != 1 && op != 2);
    }

    private String formatarNomeAtributo(String nome) {
        StringBuilder sb = new StringBuilder();
        String[] split = nome.split(" ");
        sb.append(split[0].toLowerCase());

        for(int i = 1; i < split.length; i++)
            sb.append(Character.toUpperCase(split[i].charAt(0))).append(split[i].substring(1).toLowerCase());

        return sb.toString();
    }

    @Override
    public String toString() {
        return "GeradorAtributo{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", modificador='" + modificador + '\'' +
                '}';
    }
}
