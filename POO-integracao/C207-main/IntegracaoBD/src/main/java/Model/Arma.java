package Model;

import DAO.ConnectionDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Arma {
    String nomeID;
    int dano;
    int alcance;
    int velocidade;
    int knockback;

    public Arma(String nomeID, int dano, int alcance, int velocidade, int knockback){
        this.nomeID = nomeID;
        this.dano = dano;
        this.alcance = alcance;
        this.velocidade = velocidade;
        this.knockback = knockback;

    }

    public String getNome() {
        return nomeID;
    }

    public Integer getDano() {
        return dano;
    }

    public Integer getAlcance() {
        return alcance;
    }

    public Integer getVelocidade() {
        return velocidade;
    }

    public Integer getKnockback() {
        return knockback;
    }


}