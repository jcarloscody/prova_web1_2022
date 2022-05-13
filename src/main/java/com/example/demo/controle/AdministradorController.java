package com.example.demo.controle;

import com.example.demo.entidades.Livro;
import com.example.demo.respositorio.LivroRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;

@Controller
public class AdministradorController {
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public void entradaCadastroCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        var writer = response.getWriter();

        //cabecalho
        writer.println(cabecalhoEstatico);
        writer.println(cabecalhoCoisasAInserir);
        writer.println(cabecalhoCoisasAInserir);
        writer.println(titulo);
        writer.println(cabecalhoFechamento);

        //fechamento
        writer.println(corpoAbertura);
        writer.println(templateCadastroCliente);
        writer.println(javaScriptNoCorpo);
        writer.println(corpoFechamento);
        writer.close();
    }

    @RequestMapping(value = "/cadastra", method = RequestMethod.POST)
    public void cadastraUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String autor = request.getParameter("autor");
        String livro = request.getParameter("livro");
        String ano = request.getParameter("ano");
        String numero_edicao = request.getParameter("numero_edicao");
        Integer paginas = Integer.parseInt(request.getParameter("paginas"));


        Livro u = new Livro(nome, autor, livro, ano, numero_edicao, paginas);
        LivroRepositorio.inserir(u);
        Date d = new Date();
        Cookie visita = new Cookie("visita", d.toInstant().toString());
        visita.setMaxAge(60 * 60 * 24);
        response.addCookie(visita);

        response.sendRedirect("http://localhost:8080/admin");
    }









    String cabecalhoEstatico = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
    String cabecalhoCoisasAInserir = "";
    String titulo = "<title>Usuário</title>";
    String cabecalhoFechamento = "</head>";


    String corpoAbertura = "<body>";
    String templateCadastroCliente = "<form action=\"http://localhost:8080/cadastra\" method=\"POST\">\n" +
            "      <div >\n" +
            "        <label for=\"nome\">Nome</label>\n" +
            "        <input type=\"text\" name=\"nome\"  placeholder=\"Digite o seu nome \" >\n" +
            "      </div>\n" +

            "      <div>\n" +
            "          <label for=\"autor\">Autor</label>\n" +
            "          <input type=\"text\" name=\"autor\" placeholder=\"autor\" >\n" +
            "      </div>\n" +

            "      <div>\n" +
            "          <label for=\"livro\">Livro</label>\n" +
            "          <input type=\"text\" name=\"livro\" placeholder=\"livro\">\n" +
            "      </div>\n" +

            "      <div>\n" +
            "          <label for=\"ano\">Ano</label>\n" +
            "          <input type=\"text\" name=\"ano\" placeholder=\"ano\">\n" +
            "      </div>\n" +

            "      <div>\n" +
            "          <label for=\"edicao\">Edicao</label>\n" +
            "          <input type=\"text\" name=\"edicao\" placeholder=\"edicao\">\n" +
            "      </div>\n" +

            "      <div>\n" +
            "          <label for=\"paginas\">Pagina</label>\n" +
            "          <input type=\"text\" name=\"paginas\" placeholder=\"paginas\">\n" +
            "      </div>\n" +

            "      <div >\n" +
            "        <input style='background-color: #4CAF50;\n border: none;\n color: white;\n padding: 5px 15px;\n text-align: center;\n text-decoration: none;\n display: inline-block;\n font-size: 12px;\n margin: 4px 2px;\n cursor: pointer;' id=\"btn-submitw\" type=\"submit\" value=\"Cadastrar\">\n" +
            "      </div>\n" +
            "\n" +
            "    </form>";

    String javaScriptNoCorpo = "";
    String corpoFechamento = "</body>";
}
