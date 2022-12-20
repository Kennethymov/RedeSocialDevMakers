package Perfil;

import Posts.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Perfil {
    private String nome;
    private String login;
    private String senha;
    private List<Post> posts = new ArrayList<Post>();
    Scanner sc = new Scanner(System.in);

    public Perfil(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean verifySenha(String senha) {
        return senha.equals(this.senha);
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void postar() {
        Scanner sc = new Scanner(System.in);
        String text;

        // formatação de data atual retirada do stackOverFlow por hkotsubo
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        System.out.println(dataHoraAtual);
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dataFormatada = formatterData.format(dataHoraAtual);
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        String horaFormatada = formatterHora.format(dataHoraAtual);


        System.out.println("Digite o que quer postar!");

        text = sc.nextLine();

        while (text.isBlank() || text.isEmpty()) {
            System.out.println("É necesário digitar algo para postar!!");
            text = sc.nextLine();
        }

        Post post = new Post(text, dataFormatada, horaFormatada);

        posts.add(post);

        System.out.println("Post adicionado com sucesso!!");
    }

    public void timeLine() {
        for (Post post: posts) {
            System.out.println(post.getData() + " às " + post.getHora() + " - '" + post.getText() + "'");
        }
    }

}
