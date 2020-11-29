package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Review {
    private int id, profilePic;
    private double classificacao;
    private String comentario, username, dataCriacao;

    public Review(int id, int profilePic, double classificacao, String comentario, String username, String dataCriacao) {
        this.id = id;
        this.profilePic = profilePic;
        this.classificacao = classificacao;
        this.comentario = comentario;
        this.username = username;
        this.dataCriacao = dataCriacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
