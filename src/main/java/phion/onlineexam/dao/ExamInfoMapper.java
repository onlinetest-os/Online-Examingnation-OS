package phion.onlineexam.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import phion.onlineexam.bean.ExamInfo;
import phion.onlineexam.bean.ExamInfoExample;

public interface ExamInfoMapper {
    long countByExample(ExamInfoExample example);

    int deleteByExample(ExamInfoExample example);

    int deleteByPrimaryKey(Integer inId);

    int insert(ExamInfo record);

    int insertSelective(ExamInfo record);

    List<ExamInfo> selectByExampleWithBLOBs(ExamInfoExample example);

    List<ExamInfo> selectByExample(ExamInfoExample example);

    ExamInfo selectByPrimaryKey(Integer inId);
    
    /**
     * 通过eId查询考试信息
     * @param eId
     * @return
     */
    ExamInfo selectByExamID(Integer eId);

    int updateByExampleSelective(@Param("record") ExamInfo record, @Param("example") ExamInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ExamInfo record, @Param("example") ExamInfoExample example);

    int updateByExample(@Param("record") ExamInfo record, @Param("example") ExamInfoExample example);

    int updateByPrimaryKeySelective(ExamInfo record);

    int updateByPrimaryKeyWithBLOBs(ExamInfo record);

    int updateByPrimaryKey(ExamInfo record);
}