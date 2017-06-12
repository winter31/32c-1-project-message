package music;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.petAnimalGUI;
import javazoom.jl.player.Player;

public class MusicThread2 extends Thread {
	private File f = new File("src\\musicsfiles");
	private FileInputStream fis;
	private Player play;
	static int x = 1;
	ArrayList<File> flist;
	ActionListener gg;
	petAnimalGUI g;

	public MusicThread2() {
	}

	@Override
	public void run() {
		nextMusic();
		super.run();
	}

	public File[] list1() {
		File[] result = f.listFiles();
		return result;
	}

	public void nextMusic() {
		x++;
		try {
			File[] mlist = list1();
			for (File ff : mlist) {
				flist = new ArrayList<>();
				flist.add(ff);
			}
			for (File ff : flist) {
				
				x = flist.indexOf(ff);
				File d = flist.get(x);

				System.out.println(d.getPath());
				fis = new FileInputStream(d.getPath());
				Player play = new Player(fis);
				play.play();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void close(){
		play.close();
	}

	
}
