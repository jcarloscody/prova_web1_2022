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
public class PopularBanco {

    @GetMapping("/config")
    public void doRemoverCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String texto = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur molestie sed nulla sit amet pharetra. Phasellus congue tincidunt placerat. Phasellus et libero id justo malesuada interdum ut eu magna. Praesent est lectus, finibus et velit sed, vehicula ullamcorper enim. Aliquam sit amet porttitor tellus. Aliquam pulvinar elit odio, sit amet dignissim risus pretium nec. Vestibulum id nisl accumsan, convallis lorem quis, efficitur orci. Praesent congue, ligula ac dapibus congue, tortor velit iaculis velit, quis congue purus quam a ante. Quisque et nisl blandit massa vestibulum sodales. Aliquam volutpat nisi ligula, ac rutrum ipsum ultricies et. Maecenas auctor condimentum sodales.";
        Livro livro1 = new Livro("Domain-Driven Design", "Eric", texto, "2016", "5", 256);
        Livro livro2 = new Livro("Ciencias Naturais", "Viking", texto, "2016", "5", 256);
        Livro livro3 = new Livro("A era da escrita cuneiformes", "galaxias", texto, "2016", "5", 256);
        Livro livro4 = new Livro("Moises e o EGITO", "Eric M", texto, "2016", "5", 256);
        Livro livro5 = new Livro("Domain-Driven Design", "Eric", texto, "2016", "5", 256);

        try {
            LivroRepositorio.criarTabela();
        }catch (Exception e){
            System.out.println(e);
        }


        try {

            LivroRepositorio.inserir(livro1);
            LivroRepositorio.inserir(livro2);
            LivroRepositorio.inserir(livro3);
            LivroRepositorio.inserir(livro4);
            LivroRepositorio.inserir(livro5);
            var writer = response.getWriter();
            writer.println("sucesso na criação e população de banco de dados");
        } catch (Exception e){

            var writer = response.getWriter();
            writer.println("fracaçou!");
        }

    }
}
