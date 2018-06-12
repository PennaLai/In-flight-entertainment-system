package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.model.User;
import com.util.JsonLoader;
import javafx.fxml.FXML;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import static com.MainApp.mainUser;
import static com.util.DataLoader.getUser;
import static com.util.DataLoader.loadStarRelation;


public class LoginController {
    public JFXTextField user_name;
    public JFXPasswordField user_pw;

    public JFXButton Login;
    public JFXButton Sign;
    public Text user_name_warning;
    public Text user_pw_warning;
    private Stage dialogStage;
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp){this.mainApp=mainApp;}
    public void setDialogStage(Stage dialogStage){
        this.dialogStage=dialogStage;
    }
    public Stage getDialogStage() { return dialogStage; }

    @FXML
    private JFXButton close;

    @FXML
    private void initialize(){
    }

    public void loadLanguage(String language){
        JSONObject jsonObject = JsonLoader.getJsonValue(language,"loginDialog");
        try {
            assert jsonObject != null;
            Login.setText(jsonObject.getString("login"));
            Sign.setText(jsonObject.getString("sign"));
            user_name.setPromptText(jsonObject.getString("username"));
            user_pw.setPromptText(jsonObject.getString("password"));
        } catch (JSONException | NullPointerException e){
            e.printStackTrace();
        }
    }

    /* *  这个方法用来监听关闭，以便于可以再次代开login.
     * @author PennaLia
     * @date 2018/5/29 0:48
     * @param
     * @return
     */
    public  void setCloseAction(){
        dialogStage.setOnCloseRequest(windowEvent -> {
            mainApp.setOpenLogin(false);
        });
    }

    @FXML
    public void handleClose(){
        dialogStage.close();
        mainApp.setOpenLogin(false);
    }

    public void pressLoginButton(MouseEvent mouseEvent) {
        Login.setButtonType(JFXButton.ButtonType.FLAT);
    }

    public void releaseLoginButton(MouseEvent mouseEvent) {
        Login.setButtonType(JFXButton.ButtonType.RAISED);
    }

    public void clickLoginButton(MouseEvent mouseEvent) {
        String user_nameText = user_name.getText();
        String user_pwText = user_pw.getText();
        User user = getUser(user_nameText);
        if(user==null){
            user_name_warning.setText("Username not exists");
        } else {
            if (!user.verifyPassword(user_pwText)){
                user_pw_warning.setText("Wrong password");
            } else {
                dialogStage.close();
                mainUser = user;
                loadStarRelation();
                if (mainUser.getIfVIP() == 1){
                    mainApp.getRootLayoutController().getDrawerContentController().beHuiyuan();
                }
                if (mainUser.getIfAdmin() == 1){
                    MainApp.Admin = true;
                    mainApp.getRootLayoutController().settingPageController.getWebScrapingButton().setDisable(false);
                }
            }
        }
    }


    public void pressSignButton(MouseEvent mouseEvent) {
        Sign.setButtonType(JFXButton.ButtonType.FLAT);
    }

    public void releaseSignButton(MouseEvent mouseEvent) {
        Sign.setButtonType(JFXButton.ButtonType.RAISED);
    }

    public void getUsernameChanged(InputMethodEvent inputMethodEvent) {
        String input = user_name.getText();
//        System.err.println(input);
    }

    public void getPwChanged(InputMethodEvent inputMethodEvent) {
        user_pw_warning.setText("");
    }


    public void getUsernameTyped(KeyEvent keyEvent) {
        String input = user_name.getText()+keyEvent.getCharacter();
//        System.err.println(input);
        if(getUser(input) != null) {
            Sign.setButtonType(JFXButton.ButtonType.FLAT);
            Sign.setDisable(true);
//            user_name_warning.setText("Username exists");
        } else {
            Sign.setButtonType(JFXButton.ButtonType.RAISED);
            Sign.setDisable(false);
            user_name_warning.setText("");
        }
    }

    public void getPwTyped(KeyEvent keyEvent) {
        user_pw_warning.setText("");
    }

    public void clickSignButton(MouseEvent mouseEvent) {

    }
}
