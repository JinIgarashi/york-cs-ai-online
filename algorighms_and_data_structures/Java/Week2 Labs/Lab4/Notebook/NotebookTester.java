
public class NotebookTester {

	public static void main(String[] args) {
		//Create Notebook objects and test the methods
		Notebook n=new Notebook();
		
		n.storeNote("Monday");
		n.storeNote("Tuesday");
		n.storeNote("Wednesday");
		n.storeNote("THursday");
		n.storeNote("Friday");
		n.storeNote("Saturday");
		n.storeNote("Sunday. It is the end of this week!!");
		
		n.showNote(2);
		n.listNotes();

	}

}
