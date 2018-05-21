package minesweeper;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		Rectangle rec = new Rectangle(40,40);
		rec.setX(20);
		rec.setY(20);
		root.getChildren().add(rec);
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
				System.out.println(tile.x*tileWidth-1+tileWidth);
				root.getChildren().add(new Rectangle(tile.x*tileWidth+1,tile.y*tileHeight+1, tileWidth-2, tileWidth-2));
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
				
		public Tile(int inX, int inY) {
			this.x = inX;
			this.y = inY;
		}
	}
}