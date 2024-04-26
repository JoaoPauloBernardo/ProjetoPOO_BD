package DAO;

import Model.Arma;

import java.sql.SQLException;
import java.util.ArrayList;

public class ArmaDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertArma(Arma arma) {

        connectToDB();

        String sql = "INSERT INTO Arma (nomeID, dano, alcance, velocidade, knockback) values(?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, arma.getNome());
            pst.setInt(2, arma.getDano());
            pst.setInt(3, arma.getAlcance());
            pst.setInt(4, arma.getVelocidade());
            pst.setInt(5, arma.getKnockback());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //UPDATE
    public boolean updateArmaNome(int dano, String nome) {
        connectToDB();
        String sql = "UPDATE Armas SET nomeID=? where dano=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setInt(2, dano);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //DELETE
    public boolean deleteArma(int dano) {
        connectToDB();
        String sql = "DELETE FROM Armas where dano=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, dano);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //SELECT
    public ArrayList<Arma> selectArma() {
        ArrayList<Arma> armas = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Armas";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de armas: ");

            while (rs.next()) {

                Arma armaAux = new Arma(rs.getString("nomeID"), rs.getInt("dano"), rs.getInt("alcance"), rs.getInt("velocidade"), rs.getInt("knockback"));

                System.out.println("nome = " + armaAux.getNome());
                System.out.println("dano = " + armaAux.getDano());
                System.out.println("alcance = " + armaAux.getAlcance());
                System.out.println("velocidade = " + armaAux.getVelocidade());
                System.out.println("knockback = " + armaAux.getKnockback());
                System.out.println("--------------------------------");

                armas.add(armaAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return armas;
    }
}
