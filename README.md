# DatabaseTutorial

This repository contains some .java files to explain how to create and read entries from MongoDB.

You can read more about the steps [here](https://www.mongodb.com/developer/languages/java/java-setup-crud-operations/)


## `pom.xml`

This file is saved in the root of the project folder. Usually at the same level as `src` folder. It is used to specify the dependencies, plugins, metadata, and other instructions for maven on how to build and run our project. 

You can read more about the file [here](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)


Following is a brief description of each tag in the `pom.xml` file:

* `project` - All tags are within the `project` tag. This is the root tag of the file. 
* `modelVersion` - This specifies the version of the format we will be using `pom.xml`
* `groupId`, `artifactId`, and `version` - These tags helps us define which organization does our project belong to (`groupdId`), what is the name of our project (`artifactId`), and what is the current version of this project  (`version`). We use these three tags when trying to install other dependencies as well.
* `properties` - In this tag we specify important details for maven such as the Java version in which the code is written (`maven.compiler.source`) and the version it should be compiled into for use (`maven.compiler.target`). You can specify a different (lower) version your project should be compiled into.
* `dependency` - As the tag suggests, you define the dependencies of your project or the libraries you need maven to install so that you can use them. Note that we use `groupId`, `artifactId`, and `version` tags to specify the version of the library we want to use (similar to how we define it at the top of the `pom.xml` file).

## `Packages`

A typical project structure for any Java project should be something like:

```
 src
   └── main
│   │    └── java
│   │          └── com
│   │               └── system
│   │                   |-- package 1
|   |                           |--- File1.java
|   |                           |--- File2.java
|   |                   |-- package 2
...
|   |                   |-- package N
...
```

While this project structure is complex (and may seem unnecessary), it helps manage class files and other resources that a project file may require. 

**Note** that the `pom.xml` file should be at the same folder level as `src` folder. 


## `mvn` and `HelloWorld.java`

We createa a simple `HelloWorld.java` with a `main` method. Note that while `HelloWorld.java` is in `src/main/java/com/reviewsystem/review/`, its package is actually `com.reviewsystem.review. The root of the package structure start from `com`. You can read more about java packages and their structure. But [here](https://jenkov.com/tutorials/java/packages.html) is a short guide.


Now to run the `HelloWorld.java` file, we can use maven as follows: `mvn compile exec:java -Dexec.mainClass="com.reviewsystem.review.HelloWorld"`

If everything goes smoothly, you should get "Hello World" on your terminal screen.

## Reading an entry

**NOTE** - This section assumes you have a database and collection with sample dataset created on MongoDB. If not, first create an account and populate it with sample dataset. [Here](https://www.mongodb.com/docs/atlas/getting-started/) is an initial guide but search the web for necessary instructions/guide. 

First, [get the connection string from MongoDB](https://www.mongodb.com/docs/guides/atlas/connection-string/). Replace <db_password> in the connection string with your MongoDB password. If you do not remember the password, search the web on how to reset the password.

After you have your connection string, replace it with the dummy string on line 14 in `Read.java`. This connection string helps us connect to our database.

In the following code snippet, we first connect to a client and then from our `comments` collection inside `sample_mflix` database, we read the first entry. 

```java

try (MongoClient mongoclient = MongoClients.create(connectionString)) {

    MongoDatabase database = mongoclient.getDatabase("sample_mflix");

    MongoCollection<Document> commentsCollection = database.getCollection("comments");

    Document firstDocument = commentsCollection.find().first();

    System.out.println(firstDocument.toString());
}

```

You can run this code using the maven - `mvn compile exec:java -Dexec.mainClass="com.reviewsystem.review.Read"`

Notice, we replaced "`HelloWorld`" with "`Read`" since the main method we want to use is now in a different class. 


## Creating an entry into the database


Similar to `Read.java` we first get the connection to our collections in our database and then create an entry and insert it.

We use `mvn compile exec:java -Dexec.mainClass="com.reviewsystem.review.Create"` to run the main method in this `Create.java` class file.

You can check if the entries were truly created or not by going to MongoDB and under "comments" collections in your cluster, paste this query `{"movie_name": "The Matrix"}`. You should get two entries matching `firstDocument` and `secondDocument`.





