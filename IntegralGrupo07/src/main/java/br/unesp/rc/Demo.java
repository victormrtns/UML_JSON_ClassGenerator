package br.unesp.rc;

import br.unesp.rc.Modelos.GeradorClasse;
import br.unesp.rc.Modelos.GeradorProjeto;
import br.unesp.rc.Modelos.GeradorRelacionamento;
import br.unesp.rc.Utils.ArquivoUtils;
import br.unesp.rc.Utils.GeradorUml;
import br.unesp.rc.Utils.GsonUtils;

import java.util.Scanner;

/**
 *
 * @author Prof. Frank J. Affonso
 */
public class Demo {
    final static String prefix = "IntegralGrupo07\\src\\output\\";
    //final static String CAMINHO_APRESENTACAO = "C:\\Users\\aluno\\Desktop\\" + prefix;
    final static String CAMINHO_TESTE = "C:\\Users\\steam\\Desktop\\POO\\" + prefix;
    static String CAMINHO = CAMINHO_TESTE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GeradorProjeto genProj = new GeradorProjeto();
        String json;
        int op;

        //Menu
        System.out.println("*------------------------------------*");
        System.out.println("*-------- GERADOR DE PROJETO --------*");
        System.out.println("*------------------------------------*\n");

        do {
            System.out.println("*____________________________________*");
            System.out.println("|  Opcao      | Funcao                |");
            System.out.println("|    1.       | Criar/alterar projeto |");
            System.out.println("|    2.       | Exportar projeto      |");
            System.out.println("|    3.       | Importar projeto      |");
            System.out.println("|    4.       | Gerar UML             |");
            System.out.println("|    5.       | Imprimir              |");
            System.out.println("|    0.       | Sair                  |");
            System.out.println("*------------------------------------*");
            System.out.print("Digite a opcao: ");
            op = sc.nextInt();

            switch(op) {
                case 1:
                    int op2;

                    if(genProj.getNomeProjeto() == null) {
                        System.out.print("Digite qual sera o nome do projeto: ");
                        sc.nextLine();
                        genProj.setNomeProjeto(genProj.verificarNomeProjeto(sc.nextLine()));
                        System.out.println(genProj.getNomeProjeto());
                    }

                    do {
                        System.out.println("*____________________________________*");
                        System.out.println("|  Opcao      | Funcao               |");
                        System.out.println("|    1.       | Criar classe         |");
                        System.out.println("|    2.       | Criar relacionamento |");
                        System.out.println("|    3.       | Retornar ao menu     |");
                        System.out.println("*------------------------------------*");
                        System.out.print("Digite a opcao: ");
                        op2 = sc.nextInt();

                        if(op2 == 1) {
                            GeradorClasse novaClasse = new GeradorClasse();
                            novaClasse.criarClasse(sc, genProj.getClasses());
                            genProj.getClasses().add(novaClasse);
                            System.out.println(novaClasse.toString());
                        } else if(op2 == 2) {
                            GeradorRelacionamento novoRelacionamento = new GeradorRelacionamento();
                            novoRelacionamento = novoRelacionamento.criarRelacionamento(sc, genProj.getClasses());

                            if(novoRelacionamento != null) {
                                System.out.println("Relacionamento feito com sucesso!\n" + novoRelacionamento.toString());
                                genProj.getRelacionamentos().add(novoRelacionamento);
                            }
                        }
                    } while(op2 != 3);
                    break;
                case 2:
                    if(genProj.getNomeProjeto() == null) {
                        System.out.println("ERRO: Nenhum projeto existente no gerador! Crie ou importe um projeto.");
                        break;
                    }

                    json = GsonUtils.objetoToXML(genProj);


                    if(ArquivoUtils.salvar(CAMINHO, genProj.getNomeProjeto() + ".json", json)) {
                        System.out.println("O projeto " + genProj.getNomeProjeto() + " foi exportado com sucesso!");
                        System.out.println("Conteudo do JSON exportado");
                        System.out.println(json);
                    }
                    else
                        System.out.println("ERRO: Nao foi possivel exportar o projeto. Verifique a mensagem de erro acima.");

                    break;
                case 3:
                    String arq;
                    System.out.print("Digite o nome do arquivo (.json): ");
                    arq = sc.next();
                    json = ArquivoUtils.leitura(CAMINHO + arq);

                    if(json.isEmpty()) {
                        System.out.println("ERRO: Nao foi possivel importar o projeto. Verifique a mensagem de erro acima.");
                        break;
                    }

                    genProj = (GeradorProjeto) GsonUtils.xmlToObjeto(json, GeradorProjeto.class);
                    System.out.println(genProj.toString());
                    System.out.println("O projeto " + genProj.getNomeProjeto() + " foi importado com sucesso!");
                    break;
                case 4:
                    GeradorUml geradorUml = new GeradorUml();
                    geradorUml.gerarUml(genProj, genProj.getClasses(), genProj.getRelacionamentos(), sc, CAMINHO);
                    break;
                case 5:
                    genProj.imprimir();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        } while(op != 0);
    }
}
