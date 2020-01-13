/**
 * Copyright : www.easipay.net , 2011-2012
 * Project : PEPP
 * $Id$
 * $Revision$
 * Last Changed by $Author$ at $Date$
 * $URL$
 * <p>
 * Change Log
 * Author      Change Date    Comments
 * -------------------------------------------------------------
 * zhangyl     2012-2-8        Initailized
 */

package com.santiago.commons.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 读写excel
 *
 * @author zhangyl
 */
public class ExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    private static final String DEFAULT_SHEET_NAME = "sheet01";
    private static Map<String, String> defaultColumnNameMap = new HashMap<String, String>();
    static {
        Map<String, Object> cacheByTypeToMap = new HashMap<>();
        Set<Map.Entry<String, Object>> entries = cacheByTypeToMap.entrySet();
        for (Map.Entry entry : entries) {
            defaultColumnNameMap.put((String) entry.getKey(), entry.getValue().toString());
        }
    }

    public static HSSFWorkbook createExcelFile(List<?> list, Map<String, String> columnNameMap) throws IllegalAccessException {
        return createExcelFile(DEFAULT_SHEET_NAME, list, columnNameMap);
    }

    public static HSSFWorkbook createExcelFile(List<?> list) throws IllegalAccessException {
        return createExcelFile(DEFAULT_SHEET_NAME, list, defaultColumnNameMap);
    }

    public static HSSFWorkbook createExcelFile(String sheetName, List<?> list) throws IllegalAccessException{
        return createExcelFile(sheetName, list, defaultColumnNameMap);
    }
        /**
         * list中字段类型必须为字符串，调用此方法须完善columnNameMap以便准确进行名称转换
         * @param sheetName
         * @param list
         * @return
         * @throws IllegalAccessException
         */
    public static HSSFWorkbook createExcelFile(String sheetName, List<?> list, Map<String, String> columnNameMap) throws IllegalAccessException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultRowHeight((short) (2 * 256));//设置行高
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        int column = 0;
        if (CollectionUtils.isEmpty(list)) {
            cell = row.createCell(column);
            cell.setCellValue("未查询到记录");
            return workbook;
        }
        Class<?> aClass = list.get(0).getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            String columnName = StringUtils.defaultString(columnNameMap.get(fieldName), fieldName);
            int columnWidth = columnName.length() * 1000;
            sheet.setColumnWidth(column, columnWidth);//设置列宽
            cell = row.createCell(column);
            cell.setCellValue(columnName);
            column++;
        }
        HSSFRow rows;
        for (int i = 0; i < list.size(); i++) {
            rows = sheet.createRow(i + 1);
            column = 0;
            for (Field field : fields) {
                field.setAccessible(true);
                Object o = list.get(i);
                String str = (String) field.get(o);
                rows.createCell(column).setCellValue(str);
                column++;
            }
        }
        return workbook;
    }


}
