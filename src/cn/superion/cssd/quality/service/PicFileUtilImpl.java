package cn.superion.cssd.quality.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.superion.base.ReadPropertiesFile;
import cn.superion.util.SessionUtil;



public class PicFileUtilImpl implements IPicFileUtil {
	private static String baseFilePath;
	static{
		baseFilePath=ReadPropertiesFile.getValue("CSSD_FILE_DIR");
	}

	@Override
	public void savePic(String patientId, String fstrDeptCode,
			List<Map<String, Object>> pics){
		try {
			if(pics==null){
				return;
			}
			String lstrPath = makeFullPath(patientId, fstrDeptCode);
			System.out.println(lstrPath);
			File dir = new File(lstrPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			saveToFile(lstrPath + "/" ,pics);			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("static-access")
	public void setBaseFilePath(String baseFilePath) {
		this.baseFilePath = baseFilePath;
	}

	public String getBaseFilePath() {
		return baseFilePath;
	}

	public void saveToFile(String fstrPath,List<Map<String, Object>> pics)
			throws Exception {
		for(Map<String, Object> picItem:pics){
			String fileName=fstrPath+picItem.get("title")+".jpg";
		    FileOutputStream fos = new FileOutputStream(fileName);
		    fos.write((byte[])picItem.get("data"));
		    fos.close();
		}
	}

	@Override
	public List<Map<String,Object>> loadPic(String patientId, String fstrDeptCode){
		String lstrPath =  makeFullPath(patientId,fstrDeptCode);
		File dir = new File(lstrPath);
		File[] files = dir.listFiles();
		if(files==null){
			files=new File[0];
		}
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>(); 
		for (File file : files) {
			if (!file.isDirectory()) {
				byte[] data=null;
				try {
					data=readFile(file);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				Map<String,Object> map=new HashMap<String, Object>();
				String fileName=file.getName();
				fileName=fileName.substring(0, fileName.length()-4);
				map.put("title", fileName);
				map.put("data", data);
				System.out.println(file.getName());
				list.add(map);
			}
		}
		return list;
	}
    private byte[] readFile(File file)
    throws Exception
    {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));      
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);      
     
        System.out.println("Available bytes:" + in.available());      
     
        byte[] temp = new byte[1024];      
        int size = 0;      
        while ((size = in.read(temp)) != -1) {      
            out.write(temp, 0, size);      
        }      
        in.close();      
     
        byte[] content = out.toByteArray();      
        return content;            
    }
	private String makeFullPath(String patientId, String fstrDeptCode) {
		String lstrUnitsCode = SessionUtil.getUnitsCode();
		String lstrPath = baseFilePath + "/" + lstrUnitsCode + "/"
				+ fstrDeptCode + "/" + patientId + "/";
		return lstrPath;
	}

	public static void main(String[] args) throws Exception {
		PicFileUtilImpl pic = new PicFileUtilImpl();
		pic.loadPic("ddd", "ggg");
	}
}
