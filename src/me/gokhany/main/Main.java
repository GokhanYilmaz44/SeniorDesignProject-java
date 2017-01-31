package me.gokhany.main;

import me.gokhany.manager.TweetManager;
import me.gokhany.manager.TwitterCriteria;
import me.gokhany.model.Tweet;

public class Main {

    public static void main(String[] args) {
        /**
         * Reusable objects
         */
        TwitterCriteria criteria = null;
        Tweet t = null;

        /**
         *  Example 1 - Get tweets by username
         **/

        criteria = TwitterCriteria.create()
                .setUsername("cnnturk")
                .setMaxTweets(1);
        t = TweetManager.getTweets(criteria).get(0);

        System.out.println("### Example 1 - Get tweets by username [cnnturk]");
        System.out.println("Username: " + t.getUsername());
        System.out.println("Retweets: " + t.getRetweets());
        System.out.println("Text: " + t.getText());
        System.out.println("Mentions: " + t.getMentions());
        System.out.println("Hashtags: " + t.getHashtags());
        System.out.println();

        /**
         *  Example 2 - Get tweets by query search
         **/
        criteria = TwitterCriteria.create()
                .setQuerySearch("europe refugees")
                .setSince("2015-05-01")
                .setUntil("2015-10-25")
                .setMaxTweets(1);
        t = TweetManager.getTweets(criteria).get(0);

        System.out.println("### Example 2 - Get tweets by query search [europe refugees]");
        System.out.println("Username: " + t.getUsername());
        System.out.println("Retweets: " + t.getRetweets());
        System.out.println("Text: " + t.getText());
        System.out.println("Mentions: " + t.getMentions());
        System.out.println("Hashtags: " + t.getHashtags());
        System.out.println();

        /**
         *  Example 3 - Get tweets by username and bound dates
         **/
        criteria = TwitterCriteria.create()
                .setUsername("europe refugees")
                .setSince("2016-08-15")
                .setUntil("2016-08-16")
                .setMaxTweets(1);
        t = TweetManager.getTweets(criteria).get(0);

        System.out.println("### Example 3 - Get tweets by username and bound dates [cnnturk, '2016-08-15', '2016-08-16']");
        System.out.println("Username: " + t.getUsername());
        System.out.println("Retweets: " + t.getRetweets());
        System.out.println("Text: " + t.getText());
        System.out.println("Mentions: " + t.getMentions());
        System.out.println("Hashtags: " + t.getHashtags());
        System.out.println();
    }

}