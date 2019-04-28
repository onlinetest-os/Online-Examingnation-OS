package phion.onlineexam.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import phion.onlineexam.bean.Student;
import phion.onlineexam.bean.StudentExample;
import phion.onlineexam.bean.Teacher;
import phion.onlineexam.bean.TeacherExample;

public interface StudentMapper {
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Integer stuId);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Integer stuId);
    
    /**
     * 按所给条件查询学生
     * @param record
     * @param example
     * @return
     */
    List<Student> selectBySelective(@Param("record") Student record, @Param("example") StudentExample example);

    /**
     * 根据考试id联合考试安排表查询学生
     * @param record
     * @param example
     * @return
     */
    List<Student> selectByEId(@Param("eId") Integer eId);
    
    /**
     * 根据考试id联合考试安排表有有选择的查询学生
     * @param record
     * @param example
     * @return
     */
    List<Student> selectByEIdSelective(@Param("eId") Integer eId,@Param("record") Student record);
    
    
    /**
     * 根据考试id联合考试安排表查询已提交的学生学生
     * @param record
     * @param example
     * @return
     */
    List<Student> selectByEIdWithNotNullCommitinfo(@Param("eId") Integer eId);
    
    /**
     * 根据考试id联合考试安排表查询未提交的学生学生
     * @param record
     * @param example
     * @return
     */
    List<Student> selectByEIdWithNullCommitinfo(@Param("eId") Integer eId);
    
    /**
     * 根据考试id联合考试安排表查询已登录的学生学生
     * @param record
     * @param example
     * @return
     */
    List<Student> selectByEIdWithNotNullIp(@Param("eId") Integer eId);
    
    /**
     * 根据考试id联合考试安排表查询未登录的学生学生
     * @param record
     * @param example
     * @return
     */
    List<Student> selectByEIdWithNullIp(@Param("eId") Integer eId);
    
    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}