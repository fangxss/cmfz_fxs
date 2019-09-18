package com.fxs.poi;

import com.fxs.CmfzFxsApplication;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzFxsApplication.class)
public class TestPoi {

    @Test
    public void test1(){
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("用户信息表");

        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("内容1");

        try {
            workbook.write(new FileOutputStream(new File("E:\\idea_work\\Testpoi.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
