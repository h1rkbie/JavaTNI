import java.util.Scanner;

public class Lab602 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] movies = {"Me Before You", "Titanic", "Before Sunrise", "The Holiday", "A Walk to Remember"};
        double[] rate_scores = {4.2, 4.9, 4.4, 3.7, 4.3};
        System.out.print("Enter a movie title: ");
        String SearchTitle = input.nextLine();
        System.out.println();
        int count = 0;

        for (int i = 0; i < movies.length; i++) {
            if (movies[i].equalsIgnoreCase(SearchTitle)) {
                System.out.println("The rating score of \"" + movies[i] + "\" is " + rate_scores[i]);
                int rank = find_rank(rate_scores, rate_scores[i]);
                System.out.println("This movie is ranked number " + rank);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Cannot found this movie title...");
        }
    }

    static int find_rank (double[] rate_scores, double find_rank) {
        int rank = 1;
        for (int i = 0; i < rate_scores.length; i++) {
            if (rate_scores[i] > find_rank) {
                rank++;
            }
        }
        return rank;
    }
}
