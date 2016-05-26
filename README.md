# Go Transit API

A JSON API that wraps [GTFS](https://developers.google.com/transit/gtfs/reference) files provided by Go Transit:

[http://www.gotransit.com/publicroot/en/schedules/GTFSdownload.aspx](http://www.gotransit.com/publicroot/en/schedules/GTFSdownload.aspx)

## Quick Start


### Run

```
./gradlew run
```

### Build

This project uses the [Gradle Shadow plugin](https://github.com/johnrengelman/shadow) to create fat jars.

```
./gradlew shadowJar
```

### Test

```
./gradlew test
```