package kr.ac.hansung.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
//인자가없는 생성자
@ToString
public class Course {
//여기에 들어갈것들이 db의 레코드

	//@Override
	//public String toString() { //객체를 출력할때 toString으로 근데 이것도 롬복있어;;
	//	return "Offer [id=" + id + ", name=" + name + ", email=" + email + ", text=" + text + "]";
	//}
	private int year;
	private int semester;
	private String course_id;
	private String title;
	private String division;
	private int credit;

}
