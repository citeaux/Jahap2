package org.jahap;




import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.jahap.gui.LogonGuiController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<JahapApplication.StageReadyEvent> {
    private final String applicationTitle;
    private final FxWeaver fxWeaver;
    private MainEventResult mEv;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            FxWeaver fxWeaver) {
        this.applicationTitle = applicationTitle;

        this.fxWeaver = fxWeaver;

    }

    @Override
    public void onApplicationEvent(JahapApplication.StageReadyEvent event ) {
        Stage stage = event.getStage();


        stage.setScene(new Scene(fxWeaver.loadView(LogonGuiController.class)));
        stage.setTitle(applicationTitle);

        fxWeaver.loadController(LogonGuiController.class).init(event.getmEv());
        stage.show();
    }
}