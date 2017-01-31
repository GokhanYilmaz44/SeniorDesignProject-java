package me.gokhany.main;

import me.gokhany.manager.MySQLConnection;
import me.gokhany.manager.TweetManager;
import me.gokhany.manager.TwitterCriteria;
import me.gokhany.model.Tweet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Exporter {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.err.println("You must pass some parameters. Use \"-h\" to help.");
            MySQLConnection myDb = new MySQLConnection();
            myDb.insertUsers("cnnturk");
            System.exit(0);
        }

        if (args.length == 1 && args[0].equals("-h")) {
            System.out.println("\nTo use this jar, you can pass the folowing attributes:");
            System.out.println("   username: Username of a specific twitter account (whitout @)");
            System.out.println("      since: The lower bound date (yyyy-mm-aa)");
            System.out.println("      until: The upper bound date (yyyy-mm-aa)");
            System.out.println("querysearch: A query text to be matched");
            System.out.println("  maxtweets: The maximum number of tweets to retrieve");

            System.out.println("\nExamples:");

            System.out.println("# Example 1 - Get tweets by username [cnnturk]");
            System.out.println("java -jar SeniorDesignProject.jar username=cnnturk maxtweets=1\n");

            System.out.println("# Example 2 - Get tweets by query search [europe refugees]");
            System.out.println("java -jar SeniorDesignProject.jar querysearch=\"europe refugees\" maxtweets=1\n");

            System.out.println("# Example 3 - Get tweets by username and bound dates [cnnturk, '2016-08-15', '2016-08-16']");
            System.out.println("java -jar SeniorDesignProject.jar username=cnnturk since=2016-08-15 until=2016-08-16 maxtweets=1");
        } else {
            TwitterCriteria criteria = TwitterCriteria.create();

            for (String parameter : args) {
                String[] parameterSplit = parameter.split("=");

                if (parameterSplit[0].equals("username")) {
                    criteria.setUsername(parameterSplit[1]);
                } else if (parameterSplit[0].equals("since")) {
                    criteria.setSince(parameterSplit[1]);
                } else if (parameterSplit[0].equals("until")) {
                    criteria.setUntil(parameterSplit[1]);
                } else if (parameterSplit[0].equals("querysearch")) {
                    criteria.setQuerySearch(parameterSplit[1]);
                } else if (parameterSplit[0].equals("maxtweets")) {
                    criteria.setMaxTweets(Integer.valueOf(parameterSplit[1]));
                }
            }

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("output_got.csv"));
                bw.write("username;date;retweets;favorites;text;geo;mentions;hashtags;id;permalink");
                bw.newLine();

                System.out.println("Searching... \n");
                for (Tweet t : TweetManager.getTweets(criteria)) {
                    bw.write(String.format("%s;%s;%d;%d;\"%s\";%s;%s;%s;\"%s\";%s", t.getUsername(), sdf.format(t.getDate()), t.getRetweets(), t.getFavorites(), t.getText(), t.getGeo(), t.getMentions(), t.getHashtags(), t.getId(), t.getPermalink()));
                    bw.newLine();
                }

                bw.close();

                System.out.println("Done. Output file generated \"twitter_data.csv\".");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
