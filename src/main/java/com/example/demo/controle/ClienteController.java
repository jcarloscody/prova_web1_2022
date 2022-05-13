package com.example.demo.controle;

import com.example.demo.entidades.Livro;
import com.example.demo.respositorio.LivroRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ClienteController {

    @GetMapping("/cliente")
    public void doListAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var writer = response.getWriter();
        writer.println("<html><body>");
        writer.println("<table>");
        var listaLivros = LivroRepositorio.buscarTodos();
        for (var p:listaLivros ){
            writer.println("<tr>");

            writer.println("<td>");
            writer.println(p.getNome());
            writer.println("</td>");

            writer.println("<td>");
            writer.println(p.getAutor());
            writer.println("</td>");

            writer.println("<td>");
            writer.println(p.getAno());
            writer.println("</td>");

            writer.println("<td>");
            writer.println("<a href='/adicionarCarrinho?id="+p.getIdlivro()+"'>Adicionar</a>");
            writer.println("</td>");

            writer.println("</tr>");
        }
        writer.println("</table>");
        writer.println("</body></html>");


        writer.println("\n" +
                "<a href=\"http://localhost:8080/verCarrinho\">Ver Carrinho</a>");
    }
}
