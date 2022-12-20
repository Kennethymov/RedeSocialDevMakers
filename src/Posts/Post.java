package Posts;

public class Post {
    private final String data;
    private final String hora;
    private String text;

    public Post(String text, String data, String hora ) {
        this.text = text;
        this.data = data;
        this.hora = hora;
    }

    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getData() {
        return this.data;
    }

    public String getHora() {
        return this.hora;
    }
}
