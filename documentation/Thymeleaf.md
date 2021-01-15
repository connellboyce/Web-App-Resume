# Thymeleaf

### Table of Contents
1. Dependency
2. Resources Directory
3. Controllers
4. Linking Local HTML, JavaScript and CSS with Thymeleaf

### Dependency
- To use Thymeleaf, the dependency below is necessary:
```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

### Resources Directory
- Thymeleaf will look for files within the resources directory of the project.
- Even if your files are in this directory, they must be stored accordingly to work:
```
|resources
    |static
        |css
            .css
        |js
            .js
        |images
            .png/.jpeg/etc...
    |templates
        .html
```
- Naming is very important, because if the files are not named accordingly then Thymeleaf will not be able to find them.
- Note that the templates directory is within resources ALONE and not within static like the others.

### Controllers
- Thymeleaf will require a controller to handle each page. This can be handled as shown below:
```java
@Controller
public class NavigationController {
    @GetMapping("/NAME_OF_ENDPOINT")
    public String getIndex() {
        return "HTML_FILE_OMITTING_THE_EXTENSION";
    }
}
```
- Remember to omit the .html from the file you are connecting.
- In the HTML, when you are providing a link for things like a href=, you will also omit the .html extension.

### Linking Local HTML, JavaScript and CSS with Thymeleaf
- The first thing you will need to do is define the Thymeleaf "th" in every HTML file that will use Thymeleaf. This is done within the html tag that encompasses the file.
```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
    </head>

    <body>
    </body>
</html>
```
- Linking JavaScript and CSS to your project requires:
```html
JavaScript:     <script th:src="@{/js/YOUR_JAVASCRIPT.js}"></script>
CSS:            <link rel="stylesheet" th:href="@{/css/YOUR_CSS.css}">            
```