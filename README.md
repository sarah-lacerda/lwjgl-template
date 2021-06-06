# LWJGL Template

This is a template for kick-starting LWJGL 🏞️

## Breakdown

This template is using Java 1.11

This is a Gradle project, it comes with the following dependencies:

- Everything needed to spin up a simple LWJGL application with GLFW.
- Java OpenGL Math Library
- guacamole: A small collection of some common and very basic utilities for libGDX games

For more details about dependencies, take a look at the build.gradle file

This template is divided into the following:

![Util package](/src/main/java/util):
 - ![Color.java](/src/main/java/util/Color.java): Utilitary enum class for RGB colors. You can instantiate a Color with RGB values by its name.  
  For example, let's say you wanted to get the RGB values for the color `purple`:  
  ``` java
     // Call Color.from(nameOfYourColorAsString);
     Color purpleColor = Color.from("purple");
     
     System.out.println(purpleColor.getRed());
     System.out.println(purpleColor.getGreen());
     System.out.println(purpleColor.getBlue());

  ```
  The output would be:
  ``` console
     0.502
     0.0
     0.502
  ```
  
  - ![FileUtils.java](/src/main/java/util/FileUtils.java): Utilitary class that can retrieve the contents of a resource file as an `InputStream`.  
   NOTE: Files should be stored inside: `{project_root}/src/main/resources/` as this is the starting root directory for resources.
   Storing files inside this directory will ensure the files can also be located when you build your application into a jar.  
   For example, let's say you wanted to get the contents of a file whose path is `{project_root}/src/main/resources/example/HelloWorld.txt`.  
     
   The following code would print out the file contents of the file:
   ``` java
     String fileContents = FileUtils.getFileFromResourceAsStream("example/HelloWorld.txt").toString();
     System.out.println(fileContents);
   ```
   
   ![geometry.configuration package:](/src/main/java/geometry/configuration)
   
   TODO: Project structure
   
   # Getting started
   
   TODO: configuration and building/running
  
