package DAO;

import Model.Efeito;

import java.sql.SQLException;
import java.util.ArrayList;

public class EfeitoDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertEfeito(Efeito efeito) {

        connectToDB();

        String sql = "INSERT INTO Efeito (nomeID, danoExtra, velocidadeExtra) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, efeito.getNome());
            pst.setInt(2, efeito.getDano());
            pst.setInt(3, efeito.getVelocidade());
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
    public boolean updateEfeitoNome(int danoExtra, String nome) {
        connectToDB();
        String sql = "UPDATE Armas SET nomeID=? where danoExtra=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setInt(2, danoExtra);
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
    public boolean deleteEfeito(int danoExtra) {
        connectToDB();
        String sql = "DELETE FROM Armas where danoExtra=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, danoExtra);
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
    public ArrayList<Efeito> selectEfeito() {
        ArrayList<Efeito> efeitos = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Efeitos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de efeitos: ");

            while (rs.next()) {

                Efeito efeitoAux = new Efeito(rs.getString("nomeID"), rs.getInt("danoExtra"), rs.getInt("velocidadeExtra"));

                System.out.println("nome = " + efeitoAux.getNome());
                System.out.println("dano extra = " + efeitoAux.getDano());
                System.out.println("velocidade extra = " + efeitoAux.getVelocidade());
                System.out.println("--------------------------------");

                efeitos.add(efeitoAux);
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
        return efeitos;
    }
}
