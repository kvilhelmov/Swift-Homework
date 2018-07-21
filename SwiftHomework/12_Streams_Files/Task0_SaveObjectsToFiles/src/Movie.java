
import java.io.Serializable;
import java.time.LocalDate;

public class Movie implements Serializable{
    String title;
    String director;
    String[] actors;
    LocalDate releaseDate;
    
    Movie(String title, String director, String[] actors, LocalDate releaseDate){
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.releaseDate = releaseDate;
    }
}
