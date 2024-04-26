import DAO.HabilidadeDAO;
import DAO.PersonagemDAO;
import Model.Habilidade;
import Model.Personagem;

public class Main {

    public static void main(String[] args) {

        PersonagemDAO personagemDAO = new PersonagemDAO();

        Personagem p1 = new Personagem("Glauco","Barbaro",23,3000,60);
        Personagem p2 = new Personagem( "China", "Mago", 22, 1500,55);

        personagemDAO.insertPersonagem(p1);
        personagemDAO.insertPersonagem(p2);

        personagemDAO.selectPersonagem();

        personagemDAO.updatePersonagemNome(22, "Glauco Maligno");

        personagemDAO.selectPersonagem();

        personagemDAO.deletePersonagem(23);

        HabilidadeDAO habilidadeDAO = new HabilidadeDAO();

        Habilidade h1 = new Habilidade("Tiro de fogo","fogo",60,30,12,"Coloca o alvo em chamas");
        Habilidade h2 = new Habilidade( "Passos leves", "Vento", 0, 10000,15,"deixa seus passos inaudíveis e rápidos");

        habilidadeDAO.insertHabilidade(h1);
        habilidadeDAO.insertHabilidade(h2);

        habilidadeDAO.selectHabilidade();

        habilidadeDAO.updateHabilidadeNome(0, "Passos levíssimos");

        habilidadeDAO.selectHabilidade();

        habilidadeDAO.deleteHabilidade(60);



    }
}
