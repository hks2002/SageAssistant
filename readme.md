# Getting Started

### This project contains two parts 
* srv(developed with java)
* web(is developed with vue/quasar)

### To develope the web, please read [README](web/README.md)

### If you want to deploy this app in standalone jar file:
* please build web first, then build srv, 
* before build srv, copy all files under ```web\dist\``` files to ```srv\src\main\resources\static\``` or ```srv\target\classes\static\```,
* you could make a symbol linked to them also, then you doesn't need to copy web dist files, it is done automaticly.

### When you get ```SageAssistant.jar```,then run ```java -jar SageAssistant.jar ``` in command.

```Crystal report doesn't support OpenJDK, it cannot found fonts when export pdf. So, please make sure you are using Oracle Jre runtime, and install the fonts first, if you plan to deploy it to Linux.```
```Don't forget open you firewall to allow the http 80 port````
