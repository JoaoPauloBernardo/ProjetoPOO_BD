package Model;

import DAO.ConnectionDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Item {
    String nome;
    String efeito;
    int consumivel;

    public Item(String nome, String efeito, int consumivel){
        this.nome = nome;
        this.efeito = efeito;
        this.consumivel = consumivel;

    }

    public String getNome() {
        return nome;
    }

    public String getEfeito() {
        return efeito;
    }

    public Integer getConsumivel() {
        return consumivel;
    }

}