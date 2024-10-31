package com.reviewsystem.review;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

public class Create {

    public static void main(String[] args) {
        String connectionString = "mongodb+srv://<username>:<password>@<cluster-name>.vajhl.mongodb.net/?retryWrites=true&w=majority&appName=<cluster-name>";

        try (MongoClient mongoclient = MongoClients.create(connectionString)) {

            MongoDatabase database = mongoclient.getDatabase("sample_mflix");

            MongoCollection<Document> commentsCollection = database.getCollection("comments");

            Document newDocument = new Document();

            Document firstDocument = new Document();
            Document secondDocument = new Document();

            firstDocument.append("name", "Jane Doe").append("email", "janedoe@email.com")
                    .append("text", "Great Movie").append("movie_name", "The Matrix");
                    secondDocument.append("name", "Jane Smith").append("email", "janesmith@email.com")
                    .append("text", "Awesome Movie").append("movie_name", "The Matrix"); 

            newDocument.append("name", "John Darling").append("email", "johndarling@email.com")
                    .append("text", "Good Movie").append("movie_name", "The Dark Knight");

            commentsCollection.insertOne(newDocument);
            commentsCollection.insertOne(firstDocument);
            commentsCollection.insertOne(secondDocument);
        }

    }
}