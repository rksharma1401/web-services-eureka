package learn.springcloud.restService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import learn.springcloud.model.Person;

@RestController
public class PersonService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/addPerson")
    public int addPerson(@RequestBody  Person person){
        String sql = "INSERT INTO person(first_name, last_name, age, place) VALUES(?,?,?,?)";
        int result=1;
        try{
            result= jdbcTemplate.update(sql, person.getFirstName(),
            person.getLastName(), person.getAge(), person.getPlace());
        }catch(org.springframework.dao.DuplicateKeyException ex){
            result=-1;
        }
        
        return result;
        
      }
      @RequestMapping(value = "/getPersonList", method = RequestMethod.GET)
      public List<Person> getAllPerson(){

        System.out.println("called getAllPerson");
        return jdbcTemplate.query("SELECT * FROM person", new RowMapper<Person>(){
    
          public Person mapRow(ResultSet rs, int arg1) throws SQLException {
            Person p = new Person();
            p.setAge(rs.getInt("age"));
            p.setFirstName(rs.getString("first_name"));
            p.setLastName(rs.getString("last_name"));
            p.setPlace(rs.getString("place"));
            return p;
          }
    
        });
      }
      
      @RequestMapping(value = "/getPersonByName", method = RequestMethod.GET)
      public List<Person> getPersonByName(@RequestParam(value = "fname") String fname,
      @RequestParam(value = "lname") String lname){

            return jdbcTemplate.query("SELECT * FROM person where first_name='"+fname+"' and last_name='"+ lname+"'", new RowMapper<Person>(){
    
              public Person mapRow(ResultSet rs, int arg1) throws SQLException {
                Person p = new Person();
                p.setAge(rs.getInt("age"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setPlace(rs.getString("place"));
                return p;
              }
    
            });
          }
}