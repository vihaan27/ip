public class EmptyDescriptionException extends ChipException{
    public EmptyDescriptionException(){
        super("Oops! Please provide a description for your task.");
    }
}
