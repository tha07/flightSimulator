import javax.swing.JFrame;
 
public class frame extends JFrame
{
    panel ng;
 
    public frame()
        {
            ng = new panel();
            setSize(500,500);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false); 
            add(ng);
        }
        public static void main(String [] args)
        {
            frame n = new frame();
        }
}