package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class RunGame implements Serializable{
	private static final long serialVersionUID = 1L;
	private AnchorPane apane;
	private Scene scene;
	public Stage stage;
	private final double mainWidth;
	private final double mainHeight;
	private subScene s1;
	private subScene s2;
	private subScene s3;
	public MediaPlayer mPlayer;
	int count=1;
	private int page;
	RunGame runGame=this;
	public ArrayList<State> stateDeserializeList;
	public static ArrayList<State> stateList;
	
	public RunGame()
	{
		
		try {
			stateList=deserialize();
			if(stateList==null)
			{
				System.out.println("The list is Null");
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mainWidth=1050;
		this.mainHeight=700;
		this.displayGUI();
	}
	public void displayGUI()
	{
		apane=new AnchorPane();
		scene=new Scene(apane,mainWidth,mainHeight);
		stage=new Stage();
		stage.setTitle("Color Switch");
		stage.setScene(scene);
		backGround();
		logo();
		buttons();
		music();
		stage.getIcons().add(new Image("/application/Resources/titlelogo.png"));
	}
	public void buttons()
	{
		newGameButton();
		loadGameButton();
		creditButton();
		exitButton();
		helpButton();
		musicButton();
		ImageView iv1=new ImageView("/application/Resources/star.png");
		apane.getChildren().add(iv1);
		iv1.setLayoutX(50);
		iv1.setLayoutY(35);
		ImageView iv2=new ImageView("/application/Resources/star.png");
		apane.getChildren().add(iv2);
		iv2.setLayoutX(790);
		iv2.setLayoutY(35);
		scene();
	}
	public void musicButton()
	{
		button b1=new button("/application/Resources/sound1.jpg","/application/Resources/sound1.jpg");
		apane.getChildren().add(b1);
		b1.setLayoutX(940);
		b1.setLayoutY(600);
		
		b1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent a)
			{
				if(count==1)
				{
					mPlayer.pause();
					count=0;
					ImageView imageView=new ImageView("/application/Resources/sound3.jpg");
					b1.setGraphic(imageView);
				}
				else if(count==0)
				{
					ImageView imageView=new ImageView("/application/Resources/sound1.jpg");
					b1.setGraphic(imageView);
					mPlayer.play();
					count=1;
				}
			}
		});
		b1.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent e)
			{
				ImageView imageView=new ImageView("/application/Resources/sound1.jpg");
				b1.setGraphic(imageView);
			}
		
		});
		b1.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent e)
			{
				//ImageView imageView=new ImageView("/application/Resources/sound1.jpg");
				//b1.setGraphic(imageView);
			}
		
		});
	}
	public void scene()
	{
		s1=new subScene("h");
		s2=new subScene("s");
		s3=new subScene("h");
		apane.getChildren().add(s1);
		apane.getChildren().add(s2);
		apane.getChildren().add(s3);
		s3.addImage("/application/Resources/TurquoisePanel.jpg",25,150);
		s1.addImage("/application/Resources/YellowPanel1.jpg",100,150 );
		s3.addImage("/application/Resources/yellowpanel.png",540,150);
		s2.addImage("/application/Resources/PurplePanel.jpg",150,150);
	}
	public void logo()
	{	ImageView iv1=new ImageView("/application/Resources/C.jpg");
		iv1.setLayoutX(280);
		iv1.setLayoutY(40);
		apane.getChildren().add(iv1);
		ImageView iv2=new ImageView("/application/Resources/O1.png");
		iv2.setLayoutX(380);
		iv2.setLayoutY(40);
		apane.getChildren().add(iv2);
		ImageView iv3=new ImageView("/application/Resources/L.jpg");
		iv3.setLayoutX(480);
		iv3.setLayoutY(50);
		apane.getChildren().add(iv3);
		ImageView iv4=new ImageView("/application/Resources/O1.png");
		iv4.setLayoutX(578);
		iv4.setLayoutY(40);
		apane.getChildren().add(iv4);
		ImageView iv5=new ImageView("/application/Resources/R.jpg");
		iv5.setLayoutX(668);
		iv5.setLayoutY(50);
		apane.getChildren().add(iv5);
		ImageView iv6=new ImageView("/application/Resources/Switch.jpeg");
		apane.getChildren().add(iv6);
		iv6.setLayoutX(280);
		iv6.setLayoutY(130);
		iv6.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent a)
			{
				iv6.setEffect(new Glow());
			}
		});
		iv6.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent a)
			{
				iv6.setEffect(null);
			}
		});
		rotate(iv2,iv4);
	}
	public void rotate(ImageView v1,ImageView v2)
	{
		RotateTransition rTrans = new RotateTransition(Duration.millis(3000),v1);
		RotateTransition rTrans1 = new RotateTransition(Duration.millis(3000),v2);
        rTrans.setByAngle(-360);
        rTrans1.setByAngle(+360);
        rTrans.setCycleCount(Animation.INDEFINITE);
        rTrans1.setCycleCount(Animation.INDEFINITE);
        rTrans.setInterpolator(Interpolator.LINEAR);
        rTrans1.setInterpolator(Interpolator.LINEAR);
        rTrans.play();
        rTrans1.play();
		
	}
	public void music()
	{  
		URL pathString=getClass().getResource("/application/Resources/audio.mp3");
		Media media=new Media(pathString.toString());
		mPlayer=new MediaPlayer(media);
		MediaView mediaView = new MediaView(mPlayer);
        apane.getChildren().add(mediaView);
        mPlayer.setOnEndOfMedia(new Runnable() {
        	public void run()
        	{
        		mPlayer.seek(Duration.ZERO);
        	}
        });
        mPlayer.play();
        //mPlayer.pause();
	}
	public Stage getStage()
	{
		return this.stage;
	}
	private void backGround()
	{
		BackgroundFill bFill=new BackgroundFill(Color.web("#292929"),CornerRadii.EMPTY,Insets.EMPTY);
		apane.setBackground(new Background(bFill));
	}
	public void newGameButton() 
	{
		button b1=new button("/application/Resources/start.jpg","/application/Resources/start.jpg");
		b1.setLayoutX(480);
		b1.setLayoutY(280);
		apane.getChildren().add(b1);
		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent a)
			{
				RunningGame runningGame=new RunningGame(runGame,null);
			}
		});

	}
	private void loadGameButton()
	{
		button b1=new button("/application/Resources/yellow2.png","/application/Resources/yellow.png");
		b1.setLayoutX(100);
		b1.setLayoutY(280);
		apane.getChildren().add(b1);
		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent a)
			{	
				s1.moveScene1();
				loadGameButton1();
			}
		});
		
	}
	public void loadGameButton1() {
		ArrayList<button1> list=new ArrayList<button1>();
		Group root = new Group();
		s1.setRoot(root);
		ScrollBar sc = new ScrollBar();
		sc.setOrientation(Orientation.VERTICAL);
		VBox vb = new VBox();
		try {
			stateDeserializeList=deserialize();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<stateDeserializeList.size();i++)
		{
			button1 b1=new button1("Saved Game-"+ Integer.toString(i+1));
			vb.getChildren().add(b1);
			list.add(b1);
			int test=i;
			b1.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent a)
				{
					RunningGame runningGame=new RunningGame(runGame, stateDeserializeList.get(test));
					s1.moveScene2();
					s1.aPane.getChildren().remove(root);

				}
			});
		}
		sc.setMax(stateDeserializeList.size()*50);
		root.setLayoutX(600);
		root.setLayoutY(200);
		sc.setLayoutX(300);
		root.getChildren().addAll(vb, sc);
		sc.valueProperty().addListener(new ChangeListener<Number>() {
		            public void changed(ObservableValue<? extends Number> ov,
		                Number old_val, Number new_val) {
		                    vb.setLayoutY(-new_val.doubleValue());
		            }
		        });
		s1.aPane.getChildren().add(root);
		button1 b=new button1("Remove All");
		b.setLayoutX(220);
		b.setLayoutY(330);
		b.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent a)
			{
				s1.aPane.getChildren().remove(root);
				stateList=new ArrayList<State>();
				try {
					serialize(stateList);
					try {
						stateList=deserialize();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		s1.aPane.getChildren().add(b);
		
	}	
	private void helpButton()
	{
		button b1=new button("/application/Resources/purple2.png","/application/Resources/purple.png");
		b1.setLayoutX(100);
		b1.setLayoutY(480);
		apane.getChildren().add(b1);
		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent a)
			{	s3.moveScene1();
			}
		});
		
	}
	private void creditButton()
	{
		button b1=new button("/application/Resources/Pink2.png","/application/Resources/Pink.png");
		b1.setLayoutX(100);
		b1.setLayoutY(380);
		apane.getChildren().add(b1);
		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent a)
			{	s2.moveScene1();
			}
		});
	}
	private void exitButton()
	{
		button b1=new button("/application/Resources/torquoise1_2.png","/application/Resources/torquoise1.png");
		b1.setLayoutX(100);
		b1.setLayoutY(580);
		apane.getChildren().add(b1);
		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent a)
			{
				stage.close();
			}
		});
	}
	
	public void serialize(ArrayList<State> s) throws FileNotFoundException, IOException {
		ArrayList<State> s1 = s;
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("file.txt"));
			out.writeObject(s1);
		}
		finally {
			out.close();
//			System.out.println("=============================");
		}
		
	}
	public ArrayList<State> deserialize() throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<State> s1 ;
		ObjectInputStream in = null;
//		State s2;
		
		try {
			in= new ObjectInputStream(new FileInputStream("file.txt"));
			s1=(ArrayList<State>) in.readObject();

		}
		finally {
			in.close();
			
		}
		return s1;
	}
	
	
	
}
