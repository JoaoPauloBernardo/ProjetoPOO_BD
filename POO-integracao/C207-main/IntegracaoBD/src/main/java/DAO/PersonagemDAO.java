package DAO;

import Model.Personagem;

import java.sql.SQLException;
import java.util.ArrayList;

public class PersonagemDAO extends ConnectionDAO{

        //DAO - Data Access Object
        boolean sucesso = false; //Para saber se funcionou

        //INSERT
        public boolean insertPersonagem(Personagem personagem) {

            connectToDB();

            String sql = "INSERT INTO Personagem (tipo, nome, nivel, vida, forca) values(?,?,?,?,?)";
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1,personagem.getTipo());
                pst.setString(2,personagem.getNome());
                pst.setInt(3, personagem.getNivel());
                pst.setInt(4, personagem.getVida());
                pst.setInt(5, personagem.getForca());
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
        public boolean updatePersonagemNome(int nivel, String nome) {
            connectToDB();
            String sql = "UPDATE Personagens SET nome=? where nivel=?";
            try {
                pst = con.prepareStatement(sql);              pst.setString(1, nome);
                pst.setInt(2, nivel);
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
        public boolean deletePersonagem(int nivel) {
            connectToDB();
            String sql = "DELETE FROM Personagens where nivel=?";
            try {
                pst = con.prepareStatement(sql);
                pst.setInt(1, nivel);
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
        public ArrayList<Personagem> selectPersonagem() {
            ArrayList<Personagem> personagens = new ArrayList<>();
            connectToDB();
            String sql = "SELECT * FROM Personagens";

            try {
                st = con.createStatement();
                rs = st.executeQuery(sql);

                System.out.println("Lista de personagens: ");

                while (rs.next()) {

                    Personagem personagemAux = new Personagem(rs.getString("tipo"), rs.getString("nome"), rs.getInt("nivel"), rs.getInt("vida"), rs.getInt("forca"));

                    System.out.println("nivel = " + personagemAux.getNivel());
                    System.out.println("nome = " + personagemAux.getNome());
                    System.out.println("tipo = " + personagemAux.getTipo());
                    System.out.println("vida = " + personagemAux.getVida());
                    System.out.println("forca = " + personagemAux.getForca());
                    System.out.println("--------------------------------");

                    personagens.add(personagemAux);
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
            return personagens;
        }
    }
