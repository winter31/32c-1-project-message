package music;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javazoom.jl.player.Player;

public class MusicThread extends Thread {
	private File f = new File("src\\musicsfiles");
	private FileInputStream fis;
	private Player play;
	int x = 0;
	ArrayList<File> flist;

	public MusicThread() {
	
	}

	@Override
	public void run() {
		boolean ee = false;
		File[] mlist = list1();
		for (File ff : mlist) {
			try {
				fis = new FileInputStream(ff);
				Player play = new Player(fis);
				play.play();
				x = 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.run();
	}

	public File[] list1() {
		File[] result = f.listFiles();
		return result;
	}

	public void nextMusic() {
		++x;
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
}
