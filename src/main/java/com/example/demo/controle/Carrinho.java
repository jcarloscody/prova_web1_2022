package com.example.demo.controle;

import com.example.demo.entidades.Livro;
import com.example.demo.respositorio.LivroRepositorio;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    @GetMapping("/adicionarCarrinho")
    public void doAdicionarCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        var idProduto = request.getParameter("id");

        var livro = LivroRepositorio.listarProdutosPorId(Integer.parseInt(idProduto));

        HttpSession session = request.getSession();

        if (livro != null){
            if (session.getAttribute("carrinho") != null){
                session.setAttribute("id", livro.getIdlivro());
                session.setAttribute("nome", livro.getNome());
                session.setAttribute("autor", livro.getAutor());
                session.setAttribute("livro", livro.getLivro());
                session.setAttribute("ano", livro.getAno());
                session.setAttribute("numero_edicao", livro.getNumero_edicao());
                session.setAttribute("paginas", livro.getPaginas());
                session.setMaxInactiveInterval(60*1);
            }
            HttpSession s = request.getSession(true);
            session.setAttribute("id", livro.getIdlivro());
            session.setAttribute("nome", livro.getNome());
            session.setAttribute("autor", livro.getAutor());
            session.setAttribute("livro", livro.getLivro());
            session.setAttribute("ano", livro.getAno());
            session.setAttribute("numero_edicao", livro.getNumero_edicao());
            session.setAttribute("paginas", livro.getPaginas());
            session.setMaxInactiveInterval(60*1);
        }

       RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente");
        dispatcher.forward(request, response);
    }

}
