# Java Android Application Core Libraries

## Requirements

ant

## SDK

you will need to change the location of your Android SDK (sdk.dir) in:

	CoreLib/local.properties

## Build

To build/compile

	ant release|debug

To clean:

	ant clean

### Test

To test, you need to create a sample file, compile it using the library and run it.

	javac -cp .:/path/to/MyLibrary.jar /path/to/LibraryDemoTest.java
	java -cp .:/path/to/MyLibrary.jar /path/to/LibraryDemoTest

eg:

	rm *.class
	javac -classpath .:./CoreLib.jar:./Jar/* *.java 
	java -classpath .:./CoreLib.jar FileCacheTest

Or if you need to include specific jars:

	java -classpath .:./CoreLib.jar:./Jar/* DownloaderServiceTest

