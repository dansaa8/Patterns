package algorithm;
import java.util.ArrayList;
import java.util.List;

public class AgeDiffFinder {
	private final List<Person> personList;

	public AgeDiffFinder(List<Person> personList) {
		this.personList = personList;
	}

	public Measure Find(AgeDifference ageDifference) {
		List<Measure> measureList = new ArrayList<Measure>();

		for (int i = 0; i < personList.size() - 1; i++) {
			for (int j = i + 1; j < personList.size(); j++) {
				Measure measure = new Measure();
				if (personList.get(i).birthDate.getTime() < personList.get(j).birthDate.getTime()) {
					measure.younger = personList.get(i);
					measure.older = personList.get(j);
				} else {
					measure.younger = personList.get(j);
					measure.older = personList.get(i);
				}
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
