public class QueueException extends Exception{

    public QueueException(){
        super("Error in the Queue");

    }

    public QueueException(String message){
        super(message);
    }
    
}
