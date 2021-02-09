package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Profile {
    private String username, password, email, fullname, age, alergias, genero, telefone, morada, image;
    private int profileId;

    public Profile(int profileId, String username, String email, String fullname, String age, String alergias, String genero, String telefone, String morada, String image) {
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
        this.password = "";
    }

    public Profile(int profileId, String username, String password, String email, String fullname, String genero, String age, String telefone, String morada, String alergias, String image) {
        this.profileId = profileId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.age = age;
        this.alergias = alergias;
        this.genero = genero;
        this.telefone = telefone;
        this.morada = morada;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", age='" + age + '\'' +
                ", alergias='" + alergias + '\'' +
                ", genero='" + genero + '\'' +
                ", telefone='" + telefone + '\'' +
                ", morada='" + morada + '\'' +
                ", image='" + image + '\'' +
                ", profileId=" + profileId +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
