import com.ruoyi.RuoYiApplication;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.mapper.SysUserMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.attendance.domain.po.Attendance;
import tyut.selab.attendance.mapper.AttendanceLogMapper;
import tyut.selab.attendance.mapper.AttendanceMapper;

import tyut.selab.attendance.mapper.GenerateLogMapper;
import tyut.selab.attendance.service.Impl.GenerateLogServiceImpl;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.service.ICommitVoteService;


import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.ruoyi.common.utils.DateUtils.*;
import static com.ruoyi.common.utils.SecurityUtils.getUserId;

/**
 * @Author: Gulu
 * @Date: 2022/11/15 14:48
 */
@SpringBootTest(classes = RuoYiApplication.class)
public class CommitVoteMapperTest {

    @Autowired
    GenerateLogMapper generateLogMapper;

    @Value("${spring.datasource.druid.master.url}")
    String url ;
    @Value("${spring.datasource.druid.master.username}")
    String username ;
    @Value("${spring.datasource.druid.master.password}")
    String password ;
@Test
public void commitVote(@Autowired ICommitVoteService service, @Autowired AttendanceMapper attendanceMapper, @Autowired AttendanceLogMapper attendanceLogMapper) throws IOException {
    /*//添加投票结果测试
    VoteResult voteResult1 = new VoteResult();
    voteResult1.setVoteOptionId(758437L);
    voteResult1.setUserId("hgfjhg");
    voteResult1.setContent("hghjhfdgh");
    voteResult1.setCreateTime(new Date());
    VoteResult voteResult2 = new VoteResult();
    voteResult2.setVoteOptionId(7556457L);
    voteResult2.setUserId("jghdfjk");
    voteResult2.setContent("hh6786");
    voteResult2.setCreateTime(new Date());
    ArrayList<VoteResult> voteResults = new ArrayList<>();
    voteResults.add(voteResult1);
    voteResults.add(voteResult2);
    System.out.println(service.commitVoteResult(voteResults));
    //获取时间测试
    System.out.println(service.getDeadLineById(1L));*/
    //System.out.println(attendanceMapper.signIn("20", dateTime(YYYY_MM_DD_HH_MM_SS, getTime())));
    //System.out.println(attendanceMapper.couleSignOut("20"));
    //System.out.println(attendanceMapper.signOut("20", dateTime(YYYY_MM_DD_HH_MM_SS, getTime()), 1));

    /*try {

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
                XSSFFont font = new XSSFFont();
                font.setColor(Font.COLOR_RED);
                cell.getCellStyle().setFont(font);
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
                    XSSFFont font = new XSSFFont();
                    font.setColor(Font.COLOR_RED);
                    cell.getCellStyle().setFont(font);
                    cell.setCellValue(resultSet2.getString(i));
            }
        }

        FileOutputStream outputStream = new FileOutputStream("../selab-attendance/src/main/resources/signlog/" + getDate() +"签到表.xlsx");
        workbook.write(outputStream);
        outputStream.close();
        System.out.println("导出成功！");
    } catch (Exception e) {
        e.printStackTrace();
    }*/

    /*File folder = new File("selab-attendance/src/main/resources/signlog/");
    List<String> fileLists = new ArrayList<>();
    String[] fileNames = folder.list();
    if (fileNames != null){
        fileLists.addAll(Arrays.asList(fileNames));
    }
    for (String file:fileLists) {
        System.out.println(file);
    }*/

    /*File folder = new File("../selab-attendance/src/main/resources/signlog/");
    List<String> fileLists = new ArrayList<>();
    File[] files = folder.listFiles();
    if (files != null){
        for (int i = 0; i < files.length; i++) {
            fileLists.add(files[i].getName());
        }
        for (int i = 0; i < fileLists.size(); i++) {
            System.out.println(fileLists.get(i));
        }
    }*/

    generateLogMapper.deleteInvalidData();

}
}
