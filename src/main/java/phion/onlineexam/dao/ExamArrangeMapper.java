package phion.onlineexam.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import phion.onlineexam.bean.ExamArrange;
import phion.onlineexam.bean.ExamArrangeExample;

public interface ExamArrangeMapper {
    long countByExample(ExamArrangeExample example);

    int deleteByExample(ExamArrangeExample example);

    int deleteByPrimaryKey(Integer arraId);

    int insert(ExamArrange record);

    int insertSelective(ExamArrange record);

    List<ExamArrange> selectByExample(ExamArrangeExample example);

    ExamArrange selectByPrimaryKey(Integer arraId);

    int updateByExampleSelective(@Param("record") ExamArrange record, @Param("example") ExamArrangeExample example);

    int updateByExample(@Param("record") ExamArrange record, @Param("example") ExamArrangeExample example);

    int updateByPrimaryKeySelective(ExamArrange record);

    int updateByPrimaryKey(ExamArrange record);
}