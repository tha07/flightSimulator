import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
 
public class map
{
 
    private int[][] tileMap;
        private int height;
        private int width;
        private Image BLOCK;
 
 
    public map(String mapFile)
    {
            BLOCK = new ImageIcon("path to sprite").getImage();
            int[] tileNums;
            try
            {
                BufferedReader br = new BufferedReader(new FileReader(mapFile));
                width=Integer.parseInt(br.readLine());
                height=Integer.parseInt(br.readLine());
                tileNums= new int[width];
                tileMap=new int[width][height];
 
                for(int row = 0; row < height; row++)
                {
                    String line=br.readLine();
                    //what I'm trying to do here is make each int in grid into array by converting char to int
                    for(int i=0; i<=line.length();i++)
                    {
                        tileNums[i]=(line.charAt(i)-48);
                    }
                    for(int col = 0; col<width; col++)
                    {
                        tileMap[row][col]=tileNums[col];
                    }
                }
 
            }
            catch(Exception e)
            {
 
            }
    }
        public void draw(Graphics g)
        {
            int ix=0;
            int iy=0;
            for(int row=0;row<height;row++)
            {
                for(int col=0;col<height;col++)
                {
                    if(tileMap[row][col]==0)
                    {
                         g.drawImage(BLOCK, ix, iy, null);
                         ix=ix+16;
                    }
                }
            }
        }
}