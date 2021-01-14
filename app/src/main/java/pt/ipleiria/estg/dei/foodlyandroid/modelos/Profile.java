package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Profile {
    private String username, email, fullname, age, alergias, genero, telefone, morada, image;
    private int profileId;

    public Profile(int profileId,String username, String email, String fullname, String age, String alergias, String genero, String telefone, String morada, String image) {
        this.profileId = profileId;
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.age = age;
        this.alergias = alergias;
        this.genero = genero;
        this.telefone = telefone;
        this.morada = morada;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }
}
