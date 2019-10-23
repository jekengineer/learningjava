package com.TwitterBotGroup.TwitterBotArtifact;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.nio.charset.Charset;

public class TwitterBotClass {
    public static void main(String[] args) {
        tweetLines();
    }
    private static void tweetLines() {
        String line;
        try {
            try (
                    InputStream fis = new FileInputStream("C:\\Users\\Nano\\eclipse-workspace\\TwitterBotArtifact\\src\\resources\\tweets.txt");
                    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("Cp1252"));
                    BufferedReader br = new BufferedReader(isr);
            ) {
                while ((line = br.readLine()) != null) {
                    // Deal with the line
                    sendTweet(line);
                    System.out.println("Tweeting: " + line + "...");

                    try {
                        System.out.println("Sleeping for 30 minutes...");
                        Thread.sleep(120000); // every 2 minutes
                        // Thread.sleep(1800000); // every 30 minutes
                        // Thread.sleep(10000); // every 10 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void sendTweet(String line) {
    	ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("bAzwfJMQ0vfLfm7GGBqxhQ5Rt")
          .setOAuthConsumerSecret("0XugBue5U7VBAQkVUFeoMWNVgAwPXgA9eRjGV2QdZGWuI8hPg0")
          .setOAuthAccessToken("1060648626811219969-8Ti7g1ZP84P67QcfjumFR5m4Tuev9a")
          .setOAuthAccessTokenSecret("M17qC74PWDd7erR6HAr6fFp6mycwYkX7xpxJ8pPDNBW6p");
    	
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        //Twitter twitter = TwitterFactory.getSingleton();
        Status status;
        try {
            status = twitter.updateStatus(line);
            System.out.println(status);
        } catch (TwitterException e) {;
            e.printStackTrace();
        }
    }
}