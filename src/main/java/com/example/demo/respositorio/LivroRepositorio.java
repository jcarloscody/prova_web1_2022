package com.example.demo.respositorio;


import com.example.demo.entidades.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepositorio {

    public static  void criarTabela(){
        System.out.println("CRIANDO TABELA");
        PreparedStatement st = null;
        Connection conn = null;

        try {
            System.out.println("CRIANDO TABELA - TRY");
            conn = Banco.getConnection();//CONECTANDO
            st = conn.prepareStatement(
                    "CREATE TABLE `livro` (\n" +
                            "  `idlivro` int NOT NULL AUTO_INCREMENT,\n" +
                            "  `nome` varchar(45) DEFAULT NULL,\n" +
                            "  `autor` varchar(45) DEFAULT NULL,\n" +
                            "  `livro` text,\n" +
                            "  `ano` varchar(45) DEFAULT NULL,\n" +
                            "  `numero_edicao` varchar(45) DEFAULT NULL,\n" +
                            "  `paginas` int DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`idlivro`)\n" +
                            ");");
            st.executeUpdate();
        }catch ( Exception e){

        }

    }

    public static List<Livro> buscarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            List<Livro> livros = new ArrayList<>() ;

            conn = Banco.getConnection();//CONECTANDO
            st = conn.prepareStatement(
                    "SELECT * FROM livro");
            rs = st.executeQuery();
            while (rs.next()) {
                System.out.println("--------------------------------");

                Livro obj = new Livro();
                obj.setIdlivro(rs.getInt("idlivro"));
                obj.setNome(rs.getString("nome"));
                obj.setLivro(rs.getString("livro"));
                obj.setAutor(rs.getString("autor"));
                obj.setNumero_edicao(rs.getString("numero_edicao"));
                obj.setAno(rs.getString("ano"));
                obj.setPaginas(rs.getInt("paginas"));
                System.out.println(obj);
                livros.add(obj);
            }

            return  livros;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            Banco.closeStatement(st);
            Banco.closeResultSet(rs);
        }
    }


   // public static void inserir (String nome, String livro, String autor, String numero_edicao, String ano, Integer paginas){
   public static void inserir (Livro livro){
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = Banco.getConnection();//CONECTANDO

            // EXAMPLE 1:
            statement = conn.prepareStatement(//vai receber o comando SQL e ficar preparado para executar
                    "INSERT INTO livro "
                            + "(nome, autor, livro, ano, numero_edicao, paginas)"
                            + "VALUES "
                            + "(?, ?, ?, ?, ?, ?)", //4 posicoes
                    Statement.RETURN_GENERATED_KEYS);//SOBRECARGA RECEBE OUTRO PARAMETRO

            statement.setString(1, livro.getNome());//tipo string, posicao 1
            statement.setString(2, livro.getAutor());
            statement.setString(3, livro.getLivro());
            statement.setString(4, livro.getAno());
            statement.setString(5, livro.getNumero_edicao());
            statement.setInt(6, livro.getPaginas());
            int rowsAffected = statement.executeUpdate();//executa sql e retorna um numero inteiro indicando quantas linhas foram alteradas

            if (rowsAffected > 0) {//SE TEVE LINHA ALTERADA
                ResultSet rs = statement.getGeneratedKeys();//VAI pegar os codigos das linhas alteradas
                while (rs.next()) {
                    int id = rs.getInt(1);//valor da primeira coluna
                    System.out.println("Done! Id: " + id);
                }
            }
            else {
                System.out.println("No rows affected!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Banco.closeStatement(statement);
            Banco.closeConnection();

        }

    }

    public static Livro listarProdutosPorId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection conn = null;
        Livro obj = new Livro();
        try {
            conn = Banco.getConnection();//CONECTANDO
            st = conn.prepareStatement(
                    "SELECT * FROM livro WHERE idlivro = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                obj.setIdlivro(rs.getInt("idlivro"));
                obj.setNome(rs.getString("nome"));
                obj.setLivro(rs.getString("livro"));
                obj.setAutor(rs.getString("autor"));
                obj.setNumero_edicao(rs.getString("numero_edicao"));
                obj.setAno(rs.getString("ano"));
                obj.setPaginas(rs.getInt("paginas"));
            }

            return  obj;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            Banco.closeStatement(st);
            Banco.closeResultSet(rs);
        }
    }
}
