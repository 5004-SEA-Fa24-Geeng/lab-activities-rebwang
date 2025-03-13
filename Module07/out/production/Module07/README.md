# Module 07 Team Activity  - MVC: Model

In this activity, we will explore the model or data layer of the Model-View-Controller (MVC) design pattern. We will create a simple model that will be used to store data for our application. We will also explore how to read and write data from XML and JSON files, which are common file types used to store data in applications.


## Grading
Grades for team activities will be based on attendance and notes. You must attend, and as a team you need to generate notes that we can confirm your work. Ideally, you upload the notes as a PDF to the team meeting after you build them out. 

> [!TIP] 
> Good notes become a study guide for you and your team! Make sure they include everything you need to help better understand the weekly material. 

### ⭐ Working in Teams ⭐
When working in teams, remember do not let one person do all the work. Make sure to work together, and ask questions. It is also better if different people program, and you all take turns programming for various team assignments.

## Learning Objectives
This team activity is designed to help you understand the following concepts:
* Understand the Model-View-Controller (MVC) design pattern
* Implement a simple model
* Use record  classes to create objects from data
* Manipulate information from XML and JSON file types


## Reflection on HW05
In Homework 05, we created an immutable [BoardGame](bg_arena_planner/BoardGame.java) class. It was immutable, as we did not provide 
any setters for the class, and none of our methods modified any of the data in the class. 
It also involved implementing toString, equals, and hashCode methods.  

👉🏽  Discussion - Take a moment to remind yourself about toString(), equals, and hashCode. Discuss key points, and why they would be 
common in classes whose primary purpose is to store data/information. 

This type of class is very common to implement in programming,
and often called a "dataclass" in many languages such as Python or Kotlin. In Java, we can use the `record` class to create a similar
class with less boilerplate code.

### Java Records

`Record` classes are a new feature in Java 16+ that allow you to create simple data classes with less boilerplate code. They all have the following properties:
    * They are immutable
    * They have a `toString()` method that prints out the values of the fields
    * They have an `equals()` and `hashCode()` method that compares the values of all the fields
    * They can to extend other classes (because they extend the `java.lang.Record` class), but they can use implements to implement interfaces. 

They can be used at the top level of a file, or as a nested class. They can even be declared inline if you need a temp object to store information.

The following classes were added to this project. 
* [RecordExample.java](RecordExample.java) - Sample program loading records
* [Student.java](Student.java) - Example of a record class
* [Person.java](Person.java) - Example of a record class 

👉🏽  Discussion - Take time to discuss and run the above classes. Additionally, we have added [BoardGame.java](BoardGame.java) as an example of implementing BoardGame as a record.  For Record BoardGame as compared to Class BoardGame, there are some subtle differences that keep them from exactly equivalent. Can you uncover the difference? Maybe try modifying some of the values in the `main` in Record BoardGame to see the results.


> [!TIP]
> What is **var**? Starting in Java 11, you can use the `var` keyword to declare local variables. The compiler will infer the type of the variable based on the value you assign to it. This can make your code more concise and easier to read. We often don't introduce it right away, as most books are still stuck back at java 8.0. But it is a good feature to know about, and can help your code. Note it is only for local (internal to methods, loops, if statements) variables, and *not for class fields or method parameters*.


### Java "Beans" Side Bar
You will find reference to Java Beans throughout java, which I often found confusing as a student. A Java Bean is a class that follows certain conventions. It must have a no-argument constructor as at least one of the constructors, and it must have getter and setter methods for its properties. That is it! But it is a common pattern in Java, and you will see it often in libraries and frameworks that use reflection to work with classes. Now that records exist in java, another way to look at it are records are immutable with less boilerplate code while beans are mutable.




## File Types
Let's take a moment to discuss file types. There are often two major 'types' of files, binary and text. Binary files are files that are stored in a format that is not human-readable. They are often used to store data in a more compact form, and are often used by programs to store data. Text files are files that are stored in a human-readable format. They are often used to store data that needs to be read or edited by humans. That is it. Extensions tell you more about the file/formatting of the content of the file. For example, a `.txt` file is a open text file with no formatting, while a `.xml` file is an XML file which relies on a hierarchy of bracket notation to define information , and a `.json` file is a JSON file that is "code like" storage of the data.

It is worth exploring some basic formats, as you work with building your models often comes hand in hand with reading and writing data to files. 


### CSV Files
Explored before, Comma Separated Values are a "flat" data system. Meaning it is a single table of data, with rows and columns. Each row is a record, and each column is a field. It is a very simple format, and can be read and written by many programs. It is flat, as the rows are not 'nested' or 'hierarchical', and those type of relationships are not easily represented in CSV files.

While we have been using .split to parse CSV files, there are libraries that can help you read and write CSV files. One such library is OpenCSV. You can find more information about OpenCSV at [http://opencsv.sourceforge.net/](http://opencsv.sourceforge.net/). This allows for the more options, such as data with commas in it, or other special characters without causing .split issues. 

> [!NOTE]
> When you see things talk about Maven like the first page of OpenCSV, that also works for gradle! Gradle is built off of maven, and  uses a different file format to store the dependencies.


## JSON Files
JavaScript Object Notation - JSON. This format was developed to be a lightweight, human-readable format for exchanging data. It is often used to store configuration information, or to send data between a server and a client. It is based on JavaScript but is language-independent. It is easy to read and write, and is supported by many programming languages.  For Java, you can use the Jackson library to read and write JSON files. You can find more information about Jackson at [https://github.com/FasterXML/jackson-docs?tab=readme-ov-file#tutorials](https://github.com/FasterXML/jackson-docs?tab=readme-ov-file#tutorials)

JSON file format looks like
```json
"people": [{
  "name": "John Doe",
  "age": 29,
  "city": "New York"
},
{
  "name": "Jane Smith",
  "age": 32,
  "city": "Los Angeles"
}],
"cities": [{
  "name": "New York",
    "coordinates": {
    "latitude": 40.7128,
    "longitude": -74.0060
    }  
},
{
  "name": "Los Angeles",
   "coordinates": {
        "latitude": 34.0522,
        "longitude": -118.2437
        }
    }]
```

Anything between `[]` is an array, and anything between `{}` is an object like a map. Objects have key-value pairs, where the key is a string, and the value can be a string, number, boolean, array, or object.  

It is important to note that it is hierarchical and can store nested data. 

👉🏽  Discuss the JSON file format, and how it is different from CSV files.

### XML Files

Extensible Markup Language - XML. This format was developed to store and transport data. It is a hierarchical format, and is often used to store configuration information, or to send data between a server and a client. You have seen us use XML before for the .properties file in java, and Java has built in readers for XML.  For Java, you can use the DOM or SAX parser to read and write XML files. You can find more information about the DOM parser at [https://docs.oracle.com/javase/tutorial/jaxp/dom/index.html](https://docs.oracle.com/javase/tutorial/jaxp/dom/index.html) and the SAX parser at [https://docs.oracle.com/javase/tutorial/jaxp/sax/index.html](https://docs.oracle.com/javase/tutorial/jaxp/sax/index.html)


While the DOM (Document Object Model)  is very common if you need to traverse an XML document, it can also be expensive as it keeps the entire document in memory. SAX (Simple API for XML) is a stream-based parser that reads the document from start to finish, and is more memory efficient.

Here is an example of a simple XML file.
```xml
<people>
  <person>
    <name>John Doe</name>
    <age>29</age>
    <city>New York</city>
  </person>
  <person>
    <name>Jane Smith</name>
    <age>32</age>
    <city>Los Angeles</city>
  </person>
</people>
```

What is it is saying is there can be any number of people, and each person has a name, age, and city.

Let's take a look at some code that reads the simple.xml file. 

:fire: [SimpleXMLReader.java](SimpleXMLReader.java) - This program reads the simple.xml file and prints the information to the console. As a group discuss and then run the file. Comment throughout the file to help your understanding of what is going on. 

Then add the following to the endElement method.

```java
    if (qName.equalsIgnoreCase("person")) {
        people.add(Person.fromMap(current));
        current = null;
    } else {
        if (current != null) {
            current.put(qName, buffer.toString());
        }
    }
```

> [!WARNING]
> The most common error in using SAX is not resetting the buffer after you have read the data. This can cause the data to be incorrect, not read at all, or read all at once. Thus the reason startElement has `buffer.setLength(0);`. Then between the start of a tag such as `<person>` and the end tag, `characters(char[] ch, int start, int length)` is called, so you add
> the contents of char[] ch to your buffer. During endElement, you deal with that information how you need (add it to another object, ignore it, etc).

## :keyboard: Putting it together

Now, as a team, you will work on writing your own SAX parser to read a more complex XML file. We have started it for you in [BGGeekXMLReader.java](BGGeekXMLReader.java). The flow starts in [BGGeek.java](BGGeek.java) where we connect to the BoardGameGeek API and download the information about a game. We then send that information to BGGeekXMLReader.java to parse the XML information. However, that information is not complete.

As a Team complete the reader to include thumbnail, description, and year published.  

> [!NOTE]
> BGGeek makes use of xml attributes, which is a way to add info inside the tag itself. For example `<thumbnail value="https://www.example.com/image.jpg"/>`, value would be an attribute to the thumbnail tag. The advantage of this is that it can be faster to read, as you only need to read the start tag to get the info. However, it is a trade off depending on what you need to represent. You can view the XML file we are reading by going to [https://boardgamegeek.com/xmlapi2/thing?id=13,14,15,16,17](https://boardgamegeek.com/xmlapi2/thing?id=13,14,15,16,17). 


## Relationship to Model

The model layer is your data layer in an application, and also how you manipulate that data. Knowing how to convert and read different file formats is only part of the model, but it is an essential element. It is also worth noting an important characteristic of the XMLReader for the Board Game example - it takes an `InputStream` not just a file! This allows the model to get the data from a variety of sources, not just a file, but still the model itself is the same once the data is brought into the program. 

### 👉🏽  Discuss 
An important aspect  of the model is that it **protects** the data from the rest of the application. This can be done by sending records or other immutable (or copies) of information to the controller and view, but rarely is the full mutable instance of an object sent up to the other components of the program - even if the object is mutable inside to the model. Talk about this concept, and what are other major characteristics of the model layer. 

You will continue to get practice with Models, and most stuff you have written already falls into the model layer of an application. The overall philosophy of MVC is to protect different aspects of the program, only exposing exactly what is needed - and nothing more. If this sounds familiar it is! This is essentially encapsulation at the design level, and many forms of architecture follow this principle. As such learning MVC will help when you explore other design patterns and architectures, if you keep in mind the goal is always to protect and provide a separation of concerns. 


## :fire: Java Practice Problem
As part of **every** team activity, we will ask you to work on a Java Practice problem, and submit the code to the team files section (or as part of your notes). This is meant to give you practice similar to technical interviews, but also help build up your java skills. **Each team member needs to select a different problem!** But you can share/and should share answers and help each other. Remember, to learn a new language, the best thing you can do is practice! Here are some resources to find practice problems but you are not limited to them:

* [CodeHS - Java Practice](https://codehs.com/practice/java)
* [Coding Bat - Java](https://codingbat.com/java)
* [Hacker Rank - Java(Basic)](https://www.hackerrank.com/domains/java?filters%5Bskills%5D%5B%5D=Java%20%28Basic%29)