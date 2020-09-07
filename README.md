<!DOCTYPE html>
<html>
<body>
    <a href="https://craftlife.com.br">
        <img align="right" src="https://i.imgur.com/ABJeWPY.png" height="220" width="220">
    </a>
    <h1>Minerva</h1>
    <p><b>a modular spigot plugin based on the eureka framework</b></p>
    <hr>
    <h2>Usage</h2>
</body>
</html>




First, make sure the repository is in your  `pom.xml`:

```xml
<repository>
 <id>craftlife</id>
 <url>https://artifactory.craftlife.com.br/artifactory/libs-release-local</url>
</repository>
```

Then, make sure the dependency is in your `pom.xml`:

```xml
<dependency>
    <groupId>br.com.craftlife</groupId>
    <artifactId>minerva-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>br.com.craftlife</groupId>
    <artifactId>minerva-impl</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
```



to create a module define the `module` annotation with the parameter of the module name:

```java
@Module("home")
public class MinervaHomeModule  {

}

the system automatically loads and records all events with the `@EurekaListener` annotation and all commands that extends `basecommand`


```
### Creating commands

To create a command, just create a class similar to this example:

```java
@CommandAlias("res|residence|resadmin")
public class ResidenceCommand extends BaseCommand {

    @Default
    @Subcommand("list")
    @Syntax("<+tag> [page] or [page]")
    @CommandCompletion("@residencelist")
    @Description("Lists all of your or another players residences.")
    public static void onList(Player player, String[] args) {
        if (args.length == 0) {
            EmpireUser user = EmpireUser.getUser(player);

            Util.sendMsg(player, "&bYou currently have &a" + user.numRes +
                "&b/&a" + user.maxRes + "&b Residences.");

            Residences.listGlobalResidences(player);
        } else {
            if (args[0].startsWith("+")) {
                int page = (args.length == 2 && NumUtil.isInteger(args[1])) ? Integer.parseInt(args[1]) : 1;
                TagManager.listTaggedResidences(player, args[0].substring(1), page);
            } else {
                Residences.listGlobalResidences(player, args[0]);
            }
        }
    }
}
```

More examples are available in [ACF](https://github.com/aikar/commands).


### Creating listeners

To create a listener, just create a class similar to this example:

```java
@EurekaListener
public class FirstLoginListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    // your code here
    }

}
```
