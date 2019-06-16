package isaacborges.com.projetoconcrete.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repositorio implements Parcelable {

    private long id;
    private String name;
    private String description;

    @JsonProperty("owner")
    private Autor autor;

    private int stargazers_count;
    private int forks_count;

    @JsonCreator
    public Repositorio(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Repositorio(Parcel in) {
        this.autor = in.readParcelable(Autor.class.getClassLoader());
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        stargazers_count = in.readInt();
        forks_count = in.readInt();
    }

    public static final Creator<Repositorio> CREATOR = new Creator<Repositorio>() {
        @Override
        public Repositorio createFromParcel(Parcel in) {
            return new Repositorio(in);
        }

        @Override
        public Repositorio[] newArray(int size) {
            return new Repositorio[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(autor, flags);
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(stargazers_count);
        dest.writeInt(forks_count);
    }
}
