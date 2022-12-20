package RedeSocial;

import Perfil.Perfil;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DevMakeRKUT {

    private final List<Perfil> perfis = new ArrayList<Perfil>();
    private boolean logado = false;
    public DevMakeRKUT() {
        menuHome();
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public boolean isLogado() {
        return logado;
    }

    private void menuHome() {
        String opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem vindo a RedeSocial.DevMakeRKUT - A SUA REDE SOCIAL\n");

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
                System.out.println("ATE MAIS!!!");
                System.exit(0);
                break;
        }
    }

    private void cadastro() {
        Scanner sc = new Scanner(System.in);
        String nome, login, senha;

        System.out.println("Bem vindo ao cadastro");
        System.out.println("Digite seu nome completo");
        nome = sc.nextLine().toUpperCase();

        while (nome.isEmpty() || nome.isBlank()){
            System.out.println("É necessario digitar seu nome completo");
            nome = sc.nextLine().toUpperCase();
        }
        System.out.println("Digite seu login");
        login = sc.nextLine().toUpperCase();
        for (Perfil perfil:perfis) {
            if (perfil.getLogin().equals(login)) {
                System.out.println("Usuario ja cadastrado no sistema! Por favor, tente novamente!!!");
                menuHome();
            }
        }
        while (login.isEmpty() || login.isBlank()){
            System.out.println("É necessario digitar um login de acesso");
            login = sc.nextLine().toUpperCase();
        }
        System.out.println("Crie uma senha");
        senha = sc.nextLine();
        while (senha.isEmpty() || senha.isBlank()){
            System.out.println("É necessario digitar uma senha");
            senha = sc.nextLine();
        }

        Perfil newPerfil = new Perfil(nome, login, senha);

        perfis.add(newPerfil);
        System.out.println("Perfil.Perfil cadastrado com sucesso!!!");
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        String login, senha;

        System.out.println("Bem vindo ao login");
        System.out.println("Digite seu login");
        login = sc.nextLine().toUpperCase();

        for (Perfil perfil:perfis) {
            if (login.equals(perfil.getLogin())) {
                System.out.println("Digite sua senha");
                senha = sc.nextLine();
                if (perfil.verifySenha(senha)) {
                    setLogado(true);
                    menuPerfil(perfil);
                } else {
                    throw new InvalidPasswordException();
                }
            }
        }
        if (!isLogado()) {
           throw new UserNotFoundException();
        }
    }

    private void menuPerfil(Perfil perfil) {
        String opcao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem vindo, " + perfil.getNome());

        do {
            System.out.println("Digite 'P' para postar, 'T' para timeline ou 'F' para fechar");
            opcao = sc.nextLine().toUpperCase();
        } while (!opcao.equals("P") && !opcao.equals("T") && !opcao.equals("F"));

        switch (opcao) {
            case "P":
                perfil.postar();
                menuPerfil(perfil);
                break;
            case "T":
                perfil.timeLine();
                menuPerfil(perfil);
                break;
            case "F":
                System.out.println("Tchau, até mais!!!");
                sairLogin();
                break;
        }
    }

    public void sairLogin() {
        setLogado(false);
        menuHome();
    }
}
