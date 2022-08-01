package br.unesp.rc.Modelos;

import java.util.ArrayList;
import java.util.List;

public class GeradorProjeto {
    private String nomeProjeto;
    List<GeradorClasse> classes = new ArrayList<>();
    List<GeradorRelacionamento> relacionamentos = new ArrayList<>();

    public GeradorProjeto() {}

    public GeradorProjeto(String nomeProjeto, List<GeradorClasse> classes, List<GeradorRelacionamento> relacionamentos) {
        this.nomeProjeto = nomeProjeto;
        this.classes = classes;
        this.relacionamentos = relacionamentos;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }


    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto.trim().replaceAll(" ", "");
    }

    public List<GeradorClasse> getClasses() {
        return classes;
    }

    public void setClasses(List<GeradorClasse> classes) {
        this.classes = classes;
    }

    public List<GeradorRelacionamento> getRelacionamentos() {
        return relacionamentos;
    }

    public void setRelacionamentos(List<GeradorRelacionamento> relacionamentos) {
        this.relacionamentos = relacionamentos;
    }

    public String verificarNomeProjeto(String nomeProjeto) {
        StringBuilder sb = new StringBuilder();
        nomeProjeto = nomeProjeto.trim().toLowerCase();
        String split[] = nomeProjeto.split(" ");

        for (String s : split) {
            sb.append(Character.toUpperCase(s.charAt(0)));
            sb.append(s.substring(1));
        }

        return sb.toString();
    }

    public void imprimir() {
        System.out.println("________________________________________");
        System.out.println("Projeto: " + this.nomeProjeto);

        for(GeradorClasse c : this.classes) {
            System.out.println("________________________________________");
            System.out.println("Pacote: " + c.getNomePacote() + "\nClasse: " + c.getNomeClasse());

            for(GeradorAtributo t : c.getAtributos())
                System.out.println("  |- " + t.getModificador() + " " + t.getTipo() + " " + t.getNome() );

            System.out.println("________________________________________\n");
        }
    }

    @Override
    public String toString() {
        return "GeradorProjeto{" +
                "nomeProjeto='" + nomeProjeto + '\'' +
                ", classes=" + classes +
                ", relacionamentos=" + relacionamentos +
                '}';
    }
}
