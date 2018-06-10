package com.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.svg.SVGGlyph;
import com.model.Movie;
import com.model.MovieType;
import com.view.viewModel.MovieItem;
import com.view.viewModel.MovieSortItem;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static com.MainApp.mainMovieTypes;
import static com.MainApp.mainMovies;
import static javafx.scene.layout.StackPane.setAlignment;

public class HomePageController {

    private static final String back_svg = "M482.759412 1015.324534a28.301931 28.301931 0 0 0 40.675137 0 30.065469 30.065469 0 0 0 0-41.727572L98.137587 541.530292h897.071669c15.900281 0 28.757038-13.226531 28.757038-29.52503s-12.856757-29.52503-28.728594-29.52503H98.137587l425.268518-432.06667a30.065469 30.065469 0 0 0 0-41.756015 28.216599 28.216599 0 0 0-40.675138 0L8.453175 491.127255a30.065469 30.065469 0 0 0 0 41.756015l303.669768 311.776351 170.664913 170.664913z";
    private static final String back_svg_2 = "M644.502803 291.160289 423.665139 512.001023 644.502803 732.838687c8.131191 8.131191 8.131191 21.315468 0 29.445636s-21.315468 8.131191-29.445636 0L379.497197 526.721283c-8.131191-8.131191-8.131191-21.312398 0-29.442566l235.55997-235.55997c8.131191-8.134261 21.315468-8.134261 29.445636 0C652.632971 269.848914 652.632971 283.030122 644.502803 291.160289z";
    @FXML
    private JFXScrollPane HomeScrollPane;

    @FXML
    private FlowPane flowpane;

    public JFXButton back =new JFXButton();
    ImageView imageView;

    private  RootLayoutController rootLayoutController;
    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController=rootLayoutController;
    }

    private void addMovieSortItem(){
        for (MovieType movieType : mainMovieTypes) {
            try {
                StackPane sort = movieType.getMovieSortItem_cn();
                movieType.handleClick(HomeScrollPane, back);
                flowpane.getChildren().add(sort);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void initialize(){
        addMovieSortItem();
        HomeScrollPane.setContent(flowpane);
        HomeScrollPane.setStyle("-fx-border-color: transparent;");
        //封面图
        imageView=new ImageView("resources/moviepage2.png");
        imageView.setFitWidth(640);
        imageView.setFitHeight(192);
        back.setPrefSize(35,35);
        back.setMinHeight(20);back.setMinWidth(20);
        back.setStyle("-fx-background-color:transparent;" +
                "-fx-background-radius: 50%;");
        SVGGlyph glyph = new SVGGlyph(-1,
                "test",
                back_svg_2,
                Color.WHITE);
        back.setGraphic(glyph);
        back.setVisible(false);
        back.setOnMouseClicked(event -> {
            HomeScrollPane.setContent(flowpane);
            back.setVisible(false);
        });
        HomeScrollPane.getTopBar().setAlignment(back, Pos.TOP_RIGHT);
        HomeScrollPane.getTopBar().getChildren().add(back);
        HomeScrollPane.getMainHeader().getChildren().add(imageView);
        HomeScrollPane.getCondensedHeader().setStyle("-fx-background-color: #FFFFFF;");

    }


        public void changeFlowContent(FlowPane flowPane){
        HomeScrollPane.setContent(flowPane);
        }
}




