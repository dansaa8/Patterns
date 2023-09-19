package algorithm;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AgeDiffFinder {
	private final List<Person> personList;

	public AgeDiffFinder(List<Person> personList) {
		this.personList = personList;
	}

	public Measure Find(AgeDifference ageDifference) {
		List<Measure> measureList = new ArrayList<Measure>();

		personList.sort(Comparator.comparing(Person::getBirthDate));

		for (int i = 0; i < personList.size() - 1; i++) {
			for (int j = i + 1; j < personList.size(); j++) {
				Measure measure = new Measure();
					measure.younger = personList.get(i);
					measure.older = personList.get(j);
				measure.distance = measure.older.birthDate.getTime() - measure.younger.birthDate.getTime();
				measureList.add(measure);
			}
		}

		if (measureList.size() < 1) {
			return new Measure();
		}

		Measure answer = measureList.get(0);
		for (Measure result : measureList) {
			switch (ageDifference) {
				case CLOSEST:
					if (result.distance < answer.distance) {
						answer = result;
					}
					break;

				case FURTHEST:
					if (result.distance > answer.distance) {
						answer = result;
					}
					break;
			}
		}

		return answer;
	}
}
