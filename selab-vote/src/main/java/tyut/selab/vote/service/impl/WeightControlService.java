package tyut.selab.vote.service.impl;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.Weight;
import tyut.selab.vote.mapper.WeightMapper;
import tyut.selab.vote.service.IWeightControlService;
import tyut.selab.vote.tools.AnonymousControl;

import java.util.*;

/**
 * @author Big_bai on 2022/11/25
 */
@Service
public class WeightControlService implements IWeightControlService {

    @Autowired
    private WeightMapper weightMapper;

    @Autowired
    private ISysRoleService iSysRoleService;

    @Override
    public void setVoteWeight(Map<String, Integer> roleWeightMap) {
        Map<Long,String> roleMap = getRoleIdMap();
        List<Weight> weightToDB = new ArrayList<>();
        Long maxWeightId = weightMapper.getMaxWeightId();
        roleWeightMap.keySet().forEach((x)->{
            roleMap.keySet().forEach((y)->{
                if(roleMap.get(y).equals(x)){
                    Weight w = new Weight();
                    w.setRoleId(y);
                    w.setWeight(roleWeightMap.get(x));
                    w.setWeightId(maxWeightId+1);
                    weightToDB.add(w);
                }
            });
        });
        weightMapper.writeWeight(weightToDB);
        weightMapper.modifyLastUse(maxWeightId+1);
    }

    @Override
    public Map<String,Integer> getNowVoteWeight() {
        Map<String,Integer> weightMap = new HashMap<>();
        Map<Long,String> roleIdMap = getRoleIdMap();
        getWeightList(getLastUseWeightId()).forEach((x)->{
            weightMap.put(roleIdMap.get(x.getRoleId()), x.getWeight());
        });
        return weightMap;
    }

    @Override
    public int getWeightByUserId(String id) {
        Long roleId = iSysRoleService.selectRoleById(Long.getLong(AnonymousControl.decrypt(id))).getRoleId();
        List<Weight> weights =  getWeightList(getLastUseWeightId());
        for (Weight w:weights) {
            if(w.getRoleId()==roleId){
                return w.getWeight();
            }
        }
        return 0;
    }

    private Map<Long,String> getRoleIdMap(){
        //ruo yi有用户登录检测，需要跑起来看看
        List<SysRole> roles = iSysRoleService.selectRoleAll();
        Map<Long,String> roleIdMap = new HashMap<>();
        roles.forEach((x)->{
            if(x.getStatus().equals("0")){
                roleIdMap.put(x.getRoleId(),x.getRoleName());
            }
        });
        return roleIdMap;
    }
    private List<Weight> getWeightList(long weightId){
         return weightMapper.getWeightList(weightId);
    }

    @Override
    public Long getLastUseWeightId(){
        Long last = weightMapper.getLastUseWeightId();
        if(last==null){
            List<Weight> weights = new ArrayList<>();
            getRoleIdMap().keySet()
                    .forEach((x)->{
                        Weight w = new Weight();
                        w.setWeight(1);
                        w.setWeightId(1);
                        w.setRoleId(x);
                        weights.add(w);
                    });
            weightMapper.initLastUse();
            weightMapper.writeWeight(weights);
            return getLastUseWeightId();
        }
        return last;
    }

}
