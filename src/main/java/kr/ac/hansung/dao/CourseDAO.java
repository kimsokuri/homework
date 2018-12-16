package kr.ac.hansung.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Course;

//이걸로 인해 아래 클래스를 빈으로 등록하겠ㄷ.
@Repository
public class CourseDAO {

	private JdbcTemplate jdbcTemplate;

	// dao가 템플릿사용해 이때 dataSource 가 매개변수로 넘어가,
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 템플릿을 활용해서 실제로 db에 접근해보자!
	// 총 레코드가 몇개인지 조회하는 메소드를 !
	public int getRowCount() {
		// offer라는 테이블로부터 레코드의 수를 세겠다.
		String sqlStatenment = "select count(*) from class";
		return jdbcTemplate.queryForObject(sqlStatenment, Integer.class);
		// 두번째인자는 타입을 넣어주는건데, 리턴값을 인티저니까!
	}


	
	// query and return a single object //여러개의 레코드 출력
	public List<Course> getCourses() { // db의 네임을 OFfer라는 객체에 넣어서 리턴하겠다;
		String sqlStatement = "select * from class"; // ?가 플레이스 홀더야.
		// 레코드를 갔다가 자바에 맵핑해주는 메소드
		//이 경우에는 두개의 인자가 맵핑된다.
		return jdbcTemplate.query(sqlStatement, new RowMapper<Course>() {
			//10개이면 offer라는 객체가 열개!
			// RowMapper라는 인터페이스를 스프링에서 정의했어 이 안에 mapRow 추상메소드 꼭 구현
			@Override
			// result set 결과값을 가져오기위해 table record를 Offer라는 object에 넣는 작업.
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course course = new Course();

				course.setYear(rs.getInt("year"));
				course.setSemester(rs.getInt("semester"));
				course.setCourse_id(rs.getString("course_id"));
				course.setTitle(rs.getString("title"));
				course.setDivision(rs.getString("division"));
				course.setCredit(rs.getInt("credit"));
				

				return course;
			}

		});

	}
	
	public List<Course> getclasses() {
		String sqlStatement = "select * from class";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

				Course course = new Course();

				course.setYear(rs.getInt("year"));
				course.setSemester(rs.getInt("semester"));
				course.setCourse_id(rs.getString("course_id"));
				course.setTitle(rs.getString("title"));
				course.setDivision(rs.getString("division"));
				course.setCredit(rs.getInt("credit"));
				

				return course;
			}
		});
	}
	
	
	
	public List<Course> getSemesterClass() {
		String sqlStatement = "select year, semester, sum(credit) from class group by year, semester";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

				Course course = new Course();

				course.setYear(rs.getInt("year"));
				course.setSemester(rs.getInt("semester"));
				course.setCredit(rs.getInt("credit"));
				
				return course;
			}
		});
	}
	
	
	public List<Course> getDetailSemesterClass(int year, int semester) {
		String sqlStatement = "select year, semester, course_id, title, division, credit from class where year=" + year
				+ " and semester = " + semester;

		return jdbcTemplate.query(sqlStatement, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

				Course course = new Course();

				course.setYear(rs.getInt("year"));
				course.setSemester(rs.getInt("semester"));
				course.setCourse_id(rs.getString("course_id"));
				course.setTitle(rs.getString("title"));
				course.setDivision(rs.getString("division"));
				course.setCredit(rs.getInt("credit"));

				return course;
			}
		});
	}
	
	public List<Course> getDivisionCourse() {
		String sqlStatement = "select division, sum(credit) from class group by division";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

				Course course = new Course();

				course.setDivision(rs.getString("division"));
				course.setCredit(rs.getInt("credit"));
				
				return course;
			}
		});
	}
	
	public List<Course> getClassCourses() {
		String sqlStatement = "select * from class";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

				Course course = new Course();

				course.setYear(rs.getInt("year"));
				course.setSemester(rs.getInt("semester"));
				course.setCourse_id(rs.getString("course_id"));
				course.setTitle(rs.getString("title"));
				course.setDivision(rs.getString("division"));
				course.setCredit(rs.getInt("credit"));

				return course;
			}
		});
	}
	
	
	public boolean insert(Course course) {
		int year = 2019;
		int semester = 1;
		String course_id = course.getCourse_id();
		String title = course.getTitle();
		String division = course.getDivision();
		int credit = 3;
		
		String sqlStatement = "insert into class (year, semester, course_id, title, division, credit) values(?,?,?,?,?,?)";
		
		//업데이트하면 리턴값이 몇개가 넘어왔는지 떠 int값.
		//우리는 지금 하나의 레코드만 업데이트할거니까 성공적으로 업데이트가 되면 1이 출력되겠지?
		//그러면 1이면 true 아니면 false가 나올거야!
		return (jdbcTemplate.update(sqlStatement, new Object[] {year, semester, course_id, title, division, credit}) == 1);
	}
	
	
	public boolean delete(String course_id) {
		String sqlStatement = "delete from class where course_id =?";
		
		return (jdbcTemplate.update(sqlStatement, new Object [] {course_id}) == 1);
	}
}
