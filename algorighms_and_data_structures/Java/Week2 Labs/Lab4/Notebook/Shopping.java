import java.util.ArrayList;

public class Shopping {

	ArrayList<String> items;
	
	public Shopping() {
		items = new ArrayList<String>();
	}
	
	public void addItem(String celeb) {
		items.add(celeb);
	}
	
	public int numberOfItems() {
		return items.size();
	}
	
	public void showItem(int index) {
		if(index >= 0 && index < numberOfItems()) {
            // This is a valid note number, so we can print it.
            System.out.println(items.get(index));
        }
	}
	
	public void listItems() {
		int index = 0;
        while(index < items.size()) {
            showItem(index);
            index++;
        }
	}

}
