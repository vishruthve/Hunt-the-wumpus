//Rayhan 
// 3/8/34


public class Gui {
    public Gui(){

    }
<<<<<<< HEAD

    public void drawHexAtGridPos(int x, int y){
        this.drawHex(50, x*75+50, y*88+(x%2==1?44:0)+50);
    }
    public void drawHex(int size, int x, int y){
        p = Arrays.copyOf(p, p.length+1);
        l = Arrays.copyOf(l, l.length+1);
        l[l.length-1] = new int[6][2]; 
        p[p.length-1] = new Polygon();
        for (int i = 0; i < 6; i++){
            p[p.length-1].addPoint((int) (x + size * Math.cos(i * 2 * Math.PI / 6)), (int) (y + size * Math.sin(i * 2 * Math.PI / 6)));
            l[l.length-1][i][0] = (int) (x + size * Math.cos(i * 2 * Math.PI / 6));
            l[l.length-1][i][1] = (int) (y + size * Math.sin(i * 2 * Math.PI / 6));
            System.out.print(Arrays.toString(l[l.length-1][i]));
        }
        System.out.println();
        

    }
}
=======
}
>>>>>>> e11c6c6c9cb291e944e5dc0f01ac3dd8bd33b36c
