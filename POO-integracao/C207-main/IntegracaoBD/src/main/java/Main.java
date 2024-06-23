import DAO.HabilidadeDAO;
import DAO.PersonagemDAO;
import Model.Habilidade;
import Model.Item;
import Model.Personagem;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int reset;
        int begin;
        PersonagemDAO personagemDAO = new PersonagemDAO();
        Scanner entrada = new Scanner(System.in);



        // introducao
        System.out.println("Olhe, eu nao queria ser um meio-sangue");
        System.out.println("Se você abriu esse jogo porque acha que pode ser um, meu conselho é o seguinte:" +
                " feche este jogo agora mesmo. Acredite em qualquer mentira que sua mãe ou seu pai lhe contou" + "\n" +
                " sobre seu nascimento, e tente levar uma vida normal.");
        System.out.println("Ser um meio sangue é perigoso. É assustador. Na maioria das vezes, acaba de um " +
                "jeito penosos e detestável.");
        System.out.println("Se você é uma criança normal, que esta jogando porque acha divertido esse tipo de" +
                " jogo, ótimo. Continue jogando. Eu o invejo por ser capaz de acreditar que nada disso aconteceu.");
        System.out.println();
        System.out.println();

        // começo do jogo
        do{

            int resposta;
            int sorte;
            int attack;
            Random rand = new Random();

            System.out.println("Deseja iniciar o jogo? Digite 1 para continuar e 0 para fechar o jogo.");
            begin = entrada.nextInt();

            if(begin == 1){

                System.out.println();
                System.out.println("Nao diga que eu nao avisei.");

                // criando o personagem
                System.out.println("Digite o nome do seu personagem: ");
                String jogador_nome = entrada.next();

                // adicionando o personagem do jogador
                Personagem jogador = new Personagem(jogador_nome, "Meio-Sangue", 1, 100, 5);
                personagemDAO.insertPersonagem(jogador);

                System.out.println("---------------------------------------------------------------------------");
                personagemDAO.selectPersonagem();
                System.out.println("---------------------------------------------------------------------------");

                Personagem monstro = new Personagem("Ciclopis", "monstro", 3, 200, 7);
                personagemDAO.insertPersonagem(monstro);

                // introdução de como funciona o jogo
                System.out.println();
                System.out.println();
                System.out.println("O seu objetivo é chegar ao acampamento meio-sangue com vida.");
                System.out.println("Relaxa que você não está totalmente sozinho nessa, o acampamento tem " +
                        "protetores espalhados por ai para evitar que você não tenha uma morte bem violenta.");
                System.out.println("Quem sabe você não tem sorte e se encontra com um?");
                System.out.println();
                System.out.println("Boa viagem. Estou te deixando um presentinho, você vai precisar.");

                do{
                    System.out.println("Ready(1)?");
                    resposta = entrada.nextInt();
                }while(resposta != 1);

                System.out.println("Voce acorda com a claridade no meio de uma floresta." +
                        "Tudo o que sabe é que a cada segundo que fica parado, você está em perigo.");

                System.out.println("Voce encontra uma arvore alta e cheia de folhas, parece um bom lugar para se " +
                            "esconder.");
                System.out.println();
                System.out.println("1 - Voce escolhe subir na arvore, mas sera que você é bom nisso?");
                System.out.println();
                System.out.println("2 - Ficar no chão e investigar os conteudos da sua mochila.");

                do{
                    System.out.println("1 ou 2?");
                    resposta = entrada.nextInt();
                }while(resposta != 1 && resposta != 2);

                // ESCOLHER SUBIR NA ARVORE
                if(resposta == 1) {

                    System.out.println("Um d20 decidira se você consegue subir na arvóre sem danos.");
                    sorte = rand.nextInt(20);

                    if (sorte >= 5) {
                        System.out.println("Parabéns, você escalou com sucesso e ganhou a habilidade de escalar. Com tantos galhos e folhas a " +
                                "sua volta nínguem vai conseguir te ver aqui.");

                        /*Habilidade escalar = new Habilidade("Escalar", "destreza", 0, 2,
                                0, "Subir mais rapido e sem precisar de sorte em arvores");

                        HabilidadeDAO.insertHabilidade(escalar);*/
                    }

                    else{
                        System.out.println("Parece que você se machucou durante a subida. Tome cuidado, voce " +
                                "levou " + sorte + " de dano.");

                        personagemDAO.updatePersonagemVida(jogador_nome, jogador.getVida() - sorte);
                        personagemDAO.selectPersonagem();
                    }

                    System.out.println("Em cima da árvore, voce pega para analisar os conteudos da sua mochila.");
                    System.out.println("Voce encontrou uma espada! Ela foi adicionada ao seu inventário. Agora voce tem +10 de ataque permanente!");

                    Item espada = new Item("Morte costas", "corte afiado", 0);

                    System.out.println();
                    System.out.println("Vai ficando de noite, voce tem que decidir...");
                    System.out.println();
                    System.out.println("1 - Descer da árvore e continuar buscando o acampamento.");
                    System.out.println();
                    System.out.println("2 - Dormir");

                    do{
                        System.out.println("1 ou 2?");
                        resposta = entrada.nextInt();
                    }while(resposta != 1 && resposta != 2);

                    // ESCOLHER DESCER
                    if(resposta == 1){
                        System.out.println("Voce desce da arvore e comeca a seguir a trilha no chao. No caminho, voce " +
                                " ve um ser metade humano e metade bode, ele diz ser um satiro, " + "\n" +
                                " e eh um dos protetores do acampamento, ele vai te guiar ate la.");
                        System.out.println();
                        System.out.println("Voces estao perto, mas acabam encontrando um ciclope.");

                        System.out.println("Nao ha outra escolha a nao ser lutar!!");

                        do{
                            do{
                                System.out.println("Atacar? Sim(1) Nao(2)");
                                resposta = entrada.nextInt();
                            }while(resposta != 1 && resposta != 2);

                            if(resposta == 1){
                                sorte = rand.nextInt(10);
                                attack = sorte * jogador.getForca() + 10;

                                System.out.println("Voce deu um dano de " + attack);
                                personagemDAO.updatePersonagemVida("monstro", monstro.getVida() - attack);

                                if(monstro.getVida() <= 0){
                                    break;
                                }
                            }

                            // ataque do satiro
                            personagemDAO.updatePersonagemVida("monstro", monstro.getVida() - 15);
                            System.out.println("satiro deu um dano de 15");

                            // ataque do monstro
                            sorte = rand.nextInt(10);
                            attack = sorte * monstro.getForca();

                            System.out.println("ciclope deu um dano de " + attack);

                            personagemDAO.updatePersonagemVida(jogador_nome, jogador.getVida() -attack);

                        }while(monstro.getVida() > 0 && jogador.getVida() > 0);

                        if(jogador.getVida() <= 0){
                            System.out.println("Voce morreu, eu te avisei que teria um fim brutal.");
                        }

                       else{
                            System.out.println("Voce derrotou o monstro e subiu 1 nivel!! Parabens!");
                            System.out.println("O satiro te guia ate o acampamento e agora voce podera ser bem treinado.");
                            System.out.println();
                            personagemDAO.updatePersonagemNivel(jogador_nome, 2);
                        }

                    }

                    // ESCOLHER DORMIR
                    else{

                        System.out.println("Voce dorme, porem apesar de estar fora de vista, monstros tambem sao atraido pelo cheiro.");
                        System.out.println("Um ciclopes te achou, agora voce tem que lutar com ele pra sobreviver.");

                        do{
                            do{
                                System.out.println("Atacar? Sim(1) Nao(2)");
                                resposta = entrada.nextInt();
                            }while(resposta != 1 && resposta != 2);

                            if(resposta == 1){
                                sorte = rand.nextInt(10);

                                attack = sorte * jogador.getForca() + 10;
                                System.out.println("Voce deu um dano de " + attack);
                                personagemDAO.updatePersonagemVida("monstro", monstro.getVida() - attack);

                                if(monstro.getVida() <= 0){
                                    break;
                                }
                            }

                            // ataque do monstro
                            sorte = rand.nextInt(10);

                            attack = sorte * monstro.getForca();
                            System.out.println("ciclope deu um dano de " + attack);

                            personagemDAO.updatePersonagemVida(jogador_nome, jogador.getVida() - attack);

                        }while(monstro.getVida() > 0 && jogador.getVida() > 0);

                        if(jogador.getVida() <= 0){
                            System.out.println("Voce morreu, eu te avisei que teria um fim brutal.");
                            break;
                        }

                        else{
                            System.out.println("Voce derrotou o monstro sozinho e subiu 1 nivel!! Parabens!");
                            System.out.println("Um satiro te encontra e te leva para o acampamento e agora voce pode ser bem treinado.");
                            System.out.println();
                            System.out.println();
                            personagemDAO.updatePersonagemNivel(jogador_nome, 2);
                        }
                    }

                }

                // ESCOLHER FICAR NO CHÃO
                else{

                    System.out.println("Voce escolheu ficar no chao.");
                    System.out.println("O que vai fazer agora?");
                    System.out.println();
                    System.out.println("1 - Correr pela trilha que pode levar ao acampamento.");
                    System.out.println();
                    System.out.println("2 - Analisar o que tem dentro da sua mochila.");

                    do{
                        System.out.println("1 ou 2?");
                        resposta = entrada.nextInt();
                    }while(resposta != 1 && resposta != 2);


                    // ESCOLHER CORRER PELA TRILHA
                    if(resposta == 1){

                        System.out.println("Voce comeca a seguir a trilha no chao. No caminho, voce " +
                                " ve um ser metade humano e metade bode, ele diz ser um satiro, " + "\n" +
                                " e eh um dos protetores do acampamento, ele vai te guiar ate la.");
                        System.out.println();
                        System.out.println("Voces estao perto, mas acabam encontrando um ciclope.");

                        System.out.println("Nao ha outra escolha a nao ser lutar!!");

                        do{
                            do{
                                System.out.println("Atacar? Sim(1) Nao(2)");
                                resposta = entrada.nextInt();
                            }while(resposta != 1 && resposta != 2);

                            if(resposta == 1){
                                sorte = rand.nextInt(10);

                                attack = sorte * jogador.getForca();

                                System.out.println("Voce deu um dano de " + attack);
                                personagemDAO.updatePersonagemVida("monstro", monstro.getVida() - attack);

                                if(monstro.getVida() <= 0){
                                    break;
                                }
                            }

                            // ataque do satiro
                            personagemDAO.updatePersonagemVida("monstro", monstro.getVida() - 15);
                            System.out.println("satiro deu um dano de 15");

                            // ataque do monstro
                            sorte = rand.nextInt(10);

                            attack = sorte * monstro.getForca();

                            System.out.println("ciclope deu um dano de " + attack);

                            personagemDAO.updatePersonagemVida(jogador_nome, jogador.getVida() - attack);

                        }while(monstro.getVida() > 0 && jogador.getVida() > 0);

                        if(jogador.getVida() <= 0){
                            System.out.println("Voce morreu, eu te avisei que teria um fim brutal.");
                            break;
                        }

                        else{
                            System.out.println("Voce derrotou o monstro e subiu 1 nivel!! Parabens!");
                            System.out.println("O satiro te guia ate o acampamento e agora voce podera ser bem treinado.");
                            System.out.println();
                            personagemDAO.updatePersonagemNivel(jogador_nome, 2);
                        }

                    }

                    // ESCOLHER ANALISAR A MOCHILA
                    else{

                        System.out.println("Voce pega para analisar os conteudos da sua mochila.");
                        System.out.println("Voce encontrou uma espada! Ela foi adicionada ao seu inventário. Agora voce tem +10 de ataque permanente!");

                        Item espada = new Item("Morte costas", "corte afiado", 0);

                        System.out.println("POrem... Um ciclopes te achou, agora voce tem que lutar com ele pra sobreviver.");

                        do{
                            do{
                                System.out.println("Atacar? Sim(1) Nao(2)");
                                resposta = entrada.nextInt();
                            }while(resposta != 1 && resposta != 2);

                            if(resposta == 1){
                                sorte = rand.nextInt(10);

                                attack = sorte * jogador.getForca() + 10;

                                System.out.println("Voce deu um dano de " + attack);
                                personagemDAO.updatePersonagemVida("monstro", monstro.getVida() - attack);

                                if(monstro.getVida() <= 0){
                                    break;
                                }
                            }

                            // ataque do monstro
                            sorte = rand.nextInt(10);
                            attack = sorte * monstro.getForca();

                            System.out.println("ciclope deu um dano de " + attack);

                            personagemDAO.updatePersonagemVida(jogador_nome, jogador.getVida() - attack);

                        }while(monstro.getVida() > 0 && jogador.getVida() > 0);

                        if(jogador.getVida() <= 0){
                            System.out.println("Voce morreu, eu te avisei que teria um fim brutal.");
                            break;
                        }

                        else{
                            System.out.println("Voce derrotou o monstro sozinho e subiu 1 nivel!! Parabens!");
                            System.out.println("Um satiro te encontra e te leva para o acampamento e agora voce pode ser bem treinado.");
                            System.out.println();
                            System.out.println();
                            personagemDAO.updatePersonagemNivel(jogador_nome, 2);
                        }

                    }
                }

                personagemDAO.deletePersonagem(3);

                // jogar novamente
                do{
                    System.out.println("Deseja jogar novamente? Sim(1) ou Não(0)");
                    reset= entrada.nextInt();
                }while(reset != 1 && reset != 0);

            }

            else {

                System.out.println("Boa escolha");
                reset = 0;

            }

        }while(reset == 1);

        System.out.println("Game Over.");

    }
}
