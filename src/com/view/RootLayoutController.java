package com.view;

import com.MainApp;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import com.view.settingpage.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.IOException;

public class RootLayoutController {
//主平面，暂时没什么用
    @FXML
    private AnchorPane mainPane;
    //导航栏
    @FXML
    private AnchorPane mainBar;
    //搜索栏
    @FXML
    private HBox searchbox;
    //搜索栏的文本
    @FXML
    private JFXTextField searchfeild;

    //这是三根下划线
    @FXML
    private Line homeline;

    @FXML
    private Line playline;

    @FXML
    private Line musicline;
//主界面
    @FXML
    private AnchorPane homepage;
//抽屉的开关
    @FXML
    private JFXHamburger hamburger;
//抽屉
    @FXML
    private JFXDrawer drawer;
//设置界面
    @FXML
    private AnchorPane SettingPane;

    @FXML
    private AnchorPane searchpane;//搜索的平面


    private MainApp mainApp; //和主应用连接
    private HamburgerNextArrowBasicTransition transition;

    //抽屉栏
    private VBox box;
    //三个界面，放电影的，播放音乐的，播放视频的，要提前加载
    public AnchorPane homeP, musicP, playerP;
    //三个界面的控制器
    public MusicPageController musicPageController;
    public HomePageController HomePageController;
    public PlayerPageController playerPageController;

    //几个设置界面
    public AnchorPane setting,aboutus,theme,money,timing,edit;
    public FlowPane collection;//收藏界面
    //设置界面的控制器
    SettingPageController settingPageController;
    AboutUsController aboutUsController;
    ThemeController themeController;
    MoneyController moneyController;
    TimingController timingController;
    CollectionController collectionController;
    EditController editController;
    DrawerContentController drawerContentController;
    //搜索界面的controler
    MovieTableViewController movieTableViewController;

    public void loadLanguage(String language){
        settingPageController.loadLanguage(language);
        aboutUsController.loadLanguage(language);
        themeController.loadLanguage(language);
        moneyController.loadLanguage(language);
        timingController.loadLanguage(language);
        collectionController.loadLanguage(language);
        editController.loadLanguage(language);
        drawerContentController.loadLanguage(language);
        movieTableViewController.loadLanguage(language);
    }


    /**
     * 设置主应用，连接到主界面
     * @param app
     */
    public void setMainApp(MainApp app) {
        mainApp = app;
    }

    public MainApp getMainApp() {
        return mainApp;
    }
    public void setVBox(VBox box) {
        this.box = box;
    }
    public VBox getVBox(){return box;}
    public JFXDrawer getDrawer(){return drawer;}
    public DrawerContentController getDrawerContentController(){return drawerContentController;}
   /* *  用来替换主界面，当想切换哪个界面的时候就把界面赋值给homepage
    * @author PennaLia
    * @date 2018/6/5 14:18
    * @param
    * @return
    */
    public void setHomePage(AnchorPane page) {
        homepage.getChildren().setAll(page);
    }
    /* *  用来替换设置界面
     * @author PennaLia
     * @date 2018/6/5 14:19
     * @param
     * @return
     */
    public void setSettingPane(AnchorPane pane) {
        SettingPane.getChildren().setAll(pane);
    }

    public void setSettingVisible() {
        getSettingPane().setVisible(true);
    }//让设置界面可见

    public void homePageNotSee() {
        homepage.setVisible(false);
    }//让主界面可见
    public void homePageSee() {
        homepage.setVisible(true);
    }//让主界面不可见

    public AnchorPane getHomepage() {
        return homepage;
    }//返回主界面
    public AnchorPane getSettingPane() {
        return SettingPane;
    }//返回设置界面
    public AnchorPane getMainBar(){return  mainBar;}
    public void searchPaneVisible(boolean a){
        searchpane.setVisible(a);
    }
    /* *  初始化界面
      * @author PennaLia
      * @date 2018/6/5 14:20
      * @param
      * @return
      */
    @FXML
    private void initialize() {
        try {
            transition = new HamburgerNextArrowBasicTransition(hamburger);
            transition.setRate(-1);

            //加载
            initDrawerContent();
            initHomePage();
            //只是预加载
            initMusicPage();
            initPlayerPage();
            //设置界面的预加载
            initSettingPage();
            initAboutUs();
            initTheme();
            initMoney();
            initTiming();
            initEdit();
            initSearchTree();
            initCollection();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    /* *  初始化抽屉栏.
     * @author PennaLia
     * @date 2018/5/29 1:08
     * @param
     * @return
     */
    public void initDrawerContent() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/DrawerContent.fxml"));
            this.setVBox(loader.load());
            drawerContentController = loader.getController();
            drawerContentController.setRootLayoutController(this);
            drawer.setSidePane(box);//把box设置给抽屉
            drawer.setVisible(false);//一开始抽屉栏不可见，否则会遮挡
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  初始化主页面，就是默认主页面时文件页面
     * @author PennaLia
     * @date 2018/5/29 1:18
     * @param
     * @return
     */
    public void initHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/HomePage.fxml"));
            homeP = loader.load();
             HomePageController = loader.getController();
            HomePageController.setRootLayoutController(this);
            setHomePage(homeP);
            homeline.setVisible(true);//一开始就是可见的
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* *  初始化音乐界面
     * @author PennaLia
     * @date 2018/6/5 14:22
     * @param
     * @return
     */
    public void initMusicPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/MusicPage.fxml"));
            musicP = loader.load();
             musicPageController = loader.getController();
            musicPageController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *  初始化播放界面
     * @author PennaLia
     * @date 2018/6/5 14:22
     * @param
     * @return
     */
    public void initPlayerPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/PlayerPage.fxml"));
            playerP = loader.load();
            playerPageController = loader.getController();
            playerPageController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* *  初始化设置界面
     * @author PennaLia
     * @date 2018/6/5 14:22
     * @param
     * @return
     */
    public void initSettingPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/SettingPage.fxml"));
            setting = loader.load();
            settingPageController = loader.getController();
            settingPageController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initAboutUs(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/AboutUs.fxml"));
            aboutus = loader.load();
            aboutUsController = loader.getController();
            aboutUsController.setRootLayoutController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initTheme(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/Theme.fxml"));
            theme = loader.load();
            themeController= loader.getController();
            themeController.setRootLayoutController(this);
            //这边要想办法得到部件的颜色信息
            themeController.getLeftbarUp().setValue(Color.web("#F08080"));
            themeController.getLeftbar().setValue(Color.web("#ee5253"));
            themeController.getUpbar().setValue(Color.web("C62F2F"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initMoney(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/Money.fxml"));
            money= loader.load();
            moneyController= loader.getController();
            moneyController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initTiming(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/Timing.fxml"));
            timing= loader.load();
            timingController= loader.getController();
            timingController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initCollection(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/Collection.fxml"));
            collection= loader.load();
            collectionController= loader.getController();
            collectionController.setRootLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initEdit(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/settingpage/Edit.fxml"));
            edit= loader.load();
            editController= loader.getController();
            editController.setRootLayoutController(this);
            //界面初始化用户信息
        editController.getTextname().setText(this.getDrawerContentController().getUserName());
        editController.getTextsign().setText(this.getDrawerContentController().getUserSign());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initSearchTree(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/MovieTableView.fxml"));
            AnchorPane pane= loader.load();
            searchpane.getChildren().addAll(pane);
            movieTableViewController= loader.getController();
            movieTableViewController.setRootLayoutController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* *  抽屉开关的响应事件
     * @author PennaLia
     * @date 2018/6/5 14:24
     * @param
     * @return
     */
    @FXML
    private void handlehamburger() {
        transition.setRate(transition.getRate() * -1);
        transition.play();
        if (drawer.isOpened()) {
            drawer.close();
            homePageSee();
            drawer.setVisible(false);
            getSettingPane().setVisible(false);
        } else {
            drawer.setVisible(true);
            drawer.open();
        }
    }

    /* * 切换到主界面的时候下划线可见
     * @author PennaLia
     * @date 2018/6/5 14:25
     * @param
     * @return
     */
    @FXML
    private void seeHomepage() {
        setHomePage(homeP);
        musicline.setVisible(false);
        playline.setVisible(false);
        homeline.setVisible(true);
    }
    /* *  播放界面下划线可见
     * @author PennaLia
     * @date 2018/6/5 14:25
     * @param
     * @return
     */
    @FXML
    private void seePlaypage() {
        setHomePage(playerP);
        musicline.setVisible(false);
        playline.setVisible(true);
        homeline.setVisible(false);

    }
    /* *  音乐界面下划线可见
     * @author PennaLia
     * @date 2018/6/5 14:25
     * @param
     * @return
     */
    @FXML
    private void seeMusicpage() {
        setHomePage(musicP);
        musicline.setVisible(true);
        playline.setVisible(false);
        homeline.setVisible(false);
    }
    /* * 关闭应用
     * @author PennaLia
     * @date 2018/6/5 14:26
     * @param
     * @return
     */
    @FXML
    private void handleClose() {
        mainApp.closeWindows();
    }

    @FXML
    private void handleSearch(KeyEvent event){
        KeyCode kc= event.getCode();
        if (kc==(KeyCode.ENTER)){
            searchPaneVisible(true);
        }
    }
    @FXML
    private void handleSearchClick(){
            searchPaneVisible(true);
    }


    /* * 用于设置夜间模式
     * @author PennaLia
     * @date 2018/6/6 8:33
     * @param
     * @return
     */
    private String DarkCSS = this.getClass().getResource("DarkTheme.css").toExternalForm(),
            DefaultCSS = this.getClass().getResource("DefaultTheme.css").toExternalForm();
    
    public void ToNight(){
        ObservableList<String> styleSheets = mainPane.getStylesheets();
        styleSheets.remove(DefaultCSS);
        if(!styleSheets.contains(DarkCSS))
            styleSheets.add(DarkCSS);
    }
    public void ToDefault(){
        ObservableList<String> styleSheets = mainPane.getStylesheets();
        styleSheets.remove(DarkCSS);
        if(!styleSheets.contains(DefaultCSS))
            styleSheets.add(DefaultCSS);
    }

    public void changeLeftColor(String color){
        drawerContentController.getUserBackPane().setStyle("-fx-background-color:"+"#"+color);
        aboutUsController.getAboutusuppane().setStyle("-fx-background-color:"+"#"+color);
        editController.getEdituppane().setStyle("-fx-background-color:"+"#"+color);
        moneyController.getMoneyuppane().setStyle("-fx-background-color:"+"#"+color);
        settingPageController.getSettinguppane().setStyle("-fx-background-color:"+"#"+color);
        themeController.getThemeuppane().setStyle("-fx-background-color:"+"#"+color);
        timingController.getTiminguppane().setStyle("-fx-background-color:"+"#"+color);
        musicPageController.getMusicPane().setStyle("-fx-background-color:"+"#"+color);
    }

}
