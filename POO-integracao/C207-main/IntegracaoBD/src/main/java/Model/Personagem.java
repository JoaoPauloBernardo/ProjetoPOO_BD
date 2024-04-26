package Model;

import DAO.ConnectionDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Personagem {
    String nome;
    String tipo;
    int nivel;
    int vida;
    int forca;

    public Personagem(String nome, String tipo, int nivel, int vida, int forca){
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.vida = vida;
        this.forca = forca;

    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getNivel() {
        return nivel;
    }

    public Integer getVida() {
        return vida;
    }

    public Integer getForca() {
        return forca;
    }


}