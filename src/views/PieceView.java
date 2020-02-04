package views;

public class PieceView {
    String color;
    char symbol;

    public PieceView(String col, char sym){
        this.color = col;
        this.symbol = sym;
    }
    public String getIconPath(){
        return "pionki/"+this.color+"/"+this.symbol+".png";
    }
}
