public class Sub_Con extends Sub_window{
    public Sub_Con(int x, int y, String title, TestUIInCode t){
        super(x,y,title,t);
    }
    public void updateHisCon(){
        for(int i = 0; i < labels.length; i++) {
            int a = i+1;
            labels[i].setText("value "+(a)+" mins ago: "+mainWindow.getDb().getHisCon().get(a));
        }
    }
}
