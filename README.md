# mongo-application
A simple Mongo DB Web Application to run on AWS

### Build steps

```bash
sudo dnf -y install git java-21-amazon-corretto-devel
git clone https://github.com/ableasdale/mongo-application.git
cd mongo-application
chmod +x gradlew
# may need to sudo reboot && chmod a+x gradlew
./gradlew run
```

If there have been no issues, you should eventually see:

```bash
[2025-04-07 14:20:59,795] [INFO] Starting HTTP Server on port 9992 - use CTRL^C to stop the server
```

`Ctrl+C` will activate the shutdown hook and drop you back to a prompt.

Now let's make sure we can create a self-contained jar for the application:

```bash
./gradlew shadowJar
```

As soon as you see the `BUILD SUCCESSFUL` report from Gradle, test the jar by running:

```bash
cd build/libs/
java -jar mongo-application-1.0-SNAPSHOT-all.jar
```

Ctrl+C when you see: 

```bash
[2025-04-07 14:58:23,044] [INFO] Starting HTTP Server on port 9992 - use CTRL^C to stop the server
```
