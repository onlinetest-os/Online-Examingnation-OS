package phion.onlineexam.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import phion.onlineexam.bean.Exam;
import phion.onlineexam.bean.ExamArrange;
import phion.onlineexam.bean.ExamArrangeExample;
import phion.onlineexam.bean.ExamExample;

public interface ExamMapper {
	
    long countByExample(ExamExample example);

    int deleteByExample(ExamExample example);

    int deleteByPrimaryKey(Integer eId);

    int insert(Exam record);

    int insertSelective(Exam record);

    List<Exam> selectByExample(ExamExample example);

    Exam selectByPrimaryKey(Integer eId);
    
    /**
     * 查询考试，附带考试info
     * @param example
     * @return
     */
    List<Exam> selectByExampleWithExamInfo(ExamExample example);
    
    /**
     * 查询考试，附带考试info
     * @param example
     * @return
     */
    Exam selectByPrimaryKeyWithExamInfo(Integer eId);
    
    /**
     * 有选择的查询考试，附带考试信息
     * @param record
     * @return
     */
    List<Exam> selectWithExamInfoSelective(Exam record);
    
    /**
     * 带学生信息的查询，返回某一场考试
     * @param example eId
     * @return
     */
    Exam selectByPrimaryKeyWithStudent(Integer eId);

    int updateByExampleSelective(@Param("record") Exam record, @Param("example") ExamExample example);

    int updateByExample(@Param("record") Exam record, @Param("example") ExamExample example);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);
}