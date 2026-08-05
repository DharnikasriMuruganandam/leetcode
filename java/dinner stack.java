import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

class DinnerPlates {
    private List<Stack<Integer>> stacks;
    private TreeSet<Integer> available;
    private int capacity;

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
        this.stacks = new ArrayList<>();
        this.available = new TreeSet<>();
    }
    
    public void push(int val) {
        if (available.isEmpty()) {
            stacks.add(new Stack<>());
            available.add(stacks.size() - 1);
        }
        
        int firstAvailable = available.first();
        stacks.get(firstAvailable).push(val);
        
        if (stacks.get(firstAvailable).size() == capacity) {
            available.remove(firstAvailable);
        }
    }
    
    public int pop() {
        while (!stacks.isEmpty() && stacks.get(stacks.size() - 1).isEmpty()) {
            available.remove(stacks.size() - 1);
            stacks.remove(stacks.size() - 1);
        }
        if (stacks.isEmpty()) {
            return -1;
        }
        return popAtStack(stacks.size() - 1);
    }
    
    public int popAtStack(int index) {
        if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty()) {
            return -1;
        }
        
        int val = stacks.get(index).pop();
        available.add(index);
        
        while (!stacks.isEmpty() && stacks.get(stacks.size() - 1).isEmpty()) {
            available.remove(stacks.size() - 1);
            stacks.remove(stacks.size() - 1);
        }
        
        return val;
    }
}
