package cn.superion.cssd.quality.service;

import java.util.List;
import java.util.Map;

public interface IPicFileUtil {
	/**
	 * 保存图片
	 * @param patientId 病人id
	 * @param fstrDeptCode 病人所在科室
	 * @param fstrFileName  文件名
	 * @param fileData 文件数据
	 * @throws Exception
	 */
     public void savePic(String patientId,String fstrDeptCode,List<Map<String, Object>> pics);
     
     /**
      * 
      * @param patientId 病人id
      * @param fstrDeptCode 病人所在科室
      * @return  List< Map<String,Object>>
      *          包含有图片文件数据和文件名
      *          [
      *             {fileName:"filename.jpg",data:byte[]}
      * @throws Exception
      */
     public List<Map<String,Object>>  loadPic(String patientId,String fstrDeptCode);
}
