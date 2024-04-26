package Model;

import DAO.ConnectionDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Efeito {
    String nomeID;
    int danoExtra;
    int velocidadeExtra;

    public Efeito(String nomeID, int danoExtra, int velocidadeExtra){
        this.nomeID = nomeID;
        this.danoExtra = danoExtra;
        this.velocidadeExtra = velocidadeExtra;

    }

    public String getNome() {
        return nomeID;
    }

    public Integer getDano() {
        return danoExtra;
    }

    public Integer getVelocidade() {
        return velocidadeExtra;
    }

}