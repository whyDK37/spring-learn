package why.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pojo.User;

@Repository
public interface UserMapper {

  @Select("Select * from user")
// @Results({
//     @Result(id = true, column = "id", property = "id"),
//     @Result(column = "name", property = "name"),
//     @Result(column = "sex", property = "sex"),
//     @Result(column = "age", property = "age")
// })
  List<User> getById();
}
