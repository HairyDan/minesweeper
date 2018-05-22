package minesweeper;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainWindow extends Application {
	
	int xTiles = 4, yTiles = 4, bombsNum = 3, tileSize = 50, WINDOW_WIDTH = 500, WINDOW_HEIGHT = 500;
	public Tile[][] gameGrid;
	int tileWidth = WINDOW_WIDTH/xTiles;
	int tileHeight = WINDOW_HEIGHT/yTiles;
	
	Pane root;


	@Override
	public void start(Stage primaryStage) throws Exception {

		root = new Pane();
		
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);		
		
		
		primaryStage.setTitle("Minesweeper");
		SetupGrid();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void SetupGrid() {
		gameGrid = new Tile[xTiles][yTiles];
		/*for (Tile[] tileVec : gameGrid) {
			for (Tile tile : tileVec) {
				
			}
		}*/
		for (int i=0; i<xTiles; i++) {
			for (int j=0; j<yTiles; j++) {
				gameGrid[i][j] = new Tile(i,j);
			}
		}
		//root.getChildren().add(new Rectangle(30,30));
		//add bombs
		for(int i=0; i<bombsNum; i++) {
			//System.out.println(new Double(Math.random()*xTiles).intValue());
			gameGrid[new Double(Math.random()*xTiles).intValue()][new Double(Math.random()*yTiles).intValue()].hasBomb = true;
		}
		DrawTiles();
		//setup adjacent nums
	}
	
	public void DrawTiles() {
		for (Tile[] tileVec : gameGrid) {
			for (Tile tile : tileVec) {
				//System.out.println(tile.x*tileWidth-1+tileWidth);
				Rectangle rect = new Rectangle(tile.x*tileWidth+1,tile.y*tileHeight+1, tileWidth-2, tileWidth-2);
				tile.rect = rect;
				rect.setOnMouseClicked(new EventHandler<MouseEvent>()
		        {
		            @Override
		            public void handle(MouseEvent t) {
		                rect.setFill(Color.RED);
		            }
		        });

				root.getChildren().add(rect);
			}
		}
	}

	

	public static void main(String[] args) {
		launch(args);
	}
	
	//CLASSES
	
	private class Tile extends StackPane{
		private int x, y;
		private boolean hasBomb, flagged;
		private Rectangle rect;
				
		public Tile(int inX, int inY) {
			this.x = inX;
			this.y = inY;
			this.rect = rect;
		}
	}
}