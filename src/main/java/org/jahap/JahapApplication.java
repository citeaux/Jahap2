package org.jahap;

import javafx.application.Application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

public class JahapApplication extends Application implements MainEventListener{
    Logger log = LoggerFactory.getLogger(MainApp.class);
    private ConfigurableApplicationContext applicationContext;
    private MainEventResult mEv;
    ResourcenManager jk;
    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(MainApp.class).run();



        log.debug("Function entry start");
       mEv = new MainEventResult();
        mEv.addIDListener(this);



        /*


       String   fxmlFile = "fxml/LogonGui.fxml";

        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(fxmlFile));
	log.debug("after loader");


        Parent rootNode = (Parent) loader.load();

        //log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode);

         LogonGuiController controller= loader.<LogonGuiController>getController();
       controller.init(mEv);

        stage.setScene(scene);

     stage.show();

       log.debug("Function exit start");
         */


    }

    @Override
    public void start(Stage stage) {
        log.debug("Start");
        applicationContext.publishEvent(new StageReadyEvent(stage, mEv));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    static class StageReadyEvent extends ApplicationEvent {
        private MainEventResult mEv;

        public StageReadyEvent(Stage stage, MainEventResult mEv) {
            super(stage);
            this.mEv=mEv;
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
        public MainEventResult getmEv() {
            return mEv;
        }
    }



    public void idinfo(MainEvent e) {

        log.debug("Function entry idinfo ");
        // ResourcenManager
        Resourcen kk= new Resourcen();
        jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(e.isIsSuccessFull()==true){


            Stage stage = new Stage();
            String  fxmlFile = "/fxml/Maingui.fxml";

            URL fxmlURL = classLoader.getResource("fxml/Maingui.fxml");
            //log.debug("Loading FXML for main view from: {}", fxmlFile);

            // fxWeaver.loadController(DialogController.class).show()
            FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("i18n.Maingui"));
            AnchorPane page = null;

            try {
                page = (AnchorPane) loader.load();
                //log.debug("Showing JFX scene");
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }


            Scene scene = new Scene(page);


            stage.setScene(scene);
            stage.show();
            log.debug("Function exit idinfo");
        }
    }
}
