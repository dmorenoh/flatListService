import io.flatservice.controller.FlattenerController;
import io.flatservice.controller.ServerApp;
import io.flatservice.service.FlattenerService;

public class App {

    public static void main(String[] args) {
        ServerApp serverApp = new ServerApp(new FlattenerController(new FlattenerService()));
        serverApp.start(7000);
    }
}
