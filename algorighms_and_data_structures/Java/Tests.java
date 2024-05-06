public class Tests{
public static void Tests() {
		String s1 = new String("Computer");
		String s2 = new String("TV");
		String s3 = new String("Radio");
		s1 = s2 = s3;
		System.out.println(s2); // first word:

		s3 = s2;
		System.out.println(s2); // second word:

		s2 = s3 = s2;
		System.out.println(s3); // third word:

	}
}

