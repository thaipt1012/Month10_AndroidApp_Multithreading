package thai.DuaNgua;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Thaipt_54_Lab11_DuaNgua extends Activity {
	ProgressBar bar1;
	ProgressBar bar2;
	
	TextView kqChungCuoc;
	TextView NhatKi1;
	TextView NhatKi2;
	TextView TienDo1;
	TextView TienDo2;
	
	boolean isRunning1 = false;
	boolean isRunning2 = false;
	final int MAX_LEN =  400;
	final int MAX_SPEED = 20;
	boolean winner = false;
	
	
	Handler handler1 = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			String x1;
			String TocDo1TraVe = (String)msg.obj;
			//Hien gia tri tra ve tu Backgruond ra TextView1
			int speed1 = Integer.parseInt(TocDo1TraVe);
			bar1.incrementProgressBy(speed1);

			//Kiem tra xem ngua da ve dich chua
			if (bar1.getProgress() >= bar1.getMax())
			{
				x1 = MAX_LEN + "/" + MAX_LEN;
				TienDo1.setText(x1);
				NhatKi1.append("1: " + TocDo1TraVe + " - " + x1 + "\n");
				isRunning1 = false;
				if (winner == false)
				{
					kqChungCuoc.setText("Horse 1 wins");
					winner = true;
				}
			}
			else
			{
				x1 = bar1.getProgress() + "/" + MAX_LEN;
				TienDo1.setText(x1);
				NhatKi1.append("1: " + TocDo1TraVe + " - " + x1 + "\n");
			}
		}
	}; //handler
	
	
	
	
	
	Handler handler2 = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			String x2;
			String TocDo2TraVe = (String)msg.obj;
			//Hien gia tri tra ve tu Backgruond ra TextView2
			int speed2 = Integer.parseInt(TocDo2TraVe);
			bar2.incrementProgressBy(speed2);

			//Kiem tra xem ngua da ve dich chua
			if (bar2.getProgress() >= bar2.getMax())
			{
				x2 = MAX_LEN + "/" + MAX_LEN;
				TienDo2.setText(x2);
				NhatKi2.append("2: " + TocDo2TraVe + " - " + x2 + "\n");
				isRunning2 = false;
				if (winner == false)
				{
					kqChungCuoc.setText("Horse 2 wins");
					winner = true;
				}
			}
			else
			{
				x2 = bar2.getProgress() + "/" + MAX_LEN;
				TienDo2.setText(x2);
				NhatKi2.append("2: " + TocDo2TraVe + " - " + x2 + "\n");
			}
		}
	}; //handler
	
	
	
	
	@Override
	public void onCreate(Bundle icicle)
	{

		super.onCreate(icicle);
		setContentView(R.layout.main);
		//TextView hein thi kq
		kqChungCuoc = (TextView)findViewById(R.id.TextView3);
		kqChungCuoc.setText("winner is ...|");
		
		//Ngua so 1
		bar1 = (ProgressBar) findViewById(R.id.progress1);
		bar1.setMax(MAX_LEN);
		bar1.setProgress(0);
		TienDo1 = (TextView)findViewById(R.id.TextView1);
		NhatKi1 = (TextView)findViewById(R.id.TextView4);
		TienDo1.setText(bar1.getProgress() + "/" + MAX_LEN);
		NhatKi1.setText("Horse 1" + "\n");
		
		//Ngua so 2
		bar2 = (ProgressBar) findViewById(R.id.progress2);
		bar2.setMax(MAX_LEN);
		bar2.setProgress(0);
		TienDo2 = (TextView)findViewById(R.id.TextView2);
		NhatKi2 = (TextView)findViewById(R.id.TextView5);
		TienDo2.setText(bar2.getProgress() + "/" + MAX_LEN);
		NhatKi2.setText("Horse 2" + "\n");
	}//onCreate


	public void onStop()
	{
		super.onStop();
		isRunning1 = false;
	}
	
	public void onStart()
	{
		super.onStart();
//Thread 1
		Thread background1 = new Thread(new Runnable() 
		{
			public void run()
			{
				try
				{
					while (isRunning1)
					{
						//1. Dung thread trong 1s
						Thread.sleep(1000);
						
						//2. Tao 1 so ngau nhien <=3
						Random rnd1 = new Random();
						int TocDo1 = (int) rnd1.nextInt(MAX_SPEED);
						
						//3. Gui tra kq ve cho Handler
						String data = Integer.toString(TocDo1);
						Message msg = handler1.obtainMessage(1, (String)data);
						
						if (isRunning1)
						{
							handler1.sendMessage(msg);
						}
					}
				}
				catch (Throwable t)
				{
					//just end the background thread
				}
			}//run
		});//background

		
//Thread 2
		Thread background2 = new Thread(new Runnable() 
		{
			public void run()
			{
				try
				{
					
					while (isRunning2)
					{
						//1. Dung thread trong 1s
						Thread.sleep(1000);
						
						//2. Tao 1 so ngau nhien <=3
						Random rnd2 = new Random();
						int TocDo2 = (int) rnd2.nextInt(MAX_SPEED);
						
						//3. Gui tra kq ve cho Handler
						String data = Integer.toString(TocDo2);
						Message msg = handler2.obtainMessage(1, (String)data);
						
						if (isRunning2)
						{
							handler2.sendMessage(msg);
						}
					}
				}
				catch (Throwable t)
				{
					//just end the background thread
				}
			}//run
		});//background
		
		isRunning1 = true;
		background1.start();
		isRunning2 = true;
		background2.start();
	}//onStart
} //class
