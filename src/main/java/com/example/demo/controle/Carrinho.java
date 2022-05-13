package com.example.demo.controle;

import com.example.demo.respositorio.LivroRepositorio;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Carrinho {

    @GetMapping("/adicionarCarrinho")
    public void doAdicionarCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        var idProduto = request.getParameter("id");

        var produto = LivroRepositorio.listarProdutosPorId(Integer.parseInt(idProduto));

        HttpSession session = request.getSession(false);
        boolean achouCarrinho = false;


        if ( session != null){
            String usuarioSession = (String) session.getAttribute("usuario");
            String emailSession = (String) session.getAttribute("email");
            String tipoSession = (String) session.getAttribute("tipo");
            if (tipoSession == "cliente"){
                response.sendRedirect("http://localhost:8080/usuario/menucliente");
            } else {
                response.sendRedirect("http://localhost:8080/usuario/menulojista");
            }
        }

        if (requestCookies != null) {
            for (var c : requestCookies) {
                if (c.getName().equals("carrinho")) {
                    achouCarrinho = true;
                    carrinho = c;
                    break;
                }
            }
        }

        Produto produtoEscolhido = null;

        if (produto != null) {
            produtoEscolhido = produto;
            if (achouCarrinho) {
                String value = carrinho.getValue();
                System.out.println("-------------------" + value);
                carrinho.setValue(value + produtoEscolhido.getId() + "|");
            } else {
                carrinho.setValue(String.valueOf(produtoEscolhido.getId()));
            }
        } else {
            response.addCookie(carrinho);
            response.getWriter().println("Id inexistente");
        }
        response.addCookie(carrinho);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaProdutos");
        dispatcher.forward(request, response);
    }

}
