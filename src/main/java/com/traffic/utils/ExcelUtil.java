package com.traffic.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.traffic.bean.Punishmentway;
import com.traffic.bean.Traffic;

/**
 * Excel 解析 和 创建
 * @author huyande
 *
 */
public class ExcelUtil {
	
	private final static String XLS = "xls";  
    private final static String XLSX = "xlsx";  
	  
	/**
	 * 导出标签新的Excel方法 
	 * 此方法会在同一个Excel中创建两个sheet片
	 * @param dataMap 传入封装符合规则的集合 存在sheet2
	 * @param dataList 传入规则数据数据集合 
	 * @param labelName 
	 * @param namesList sheet1 sheet2 第0行表头
	 * @param wordList sheet2 第1行表头
	 * @return
	 * @throws Exception
	 */
	public static HSSFWorkbook generateExcelFile(Traffic traffic) throws Exception {
		//创建Excel工作簿
		HSSFWorkbook workbook = new HSSFWorkbook(); 
		//创建工作片
		HSSFSheet sheet1 = workbook.createSheet();
		HSSFRow sheet1_row = sheet1.createRow(0); // 创建第0行，也就是输出表头
		
		//设置单元格宽度
		sheet1.setColumnWidth(0, 25 * 256);
		sheet1.setColumnWidth(2, 25 * 256);
		
		//创建样式
		CellStyle cellStyle=workbook.createCellStyle();
		
		CellStyle cellStyle_other=workbook.createCellStyle();
		
		//设置居中
		cellStyle.setAlignment(HorizontalAlignment.CENTER);// 居中   
		
		//创建字体 表头
		HSSFFont font_top = workbook.createFont();
		font_top.setFontName("黑体");
		font_top.setFontHeightInPoints((short) 20);//设置字体大小
		cellStyle.setFont(font_top);
		
		//创建字体 
		HSSFFont font = workbook.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 14);//设置字体大小
		//font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		cellStyle_other.setFont(font);
		
		HSSFCell cell;
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 3);
		sheet1.addMergedRegion(cra);
		cell = sheet1_row.createCell(0); // 创建第i列
		cell.setCellValue("交通违章处罚书");		
		cell.setCellStyle(cellStyle); // 设置单元格样式
		
		
		//创建第一行
		sheet1_row = sheet1.createRow(1);// 创建第1行
		cell = sheet1_row.createCell(0);// 创建第0列
		cell.setCellValue("姓名");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(1);// 创建第1列
		cell.setCellValue(traffic.getName());
		cell = sheet1_row.createCell(2);// 创建第2列
		cell.setCellValue("驾驶执照号");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(3);// 创建第3列
		cell.setCellValue(traffic.getLicenseid());
		
		//创建第二行
		sheet1_row = sheet1.createRow(2);// 创建第1行
		cell = sheet1_row.createCell(0);
		/**
		 * 起始行
		 * 结束行
		 * 起始列
		 * 结束列
		 */
		CellRangeAddress cra1 = new CellRangeAddress(2, 2, 1, 3);
		sheet1.addMergedRegion(cra1);
		cell.setCellValue("地址");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(1);
		cell.setCellValue(traffic.getAddress());
		
		//创建第三行
		sheet1_row = sheet1.createRow(3);// 创建第1行
		cell = sheet1_row.createCell(0);
		cell.setCellValue("邮编");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(1);
		cell.setCellValue(traffic.getDecodes());
		cell = sheet1_row.createCell(2);
		cell.setCellValue("电话");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(3);
		cell.setCellValue(traffic.getPhonenumber());
		
		//创建第四行  
		sheet1_row = sheet1.createRow(4);// 创建第1行
		cell = sheet1_row.createCell(0);
		cell.setCellValue("机动车牌照号");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(1);
		cell.setCellValue(traffic.getLicenceplate());
		cell = sheet1_row.createCell(2);
		cell.setCellValue("型号");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(3);
		cell.setCellValue(traffic.getModeltype());
		
		//创建第五行
		sheet1_row = sheet1.createRow(5);// 创建第1行
		cell = sheet1_row.createCell(0);
		cell.setCellValue("制造厂");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(1);
		cell.setCellValue(traffic.getFactoryname());
		cell = sheet1_row.createCell(2);
		cell.setCellValue("生产日期");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(3);
		cell.setCellValue(DateUtil.format(DateUtil.convertStrToDate(traffic.getExpiringdate()),"yyyy/MM/dd"));
		
		//创建地六行 
		sheet1_row = sheet1.createRow(6);// 创建第1行
		cell = sheet1_row.createCell(0);
		cell.setCellValue("违章日期");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(1);
		cell.setCellValue(DateUtil.format(DateUtil.convertStrToDate(traffic.getViolationdate()),"yyyy/MM/dd"));
		cell = sheet1_row.createCell(2);
		cell.setCellValue("时间");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(3);
		cell.setCellValue(DateUtil.format(DateUtil.convertStrToDate(traffic.getCreatetime()),"yyyy/MM/dd"));
		
		//创建第七行 
		sheet1_row = sheet1.createRow(7);// 创建第1行
		cell = sheet1_row.createCell(0);
		cell.setCellValue("违章地点");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(1);
		cell.setCellValue(traffic.getViolationaddress());
		cell = sheet1_row.createCell(2);
		cell.setCellValue("违章记载");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(3);
		cell.setCellValue(traffic.getViolationcontent());
		
		//创建第八行 
		sheet1_row = sheet1.createRow(8);// 创建第1行
		cell = sheet1_row.createCell(0);
		/**
		 * 起始行
		 * 结束行
		 * 起始列
		 * 结束列
		 */
		CellRangeAddress cra2 = new CellRangeAddress(8, 8, 1, 3);
		sheet1.addMergedRegion(cra2);
		cell.setCellValue("处罚方式");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(1);
		List<Punishmentway> punishmentways = traffic.getPunishmentways();
		StringBuffer punish_str =  new StringBuffer();
		for(Punishmentway punish:punishmentways) {
			if(punish.getPunishmenttype()==1) {
				punish_str.append("警告;");
			}else if(punish.getPunishmenttype()==2) {
				punish_str.append("罚款;");
			}else {
				punish_str.append("暂扣驾驶执照;");
			}
		}
		cell.setCellValue(punish_str.toString());
		
		//创建第九行
		sheet1_row = sheet1.createRow(9);// 创建第1行
		cell = sheet1_row.createCell(0);
		cell.setCellValue("警察签字");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(1);
		cell.setCellValue("");
		cell = sheet1_row.createCell(2);
		cell.setCellValue("警察编号");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		cell = sheet1_row.createCell(3);
		cell.setCellValue("");
		
		//创建第十行
		sheet1_row = sheet1.createRow(10);// 创建第1行
		cell = sheet1_row.createCell(0);
		cell.setCellValue("被处罚人签字");
		cell.setCellStyle(cellStyle_other); // 设置单元格样式
		
		return workbook;
	}
	/**
	 * 读取excel数据
	 */
	public static List<Map<String, Object>> exportListFromExcel(File file, int sheetNum) throws IOException {  
        return exportListFromExcel(new FileInputStream(file), FilenameUtils.getExtension(file.getName()), sheetNum);  
    }  
  
    public static List<Map<String, Object>> exportListFromExcel(InputStream is, String extensionName, int sheetNum) throws IOException {  
  
        Workbook workbook = null;  
  
        if (extensionName.toLowerCase().equals(XLS)) {  
            workbook = new HSSFWorkbook(is);  
        } else if (extensionName.toLowerCase().equals(XLSX)) {  
            workbook = new XSSFWorkbook(is);  
        }  
  
        return readCell(workbook, sheetNum);  
    }  
  
    public static List<Map<String, Object>> readCell(Workbook workbook, int sheetNum) {  
        Sheet sheet = workbook.getSheetAt(sheetNum);  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int sNum=1; //如果是片1
        	sNum=0;
			if(sheetNum==0) {
        }else if(sheetNum==1){//如果是片2
        	sNum=1;
        }
        for (int i=sNum; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);  
            Map<String, Object> map = new HashMap<String, Object>();  
            for (Cell cell : row) {  
  
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());  
                String key = cellRef.formatAsString();  
  
                switch (cell.getCellType()) {  
                case Cell.CELL_TYPE_STRING:  
                    map.put(key, cell.getRichStringCellValue().getString());  
                    break;  
                case Cell.CELL_TYPE_NUMERIC:  
                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {  
                        map.put(key, cell.getDateCellValue());  
                    } else {
                    	DecimalFormat dfs = new DecimalFormat("0");
                        map.put(key, dfs.format(cell.getNumericCellValue()));  
                    }  
                    break;  
                case Cell.CELL_TYPE_BOOLEAN:  
                    map.put(key, cell.getBooleanCellValue());  
                    break;  
                case Cell.CELL_TYPE_FORMULA:  
                    map.put(key, cell.getCellFormula());  
                    break;  
                case Cell.CELL_TYPE_BLANK:  
                    break;  
                case Cell.CELL_TYPE_ERROR:  
                    break;  
                default:  
                    map.put(key, "");  
                }  
            }  
            list.add(map);  
        }  
        return list;  
    } 
    
    
    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static void getHSSFWorkbook(String sheetName, String titleName,
			String fileName, int columnNumber, int[] columnWidth,
			String[] columnName, String[][] dataList,
			HttpServletResponse response) throws Exception {
		if (columnNumber == columnWidth.length&& columnWidth.length == columnName.length) {
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// sheet.setDefaultColumnWidth(15); //统一设置列宽
			for (int i = 0; i < columnNumber; i++) 
			{
				for (int j = 0; j <= i; j++) 
				{
					if (i == j) 
					{
						sheet.setColumnWidth(i, columnWidth[j] * 256); // 单独设置每列的宽
					}
				}
			}
			// 创建第1行 也就是表头
			HSSFRow row = sheet.createRow((int) 0);
			row.setHeightInPoints(37);// 设置表头高度
 
		
			// 第四.一步，创建表头的列
			for (int i = 0; i < columnNumber; i++) 
			{
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(columnName[i]);
//				cell.setCellStyle(style);
			}
 
			// 第五步，创建单元格，并设置值
			for (int i = 0; i < dataList.length; i++) 
			{
				row = sheet.createRow((int) i + 1);
				
				HSSFCell datacell = null;
				for (int j = 0; j < columnNumber; j++) 
				{
					datacell = row.createCell(j);
					datacell.setCellValue(dataList[i][j]);
//					datacell.setCellStyle(zidonghuanhang2);
				}
			}
 
			// 第六步，将文件存到浏览器设置的下载位置
			//String filename = fileName + ".xls";
			response.setContentType("application/ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					.concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
			OutputStream out = response.getOutputStream();
			try {
				wb.write(out);// 将数据写出去
				String str = "导出" + fileName + "成功！";
				System.out.println(str);
			} catch (Exception e) {
				e.printStackTrace();
				String str1 = "导出" + fileName + "失败！";
				System.out.println(str1);
			} finally {
				out.close();
			}
 
		} else {
			System.out.println("列数目长度名称三个数组长度要一致");
		}
 
	}
    
    
    
    
    
    /** 
     * 导入
     * 拼装单个obj 
     * @param obj 
     * @param row 
     * @return 
     * @throws Exception 
     */  
    private  static  Map<String, Object>  dataObj(Object obj, HSSFRow row) throws Exception {  
        Class<?> rowClazz= obj.getClass();      
        Field[] fields = FieldUtils.getAllFields(rowClazz);  
        if (fields == null || fields.length < 1) {  
            return null;  
        }  
          
        //容器  
        Map<String, Object> map = new HashMap<String, Object>();  
          
        //注意excel表格字段顺序要和obj字段顺序对齐 （如果有多余字段请另作特殊下标对应处理）  
        for (int j = 0; j < fields.length; j++) {  
            map.put(fields[j].getName(), getVal(row.getCell(j)));  
        }  
        return map;   
    }  
      
    public  static   List<Map<String, Object>> importExcel(MultipartFile file, Object obj) throws Exception {  
          
        //装载流  
        POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());  
        HSSFWorkbook hw= new HSSFWorkbook(fs);  
          
        //获取第一个sheet页  
        HSSFSheet sheet = hw.getSheetAt(0);  
          
        //容器  
        List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();  
          
        //遍历行 从下标第一行开始（去除标题）  
        for (int i = 1; i < sheet.getLastRowNum(); i++) {  
            HSSFRow row= sheet.getRow(i);  
            if(row!=null){  
                //装载obj  
                 ret.add(dataObj(obj,row));  
            }  
        }  
        return ret;  
    }  
      
    /** 
     * 处理val（暂时只处理string和number，可以自己添加自己需要的val类型） 
     * @param hssfCell 
     * @return 
     */  
    public static String getVal(HSSFCell hssfCell) {  
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {  
            return hssfCell.getStringCellValue();  
        } else {  
            return String.valueOf(hssfCell.getNumericCellValue());  
        }  
    }  


    
    
}
