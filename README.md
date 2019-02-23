# RoomExample
this is a toturial for Room library and this Architecture



## What we will build

You will build an app that uses Android Architecture Components and implements the architecture from Guide to App Architecture for these components. The sample app stores a list of words in a Room database and displays it in a RecyclerView. The app is bare bones but sufficiently complex that you can use it as a template to build upon.

In this codelab you build an app that does the following:
- Works with a database to get and save the data, and pre-populates the database with some words.
- Displays all the words in a RecyclerView in MainActivity.
- Opens a second activity when the user taps the + button. When the user enters a word, adds the word to the database and the list.


<table>
<tbody><tr><td colspan="1" rowspan="1"><p><img style="max-width: 194.00px" src="https://codelabs.developers.google.com/codelabs/android-room-with-a-view/img/2a518dec65b5b3dd.png"></p>
</td><td colspan="1" rowspan="1"><p><img style="max-width: 194.00px" src="https://codelabs.developers.google.com/codelabs/android-room-with-a-view/img/ba2d72cea775f744.png"></p>
</td><td colspan="1" rowspan="1"><p><img style="max-width: 194.00px" src="https://codelabs.developers.google.com/codelabs/android-room-with-a-view/img/ee5319926f0482eb.png"></p>
</td></tr>
</tbody>
</table>

## What you'll learn
> **How to design and construct an app using the Architecture Components Room and Lifecycles libraries.**

There are a lot of steps to using the Architecture Components and implementing the recommended architecture. The most important thing is to create a mental model of what is going on, and understand how the pieces fit together and how the data flows. As you work through this codelab, don't just copy and paste the code, but try to start building that inner understanding.

## What you'll need
- Android Studio 3.0 or later and knowledge of how to use it. Make sure Android Studio is updated, as well as your SDK and Gradle. Otherwise, you may have to wait until all the updates are done.

- An Android device or emulator.

You need to be solidly familiar with the Java programming language, object-oriented design concepts, and Android Development Fundamentals. In particular:
- **RecyclerView and adapters.**

- **SQLite database and the SQLite query language.**

- **Threading and AsyncTask.**

This codelab is focused on Android Architecture Components. Off-topic concepts and code are provided for you to simply copy and paste.

This codelab provides all the code you need to build the complete app.

![alt text](https://codelabs.developers.google.com/codelabs/android-room-with-a-view/img/3840395bfb3980b8.png)

## Create your app

-Call the app RoomWordSample
-Target SDK 26+
-Uncheck both, include Kotlin support and include C++ support.
-Check Phone & Tablet form factor only and minimum SDK API 26.
-Choose the Basic Activity.

## Update gradle files

You have to add the component libraries to your gradle files.

Add the following code to your build.gradle (Module: app) file, at the end of the dependencies block.

```gradle
// Room components
implementation "android.arch.persistence.room:runtime:$rootProject.roomVersion"
annotationProcessor "android.arch.persistence.room:compiler:$rootProject.roomVersion"
androidTestImplementation "android.arch.persistence.room:testing:$rootProject.roomVersion"

// Lifecycle components
implementation "android.arch.lifecycle:extensions:$rootProject.archLifecycleVersion"
annotationProcessor "android.arch.lifecycle:compiler:$rootProject.archLifecycleVersion"

```
In your build.gradle (Project: RoomWordsSample) file, add the version numbers to the end of the file, as given in the code below.
> Get the most current version numbers from the Adding Components to Your Project page.

```gradle
ext {
   roomVersion = '1.1.1'
   archLifecycleVersion = '1.1.1'
}
```

## Create the entity

The data for this app is words, and each ` word ` is an Entity. Create a class called Word that describes a word Entity. You need a constructor and a "getter" method for the data model class, because that's how `Room` knows to instantiate your objects.

![alt text](https://codelabs.developers.google.com/codelabs/android-room-with-a-view/img/4286f79b23797413.png)

Here is the code:
```
public class Word {

   private String mWord;

   public Word(@NonNull String word) {this.mWord = word;}

   public String getWord(){return this.mWord;}
}
```
To make the `Word` class meaningful to a Room database, you need to annotate it. Annotations identify how each part of this class relates to an entry in the database. Room uses this information to generate code.

- `@Entity(tableName = "word_table")`
Each `@Entity` class represents an entity in a table. Annotate your class declaration to indicate that it's an entity. Specify the name of the table if you want it to be different from the name of the class.
- `@PrimaryKey`
Every entity needs a primary key. To keep things simple, each word acts as its own primary key.
- `@NonNull`
Denotes that a parameter, field, or method return value can never be null.
- `@ColumnInfo(name = "word")`
Specify the name of the column in the table if you want it to be different from the name of the member variable.
- Every field that's stored in the database needs to be either public or have a "getter" method. This sample provides a `getWord()` method.

You can find a complete list of annotations in the Room package summary reference.
Update your Word class with annotations as shown in this code. If you type the annotations, Android Studio will auto-import.
```Java
@Entity(tableName = "word_table")
public class Word {

   @PrimaryKey
   @NonNull
   @ColumnInfo(name = "word")
   private String mWord;

   public Word(String word) {this.mWord = word;}

   public String getWord(){return this.mWord;}
}
```
> Tip: You can autogenerate unique keys by annotating the primary key as follows:
```Java
@Entity(tableName = "word_table")
public class Word {

@PrimaryKey(autoGenerate = true)
private int id;

@NonNull
private String word;
//..other fields, getters, setters
}
```
