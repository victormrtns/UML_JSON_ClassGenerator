package br.unesp.rc.Modelos;

import java.util.List;
import java.util.Scanner;

public class GeradorRelacionamento {
    private String classe1;
    private String classe2;
    private String multiplicidade1 = "";
    private String multiplicidade2 = "";
    private String relacionamento;

    public GeradorRelacionamento() {}

    public GeradorRelacionamento(String classe1, String classe2, String multiplicidade1, String multiplicidade2, String relacionamento) {
        this.classe1 = classe1;
        this.classe2 = classe2;
        this.multiplicidade1 = multiplicidade1;
        this.multiplicidade2 = multiplicidade2;
        this.relacionamento = relacionamento;
    }

    public String getClasse1() {
        return classe1;
    }

    public void setClasse1(String classe1) {
        this.classe1 = classe1;
    }

    public String getClasse2() {
        return classe2;
    }

    public void setClasse2(String classe2) {
        this.classe2 = classe2;
    }

    public String getMultiplicidade1() {
        return multiplicidade1;
    }

    public void setMultiplicidade1(String multiplicidade1) {
        this.multiplicidade1 = multiplicidade1;
    }

    public String getMultiplicidade2() {
        return multiplicidade2;
    }

    public void setMultiplicidade2(String multiplicidade2) {
        this.multiplicidade2 = multiplicidade2;
    }

    public String getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(String relacionamento) {
        this.relacionamento = relacionamento;
    }

    public GeradorRelacionamento criarRelacionamento(Scanner sc, List<GeradorClasse> classes) {
        boolean encontrado = false;
        GeradorRelacionamento relacionamento = new GeradorRelacionamento();

        System.out.print("Digite o nome da primeira classe: ");
        relacionamento.setClasse1(sc.next());

        System.out.print("Digite o nome da segunda classe: ");
        relacionamento.setClasse2(sc.next());

        if(relacionamento.getClasse1().equals(relacionamento.getClasse2())) {
            System.out.println("ERRO: Nao e possivel fazer um relacionamento entre a mesma classe");
            return null;
        }

        for(GeradorClasse classe : classes) {
            if (classe.getNomeClasse().equals(relacionamento.getClasse1())) {
                encontrado = true;
                break;
            }
        }

        if(encontrado) {
            encontrado = false;
            for (GeradorClasse classe : classes)
                if (classe.getNomeClasse().equals(relacionamento.getClasse2())) {
                    encontrado = true;
                    break;
                }
        }

        if(!encontrado) {
            System.out.println("ERRO: Uma das classes digitadas nao existe no gerador!");
            return null;
        }

        gerarTipoRelacionamento(relacionamento, sc);
        gerarMultiplicidade(relacionamento, sc);

        return relacionamento;
    }

    private String gerarTipoRelacionamento(GeradorRelacionamento relacionamento, Scanner sc) {
        int op;

        do {
            System.out.println("*---------- RELACIONAMENTO ----------*");
            System.out.println("|  Digite     | Para                 |");
            System.out.println("|    0.       | Heranca              |");
            System.out.println("|    1.       | Composicao           |");
            System.out.println("|    2.       | Agregacao            |");
            System.out.println("|    3.       | Nenhum               |");
            System.out.println("*------------------------------------*");
            System.out.print("Digite o tipo de relacionamento: ");
            op = sc.nextInt();

            switch (op) {
                case 0 -> relacionamento.setRelacionamento("inheritance");
                case 1 -> relacionamento.setRelacionamento("composition");
                case 2 -> relacionamento.setRelacionamento("aggregation");
                case 3 -> relacionamento.setRelacionamento("none");
                default -> System.out.println("ERRO: O relacionamento escolhido nao existe!");
            }
        } while(op != 0 && op != 1 && op != 2 && op != 3);

        return relacionamento.getRelacionamento();
    }

    private void gerarMultiplicidade(GeradorRelacionamento relacionamento, Scanner sc) {
        int op, i;

        for(i = 1; i <= 2; i++) {
            do {
                String cls = i == 1 ? relacionamento.getClasse1() : relacionamento.getClasse2();

                System.out.println("*---------- MULTIPLICIDADE ----------*");
                System.out.println("|  Digite     | Para                 |");
                System.out.println("|    0.       | Um                   |");
                System.out.println("|    1.       | Muitos               |");
                System.out.println("*------------------------------------*");
                System.out.print("Digite o tipo de multiplicidade da classe " + cls + ": ");
                op = sc.nextInt();

                switch (op) {
                    case 0 -> {
                        if(i == 1)
                            relacionamento.setMultiplicidade1("1");
                        else
                            relacionamento.setMultiplicidade2("1");
                    }
                    case 1 -> {
                        if(i == 1)
                            relacionamento.setMultiplicidade1("1..*");
                        else
                            relacionamento.setMultiplicidade2("1..*");
                    }
                    default -> System.out.println("ERRO: O relacionamento escolhido nao existe!");
                }
            } while(op != 0 && op != 1 && op != 2);
        }
    }

    @Override
    public String toString() {
        return "GeradorRelacionamento{" +
                "classe1='" + classe1 + '\'' +
                ", classe2='" + classe2 + '\'' +
                ", multiplicidade1='" + multiplicidade1 + '\'' +
                ", multiplicidade2='" + multiplicidade2 + '\'' +
                ", relacionamento='" + relacionamento + '\'' +
                '}';
    }
}
