package com.example.demo.respositorio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Banco {

    private static Connection connection = null; //vai ser um objeto de conexao dopacote  java.sql com o BD do JDBC, da

    public static Connection getConnection() {
        if (connection == null) {//o connection for nulo
            try {//tentativa de conexao
                Properties properties = loadProperties();//executa o metodo criado abaixo e guarda o retorno
                String url = properties.getProperty("dburl");//pega a url descrita no arquivo como dburl carregada no return
                connection = DriverManager.getConnection(url, properties);
                //estabelecendo uma conexao com o bd
                //chama a classe DriveManager, e o metodo de conexao e passa as propriedades e url
                //conectar com o bd com o jdbc, é instanciar um objeto do tipo connection
                System.out.println("Conexao realizada com sucesso!");
            }
            catch (SQLException e) {
                System.out.println("Conexao falhou!");
                throw new DbException(e.getMessage());

            }
        }
        return connection; //retorno do connection. conexao feita
    }

    public static void closeConnection() {//desligando a conexao
        if (connection != null) {//quando ligado
            try {//tenta desligar
                connection.close(); //fecha a conexao
                connection = null;
                System.out.println("Conexao encerrada com sucesso!");
            } catch (SQLException e) {
                System.out.println("Conexao nao encerrada com sucesso!");
                throw new DbException(e.getMessage());//lança uma excecao personalizada, é melhor lançar uma própria exceção pq
                //o sqlexception é derivado da classe exception, vc é obrigado a tratar, e a classe que fizemos, dbexception,
                //extends a runtimeexception e como ela herda isto, o meu programa não vai precisar ficar toda hora colocando
                //try/cache, só vou colocar o try/cache somente quando achar necessário.
            }
        }
    }

    private static Properties loadProperties() {
        //abrindo o arquivo db.properties
        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            Properties properties = new Properties();//objeto do tipo Properties
            properties.load(fileInputStream);//o comando load faz a leitura do arquivo Properties e vai guardar dentro da variável
            //properties, os dados do arquivo
            return properties;
        }
        catch (IOException e) {
            throw new DbException(e.getMessage()); //lança uma exceção persobnalizada no DbException
        }
    }


    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
