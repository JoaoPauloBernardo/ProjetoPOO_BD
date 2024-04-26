package DAO;

import Model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertItem(Item item) {

        connectToDB();

        String sql = "INSERT INTO Item (nome, efeito, consumivel) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,item.getNome());
            pst.setString(2,item.getEfeito());
            pst.setInt(3, item.getConsumivel());
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
    public boolean updateItemNome(int consumivel, String nome) {
        connectToDB();
        String sql = "UPDATE Itens SET nome=? where consumivel=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setInt(2, consumivel);
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
    public boolean deleteItem(int consumivel) {
        connectToDB();
        String sql = "DELETE FROM Personagens where consumivel=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, consumivel);
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
    public ArrayList<Item> selectItens() {
        ArrayList<Item> itens = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Itens";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de itens: ");

            while (rs.next()) {

                Item itemAux = new Item(rs.getString("nome"), rs.getString("efeito"), rs.getInt("consumivel"));

                System.out.println("nome = " + itemAux.getNome());
                System.out.println("efeito = " + itemAux.getEfeito());
                System.out.println("Cargas Consumíveis = " + itemAux.getConsumivel());
                System.out.println("--------------------------------");

                itens.add(itemAux);
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
        return itens;
    }
}
