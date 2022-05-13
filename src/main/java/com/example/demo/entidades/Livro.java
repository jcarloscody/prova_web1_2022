package com.example.demo.entidades;

public class Livro {
    private Integer idlivro;
    private String nome;
    private String autor;
    private String livro;
    private String ano;
    private String numero_edicao;
    private Integer paginas;

    public Livro(){

    }

    public Livro(String nome, String autor, String livro, String ano, String numero_edicao, Integer paginas) {

        this.nome = nome;
        this.autor = autor;
        this.livro = livro;
        this.ano = ano;
        this.numero_edicao = numero_edicao;
        this.paginas = paginas;
    }

    public Livro(Integer idlivro, String nome, String autor, String livro, String ano, String numero_edicao, Integer paginas) {
        this.idlivro = idlivro;
        this.nome = nome;
        this.autor = autor;
        this.livro = livro;
        this.ano = ano;
        this.numero_edicao = numero_edicao;
        this.paginas = paginas;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "idlivro=" + idlivro +
                ", nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", livro='" + livro + '\'' +
                ", ano='" + ano + '\'' +
                ", numero_edicao='" + numero_edicao + '\'' +
                ", paginas=" + paginas +
                '}';
    }

    public Integer getIdlivro() {
        return idlivro;
    }

    public void setIdlivro(Integer idlivro) {
        this.idlivro = idlivro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getNumero_edicao() {
        return numero_edicao;
    }

    public void setNumero_edicao(String numero_edicao) {
        this.numero_edicao = numero_edicao;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }
}
