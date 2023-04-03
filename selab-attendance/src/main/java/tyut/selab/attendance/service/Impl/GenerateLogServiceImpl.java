package tyut.selab.attendance.service.Impl;

import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tyut.selab.attendance.domain.po.Attendance;
import tyut.selab.attendance.mapper.AttendanceLogMapper;
import tyut.selab.attendance.mapper.GenerateLogMapper;
import tyut.selab.attendance.service.IGenerateLogService;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static com.ruoyi.common.utils.DateUtils.getDate;

/**
 * @Author: Gulu
 * @Date: 2023/3/27 16:21
 */
@Service
public class GenerateLogServiceImpl implements IGenerateLogService {

    @Autowired
    GenerateLogMapper generateLogMapper;

    @Autowired
    AttendanceLogMapper attendanceLogMapper;

    @Value("${spring.datasource.druid.master.url}")
    String url ;
    @Value("${spring.datasource.druid.master.username}")
    String username ;
    @Value("${spring.datasource.druid.master.password}")
    String password ;

    @Override
    public void writeLogFileThisWeek() {

        //生成签到日志Excel表格
        try {

            XSSFWorkbook workbook = new XSSFWorkbook();

            org.apache.poi.ss.usermodel.Sheet sheet1 = workbook.createSheet("签到日志");

            Row headerRow = sheet1.createRow(0);
            headerRow.setHeightInPoints(30);

            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 18);
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

            Cell headerCell = headerRow.createCell(0);
            headerCell.setCellValue("签到日志");
            headerCell.setCellStyle(headerCellStyle);

            Row row = sheet1.createRow(1);
            sheet1.setColumnWidth(1, 20 * 256);
            sheet1.setColumnWidth(2, 20 * 256);

            Cell cell0 = row.createCell(0);
            cell0.setCellValue("姓名");
            Cell cell1 = row.createCell(1);
            cell1.setCellValue("签到时间");
            Cell cell2 = row.createCell(2);
            cell2.setCellValue("签退时间");
            Cell cell3 = row.createCell(3);
            cell3.setCellValue("时长/h");

            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT att_user, att_start_time, att_end_time, `interval` FROM selab_attendance_log";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            int rowNum = 2;
            int numColumns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                Row dataRow = sheet1.createRow(rowNum++);
                for (int i = 1; i <= numColumns; i++) {
                    Cell cell = dataRow.createCell(i - 1);
                    cell.setCellValue(resultSet.getString(i));
                }
            }

            org.apache.poi.ss.usermodel.Sheet sheet2 = workbook.createSheet("签到统计");

            Row headerRow2 = sheet2.createRow(0);
            headerRow2.setHeightInPoints(30);

            CellStyle headerCellStyle2 = workbook.createCellStyle();
            Font headerFont2 = workbook.createFont();
            headerFont2.setBold(true);
            headerFont2.setFontHeightInPoints((short) 18);
            headerCellStyle2.setFont(headerFont2);
            headerCellStyle2.setAlignment(HorizontalAlignment.CENTER);
            sheet2.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

            Cell headerCell2 = headerRow2.createCell(0);
            headerCell2.setCellValue("签到统计");
            headerCell2.setCellStyle(headerCellStyle2);

            Row row2 = sheet2.createRow(1);

            Cell cell00 = row2.createCell(0);
            cell00.setCellValue("姓名");
            Cell cell01 = row2.createCell(1);
            cell01.setCellValue("总时长/h");

            String sql2 = "SELECT att_user, `interval` FROM selab_attendance_log_statistics";
            Statement statement2 = conn.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(sql2);

            int rowNum2 = 2;
            int numColumns2 = resultSet2.getMetaData().getColumnCount();

            while (resultSet2.next()) {
                Row dataRow = sheet2.createRow(rowNum2++);
                for (int i = 1; i <= numColumns2; i++) {
                    Cell cell = dataRow.createCell(i - 1);
                    cell.setCellValue(resultSet2.getString(i));
                }
            }

            FileOutputStream outputStream = new FileOutputStream(".\\signlog\\"+ getDate() +"签到表.xlsx");
            workbook.write(outputStream);
            outputStream.close();
            System.out.println("导出成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteThisWeekLog() {
        generateLogMapper.deleteThisWeekLog();
        generateLogMapper.deleteThisWeekLogStatistics();
    }

    @Override
    public void deleteInvalidData() {
        generateLogMapper.deleteInvalidData();
    }
}
