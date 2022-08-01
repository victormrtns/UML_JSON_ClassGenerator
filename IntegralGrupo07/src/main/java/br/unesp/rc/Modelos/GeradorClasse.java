package br.unesp.rc.Modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GeradorClasse {
    private String nomeClasse;

    private String nomePacote;

    private List<GeradorAtributo> atributos = new ArrayList<>();

    public GeradorClasse() {}

    public GeradorClasse(String nomeClasse, String nomePacote, List<GeradorAtributo> atributos) {
        this.nomeClasse = nomeClasse;
        this.nomePacote = nomePacote;
        this.atributos = atributos;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public List<GeradorAtributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<GeradorAtributo> atributos) {
        this.atributos = atributos;
    }

    public String getNomePacote() {
        return nomePacote;
    }

    public void setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
    }

    public GeradorClasse criarClasse(Scanner sc, List<GeradorClasse> classes) {
        int resposta = 1;
        boolean encontrado = false;
        String nomeClasse;

        System.out.println("*---------- Classe ----------*");

        System.out.print("Digite o nome: ");
        nomeClasse = sc.next();

        for(GeradorClasse classe : classes)
            if(classe.getNomeClasse().equals(nomeClasse)) {
                System.out.println("ERRO: Ja existe uma classe com esse nome!");
                return null;
            }

        this.nomeClasse =  nomeClasse;
        System.out.print("Digite o nome do pacote: ");
        nomeClasse = sc.next();
        this.nomePacote = nomeClasse;

        do {
            GeradorAtributo atributo = new GeradorAtributo();
            atributo.criarAtributo(sc);

            Iterator<GeradorAtributo> it = atributos.iterator();

            while(it.hasNext())
                if(it.next().getNome().equals(atributo.getNome())){
                    System.out.println("ERRO: Ja existe um atributo com esse nome!");
                    encontrado = true;
                    break;
                }

            if(!encontrado)
                atributos.add(atributo);

            System.out.println("Deseja criar mais um atributo? (Digite 0 para encerrar e 1 para criar)");
            resposta = sc.nextInt();

        } while(resposta != 0);

        System.out.println("A classe " + this.getNomeClasse() + " foi criada com sucesso!");

        return this;
    }

    @Override
    public String toString() {
        return "GeradorClasse{" +
                "nomeClasse='" + nomeClasse + '\'' +
                ", nomePacote='" + nomePacote + '\'' +
                ", atributos=" + atributos +
                '}';
    }
}
