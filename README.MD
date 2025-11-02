# Programmeringsuppgift 3
## Bygg
./gradlew build
## Kör
./gradlew run

### Modifiera tester
I App.java kan ni ändra på på datasettings med att testa olika typer av arraysizes (rad 14), range på nummer att sortera (rad 15, 16) och vid vilket djup quicksort ska gå över till heapsort (rad 17).


```java
        // DATA SETTINGS
        int dataSize = 900_000_000;
        int maxValue = 900_000_000;
        int minValue = 0;
        int depthSwitch = (int) (3 * Math.floor(Math.log(dataSize) / Math.log(2)));
        // int depthSwitch = 0;
```