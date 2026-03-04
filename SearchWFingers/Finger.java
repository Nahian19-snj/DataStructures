public class Finger<T> {

private DoubleNode<T> node;
private int idx;

public Finger(int idx, DoubleNode<T> node){
    this.idx = idx;
    this.node = node;
}

public int getIndex(){

    return idx;

}

public void setIndex(int idx){

    this.idx= idx;
}

public DoubleNode<T> getNode(){
    return node;
}

public void setNode(DoubleNode<T> node){
    this.node = node;


}


}
