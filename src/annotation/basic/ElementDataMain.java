package annotation.basic;

public class ElementDataMain {

	public static void main(String[] args) {
		Class<ElementData1> annoClass = ElementData1.class;
		AnnoElement annotation = annoClass.getAnnotation(AnnoElement.class);

		String value = annotation.value();
		System.out.println("value = " + value);

		int count = annotation.count();
		System.out.println("count = " + count);

		String[] tags = annotation.tags();
		for (String tag : tags) {
			System.out.println("tag = " + tag);
		}
	}
}
