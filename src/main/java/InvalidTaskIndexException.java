public class InvalidTaskIndexException extends ChipException{
    public InvalidTaskIndexException(int index){
        super("Oops! There is no item at index " + index + ".");
    }
}
