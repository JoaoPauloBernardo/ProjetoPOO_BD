package DAO;

import Model.Habilidade;

import java.sql.SQLException;
import java.util.ArrayList;

public class HabilidadeDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertHabilidade(Habilidade habilidade) {

        connectToDB();

        String sql = "INSERT INTO Habilidade (nome, tipo, efeito, dano, velocidade, cooldown) values(?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,habilidade.getNome());
            pst.setString(2,habilidade.getTipo());
            pst.setString(3, habilidade.getEfeito());
            pst.setInt(4, habilidade.getDano());
            pst.setInt(5, habilidade.getVelocidade());
            pst.setInt(6, habilidade.getCooldown());
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
    public boolean updateHabilidadeNome(int dano, String nome) {
        connectToDB();
        String sql = "UPDATE Habilidades SET nome=? where dano=?";
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
    public boolean deleteHabilidade(int dano) {
        connectToDB();
        String sql = "DELETE FROM Habilidades where dano=?";
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
    public ArrayList<Habilidade> selectHabilidade() {
        ArrayList<Habilidade> habilidades = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Habilidades";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de habilidades: ");

            while (rs.next()) {

                Habilidade habilidadeAux = new Habilidade(rs.getString("tipo"), rs.getString("nome"), rs.getInt("dano"), rs.getInt("velocidade"), rs.getInt("cooldown"), rs.getString("efeito"));

                System.out.println("efeito = " + habilidadeAux.getEfeito());
                System.out.println("nome = " + habilidadeAux.getNome());
                System.out.println("tipo = " + habilidadeAux.getTipo());
                System.out.println("dano = " + habilidadeAux.getDano());
                System.out.println("cooldown = " + habilidadeAux.getCooldown());
                System.out.println("velocidade = " + habilidadeAux.getVelocidade());
                System.out.println("--------------------------------");

                habilidades.add(habilidadeAux);
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
        return habilidades;
    }
}
