import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Game> games = new ArrayList<>();

    public List<Game> sortByName(){
        List<Game> sortedByName = new ArrayList<>(games);
        sortedByName.sort(Comparator.comparing(Game::getName));
        return sortedByName;
    }

    public List<Game> sortByRating(){
        List<Game> sortedByRating = new ArrayList<>(games);
        sortedByRating.sort(Comparator.comparingDouble(Game::getRating).reversed());
        return sortedByRating;
    }

    public List<Game> sortByPrice(){
        List<Game> sortedByPrice = new ArrayList<>(games);
        sortedByPrice.sort(Comparator.comparingInt(Game::getPrice).reversed());
        return sortedByPrice;
    }

    public void setUp() throws IOException {
        File input = new File("src/Resources/Video_games.html");
        Document doc = Jsoup.parse(input, "UTF-8");

        Elements gameElements = doc.select(".col-md-4.game");

        for (Element gameElement : gameElements) {
            String name = gameElement.selectFirst(".game-name").text();
            String ratingText = gameElement.selectFirst(".game-rating").text();
            String priceText = gameElement.selectFirst(".game-price").text();

            double rating = Double.parseDouble(ratingText.split("/")[0]);
            int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));

            games.add(new Game(name, rating, price));
        }
    }


    public static void main(String[] args) {

    }

}
