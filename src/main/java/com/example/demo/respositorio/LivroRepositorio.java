package com.example.demo.respositorio;


import com.example.demo.entidades.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepositorio {

    public static List<Livro> buscarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            List<Livro> livros = new ArrayList<>() ;

            conn = Banco.getConnection();//CONECTANDO
            st = conn.prepareStatement(
                    "SELECT * FROM produto");
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


    public static void inserir (String nome, String livro, String autor, String numero_edicao, String ano, Integer paginas){
        Connection conn = null;
        PreparedStatement statement = null;


        try {
            conn = Banco.getConnection();//CONECTANDO

            // EXAMPLE 1:
            statement = conn.prepareStatement(//vai receber o comando SQL e ficar preparado para executar
                    "INSERT INTO produto "
                            + "(nome, descricao, quantidade, preco) "
                            + "VALUES "
                            + "(?, ?, ?, ?)", //4 posicoes
                    Statement.RETURN_GENERATED_KEYS);//SOBRECARGA RECEBE OUTRO PARAMETRO

            statement.setString(1, nome);//tipo string, posicao 1
            statement.setString(2, descricao);
            statement.setInt(3, quantidade);
            statement.setDouble(4, preco);
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

    public static Produto listarProdutosPorId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection conn = null;
        Produto obj = new Produto();
        try {
            conn = Banco.getConnection();//CONECTANDO
            st = conn.prepareStatement(
                    "SELECT * FROM produto WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setPreco(rs.getDouble("preco"));
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
