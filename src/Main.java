import javax.swing.JFrame;

public class Main {
    JFrame frame;

    public Main(){
        frame = new JFrame("CarRoot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.add(new GameScreen());
        frame.setVisible(true);
    }

    public static void main(String [] args){
        new Main();
    }
}
