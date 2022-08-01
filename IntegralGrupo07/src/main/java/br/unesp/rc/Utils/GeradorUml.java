package br.unesp.rc.Utils;

import br.unesp.rc.Modelos.GeradorAtributo;
import br.unesp.rc.Modelos.GeradorClasse;
import br.unesp.rc.Modelos.GeradorProjeto;
import br.unesp.rc.Modelos.GeradorRelacionamento;

import java.util.List;
import java.util.Scanner;

public class GeradorUml {

    public void gerarUml(GeradorProjeto projeto, List<GeradorClasse> classes, List<GeradorRelacionamento> relacs, Scanner sc, String caminho) {
        StringBuilder uml = new StringBuilder();

        char c;
        c = projeto.getNomeProjeto().charAt(0);
        uml.append("@startuml\ntitle "); //nome projeto0

        uml.append(projeto.getNomeProjeto()).append(("\n"));
        uml.append(gerarUmlClasseAtributo(projeto, classes, relacs));
        uml.append(gerarUmlRelacionamento(relacs));
        uml.append("@enduml");

        if(exportarUml(projeto, uml.toString(), sc, caminho)) {
            System.out.println("UML exportado com sucesso!");
            System.out.println(uml.toString());
        }
        else
            System.out.println("ERRO: Ocorreu um erro ao exportar o UML. Verifique a mensagem de erro.");
    }

    private StringBuilder gerarUmlClasseAtributo(GeradorProjeto projeto, List<GeradorClasse> classes, List<GeradorRelacionamento> relacs) {
        StringBuilder uml = new StringBuilder();
        char c;

        for(GeradorClasse classe: classes) {
            c = classe.getNomeClasse().charAt(0);
            uml.append("class ").append(Character.toUpperCase(c)).append(classe.getNomeClasse().substring(1)).append(" {");
            for(GeradorAtributo a : classe.getAtributos()) {
                switch (a.getModificador()) {
                    case "private" -> c = '-';
                    case "protected" -> c = '#';
                    case "public" -> c = '+';
                }

                //Atributos
                uml.append("\n\t").append(c).append(" ").append(a.getTipo()).append(" ").append(a.getNome());
            }

            uml.append("\n\t");

            //Construtor da classe
            c = classe.getNomeClasse().charAt(0);
            uml.append("+ ").append(Character.toUpperCase(c)).append(classe.getNomeClasse().substring(1)).append("()");
            uml.append("\n}\n");
        }

        return uml;
    }

    private StringBuilder gerarUmlRelacionamento(List<GeradorRelacionamento> relacionamentos) {
        StringBuilder uml = new StringBuilder();

        for(GeradorRelacionamento a : relacionamentos) {

            uml.append(a.getClasse1()).append(" ");


            uml.append('"').append(a.getMultiplicidade1()).append('"');

            switch (a.getRelacionamento()) {
                case "inheritance" -> uml.append(" <|-- ");
                case "composition" -> uml.append(" *-- ");
                case "aggregation" -> uml.append(" o-- ");
                case "none" -> uml.append(" -- ");
            }


            uml.append(" ").append('"').append(a.getMultiplicidade2()).append('"').append(" ");
            uml.append(a.getClasse2()).append(" ").append("\n");
        }
        return uml;
    }

    private boolean exportarUml(GeradorProjeto projeto, String uml, Scanner sc, String caminho) {
        return ArquivoUtils.salvar(caminho + "/", projeto.getNomeProjeto() + ".puml", uml);
    }
}
