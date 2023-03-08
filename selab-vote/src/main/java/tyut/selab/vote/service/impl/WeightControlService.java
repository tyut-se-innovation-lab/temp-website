package tyut.selab.vote.service.impl;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.Weight;
import tyut.selab.vote.mapper.WeightMapper;
import tyut.selab.vote.service.IWeightControlService;

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
    private Long getLastUseWeightId(){
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
