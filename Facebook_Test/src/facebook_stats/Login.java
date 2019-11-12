package facebook_stats;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Login extends Application {

  private Scene scene;

  public void start(Stage primaryStage) throws Exception {

    // create the scene
    primaryStage.setTitle("Facebook Login per Statistiche");
    String appId = "430339450830428";
    String appSecret = "e1ac532cb85a7c392ef6f39b1d7014a3";
    Browser facebookBrowser = new Browser(appId, appSecret);
    scene = new Scene(facebookBrowser, 900, 600, Color.web("#666970"));
    primaryStage.setScene(scene);
    primaryStage.show();
    facebookBrowser.showLogin();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
