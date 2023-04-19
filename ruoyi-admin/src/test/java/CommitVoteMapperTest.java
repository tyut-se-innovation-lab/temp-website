import com.github.pagehelper.PageInfo;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.mapper.SysUserMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.attendance.domain.po.Attendance;
import tyut.selab.attendance.domain.vo.AttendanceLog;
import tyut.selab.attendance.mapper.AttendanceLogMapper;
import tyut.selab.attendance.mapper.AttendanceMapper;

import tyut.selab.attendance.mapper.GenerateLogMapper;
import tyut.selab.attendance.service.Impl.AttendanceLogServiceImpl;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ruoyi.common.utils.DateUtils.*;
import static com.ruoyi.common.utils.SecurityUtils.getUserId;

/**
 * @Author: Gulu
 * @Date: 2022/11/15 14:48
 */
@SpringBootTest(classes = RuoYiApplication.class)
public class CommitVoteMapperTest {

    @Autowired
    GenerateLogServiceImpl generateLogService;

    @Test
    public void xlsx(){
        generateLogService.writeLogFileThisWeek();
    }

}
