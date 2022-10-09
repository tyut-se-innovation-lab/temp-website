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


    public List<SysUser>[][] ArrangePeriod(ArrangePeriodRequest arrangePeriodRequest){
        //记录每个人一个星期还剩多少个小时
        Integer needTime = arrangePeriodRequest.getNeedTime();
        //记录该节空课的用户和剩余的时长
        Map<Long,Integer> remainTime = new HashMap<Long,Integer>();
        //保存每节课，空课的人
        List<SysUser>[][] userLists = new LinkedList[7][10];
        //记录该节空课人数大于座位数的用户和剩余时长
        Map<Long,Integer> againRemainTime = new HashMap<Long,Integer>();
        //查看空课人数-把角色之内全部成员全部查出来
        SysUser sysUser = new SysUser();
        //查看该角色下所有的用户
        sysUser.setRoleId(arrangePeriodRequest.getRoleId());
        List<SysUser> sysUserList = sysUserMapper.selectUserList(sysUser);
        List<Long> userId = new ArrayList<>();
        for (SysUser user : sysUserList) {
            userId.add(user.getUserId());
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
            for(Period period: Period.values()){
                timeFrame.setPeriod(period);
                timeFrame.setWeek(week);
                //每一节课-空课的用户集合
                userIdByTimeFrame = displayLeisureMapper.getUserIdByTimeFrame(timeFrame, userId);
                //每一节课-空课的人数
                emptyLessonStudent = userIdByTimeFrame.size();

                //初始化每一位同学(把userid和需要的时间) 第一次肯定会全部保存进去
                for (Long userid : userIdByTimeFrame) {
                    if(!remainTime.containsKey(userid)){
                        remainTime.put(userid,needTime);
                    }
                }

                //如果那一节空课的人小于空座位数：则直接安排
                if(emptyLessonStudent <= arrangePeriodRequest.getSeatNum()){
                    //遍历每一节空课的用户Id  小于20
                    for (Long aLong : userIdByTimeFrame) {
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
                        for (Long bLong : userIdByTimeFrame){
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
                    if(!remainTime.containsKey(userIdByTimeFrame.get(j))){
                        againRemainTime.put(userIdByTimeFrame.get(j),needTime);
                    }else {
                        againRemainTime.put(userIdByTimeFrame.get(j),remainTime.get(userIdByTimeFrame.get(j)));
                    }
                }

                for (int j = 1; j <= userLists.length; j++) {
                    for (int k = 1; k <= userLists[j].length; k++) {
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
}
