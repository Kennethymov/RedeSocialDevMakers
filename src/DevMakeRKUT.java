import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DevMakeRKUT {

    static List<Perfil> perfis = new ArrayList<Perfil>();

    static void menuHome() {
        String opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem vindo a DevMakeRKUT - A SUA REDE SOCIAL\n");

        do {
            System.out.println("Digite 'C' para cadastrar-se, 'E' para entrar ou 'F' para fechar");
            opcao = sc.nextLine().toUpperCase();
        } while (!opcao.equals("C") && !opcao.equals("E") && !opcao.equals("F"));

        switch (opcao) {
            case "C":
                cadastro();
                menuHome();
                break;
            case "E":
                try {
                    login();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    menuHome();
                }
                break;
            case "F":
                System.out.println("Tchau, até mais!!!");
                break;
        }
    }

    static void cadastro() {
        Scanner sc = new Scanner(System.in);
        Perfil newPerfil = new Perfil();
        System.out.println("Bem vindo ao cadastro");
        System.out.println("Digite seu nome completo");
        newPerfil.nome = sc.nextLine().toUpperCase();

        while (newPerfil.nome.isEmpty() || newPerfil.nome.isBlank()){
            System.out.println("É necessario digitar seu nome completo");
            newPerfil.nome = sc.nextLine().toUpperCase();
        }
        System.out.println("Digite seu login");
        newPerfil.login = sc.nextLine().toUpperCase();
        for (Perfil perfil:perfis) {
            if (perfil.login.equals(newPerfil.login)) {
                System.out.println("Usuario ja cadastrado no sistema! Por favor, tente novamente!!!");
                menuHome();
            }
        }
        while (newPerfil.login.isEmpty() || newPerfil.login.isBlank()){
            System.out.println("É necessario digitar um login de acesso");
            newPerfil.login = sc.nextLine().toUpperCase();
        }
        System.out.println("Crie uma senha");
        newPerfil.senha = sc.nextLine();
        while (newPerfil.senha.isEmpty() || newPerfil.senha.isBlank()){
            System.out.println("É necessario digitar uma senha");
            newPerfil.senha = sc.nextLine();
        }
        perfis.add(newPerfil);
        System.out.println("Perfil cadastrado com sucesso!!!");
    }

    static void login() {
        Scanner sc = new Scanner(System.in);
        Perfil perfil = new Perfil();
        boolean logado = false;

        System.out.println("Bem vindo ao login");
        System.out.println("Digite seu login");
        perfil.login = sc.nextLine().toUpperCase();

        for (Perfil p:perfis) {
            logado = false;
            if (perfil.login.equals(p.login)) {
                System.out.println("Digite sua senha");
                perfil.senha = sc.nextLine();
                if (perfil.senha.equals(p.senha)) {
                    logado = true;
                    menuPerfil(p);
                } else {
                    throw new InvalidPasswordException();
                }
            }
        }
        if (!logado) {
           throw new UserNotFoundException();
        }
    }

    static void postar(Perfil perfil) {
        Scanner sc = new Scanner(System.in);

        // formatação de data atual retirada do stackOverFlow por hkotsubo
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        System.out.println(dataHoraAtual);
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dataFormatada = formatterData.format(dataHoraAtual);
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        String horaFormatada = formatterHora.format(dataHoraAtual);
        Post post = new Post();

        System.out.println("Digite o que quer postar!");
        post.data = dataFormatada;
        post.hora = horaFormatada;
        post.text = sc.nextLine();

        while (post.text.isBlank() || post.text.isEmpty()) {
            System.out.println("É necesário digitar algo para postar!!");
            post.text = sc.nextLine();
        }

        perfil.posts.add(post);

        System.out.println(post.data + ", " + post.hora + ", " + post.text);
    }

    static void timeLine(Perfil perfil) {
        for (Post post: perfil.posts) {
            System.out.println(post.data + " às " + post.hora + " - '" + post.text + "'");
        }
    }
    static void menuPerfil(Perfil perfil) {
        String opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem vindo, " + perfil.nome);

        do {
            System.out.println("Digite 'P' para postar, 'T' para timeline ou 'F' para fechar");
            opcao = sc.nextLine().toUpperCase();
        } while (!opcao.equals("P") && !opcao.equals("T") && !opcao.equals("F"));

        switch (opcao) {
            case "P":
                postar(perfil);
                menuPerfil(perfil);
                break;
            case "T":
                timeLine(perfil);
                menuPerfil(perfil);
                break;
            case "F":
                System.out.println("Tchau, até mais!!!");
                menuHome();
                break;
        }
    }



    public static void main(String[] args) {
        menuHome();
    }
}
