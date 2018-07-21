
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Arrays;

public class Task0_SaveObjectsToFiles {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String[] actors = {"Bruce Willis", "Haley Joel Osment", "Toni Collette"};

        Movie movie = new Movie("The Sixth Sense", "M. Night Shyamalan",
                actors, LocalDate.of(1999, 8, 6));

        String fileName = "sixthsense";

        try (FileOutputStream fs = new FileOutputStream(fileName)) {
            try (ObjectOutputStream os = new ObjectOutputStream(fs)) {
                os.writeObject(movie);
            }
        }

        Movie parsed;

        try (FileInputStream fs = new FileInputStream(fileName)) {
            try (ObjectInputStream is = new ObjectInputStream(fs)) {
                parsed = (Movie) is.readObject();
            }
        }

        if (movie.director.equals(parsed.director)
                && movie.releaseDate.equals(parsed.releaseDate)
                && movie.title.equals(parsed.title)
                && Arrays.deepEquals(movie.actors, parsed.actors)) {
            System.out.println("same object");
        }
    }
}
