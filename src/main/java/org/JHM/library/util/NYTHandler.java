package org.JHM.library.util;



import org.JHM.library.models.NYTbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class NYTHandler {

    //FICTION
    static String ficURL = "https://api.nytimes.com/svc/books/v3/lists.json?list=combined-print-and-e-book-fiction&api-key=" + Keys.getNYTkey();
    static ArrayList<NYTbook> fiction = new ArrayList<NYTbook>();

    //NONFICTION
    static String nonficURL = "https://api.nytimes.com/svc/books/v3/lists.json?list=combined-print-and-e-book-nonfiction&api-key=" + Keys.getNYTkey();
    static ArrayList<NYTbook> nonfiction = new ArrayList<NYTbook>();


    public static String readStringfromUrl(String request) throws IOException {
        //URL reader
        try (Scanner sc = new Scanner(new URL(request).openStream(), StandardCharsets.UTF_8.toString())) {
            sc.useDelimiter("\\A");
            return sc.hasNext() ? sc.next() : "";
        }
    }

    public static ArrayList createArray(Boolean isfic) throws IOException {

        String Url;

        if (isfic == true) {
            Url = ficURL;
        } else {
            Url = nonficURL;
        }


        String JSONlist = readStringfromUrl(Url);

        //create inital JSON object
        JSONObject obj = new JSONObject(JSONlist);

        //gets results array
        JSONArray arr = obj.getJSONArray("results");
        //get each object (book) out of results
        for (int i = 0; i < arr.length(); i++) {
            JSONObject book = arr.getJSONObject(i);

            //lose sale and format info
            JSONArray desc = book.getJSONArray("book_details");
            //access actual object within array (index 0)
            JSONObject fulldesc = desc.getJSONObject(0);

            NYTbook newbook = new NYTbook(fulldesc.get("title").toString(), fulldesc.get("author").toString(), fulldesc.get("description").toString());

            if (isfic == true) {
                fiction.add(newbook);
            } else {
                nonfiction.add(newbook);
            }
        }
        if (isfic == true) {
            return fiction;
        } else {
            return nonfiction;
        }
    }

}
