public class Task {
    private String item;
    private boolean isComplete;

    public Task(String item, boolean isComplete){
        this.item = item;
        this.isComplete = isComplete;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
