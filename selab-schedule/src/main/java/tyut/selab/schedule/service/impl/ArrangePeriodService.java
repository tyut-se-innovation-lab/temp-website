package tyut.selab.schedule.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.TimeFrame;
import tyut.selab.schedule.domain.vo.ArrangePeriodRequest;
import tyut.selab.schedule.domain.vo.ArrangePeriodResponse;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.mapper.DisplayLeisureMapper;
import tyut.selab.schedule.service.IArrangePeriodService;

import java.util.*;

/**
 * @author 冯洋
 * @version 1.0
 */
@Service
public class ArrangePeriodService implements IArrangePeriodService {
    @Autowired
    private DisplayLeisureMapper displayLeisureMapper;

    @Autowired
    private SysUserMapper sysUserMapper;


    public List<SysUser>[][] arrangePeriod(ArrangePeriodRequest arrangePeriodRequest){
        //记录每个人一个星期还剩多少个小时
        Integer needTime = arrangePeriodRequest.getNeedTime();
        //记录该节空课的用户和剩余的时长
        Map<Long,Integer> remainTime = new HashMap<Long,Integer>();
        //保存每节课，空课的人
        List<SysUser>[][] userLists = new LinkedList[8][11];
        //记录该节空课人数大于座位数的用户和剩余时长
        Map<Long,Integer> againRemainTime = new HashMap<Long,Integer>();
        //查看空课人数-把角色之内全部成员全部查出来
        SysUser sysUser = new SysUser();
        //查看该角色下所有的用户
        sysUser.setRoleId(arrangePeriodRequest.getRoleId());
        List<SysUser> sysUserList = sysUserMapper.selectAllocatedList(sysUser);
        Set<Long> userId = new HashSet<>();
        LinkedList<Long> userIdToList = new LinkedList<>();
        for (SysUser user : sysUserList) {
            userId.add(user.getUserId());
            userIdToList.add(user.getUserId());
        }

        //查看空课人数-查询第几周星期几第几节课
        TimeFrame timeFrame = new TimeFrame();
        timeFrame.setWeekNo(arrangePeriodRequest.getWeekNo());
        //每一节课-空课的用户集合
        List<Long> userIdByTimeFrame = null;
        //每一节课-空课的人数
        int emptyLessonStudent = 0;
        //记录人数比空座位多出来的人数
        int i = 0;
        //循环遍历每一节课

        for(Week week: Week.values()){
            for(Period period: Period.values()) {
                userLists[week.getId()][period.getId()] = new LinkedList<>();
            }
        }
        for(Week week: Week.values()){
            for(Period period: Period.values()){
                timeFrame.setPeriod(period);
                timeFrame.setWeek(week);
                //每一节课-空课的用户集合
                userIdByTimeFrame = displayLeisureMapper.getUserIdByTimeFrame(timeFrame, userId);
                userIdToList.removeAll(userIdByTimeFrame);
                //每一节课-空课的人数
                emptyLessonStudent = userIdToList.size();

                //初始化每一位同学(把userid和需要的时间) 第一次肯定会全部保存进去
                for (Long userid : userIdToList) {
                    if(!remainTime.containsKey(userid)){
                        remainTime.put(userid,needTime);
                    }
                }

                //如果那一节空课的人小于空座位数：则直接安排
                if(emptyLessonStudent <= arrangePeriodRequest.getSeatNum()){
                    //遍历每一节空课的用户Id  小于20
                    for (Long aLong : userIdToList) {
                        //遍历用户，修改时间
                        for (Map.Entry<Long,Integer> entry : remainTime.entrySet()){
                            if(entry.getKey() == aLong){
                                SysUser user = new SysUser();
                                user.setRoleId(arrangePeriodRequest.getRoleId());
                                user.setUserId(aLong);
                                Integer value = entry.getValue();
                                if(value > 0) {
                                    value--;
                                    entry.setValue(value);
                                    userLists[week.getId()][period.getId()].add(user);
                                    break;
                                } else {
                                    System.out.println(aLong + "该用户的空课已经安排满");
                                    break;
                                }
                            }
                        }
                    }
                } else {  //如果那一节空课的人大于空座位数，先把座位数安排满，最后在插入在最后 23 > 20
                    SysUser user = null;
                    //空课人数比空课人数多出来的人数
                    i = emptyLessonStudent - arrangePeriodRequest.getSeatNum();  //3

                    //放满座位数
                    for (int j = 1; j <= arrangePeriodRequest.getSeatNum(); j++) {
                        for (Long bLong : userIdToList){
                            for (Map.Entry<Long,Integer> entry : remainTime.entrySet()){
                                if(entry.getKey() == bLong){
                                    user = new SysUser();
                                    user.setRoleId(arrangePeriodRequest.getRoleId());
                                    user.setUserId(bLong);
                                    Integer value = entry.getValue();
                                    if(value > 0) {
                                        value--;
                                        entry.setValue(value);
                                        userLists[week.getId()][period.getId()].add(user);
                                        break;
                                    } else {
                                        System.out.println(bLong + "该用户的空课已经安排满");
                                        break;
                                    }

                                }
                            }
                        }
                    }
                }

                for (int j = arrangePeriodRequest.getSeatNum(); j < emptyLessonStudent ; j++){
                    if(!remainTime.containsKey(userIdToList.get(j))){
                        againRemainTime.put(userIdByTimeFrame.get(j),needTime);
                    }else {
                        againRemainTime.put(userIdByTimeFrame.get(j),remainTime.get(userIdByTimeFrame.get(j)));
                    }
                }

                for (int j = 1; j < userLists.length; j++) {
                    for (int k = 1; k < userLists[j].length; k++) {
                        if(userLists[j][k].size() != arrangePeriodRequest.getSeatNum()){
                            for (int n = userLists[j][k].size() ; n <= arrangePeriodRequest.getSeatNum();n++){
                                for (Map.Entry<Long,Integer> entry:againRemainTime.entrySet()){
                                    SysUser user = new SysUser();
                                    user.setRoleId(arrangePeriodRequest.getRoleId());
                                    user.setUserId(entry.getKey());
                                    Integer value = entry.getValue();
                                    if(value > 0) {
                                        value--;
                                        entry.setValue(value);
                                        userLists[j][k].add(user);
                                        break;
                                    } else {
                                        System.out.println(entry.getKey() + "该用户的空课已经安排满");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                userIdByTimeFrame.clear();
            }
        }

        for (Map.Entry<Long,Integer> entry : remainTime.entrySet()){
            if(entry.getValue() != 0){
                System.out.println(entry.getKey());
            }
        }
        return userLists;
    }

    public List<SysUser>[][] arrangePeriodSecond(ArrangePeriodRequest arrangePeriodRequest){
        Integer needTime = arrangePeriodRequest.getNeedTime();
        //返回空课表,二维数组
        List<SysUser>[][] emptySchedule = new LinkedList[8][11];
        //初始化课表，每一节课的空课的用户都为一个LinkedLink集合
        for (Week week : Week.values()){
            for (Period period : Period.values()){
                if(period.getId() >= 9){
                    break;
                }
                emptySchedule[week.getId()][period.getId()] = new LinkedList<SysUser>();
            }
        }
        //查询每节课空课人数，保存到一个二维数组里
        Integer[][] emptyLessonStudent = new Integer[8][11];
        TimeFrame timeFrame = new TimeFrame();
        timeFrame.setWeekNo(arrangePeriodRequest.getWeekNo());
        Set<Long> userid = new HashSet<>();
        List<Long> useridToList = new LinkedList<>();  //记录空课的人
        SysUser sysUser = new SysUser();
        sysUser.setRoleId(arrangePeriodRequest.getRoleId());
        List<SysUser> sysUserList = sysUserMapper.selectAllocatedList(sysUser);  //全部的用户
        for (SysUser user : sysUserList) {
            userid.add(user.getUserId());
            useridToList.add(user.getUserId());
        }

        //记录用户、还剩的时长
        Map<Long,Integer> everyUserRemainTime = new HashMap<Long,Integer>();
        for (Long aLong : userid) {
            everyUserRemainTime.put(aLong,needTime); //全部用户
        }
        //记录大于空座位数的用户、及还剩时长,临时滴，用一次清一次;还有不一定初始化为needTime
        //Map<Long,Integer> tempEveryUserRemainTime = new HashMap<Long,Integer>();
        List<Long> tempEveryUserRemainTime = new LinkedList<>();


        for (Week week : Week.values()){
            for (Period period : Period.values()){
                if(period.getId() >= 9){
                    break;
                }
                timeFrame.setWeek(week);
                timeFrame.setPeriod(period);
                List<Long> userIdByTimeFrame = displayLeisureMapper.getUserIdByTimeFrame(timeFrame, userid);
                useridToList.removeAll(userIdByTimeFrame);
                emptyLessonStudent[week.getId()][period.getId()] = useridToList.size();

                //安排座位: 小于座位数
                if(emptyLessonStudent[week.getId()][period.getId()] <= arrangePeriodRequest.getSeatNum()){
                    for (Long aLong : useridToList) {
                        Integer valueTime = everyUserRemainTime.get(aLong);
                        if(valueTime <= 0){
                            System.out.println(aLong + "本周已满十五个小时");
                            break;
                        }
                        valueTime--;
                        everyUserRemainTime.put(aLong,valueTime);
                        SysUser tempUser = new SysUser();
                        tempUser.setRoleId(arrangePeriodRequest.getRoleId());
                        tempUser.setUserId(aLong);
                        tempUser.setNickName(displayLeisureMapper.getNickNameByUserId(aLong));
                        if(emptySchedule[week.getId()][period.getId()].size() < arrangePeriodRequest.getSeatNum()) {
                            emptySchedule[week.getId()][period.getId()].add(tempUser);
                        }else{
                            int weekId = week.getId();
                            int periodId = period.getId();
                            periodId++;
                            if(periodId == 9){
                                weekId++;
                                periodId = 1;
                            }
                            for (int j = periodId; j < emptySchedule[weekId].length - 2; j++) {
                                if(emptySchedule[weekId][j].size() < arrangePeriodRequest.getSeatNum()){
                                    emptySchedule[weekId][j].add(tempUser);
                                    break;
                                }
                            }
                        }
                    }
                }else {
                    int count = 0; //计数器
                    for (Long aLong : useridToList) {
                        if (count >= arrangePeriodRequest.getSeatNum()) {
                            tempEveryUserRemainTime.add(aLong);
                            continue;
                        }
                        Integer valueTime = everyUserRemainTime.get(aLong);
                        if (valueTime <= 0) {
                            System.out.println(aLong + "本周已满十五个小时");
                            continue;
                        }
                        valueTime--;
                        everyUserRemainTime.put(aLong, valueTime);
                        SysUser tempUser = new SysUser();
                        tempUser.setRoleId(arrangePeriodRequest.getRoleId());
                        tempUser.setUserId(aLong);
                        tempUser.setNickName(displayLeisureMapper.getNickNameByUserId(aLong));
                        emptySchedule[week.getId()][period.getId()].add(tempUser);
                        count++;
                    }

                    if (count > arrangePeriodRequest.getSeatNum()) {
                        //给tempEveryUserRemainTime安排座位
                        int num = 0;
                        //46 20  26 20<23
                        for (int i = 1; i < emptySchedule.length; i++) {
                            for (int j = 1; j < emptyLessonStudent[i].length; j++) {
                                if (emptySchedule[i][j].size() < arrangePeriodRequest.getSeatNum()) {
                                    for (int k = 1; k <= emptyLessonStudent[week.getId()][period.getId()] - arrangePeriodRequest.getSeatNum(); k++) {
                                        SysUser tempUser = new SysUser();
                                        tempUser.setUserId(tempEveryUserRemainTime.get(num));
                                        tempUser.setRoleId(arrangePeriodRequest.getRoleId());
                                        tempUser.setNickName(displayLeisureMapper.getNickNameByUserId(tempEveryUserRemainTime.get(num)));
                                        Integer value = everyUserRemainTime.get(tempEveryUserRemainTime.get(num));
                                        if (value <= 0) {
                                            System.out.println(tempEveryUserRemainTime.get(num) + "本周已满十五个小时");
                                            num++;
                                            continue;
                                        }
                                        value--;
                                        everyUserRemainTime.put(tempEveryUserRemainTime.get(num), value);
                                        emptySchedule[i][j].add(tempUser);
                                        num++;
                                    }
                                }
                                if (num == tempEveryUserRemainTime.size()) {
                                    break;
                                }
                            }
                            if (num == tempEveryUserRemainTime.size()) {
                                break;
                            }
                        }
                        //清空tempEveryUserRemainTime,用于下一次
                        tempEveryUserRemainTime.clear();
                    }
                }
                useridToList.clear();
                for (SysUser user : sysUserList) {
                    useridToList.add(user.getUserId());
                }


            }
        }

        return emptySchedule;
    }
}
